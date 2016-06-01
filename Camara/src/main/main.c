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
#include <string.h>
#include <malloc.h>
#include <time.h>
#include "uart.h"
#include "HVCApi.h"
#include "HVCDef.h"
#include "HVCExtraUartFunc.h"




#define LOGBUFFERSIZE   8192
#define LOGBUFFERSIZECam   8192

#define UART_COM_NUMBER                     4            /* COM port number connected to HVC */
#define UART_COM_BAUDRATE               460800            /* Baud rate for HVC UART (bps) */

#define UART_SETTING_TIMEOUT              1000            /* HVC setting command signal timeout period */
#define UART_EXECUTE_TIMEOUT              ((10+10+6+3+15+15+1+1+15+10)*1000)
                                                          /* HVC execute command signal timeout period */

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

void SaveBitmapFile(int nWidth, int nHeight, UINT8 *unImageBuffer, const char *szFileName);

/*----------------------------------------------------------------------------*/
/* UART send signal                                                           */
/* param    : int   inDataSize  send signal data                              */
/*          : UINT8 *inData     data length                                   */
/* return   : int               send signal complete data number              */
/*----------------------------------------------------------------------------*/
int UART_SendData(int inDataSize, UINT8 *inData)
{
    /* Send Data */
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
    /* Receive Data */
    int ret = com_recv(inTimeOutTime, outResult, inDataSize);
    return ret;
}

