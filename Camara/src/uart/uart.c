/*-------------------------------------------------------------------*/
/*  Copyright(C) 2014-2015 by OMRON Corporation                      */
/*  All Rights Reserved.                                             */
/*                                                                   */
/*   This source code is the Confidential and Proprietary Property   */
/*   of OMRON Corporation.  Any unauthorized use, reproduction or    */
/*   transfer of this software is strictly prohibited.               */
/*                                                                   */
/*-------------------------------------------------------------------*/

#include <windows.h>

#include <stdio.h>
#include "uart.h"

static HANDLE hCom = INVALID_HANDLE_VALUE;

/* UART */
void com_close(void)
{
    if ( hCom != INVALID_HANDLE_VALUE ) {
        CloseHandle(hCom);
        hCom = INVALID_HANDLE_VALUE;
    }
}

int com_init(S_STAT *stat)
{
    DCB dcb;
    BOOL fSuccess;
    char device[16];

    com_close();

    sprintf_s(device, 16, "\\\\.\\COM%d", stat->com_num);
    hCom = CreateFile(device,
                        GENERIC_READ | GENERIC_WRITE,
                        0,
                        NULL,
                        OPEN_EXISTING,
                        0,
                        NULL);

    if ( hCom == INVALID_HANDLE_VALUE ) {
        return(FALSE);
    }

    fSuccess = GetCommState(hCom,&dcb);
    if ( !fSuccess ) {
        com_close();
        return(FALSE);
    }

    dcb.BaudRate = stat->BaudRate;
    dcb.ByteSize = 8;
    dcb.Parity   = NOPARITY;
    dcb.StopBits = ONESTOPBIT;
    dcb.fDsrSensitivity = FALSE;
    dcb.fOutxCtsFlow = 0;
    dcb.fTXContinueOnXoff = 0;
    dcb.fRtsControl = RTS_CONTROL_DISABLE;
    dcb.fDtrControl = DTR_CONTROL_DISABLE;

    fSuccess = SetCommState(hCom,&dcb);
    if ( !fSuccess ) {
        com_close();
        return(FALSE);
    }

    fSuccess = SetupComm(hCom, 10240, 10240);
    if ( !fSuccess ) {
        com_close();
        return(FALSE);
    }

    return TRUE;
}

int com_send(unsigned char *buf, int len)
{
    DWORD dwSize = 0;
    if ( hCom != INVALID_HANDLE_VALUE ) {
        WriteFile(hCom,buf,len,&dwSize,NULL);
    }
    return (int)dwSize;
}

int com_recv(int inTimeOutTimer, unsigned char *buf, int len)
{
    DWORD ierr;
    COMSTAT stat;
    DWORD dwSize = 0;

    int ret = 0;
    int totalSize = 0;

    double finishTime = 0.0;

    LARGE_INTEGER timeFreq = {0, 0};
    LARGE_INTEGER stopTime = {0, 0};
    LARGE_INTEGER startTime = {0, 0};

    QueryPerformanceFrequency(&timeFreq);

    if ( hCom != INVALID_HANDLE_VALUE ) {
        QueryPerformanceCounter(&startTime);
        do{
            ClearCommError(hCom,&ierr,&stat);
            if ( stat.cbInQue >= 1 ) {
                ret = len - totalSize;
                if ( ret > (int)stat.cbInQue ) ret = stat.cbInQue;
                ReadFile(hCom,&buf[totalSize],ret,&dwSize,NULL);
                totalSize += (int)dwSize;
            }
            if ( totalSize >= len ) break;

            QueryPerformanceCounter(&stopTime);
            finishTime = (double)(stopTime.QuadPart - startTime.QuadPart) * 1000 / (double)timeFreq.QuadPart;
            if ( finishTime >= (double)inTimeOutTimer ) break;
        }while(1);
    }
    return totalSize;
}
