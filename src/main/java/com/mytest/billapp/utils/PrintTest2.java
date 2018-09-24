package com.mytest.billapp.utils;
//https://www.java-forums.org/java-2d/250-need-help-pos-printer.html
import java.awt.print.PrinterJob;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.SimpleDoc;

public class PrintTest2 {
	
	PrinterJob printerJob;
	 
    public PrintTest2() {
        PrintService tsp100 = getPrintService("HP LaserJet Pro MFP M126nw");
        try {
            DocPrintJob job = tsp100.createPrintJob();
            String openDrawerCommand = ((char) 0x07) + "";
            byte by[] = openDrawerCommand.getBytes();
            DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
            Doc doc = new SimpleDoc(by, flavor, null);
            System.out.println("Printing Started");
            job.print(doc, null);
        } catch (Exception e) {
            System.out.println("Whoa bro. The printer is balls. Check it:");
            e.printStackTrace();
        }
    }
 
    private PrintService getPrintService(String serviceName) {
        PrintService[] ps = PrinterJob.lookupPrintServices();
        for (int i = 0; i < ps.length; i++) {
        	System.out.println(ps[i].getName()+" $$$$$");
            if (ps[i].getName().indexOf(serviceName) >= 0) {
                return ps[i];
            }
        }
        System.out.println("Aw SNAP! I like, can't find a printer with "
                + serviceName + " in the name dude.");
        System.exit(1);
        return null;
    }
 
    public static void main(String[] args) {
    	PrintTest2 ocd = new PrintTest2();
    }
}