/* Print Log Message */
static void PrintLog(char *pStr)
{
    puts(pStr);	 
	
	
  
}
char *timestring ( )
{
# define TIME_SIZE 40

  const struct tm *tm;
  size_t len;
  time_t now;
  char *s;
  now = time ( NULL );
  tm = localtime ( &now );

  s = ( char * ) malloc ( TIME_SIZE * sizeof ( char ) );

  len = strftime ( s, TIME_SIZE, "%d%B%Y%I%M", tm );

  return s;
# undef TIME_SIZE
}
/* HVC Execute Processing  */
int main(void)
{
    INT32 nRet = 0;  /* Return code */

    UINT8 outStatus;
    HVC_VERSION outVersion;
    HVC_RESULT *poutHVCResult = NULL;

    INT32 inAngleNo;
    HVC_THRESHOLD inThreshold;
    HVC_SIZERANGE inSizeRange;
    INT32 inPose;
    INT32 inAngle;
    INT32 inTimeOutTime;
    INT32 inExec;
    INT32 inImage;
	FILE * pFile;
	
	time_t now;
	
	char *logname[100];
	

    char *pExStr[] = {"?", "Neutral", "Feliz", "Sorpresa", "Enojado", "Triste"};

    int i;
    int ch = 0;
    int revision;
    char *pStr;                     /* String Buffer for logging output */
	char *pLog = (char*)malloc(LOGBUFFERSIZECam);
	int edad;
    int genero;

    S_STAT serial_stat;             /* Serial port set value*/

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
        /* Result Structure Allocation   */
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
        sprintf_s(&pStr[strlen(pStr)], LOGBUFFERSIZE-strlen(pStr), "\nHVC_GetThreshold : Body=%4d Hand=%4d Face=%4d Recognition=%4d",
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
            sprintf_s(&pStr[strlen(pStr)], LOGBUFFERSIZE-strlen(pStr), "\nPress Space Key to end: ");

            /******************/
            /* Log Output     */
            /******************/
            PrintLog(pStr);

            memset(pStr, 0, LOGBUFFERSIZE);
			memset(pLog,0,LOGBUFFERSIZECam);
					

            /*********************************/
            /* Execute Detection             */
            /*********************************/
            inTimeOutTime = UART_EXECUTE_TIMEOUT;
            inExec = HVC_ACTIV_BODY_DETECTION | HVC_ACTIV_HAND_DETECTION | HVC_ACTIV_FACE_DETECTION | HVC_ACTIV_FACE_DIRECTION |
                     HVC_ACTIV_AGE_ESTIMATION | HVC_ACTIV_GENDER_ESTIMATION | HVC_ACTIV_GAZE_ESTIMATION | HVC_ACTIV_BLINK_ESTIMATION |
                     HVC_ACTIV_EXPRESSION_ESTIMATION | HVC_ACTIV_FACE_RECOGNITION;
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
            if(poutHVCResult->executedFunc & HVC_ACTIV_BODY_DETECTION){
                /* Body Detection result string */
                sprintf_s(&pStr[strlen(pStr)], LOGBUFFERSIZE-strlen(pStr), "\n Body result count:%d", poutHVCResult->bdResult.num);
	                for(i = 0; i < poutHVCResult->bdResult.num; i++){
                    sprintf_s(&pStr[strlen(pStr)], LOGBUFFERSIZE-strlen(pStr), "\n      Index:%d \t\tX:%d Y:%d Size:%d Confidence:%d", i,
						      poutHVCResult->bdResult.bdResult[i].posX, poutHVCResult->bdResult.bdResult[i].posY,
                                poutHVCResult->bdResult.bdResult[i].size, poutHVCResult->bdResult.bdResult[i].confidence);
					
                }
            }
            if(poutHVCResult->executedFunc & HVC_ACTIV_HAND_DETECTION){
                /* Hand Detection result string */
                sprintf_s(&pStr[strlen(pStr)], LOGBUFFERSIZE-strlen(pStr), "\n Hand result count:%d", poutHVCResult->hdResult.num);
                for(i = 0; i < poutHVCResult->hdResult.num; i++){
                    sprintf_s(&pStr[strlen(pStr)], LOGBUFFERSIZE-strlen(pStr), "\n      Index:%d \t\tX:%d Y:%d Size:%d Confidence:%d", i,
                                poutHVCResult->hdResult.hdResult[i].posX, poutHVCResult->hdResult.hdResult[i].posY,
                                poutHVCResult->hdResult.hdResult[i].size, poutHVCResult->hdResult.hdResult[i].confidence);
                }
            }

            /* Face Detection result string */
            if(poutHVCResult->executedFunc &
                    (HVC_ACTIV_FACE_DETECTION | HVC_ACTIV_FACE_DIRECTION |
                     HVC_ACTIV_AGE_ESTIMATION | HVC_ACTIV_GENDER_ESTIMATION |
                     HVC_ACTIV_GAZE_ESTIMATION | HVC_ACTIV_BLINK_ESTIMATION |
                     HVC_ACTIV_EXPRESSION_ESTIMATION | HVC_ACTIV_FACE_RECOGNITION)){
                sprintf_s(&pStr[strlen(pStr)], LOGBUFFERSIZE-strlen(pStr), "\n Face result count:%d", poutHVCResult->fdResult.num);
				
				if(poutHVCResult->fdResult.num == 0 && poutHVCResult->bdResult.num == 0){
					void free(void *pLog);
				}else if(poutHVCResult->fdResult.num == 0 && poutHVCResult->bdResult.num >= 1){
					sprintf_s(&pLog[strlen(pLog)], LOGBUFFERSIZECam-strlen(pLog), "%d \t", poutHVCResult->bdResult.num);
					sprintf_s(&pLog[strlen(pLog)], LOGBUFFERSIZECam-strlen(pLog), "%d \t", poutHVCResult->fdResult.num);
					sprintf_s(&pLog[strlen(pLog)], LOGBUFFERSIZECam-strlen(pLog), "%d \t",0);
					sprintf_s(&pLog[strlen(pLog)], LOGBUFFERSIZECam-strlen(pLog), "%s \t","None");
					sprintf_s(&pLog[strlen(pLog)], LOGBUFFERSIZECam-strlen(pLog), "%s \t","None");
					sprintf_s(&pLog[strlen(pLog)], LOGBUFFERSIZECam-strlen(pLog), "%d \n",201 );//totem

				}else{
				sprintf_s(&pLog[strlen(pLog)], LOGBUFFERSIZECam-strlen(pLog), "%d \t", poutHVCResult->bdResult.num);
					sprintf_s(&pLog[strlen(pLog)], LOGBUFFERSIZECam-strlen(pLog), "%d \t", poutHVCResult->fdResult.num);
				}
                for(i = 0; i < poutHVCResult->fdResult.num; i++){
                    if(poutHVCResult->executedFunc & HVC_ACTIV_FACE_DETECTION){
                        /* Detection */
                        sprintf_s(&pStr[strlen(pStr)], LOGBUFFERSIZE-strlen(pStr), "\n      Index:%d \t\tX:%d Y:%d Size:%d Confidence:%d", i,
                                    poutHVCResult->fdResult.fcResult[i].dtResult.posX, poutHVCResult->fdResult.fcResult[i].dtResult.posY,
                                    poutHVCResult->fdResult.fcResult[i].dtResult.size, poutHVCResult->fdResult.fcResult[i].dtResult.confidence);
	
								
					}

                    if(poutHVCResult->executedFunc & HVC_ACTIV_FACE_DIRECTION){
                        /* Face Direction */
                        sprintf_s(&pStr[strlen(pStr)], LOGBUFFERSIZE-strlen(pStr), "\n      Face Direction\tLR:%d UD:%d Roll:%d Confidence:%d",
                                    poutHVCResult->fdResult.fcResult[i].dirResult.yaw, poutHVCResult->fdResult.fcResult[i].dirResult.pitch,
                                    poutHVCResult->fdResult.fcResult[i].dirResult.roll, poutHVCResult->fdResult.fcResult[i].dirResult.confidence);
						
                    }
                    if(poutHVCResult->executedFunc & HVC_ACTIV_AGE_ESTIMATION){
                        /* Age */
                        if(-128 == poutHVCResult->fdResult.fcResult[i].ageResult.age){
                            sprintf_s(&pStr[strlen(pStr)], LOGBUFFERSIZE-strlen(pStr), "\n      Age\t\tEstimation not possible");
                        } else {
                            sprintf_s(&pStr[strlen(pStr)], LOGBUFFERSIZE-strlen(pStr), "\n      Age\t\tAge:%d Confidence:%d",
								 poutHVCResult->fdResult.fcResult[i].ageResult.age, poutHVCResult->fdResult.fcResult[i].ageResult.confidence);
							edad = poutHVCResult->fdResult.fcResult[i].ageResult.confidence;
							sprintf_s(&pLog[strlen(pLog)], LOGBUFFERSIZECam-strlen(pLog), "%d \t",
								 poutHVCResult->fdResult.fcResult[i].ageResult.age);
						
						}
                      
                    }
                    if(poutHVCResult->executedFunc & HVC_ACTIV_GENDER_ESTIMATION){
                        /* Gender */
                        if(-128 == poutHVCResult->fdResult.fcResult[i].genderResult.gender){
                            sprintf_s(&pStr[strlen(pStr)], LOGBUFFERSIZE-strlen(pStr), "\n      Gender\t\tEstimation not possible");
                        }
                        else{
                            if(1 == poutHVCResult->fdResult.fcResult[i].genderResult.gender){
                                sprintf_s(&pStr[strlen(pStr)], LOGBUFFERSIZE-strlen(pStr), "\n      Gender\t\tGender:%s Confidence:%d",
                                            "Male", poutHVCResult->fdResult.fcResult[i].genderResult.confidence);
								genero = poutHVCResult->fdResult.fcResult[i].genderResult.confidence;
								sprintf_s(&pLog[strlen(pLog)], LOGBUFFERSIZECam-strlen(pLog), "%s \t",
                                            "Hombre");
								
                            }
                            else{
                                sprintf_s(&pStr[strlen(pStr)], LOGBUFFERSIZE-strlen(pStr), "\n      Gender\t\tGender:%s Confidence:%d",
                                            "Female", poutHVCResult->fdResult.fcResult[i].genderResult.confidence);
								genero = poutHVCResult->fdResult.fcResult[i].genderResult.confidence;
								sprintf_s(&pLog[strlen(pLog)], LOGBUFFERSIZECam-strlen(pLog), "%s \t",
                                            "Mujer");
								                           
                            }
                        }
                    }
                    if(poutHVCResult->executedFunc & HVC_ACTIV_GAZE_ESTIMATION){
                        /* Gaze */
                        if((-128 == poutHVCResult->fdResult.fcResult[i].gazeResult.gazeLR) ||
                            (-128 == poutHVCResult->fdResult.fcResult[i].gazeResult.gazeUD)){
                            sprintf_s(&pStr[strlen(pStr)], LOGBUFFERSIZE-strlen(pStr), "\n      Gaze\t\tEstimation not possible");
                        }
                        else{
                            sprintf_s(&pStr[strlen(pStr)], LOGBUFFERSIZE-strlen(pStr), "\n      Gaze\t\tLR:%d UD:%d",
                                        poutHVCResult->fdResult.fcResult[i].gazeResult.gazeLR, poutHVCResult->fdResult.fcResult[i].gazeResult.gazeUD);
                        }
                    }
                    if(poutHVCResult->executedFunc & HVC_ACTIV_BLINK_ESTIMATION){
                        /* Blink */
                        if((-128 == poutHVCResult->fdResult.fcResult[i].blinkResult.ratioL) ||
                            (-128 == poutHVCResult->fdResult.fcResult[i].blinkResult.ratioR)){
                            sprintf_s(&pStr[strlen(pStr)], LOGBUFFERSIZE-strlen(pStr), "\n      Blink\t\tEstimation not possible");
                        }
                        else{
                            sprintf_s(&pStr[strlen(pStr)], LOGBUFFERSIZE-strlen(pStr), "\n      Blink\t\tLeft:%d Right:%d",
                                        poutHVCResult->fdResult.fcResult[i].blinkResult.ratioL, poutHVCResult->fdResult.fcResult[i].blinkResult.ratioR);
                        }
                    }
                    if(poutHVCResult->executedFunc & HVC_ACTIV_EXPRESSION_ESTIMATION){
                        /* Expression */
                        if(-128 == poutHVCResult->fdResult.fcResult[i].expressionResult.expression){
                            sprintf_s(&pStr[strlen(pStr)], LOGBUFFERSIZE-strlen(pStr), "\n      Expression\tEstimation not possible");
                        }
                        else{
                            if(poutHVCResult->fdResult.fcResult[i].expressionResult.expression > EX_SADNESS){
                                poutHVCResult->fdResult.fcResult[i].expressionResult.expression = 0;
                            }
                            sprintf_s(&pStr[strlen(pStr)], LOGBUFFERSIZE-strlen(pStr), "\n      Expression\tExpression:%s Score:%d Degree:%d",
                                        pExStr[poutHVCResult->fdResult.fcResult[i].expressionResult.expression],
                                        poutHVCResult->fdResult.fcResult[i].expressionResult.score,
                                        poutHVCResult->fdResult.fcResult[i].expressionResult.degree);
							sprintf_s(&pLog[strlen(pLog)], LOGBUFFERSIZECam-strlen(pLog), "%s \t",
                                        pExStr[poutHVCResult->fdResult.fcResult[i].expressionResult.expression]  );
							sprintf_s(&pLog[strlen(pLog)], LOGBUFFERSIZECam-strlen(pLog), "%d \n",201 );//totem
							
							
						}
                    }
                    if(poutHVCResult->executedFunc & HVC_ACTIV_FACE_RECOGNITION){
                        /* Recognition */
                        if(-128 == poutHVCResult->fdResult.fcResult[i].recognitionResult.uid){
                            sprintf_s(&pStr[strlen(pStr)], LOGBUFFERSIZE-strlen(pStr), "\n      Recognition\tRecognition not possible");
                        }
                        else if(-127 == poutHVCResult->fdResult.fcResult[i].recognitionResult.uid){
                            sprintf_s(&pStr[strlen(pStr)], LOGBUFFERSIZE-strlen(pStr), "\n      Recognition\tNot registered");
                        }
                        else{
                            sprintf_s(&pStr[strlen(pStr)], LOGBUFFERSIZE-strlen(pStr), "\n      Recognition\tID:%d Confidence:%d",
                                        poutHVCResult->fdResult.fcResult[i].recognitionResult.uid,
                                        poutHVCResult->fdResult.fcResult[i].recognitionResult.confidence);
                        }
                    }

                }
			
					
				//pFile = fopen (strcat(timestring(),".txt"),"a");
				pFile = fopen ("log.txt","a");
				if (pFile!=NULL)
				 {
					 if(poutHVCResult->fdResult.num >= 1 && edad>500 && genero>500 ){
						
					 char buff[100];
					 time_t now = time (0);
					 strftime (buff, 100, "%Y-%m-%d %H:%M:%S \t", localtime (&now));
					
					 fputs ( buff,pFile);

					 fputs (pLog ,pFile);
						
					}else if(poutHVCResult->bdResult.num >= 1 && poutHVCResult->fdResult.num == 0 )					
					{
						 char buff[100];
					 time_t now = time (0);
					 strftime (buff, 100, "%Y-%m-%d %H:%M:%S \t", localtime (&now));
					
					 fputs ( buff,pFile);

					 fputs (pLog ,pFile);
					}								 
				 }
				fclose (pFile);

			
            }
            if ( _kbhit() ) {
                ch = _getch();
                ch = toupper( ch );
				
            }
        } while( ch != ' ' );
    } while(0);
    /******************/
    /* Log Output     */
    /******************/
    PrintLog(pStr);

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
		free(pLog);
		
    }
		
	
    return (0);
}
