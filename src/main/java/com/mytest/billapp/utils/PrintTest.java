package com.mytest.billapp.utils;

public class PrintTest {
	public static void main(String[] args) {
		 
		POSPrintUtil printerService = new POSPrintUtil();
		
		System.out.println(printerService.getPrinters());
				
		//print some stuff
		printerService.printString("EPSON-TM-T20II", "\n\n testing testing 1 2 3eeeee \n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
 
		// cut that paper!
		byte[] cutP = new byte[] { 0x1d, 'V', 1 };
 
		printerService.printBytes("EPSON-TM-T20II", cutP);
	
	}
}
