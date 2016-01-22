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

void SaveBitmapFile(int nWidth, int nHeight, UINT8 *unImageBuffer, const char *szFileName)
{
    int nI;
    HANDLE  hFile;
    DWORD   dw;
    DWORD   dwHeaderSize;
    BYTE    *pHeaderBuffer;
    RGBQUAD *pBmpInfoRGB;
    BITMAPINFOHEADER    *pBmpInfoHdr;
    BITMAPFILEHEADER    bmpFileHeader;

    dwHeaderSize = sizeof(BITMAPINFOHEADER) + sizeof(RGBQUAD) * 256;
    pHeaderBuffer = (BYTE*)malloc(dwHeaderSize);
    if ( pHeaderBuffer == NULL ) return;
    memset(pHeaderBuffer, 0, dwHeaderSize);

    pBmpInfoHdr = (BITMAPINFOHEADER*)pHeaderBuffer;
    pBmpInfoHdr->biSize = sizeof(BITMAPINFOHEADER);
    pBmpInfoHdr->biWidth  = nWidth;
    pBmpInfoHdr->biHeight = nHeight;
    pBmpInfoHdr->biPlanes = 1;
    pBmpInfoHdr->biCompression = BI_RGB;
    pBmpInfoHdr->biSizeImage = 0;
    pBmpInfoHdr->biXPelsPerMeter = 0;
    pBmpInfoHdr->biYPelsPerMeter = 0;
    pBmpInfoHdr->biBitCount = 8;
    pBmpInfoHdr->biClrUsed = 256;
    pBmpInfoHdr->biClrImportant = 0;

    pBmpInfoRGB = (RGBQUAD*)(pBmpInfoHdr+1);
    for (nI = 0; nI < 256; nI++) {
        pBmpInfoRGB->rgbRed = (BYTE)nI;
        pBmpInfoRGB->rgbGreen = (BYTE)nI;
        pBmpInfoRGB->rgbBlue = (BYTE)nI;
        pBmpInfoRGB->rgbReserved = 0;
        pBmpInfoRGB++;
    }

    bmpFileHeader.bfType = 0x4d42;  /* "BM" */
    bmpFileHeader.bfReserved1 = 0;
    bmpFileHeader.bfReserved2 = 0;

    bmpFileHeader.bfOffBits = sizeof(BITMAPFILEHEADER) + dwHeaderSize;
    bmpFileHeader.bfSize = nWidth * nHeight + bmpFileHeader.bfOffBits;

    hFile = CreateFile(szFileName, GENERIC_WRITE, FILE_SHARE_WRITE, NULL, CREATE_ALWAYS, FILE_ATTRIBUTE_NORMAL, NULL);
    WriteFile(hFile, &bmpFileHeader, sizeof(BITMAPFILEHEADER), &dw, NULL);
    WriteFile(hFile, pHeaderBuffer, dwHeaderSize, &dw, NULL);
    for ( nI=0; nI<nHeight; nI++ ) {
        WriteFile(hFile, unImageBuffer+(nHeight-nI-1)*nWidth, nWidth, &dw, NULL);
    }
    CloseHandle(hFile);

    free(pHeaderBuffer);
}
