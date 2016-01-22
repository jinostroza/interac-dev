/*-------------------------------------------------------------------*/
/*  Copyright(C) 2014-2015 by OMRON Corporation                      */
/*  All Rights Reserved.                                             */
/*                                                                   */
/*   This source code is the Confidential and Proprietary Property   */
/*   of OMRON Corporation.  Any unauthorized use, reproduction or    */
/*   transfer of this software is strictly prohibited.               */
/*                                                                   */
/*-------------------------------------------------------------------*/
/* 
    External UART-function definition
*/

#ifndef HVCExtraUartFunc_H__
#define HVCExtraUartFunc_H__

#ifdef  __cplusplus
extern "C" {
#endif

/*----------------------------------------------------------------------------*/
/* UART send signal                                                           */
/* param    : int   inDataSize  send signal data                              */
/*          : UINT8 *inData     data length                                   */
/* return   : int               send signal complete data number              */
/*----------------------------------------------------------------------------*/
extern int UART_SendData(int inDataSize, UINT8 *inData);

/*----------------------------------------------------------------------------*/
/* UART receive signal                                                        */
/* param    : int   inTimeOutTime   timeout time (ms)                         */
/*          : int   *inDataSize     receive signal data size                  */
/*          : UINT8 *outResult      receive signal data                       */
/* return   : int                   receive signal complete data number       */
/*----------------------------------------------------------------------------*/
extern int UART_ReceiveData(int inTimeOutTime, int inDataSize, UINT8 *outResult);

#ifdef  __cplusplus
}
#endif

#endif  /* HVCExtraUartFunc_H__ */
