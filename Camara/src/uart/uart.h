/*-------------------------------------------------------------------*/
/*  Copyright(C) 2014-2015 by OMRON Corporation                      */
/*  All Rights Reserved.                                             */
/*                                                                   */
/*   This source code is the Confidential and Proprietary Property   */
/*   of OMRON Corporation.  Any unauthorized use, reproduction or    */
/*   transfer of this software is strictly prohibited.               */
/*                                                                   */
/*-------------------------------------------------------------------*/

#ifndef UART_H__
#define UART_H__

/* Struct for the serial port */
typedef struct {
    int com_num;                /* COM number */
    unsigned long BaudRate;     /* Baud rate 9600-921600 */
} S_STAT;

#ifdef  __cplusplus
extern "C" {
#endif

void com_close(void);
int com_init(S_STAT *stat);
int com_send(unsigned char *buf, int len);
int com_recv(int inTimeOutTimer, unsigned char *buf, int len);

#ifdef  __cplusplus
}
#endif

#endif  /* UART_H__ */
