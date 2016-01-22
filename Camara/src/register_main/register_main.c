/*-------------------------------------------------------------------*/
/*  Copyright(C) 2014-2015 by OMRON Corporation                      */
/*  All Rights Reserved.                                             */
/*                                                                   */
/*   This source code is the Confidential and Proprietary Property   */
/*   of OMRON Corporation.  Any unauthorized use, reproduction or    */
/*   transfer of this software is strictly prohibited.               */
/*                                                                   */
/*-------------------------------------------------------------------*/

#include <conio.h>
#include <ctype.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <malloc.h>
#include "uart.h"
#include "HVCApi.h"
#include "HVCDef.h"
#include "HVCExtraUartFunc.h"

#define LOGBUFFERSIZE   8192

#define UART_COM_NUMBER                     49            /* COM port number connected to HVC */
#define UART_COM_BAUDRATE               921600            /* Baud rate for HVC UART (bps) */

#define UART_SETTING_TIMEOUT              1000            /* HVC setting command signal timeout period */ 
#define UART_DETECT_EXECUTE_TIMEOUT       6000            /* HVC Face Detection command signal timeout period */
#define UART_REGIST_EXECUTE_TIMEOUT       7000            /* HVC registration command signal timeout period */

#define SENSOR_ROLL_ANGLE_DEFAULT            0            /* Camera angle setting (0Åã) */

#define BODY_THRESHOLD_DEFAULT             500            /* Threshold for Human Body Detection */
#define FACE_THRESHOLD_DEFAULT             500            /* Threshold for Face Detection */
#define HAND_THRESHOLD_DEFAULT             500            /* Threshold for Hand Detection */
#define REC_THRESHOLD_DEFAULT              500            /* Threshold for Face Recognition */

#define BODY_SIZE_RANGE_MIN_DEFAULT         30            /* Human Body Detection minimum detection size */
#define BODY_SIZE_RANGE_MAX_DEFAULT       8192            /* Human Body Detection maximum detection size */
#define HAND_SIZE_RANGE_MIN_DEFAULT         40            /* Hand Detection minimum detection size */
#define HAND_SIZE_RANGE_MAX_DEFAULT       8192            /* Hand Detection maximum detection size */
#define FACE_SIZE_RANGE_MIN_DEFAULT         64            /* Face Detection minimum detection size */
#define FACE_SIZE_RANGE_MAX_DEFAULT       8192            /* Face Detection maximum detection size */

#define FACE_POSE_DEFAULT                    0            /* Face Detection facial pose (frontal face)*/
#define FACE_ANGLE_DEFAULT                   0            /* Face Detection roll angle (Å}15Åã)*/

#define REGISTER_USERID_NUMBER               0            /* Registered user ID number */

void SaveBitmapFile(int nWidth, int nHeight, UINT8 *unImageBuffer, const char *szFileName);

/*----------------------------------------------------------------------------*/
/* UART send signal                                                           */
/* param    : int   inDataSize  send signal data                              */
/*          : UINT8 *inData     data length                                   */
/* return   : int               send signal complete data number              */
/*----------------------------------------------------------------------------*/
int UART_SendData(int inDataSize, UINT8 *inData)
{
    /* UART send signal */
    int ret = com_send(inData, inDataSize);
    return ret;
}

/*----------------------------------------------------------------------------*/
/* UART receive signal                                                        */
/* param    : int   inTimeOutTime   timeout time (ms)                         */
/*          : int   *inDataSize     receive signal data size                  */
/*          : UINT8 *outResult      receive signal data                       */
/* return   : int                   receive signal complete data number       */
/*----------------------------------------------------------------------------*/
int UART_ReceiveData(int inTimeOutTime, int inDataSize, UINT8 *outResult)
{
    /* UART receive signal */
    int ret = com_recv(inTimeOutTime, outResult, inDataSize);
    return ret;
}

/* Print Log Message */
static void PrintLog(char *pStr)
{
    puts(pStr);
}


