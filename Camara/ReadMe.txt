----------------------------------------------------
 HVC-P Sample Code
----------------------------------------------------

(1) Contents
  This contains sample code for the detection process and the registration process.

  The detection process for the HVC-P will execute all 10 functions and will output the result
  in standard output.
  The registration process for the HVC-P will execute the face recognition registration function
  and output the result in standard output.

(2) Directory Structure
      src/
        HVCApi/                     HVC-P interface function
          HVCApi.c                    API function
          HVCApi.h                    API function definition
          HVCDef.h                    Struct definition
          HVCExtraUartFunc.h          Definition for external functions called from API function
        bmp/                        Function to save bitmap file
          bitmap.c                    Function to save to bitmap file the image from HVC
        uart/                       UART interface function
          uart.c                      UART function for Windows
          uart.h                      UART function definition
        main/                       Detection process sample
          main.c                      Sample code
        register_main/              Registration process sample
          register_main.c             Sample code

(3) Build method for sample code
  1. The sample code is built as to operate on Windows 7.
     It can be compiled and linked with VC10 (Visual Studio 2010 C++).
  2. Detection process sample
     The detection process module can be built with just all the files under the HVCApi directory
     and all the files under the bmp, uart and main directories.
  3. Registration process sample
     The registration process module can be built with just all the files under the HVCApi directory
     and all the files under the bmp, uart and register_main directories.

(4) Porting on non-Windows environment
  1. HVCApi
       The code in the directory below is built for versability.
       It can be used as-is in environments that can compile C language.
  2. bitmap.c
       Implement a function that can save bitmap files compatible to the environment used.
       The standard API functions for Windows CreateFile(), WriteFile() and CloseHandle() are used.
  3. uart.c, uart.h
       Implement a UART interface function compatible to the environment used.
       It should include the UART initialization, UART send, UART receive and UART end processes.
  4. main.c, register_main.c
       A keyboard input function for Windows is used.
         _kbhit(): check for keyboard input
         _getch(): Read input from the console
       Change these functions if required by the operating environment.

CAUTION
ÅEA UART port number is required in Windows.
  Confirm the COM number of the port connected to the HVC-P.
  The "define" definition on line 23 of main.c and register_main.c in the sample code need
  to be changed to the confirmed COM number.

ÅEThe UART baud rate is considered to be set to default (921600bps) for this sample code.
  The "define" definition on line 24 of main.c and register_main.c in the sample need
  to be changed to the different value accordingly if the baud rate is changed.


[NOTES ON USAGE]
* This sample code and documentation are copyrighted property of OMRON Corporation  
* This sample code does not guarantee proper operation

----
OMRON Corporation 
Copyright(C) 2014-2015 OMRON Corporation, All Rights Reserved.
