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
    HVC Sample API
*/

#ifndef HVCApi_H__
#define HVCApi_H__

#ifndef UINT8
typedef     unsigned char       UINT8;      /*  8 bit Unsigned Integer  */
#endif /* UINT8 */
#ifndef INT32
typedef     int                 INT32;      /* 32 bit Signed   Integer  */
#endif /* INT32 */
#ifndef     NULL
    #define     NULL                0
#endif

#include "HVCDef.h"

#ifdef  __cplusplus
extern "C" {
#endif

/* HVC_GetVersion                                                             */
/* param    : INT32         inTimeOutTime   timeout time (ms)                 */
/*          : HVC_VERSION   *outVersion     version data                      */
/*          : UINT8         *outStatus      response code                     */
INT32 HVC_GetVersion(INT32 inTimeOutTime, HVC_VERSION *outVersion, UINT8 *outStatus);

/* HVC_SetCameraAngle                                                         */
/* param    : INT32         inTimeOutTime   timeout time (ms)                 */
/*          : INT32         inAngleNo       camera angle number               */
/*          : UINT8         *outStatus      response code                     */
INT32 HVC_SetCameraAngle(INT32 inTimeOutTime, INT32 inAngleNo, UINT8 *outStatus);

/* HVC_GetCameraAngle                                                         */
/* param    : INT32         inTimeOutTime   timeout time (ms)                 */
/*          : INT32         *outAngleNo     camera angle number               */
/*          : UINT8         *outStatus      response code                     */
INT32 HVC_GetCameraAngle(INT32 inTimeOutTime, INT32 *outAngleNo, UINT8 *outStatus);

/* HVC_Execute                                                                */
/* param    : INT32         inTimeOutTime   timeout time (ms)                 */
/*          : INT32         inExec          executable function               */
/*          : INT32         inImage         image output number               */
/*          : HVC_RESULT    *outHVCResult   result data                       */
/*          : UINT8         *outStatus      response code                     */
INT32 HVC_Execute(INT32 inTimeOutTime, INT32 inExec, INT32 inImage, HVC_RESULT *outHVCResult, UINT8 *outStatus);

/* HVC_SetThreshold                                                           */
/* param    : INT32         inTimeOutTime   timeout time (ms)                 */
/*          : HVC_THRESHOLD *inThreshold    threshold values                  */
/*          : UINT8         *outStatus      response code                     */
INT32 HVC_SetThreshold(INT32 inTimeOutTime, HVC_THRESHOLD *inThreshold, UINT8 *outStatus);

/* HVC_GetThreshold                                                           */
/* param    : INT32         inTimeOutTime   timeout time (ms)                 */
/*          : HVC_THRESHOLD *outThreshold   threshold values                  */
/*          : UINT8         *outStatus      response code                     */
INT32 HVC_GetThreshold(INT32 inTimeOutTime, HVC_THRESHOLD *outThreshold, UINT8 *outStatus);

/* HVC_SetSizeRange                                                           */
/* param    : INT32         inTimeOutTime   timeout time (ms)                 */
/*          : HVC_SIZERANGE *inSizeRange    detection sizes                   */
/*          : UINT8         *outStatus      response code                     */
INT32 HVC_SetSizeRange(INT32 inTimeOutTime, HVC_SIZERANGE *inSizeRange, UINT8 *outStatus);

/* HVC_GetSizeRange                                                           */
/* param    : INT32         inTimeOutTime   timeout time (ms)                 */
/*          : HVC_SIZERANGE *outSizeRange   detection sizes                   */
/*          : UINT8         *outStatus      response code                     */
INT32 HVC_GetSizeRange(INT32 inTimeOutTime, HVC_SIZERANGE *outSizeRange, UINT8 *outStatus);

/* HVC_SetFaceDetectionAngle                                                  */
/* param    : INT32         inTimeOutTime   timeout time (ms)                 */
/*          : INT32         inPose          Yaw angle range                   */
/*          : INT32         inAngle         Roll angle range                  */
/*          : UINT8         *outStatus      response code                     */
INT32 HVC_SetFaceDetectionAngle(INT32 inTimeOutTime, INT32 inPose, INT32 inAngle, UINT8 *outStatus);

/* HVC_GetFaceDetectionAngle                                                  */
/* param    : INT32         inTimeOutTime   timeout time (ms)                 */
/*          : INT32         *outPose        Yaw angle range                   */
/*          : INT32         *outAngle       Roll angle range                  */
/*          : UINT8         *outStatus      response code                     */
INT32 HVC_GetFaceDetectionAngle(INT32 inTimeOutTime, INT32 *outPose, INT32 *outAngle, UINT8 *outStatus);

/* HVC_Registration                                                           */
/* param    : INT32         inTimeOutTime   timeout time (ms)                 */
/*          : INT32         inUserID        User ID (0-499)                   */
/*          : INT32         inDataID        Data ID (0-9)                     */
/*          : HVC_IMAGE     *outImage       image info                        */
/*          : UINT8         *outStatus      response code                     */
INT32 HVC_Registration(INT32 inTimeOutTime, INT32 inUserID, INT32 inDataID, HVC_IMAGE *outImage, UINT8 *outStatus);

/* HVC_DeleteData                                                             */
/* param    : INT32         inTimeOutTime   timeout time (ms)                 */
/*          : INT32         inUserID        User ID (0-499)                   */
/*          : INT32         inDataID        Data ID (0-9)                     */
/*          : UINT8         *outStatus      response code                     */
INT32 HVC_DeleteData(INT32 inTimeOutTime, INT32 inUserID, INT32 inDataID, UINT8 *outStatus);

/* HVC_DeleteUser                                                             */
/* param    : INT32         inTimeOutTime   timeout time (ms)                 */
/*          : INT32         inUserID        User ID (0-499)                   */
/*          : UINT8         *outStatus      response code                     */
INT32 HVC_DeleteUser(INT32 inTimeOutTime, INT32 inUserID, UINT8 *outStatus);

/* HVC_DeleteAll                                                              */
/* param    : INT32         inTimeOutTime   timeout time (ms)                 */
/*          : UINT8         *outStatus      response code                     */
INT32 HVC_DeleteAll(INT32 inTimeOutTime, UINT8 *outStatus);

/* HVC_GetUserData                                                            */
/* param    : INT32         inTimeOutTime   timeout time (ms)                 */
/*          : INT32         inUserID        User ID (0-499)                   */
/*          : INT32         *outDataNo      Registration Info                 */
/*          : UINT8         *outStatus      response code                     */
INT32 HVC_GetUserData(INT32 inTimeOutTime, INT32 inUserID, INT32 *outDataNo, UINT8 *outStatus);

#ifdef  __cplusplus
}
#endif

#endif  /* HVCApi_H__ */