/* HVC Execute Processing  */
int main(void)
{
    INT32 nRet = 0;  /* Return code */

    UINT8 outStatus;
    HVC_VERSION outVersion;
    HVC_IMAGE *poutImage = NULL;
    HVC_RESULT *poutHVCResult = NULL;

    INT32 inAngleNo;
    HVC_THRESHOLD inThreshold;
    HVC_SIZERANGE inSizeRange;
    INT32 inPose;
    INT32 inAngle;
    INT32 inTimeOutTime;
    INT32 inExec;
    INT32 inImage;
    INT32 inUserID;
    INT32 inDataID;
    INT32 outDataNo;

    int i;
    int ch = 0;
    int revision;
    char *pStr;                     /* String Buffer for logging output */

    S_STAT serial_stat;             /* Serial port set value */

    serial_stat.com_num = UART_COM_NUMBER;
    serial_stat.BaudRate = UART_COM_BAUDRATE;
    if ( com_init(&serial_stat) == 0 ) {
        PrintLog("Failed to open COM port.\n");
        return (-1);
    }

    /*****************************/
    /* Logging Buffer allocation */
    /*****************************/
    pStr = (char *)malloc(LOGBUFFERSIZE);
    if ( pStr == NULL ) {
        PrintLog("Failed to allocate Logging Buffer.\n");
        return (-1);
    }
    memset(pStr, 0, LOGBUFFERSIZE);

    do {
        /*********************************/
        /* Image Structure allocation    */
        /*********************************/
        poutImage = (HVC_IMAGE *)malloc(sizeof(HVC_IMAGE));
        if ( poutImage == NULL ) { /* Error processing */
            sprintf_s(&pStr[strlen(pStr)], LOGBUFFERSIZE-strlen(pStr), "\nMemory Allocation Error : %08x\n", sizeof(HVC_IMAGE));
            break;
        }
        /*********************************/
        /* Result Structure allocation   */
        /*********************************/
        poutHVCResult = (HVC_RESULT *)malloc(sizeof(HVC_RESULT));
        if ( poutHVCResult == NULL ) { /* Error processing */
            sprintf_s(&pStr[strlen(pStr)], LOGBUFFERSIZE-strlen(pStr), "\nMemory Allocation Error : %08x\n", sizeof(HVC_RESULT));
            break;
        }

        /*********************************/
        /* Get Model and Version         */
        /*********************************/
        nRet = HVC_GetVersion(UART_SETTING_TIMEOUT, &outVersion, &outStatus);
        if ( nRet != 0 ) {
            sprintf_s(&pStr[strlen(pStr)], LOGBUFFERSIZE-strlen(pStr), "\nHVCApi(HVC_GetVersion) Error : %d\n", nRet);
            break;
        }
        if ( outStatus != 0 ) {
            sprintf_s(&pStr[strlen(pStr)], LOGBUFFERSIZE-strlen(pStr), "\nHVC_GetVersion Response Error : 0x%02X\n", outStatus);
            break;
        }
        sprintf_s(&pStr[strlen(pStr)], LOGBUFFERSIZE-strlen(pStr), "\nHVC_GetVersion : ");
        for(i = 0; i < 12; i++){
            sprintf_s(&pStr[strlen(pStr)], LOGBUFFERSIZE-strlen(pStr), "%c", outVersion.string[i] );
        }
        revision = outVersion.revision[0] + (outVersion.revision[0]<<8) + (outVersion.revision[0]<<16) + (outVersion.revision[0]<<24);
        sprintf_s(&pStr[strlen(pStr)], LOGBUFFERSIZE-strlen(pStr), "%d.%d.%d.%d", outVersion.major, outVersion.minor, outVersion.relese, revision);

        /*********************************/
        /* Set Camera Angle              */
        /*********************************/
        inAngleNo = SENSOR_ROLL_ANGLE_DEFAULT;
        nRet = HVC_SetCameraAngle(UART_SETTING_TIMEOUT, inAngleNo, &outStatus);
        if ( nRet != 0 ) {
            sprintf_s(&pStr[strlen(pStr)], LOGBUFFERSIZE-strlen(pStr), "\nHVCApi(HVC_SetCameraAngle) Error : %d\n", nRet);
            break;
        }
        if ( outStatus != 0 ) {
            sprintf_s(&pStr[strlen(pStr)], LOGBUFFERSIZE-strlen(pStr), "\nHVC_SetCameraAngle Response Error : 0x%02X\n", outStatus);
            break;
        }
        inAngleNo = 0xff;
        nRet = HVC_GetCameraAngle(UART_SETTING_TIMEOUT, &inAngleNo, &outStatus);
        if ( nRet != 0 ) {
            sprintf_s(&pStr[strlen(pStr)], LOGBUFFERSIZE-strlen(pStr), "\nHVCApi(HVC_GetCameraAngle) Error : %d\n", nRet);
            break;
        }
        if ( outStatus != 0 ) {
            sprintf_s(&pStr[strlen(pStr)], LOGBUFFERSIZE-strlen(pStr), "\nHVC_GetCameraAngle Response Error : 0x%02X\n", outStatus);
            break;
        }
        sprintf_s(&pStr[strlen(pStr)], LOGBUFFERSIZE-strlen(pStr), "\nHVC_GetCameraAngle : 0x%02x", inAngleNo);
        /*********************************/
        /* Set Threshold Values          */
        /*********************************/
        inThreshold.bdThreshold = BODY_THRESHOLD_DEFAULT;
        inThreshold.hdThreshold = HAND_THRESHOLD_DEFAULT;
        inThreshold.dtThreshold = FACE_THRESHOLD_DEFAULT;
        inThreshold.rsThreshold = REC_THRESHOLD_DEFAULT;
        nRet = HVC_SetThreshold(UART_SETTING_TIMEOUT, &inThreshold, &outStatus);
        if ( nRet != 0 ) {
            sprintf_s(&pStr[strlen(pStr)], LOGBUFFERSIZE-strlen(pStr), "\nHVCApi(HVC_SetThreshold) Error : %d\n", nRet);
            break;
        }
        if ( outStatus != 0 ) {
            sprintf_s(&pStr[strlen(pStr)], LOGBUFFERSIZE-strlen(pStr), "\nHVC_SetThreshold Response Error : 0x%02X\n", outStatus);
            break;
        }
        inThreshold.bdThreshold = 0;
        inThreshold.hdThreshold = 0;
        inThreshold.dtThreshold = 0;
        inThreshold.rsThreshold = 0;
        nRet = HVC_GetThreshold(UART_SETTING_TIMEOUT, &inThreshold, &outStatus);
        if ( nRet != 0 ) {
            sprintf_s(&pStr[strlen(pStr)], LOGBUFFERSIZE-strlen(pStr), "\nHVCApi(HVC_GetThreshold) Error : %d\n", nRet);
            break;
        }
        if ( outStatus != 0 ) {
            sprintf_s(&pStr[strlen(pStr)], LOGBUFFERSIZE-strlen(pStr), "\nHVC_GetThreshold Response Error : 0x%02X\n", outStatus);
            break;
        }
        sprintf_s(&pStr[strlen(pStr)], LOGBUFFERSIZE-strlen(pStr), "\nHVC_GetThreshold : Body=%4d Hand=%4d face=%4d Recognition=%4d",
                 inThreshold.bdThreshold, inThreshold.hdThreshold, inThreshold.dtThreshold, inThreshold.rsThreshold);
        /*********************************/
        /* Set Detection Size            */
        /*********************************/
        inSizeRange.bdMinSize = BODY_SIZE_RANGE_MIN_DEFAULT;
        inSizeRange.bdMaxSize = BODY_SIZE_RANGE_MAX_DEFAULT;
        inSizeRange.hdMinSize = HAND_SIZE_RANGE_MIN_DEFAULT;
        inSizeRange.hdMaxSize = HAND_SIZE_RANGE_MAX_DEFAULT;
        inSizeRange.dtMinSize = FACE_SIZE_RANGE_MIN_DEFAULT;
        inSizeRange.dtMaxSize = FACE_SIZE_RANGE_MAX_DEFAULT;
        nRet = HVC_SetSizeRange(UART_SETTING_TIMEOUT, &inSizeRange, &outStatus);
        if ( nRet != 0 ) {
            sprintf_s(&pStr[strlen(pStr)], LOGBUFFERSIZE-strlen(pStr), "\nHVCApi(HVC_SetSizeRange) Error : %d\n", nRet);
            break;
        }
        if ( outStatus != 0 ) {
            sprintf_s(&pStr[strlen(pStr)], LOGBUFFERSIZE-strlen(pStr), "\nHVC_SetSizeRange Response Error : 0x%02X\n", outStatus);
            break;
        }
        inSizeRange.bdMinSize = 0;
        inSizeRange.bdMaxSize = 0;
        inSizeRange.hdMinSize = 0;
        inSizeRange.hdMaxSize = 0;
        inSizeRange.dtMinSize = 0;
        inSizeRange.dtMaxSize = 0;
        nRet = HVC_GetSizeRange(UART_SETTING_TIMEOUT, &inSizeRange, &outStatus);
        if ( nRet != 0 ) {
            sprintf_s(&pStr[strlen(pStr)], LOGBUFFERSIZE-strlen(pStr), "\nHVCApi(HVC_GetSizeRange) Error : %d\n", nRet);
            break;
        }
        if ( outStatus != 0 ) {
            sprintf_s(&pStr[strlen(pStr)], LOGBUFFERSIZE-strlen(pStr), "\nHVC_GetSizeRange Response Error : 0x%02X\n", outStatus);
            break;
        }
        sprintf_s(&pStr[strlen(pStr)], LOGBUFFERSIZE-strlen(pStr), "\nHVC_GetSizeRange : Body=(%4d,%4d) Hand=(%4d,%4d) Face=(%4d,%4d)",
                                                            inSizeRange.bdMinSize, inSizeRange.bdMaxSize,
                                                            inSizeRange.hdMinSize, inSizeRange.hdMaxSize,
                                                            inSizeRange.dtMinSize, inSizeRange.dtMaxSize);
        /*********************************/
        /* Set Face Angle                */
        /*********************************/
        inPose = FACE_POSE_DEFAULT;
        inAngle = FACE_ANGLE_DEFAULT;
        nRet = HVC_SetFaceDetectionAngle(UART_SETTING_TIMEOUT, inPose, inAngle, &outStatus);
        if ( nRet != 0 ) {
            sprintf_s(&pStr[strlen(pStr)], LOGBUFFERSIZE-strlen(pStr), "\nHVCApi(HVC_SetFaceDetectionAngle) Error : %d\n", nRet);
            break;
        }
        if ( outStatus != 0 ) {
            sprintf_s(&pStr[strlen(pStr)], LOGBUFFERSIZE-strlen(pStr), "\nHVC_SetFaceDetectionAngle Response Error : 0x%02X\n", outStatus);
            break;
        }
        inPose = 0xff;
        inAngle = 0xff;
        nRet = HVC_GetFaceDetectionAngle(UART_SETTING_TIMEOUT, &inPose, &inAngle, &outStatus);
        if ( nRet != 0 ) {
            sprintf_s(&pStr[strlen(pStr)], LOGBUFFERSIZE-strlen(pStr), "\nHVCApi(HVC_GetFaceDetectionAngle) Error : %d\n", nRet);
            break;
        }
        if ( outStatus != 0 ) {
            sprintf_s(&pStr[strlen(pStr)], LOGBUFFERSIZE-strlen(pStr), "\nHVC_GetFaceDetectionAngle Response Error : 0x%02X\n", outStatus);
            break;
        }
        sprintf_s(&pStr[strlen(pStr)], LOGBUFFERSIZE-strlen(pStr), "\nHVC_GetFaceDetectionAngle : Pose = 0x%02x Angle = 0x%02x", inPose, inAngle);

        do {
            sprintf_s(&pStr[strlen(pStr)], LOGBUFFERSIZE-strlen(pStr), "\nPress the Enter Key to register a face and theSpace Key to end: ");

            /******************/
            /* Log Output     */
            /******************/
            system("cls");
            PrintLog(pStr);

            memset(pStr, 0, LOGBUFFERSIZE);

            inUserID = REGISTER_USERID_NUMBER;
            /*********************************/
            /* Get Registration Info         */
            /*********************************/
            nRet = HVC_GetUserData(UART_SETTING_TIMEOUT, inUserID, &outDataNo, &outStatus);
            if ( nRet != 0 ) {
                sprintf_s(&pStr[strlen(pStr)], LOGBUFFERSIZE-strlen(pStr), "\nHVCApi(HVC_GetUserData) Error : %d\n", nRet);
                break;
            }
            if ( outStatus != 0 ) {
                sprintf_s(&pStr[strlen(pStr)], LOGBUFFERSIZE-strlen(pStr), "\nHVC_GetUserData Error : 0x%02X\n", outStatus);
                break;
            }
            sprintf_s(&pStr[strlen(pStr)], LOGBUFFERSIZE-strlen(pStr), "\nHVC_GetUserData : 0x%04x", outDataNo);

            inDataID = 0;
            for ( i=0x01; i<0x400; i<<=1 ) {
                if ( (outDataNo & i) == 0 ) break;
                inDataID++;
            }
            if ( inDataID >= 10 ) {
                sprintf_s(&pStr[strlen(pStr)], LOGBUFFERSIZE-strlen(pStr), "\nMaximum registration number reached.");
                break;
            }

            ch = 0;
            if ( _kbhit() ) {
                ch = _getch();
                ch = toupper( ch );
            }
            
            if ( ch == '\r' ) {
                /*********************************/
                /* Execute Registration          */
                /*********************************/
                inTimeOutTime = UART_REGIST_EXECUTE_TIMEOUT;
                nRet = HVC_Registration(inTimeOutTime, inUserID, inDataID, poutImage, &outStatus);
                if ( nRet != 0 ) {
                    sprintf_s(&pStr[strlen(pStr)], LOGBUFFERSIZE-strlen(pStr), "\nHVCApi(HVC_Registration) Error : %d\n", nRet);
                    break;
                }
                if ( outStatus != 0 ) {
                    sprintf_s(&pStr[strlen(pStr)], LOGBUFFERSIZE-strlen(pStr), "\nHVC_Registration Error : 0x%02X\n", outStatus);
                    break;
                }
                SaveBitmapFile(poutImage->width, poutImage->height, poutImage->image, "RegisterImage.bmp");
                sprintf_s(&pStr[strlen(pStr)], LOGBUFFERSIZE-strlen(pStr), "\nRegistration complete.");
            } else {
                /*********************************/
                /* Execute Detection             */
                /*********************************/
                inTimeOutTime = UART_DETECT_EXECUTE_TIMEOUT;
                inExec = HVC_ACTIV_FACE_DETECTION;
                inImage = HVC_EXECUTE_IMAGE_QVGA_HALF; /* HVC_EXECUTE_IMAGE_NONE; */
                nRet = HVC_Execute(inTimeOutTime, inExec, inImage, poutHVCResult, &outStatus);
                if ( nRet != 0 ) {
                    sprintf_s(&pStr[strlen(pStr)], LOGBUFFERSIZE-strlen(pStr), "\nHVCApi(HVC_Execute) Error : %d\n", nRet);
                    break;
                }
                if ( outStatus != 0 ) {
                    sprintf_s(&pStr[strlen(pStr)], LOGBUFFERSIZE-strlen(pStr), "\nHVC_Execute Response Error : 0x%02X\n", outStatus);
                    break;
                }

                if ( inImage == HVC_EXECUTE_IMAGE_QVGA_HALF ) {
                    SaveBitmapFile(poutHVCResult->image.width, poutHVCResult->image.height, poutHVCResult->image.image, "SampleImage.bmp");
                }
                /* Face Detection result string */
                if(poutHVCResult->executedFunc & HVC_ACTIV_FACE_DETECTION){
                    sprintf_s(&pStr[strlen(pStr)], LOGBUFFERSIZE-strlen(pStr), "\n Face result count:%d", poutHVCResult->fdResult.num);
                    for(i = 0; i < poutHVCResult->fdResult.num; i++){
                        /* Detection */
                        sprintf_s(&pStr[strlen(pStr)], LOGBUFFERSIZE-strlen(pStr), "\n      Index:%d \tX:%d Y:%d Size:%d Confidence:%d", i,
                                    poutHVCResult->fdResult.fcResult[i].dtResult.posX, poutHVCResult->fdResult.fcResult[i].dtResult.posY,
                                    poutHVCResult->fdResult.fcResult[i].dtResult.size, poutHVCResult->fdResult.fcResult[i].dtResult.confidence);
                    }
                }
            }
        } while( ch != ' ' );
    } while(0);
    /******************/
    /* Log Output     */
    /******************/
    PrintLog(pStr);

    /********************************/
    /* Free image area             */
    /********************************/
    if( poutImage != NULL ){
        free(poutImage);
    }
    /********************************/
    /* Free result area             */
    /********************************/
    if( poutHVCResult != NULL ){
        free(poutHVCResult);
    }

    com_close();

    /* Free Logging Buffer */
    if ( pStr != NULL ) {
        free(pStr);
    }
    return (0);
}
