package com.javacraft.moon.phase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Calendar;

public class MoonPhase {

	  public static void main ( String args[] ) throws Exception
	  {
		  MoonCalculation o = new MoonCalculation ( ) ;
		  Calendar oC = Calendar.getInstance() ;
		  oC.set(oC.DATE, 1);
		  oC.set(oC.MONTH, 0 );
		  oC.set(oC.YEAR,  2005);
		  int iPPhase = -1 ;
		  boolean bFirst = true, bFirst24Qtr = true, bFirst15Qtr = true ;
		  StringBuffer oSB = new StringBuffer ( ) ;
		  StringBuffer oSB24Qtr = new StringBuffer ( ) ;
		  StringBuffer oSB15Qtr = new StringBuffer ( ) ;
		  while ( oC.get ( oC.YEAR ) < 2015)
		  {
		    int iPhase = o.moonPhase(oC.get(oC.YEAR), oC.get(oC.MONTH), oC.get(oC.DATE)) ;
		    if ( iPPhase != iPhase )
		    {
//		      System.out.println ( oC.get(oC.YEAR) + "." + (1+oC.get(oC.MONTH)) + "." + oC.get(oC.DATE) + " " + o.phaseName(iPhase)) ;
		      iPPhase = iPhase ;
		      if ( iPhase == 0 || iPhase == 4 )
		      {
		        if ( !bFirst )
		    	  oSB.append(",\r\n") ;
		        bFirst = false ;
		        oSB.append( "{x:Date.UTC(" ).append( oC.get(oC.YEAR) ).append ( ",") ;
		        oSB.append ( 1 + oC.get(oC.MONTH) ).append( ",") ;
		        oSB.append ( 1 + oC.get(oC.DATE) ).append( "),") ;
		        oSB.append( "title:'" + o.phaseName(iPhase)) ;
		        oSB.append("'}") ;
		      }
		      if ( iPhase == 1 || iPhase == 5 )
		      {
		        if ( !bFirst15Qtr )
		        	oSB15Qtr.append(",\r\n") ;
		        bFirst15Qtr = false ;
		        oSB15Qtr.append( "{x:Date.UTC(" ).append( oC.get(oC.YEAR) ).append ( ",") ;
		        oSB15Qtr.append ( 1 + oC.get(oC.MONTH) ).append( ",") ;
		        oSB15Qtr.append ( 1 + oC.get(oC.DATE) ).append( "),") ;
		        oSB15Qtr.append( "title:'" + o.phaseName(iPhase)) ;
		        oSB15Qtr.append("'}") ;
		      }
		      if ( iPhase == 2 || iPhase == 6 )
		      {
		        if ( !bFirst24Qtr )
		        	oSB24Qtr.append(",\r\n") ;
		        bFirst24Qtr = false ;
		        oSB24Qtr.append( "{x:Date.UTC(" ).append( oC.get(oC.YEAR) ).append ( ",") ;
		        oSB24Qtr.append ( 1 + oC.get(oC.MONTH) ).append( ",") ;
		        oSB24Qtr.append ( 1 + oC.get(oC.DATE) ).append( "),") ;
		        oSB24Qtr.append( "title:'" + o.phaseName(iPhase)) ;
		        oSB24Qtr.append("'}") ;
		      }
		    }
		    oC.add(oC.DATE, 1);
		  }
//		  System.out.println ( "[" + oSB + "];")  ;
		  File oF = new File ( "MoonPhase.json" ) ;
		  PrintWriter oPW = new PrintWriter ( new FileWriter ( oF ) )  ;
		  oPW.println ( "var NF = [" + oSB + "];"  ) ;
		  oPW.println ( "var TF = [" + oSB24Qtr + "];"  ) ;
		  oPW.println ( "var OF = [" + oSB15Qtr + "];"  ) ;
		  oPW.close ( ) ;
		  System.out.println ( oF.getAbsolutePath() ) ;
	      BufferedReader oBR = new BufferedReader ( new FileReader ( oF ) ) ;      
	      String s ;
	      while ( ( s = oBR.readLine() ) != null )
	    	 System.out.println ( s ) ;
	  }
}
