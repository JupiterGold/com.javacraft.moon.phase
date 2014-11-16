import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;



public class YahooDownload
{
  public static void main ( String args[] ) throws Exception
  {
	  String sSymbol = "GLD" ;
	  URL oURL = new URL ( "http://ichart.finance.yahoo.com/table.csv?s=" + sSymbol + "&a=10&b=18&c=2012&d=11&e=31&f=2014&g=d&ignore=.csv" ) ;
//	  URL oURL = new URL ( "file://c:/tmp/GLD.txt") ;
      URLConnection uCon = oURL.openConnection();
      InputStream is = uCon.getInputStream();
      BufferedReader oBR = new BufferedReader ( new InputStreamReader ( is )) ;
      String s = "" ;
      List<String> oL = new ArrayList<String> ( ) ;
      while ( ( s = oBR.readLine() ) != null )
    	 oL.add ( s ) ;
      int n = oL.size() ;
      Date oD = null ;
      boolean bFirst = true ;
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      File oF = new File ( sSymbol + ".json" ) ;
      PrintWriter oPW = new PrintWriter ( new FileWriter ( oF )) ;
      oPW.println("var data = [");

      File oFC = new File ( sSymbol + "-Close.json" ) ;
      PrintWriter oPWC = new PrintWriter ( new FileWriter ( oFC )) ;
      oPWC.println("var data = [");

      boolean bAddComma = false ;
 	  int c = 0 ;
 	  StringBuffer oSB = new StringBuffer ( ) ;
 	  StringBuffer oSBH = new StringBuffer ( ) ;
 	  StringBuffer oSBL = new StringBuffer ( ) ;
 	  BigDecimal oMAP = BigDecimal.ZERO ;
 	  double iMA = 4.0 ;
 	  double d = 0.0, dHMA = 0.0, dLMA = 0.0 ;
 	  BigDecimal dMA = BigDecimal.ZERO ;
 	  BigDecimal dMAH = BigDecimal.ZERO ;
 	  BigDecimal dMAL = BigDecimal.ZERO ;
 	  
      for ( int i = n-1 ; i > 0 ; i-- )
      {
//    	 System.out.println ( s ) ;
    	  s = oL.get(i) ;
    	 StringTokenizer oSTK = new StringTokenizer ( s, "," ) ;
    	 while ( oSTK.countTokens() > 4)
    	 {
    		 String sD = oSTK.nextToken() ;
    		 String sO = oSTK.nextToken() ;
    		 String sH = oSTK.nextToken() ;
    		 String sL = oSTK.nextToken() ;
    		 String sC = oSTK.nextToken() ;
    		 double dH = Double.parseDouble(sH) ;
    		 double dL = Double.parseDouble(sL) ;
    		 double dC = ( Double.parseDouble(sC) + ( Double.parseDouble(sH) + Double.parseDouble(sL) ) / 2.0 ) / 2.0 ;
    		 if ( c > iMA )
    		 {
    			 d = ( d * ( iMA - 1 ) + dC ) / iMA ;
    			 dMA = new BigDecimal ( d ) ;
    			 dHMA = ( dHMA * ( iMA - 1 ) + dH ) / iMA ;
    			 dMAH = new BigDecimal ( dHMA ) ;
    			 dLMA = ( dLMA * ( iMA - 1 ) + dL ) / iMA ;
    			 dMAL = new BigDecimal ( dLMA ) ;
    		 }
    		 else
    		 {
    			 if ( d == 0.0 ) 
    			 {
    			   d = dC ;
    			   dHMA = dH ;
    			   dLMA = dL ;
    			 }
    			 else
    			 {
     			   d = ( d * ( c ) + dC ) / ( c + 1 ) ;
     			   dHMA = ( dHMA * ( c ) + dH ) / ( c + 1 ) ;
     			   dLMA = ( dLMA * ( c ) + dL ) / ( c + 1 ) ;
    			 }
    			 dMA = new BigDecimal ( d / ( c + 1 ) ) ;    			 
    			 dMAH = new BigDecimal ( dHMA / ( c + 1 ) ) ;    			 
    			 dMAL = new BigDecimal ( dLMA / ( c + 1 ) ) ;    			 
    		 }
    		 if ( bAddComma ) 
    		 {
    			 oPW.print ( ",") ;
    			 oPWC.print ( ",") ;
    			 if ( c % 3 == 0 )
    			 {
    				 oPW.println ( ) ;
    				 oPWC.println ( ) ;
    			 }
    			 if ( c > iMA )
    			 {
    			   oSB.append ( "," ) ;
    			   oSBH.append ( "," ) ;
    			   oSBL.append ( "," ) ;
    			 }
    			 if ( c % 5 == 0 )
    			 {
    				 oSB.append( "\r\n") ;
    				 oSBH.append( "\r\n") ;
    				 oSBL.append( "\r\n") ;
    			 }
    		 }
    		 else
    		 {
    			 bAddComma = true ;
    		 }
    		 oPW.print("[") ;
    		 oPW.print (sdf.parse(sD).getTime()) ;
    		 oPW.print ("," + sO + "," + sH + "," + sL + "," + sC) ;
    		 oPW.print("]") ;

    		 
    		 oPWC.print("[") ;
    		 oPWC.print (sdf.parse(sD).getTime()) ;
    		 oPWC.print ("," + dC) ;
    		 oPWC.print("]") ;

    		 if ( c > iMA )
    		 {
      		   oSB.append ( "[") ;
    		   oSB.append (sdf.parse(sD).getTime()) ;
    		   oSB.append ("," + dMA.setScale(2, BigDecimal.ROUND_DOWN)) ;
    		   oSB.append("]") ;

      		   oSBH.append ( "[") ;
    		   oSBH.append (sdf.parse(sD).getTime()) ;
    		   oSBH.append ("," + dMAH.setScale(2, BigDecimal.ROUND_DOWN)) ;
    		   oSBH.append("]") ;

      		   oSBL.append ( "[") ;
    		   oSBL.append (sdf.parse(sD).getTime()) ;
    		   oSBL.append ("," + dMAL.setScale(2, BigDecimal.ROUND_DOWN)) ;
    		   oSBL.append("]") ;
    		 }
    	 }
    	 if ( ++c > 2000)
    	   break ;
      }
      oPW.println (  ) ;
      oPW.println ( "];") ;      
      oPW.println ( "var MA = [" + oSB.toString().trim().substring(1) + "];" ) ;
      oPW.println ( "var MAH = [" + oSBH.toString().trim().substring(1) + "];" ) ;
      oPW.println ( "var MAL = [" + oSBL.toString().trim().substring(1) + "];" ) ;
      oPW.println ( "var sSymbol = '" + sSymbol + "' ;") ;
      oPW.close ( ) ;

      
      oPWC.println (  ) ;
      oPWC.println ( "];") ;      
      oPWC.println ( "var MA = [" + oSB.toString().trim().substring(1) + "];" ) ;
      oPWC.println ( "var MAH = [" + oSBH.toString().trim().substring(1) + "];" ) ;
      oPWC.println ( "var MAL = [" + oSBL.toString().trim().substring(1) + "];" ) ;
      oPWC.println ( "var sSymbol = '" + sSymbol + "' ;") ;
      oPWC.close ( ) ;
//      System.out.println ( "callback([" + oSB + "]);") ;
//      System.out.println ( "?([" + oSB + "]);") ;
      System.out.println ( oFC.getAbsolutePath() ) ;
      oBR = new BufferedReader ( new FileReader ( oFC ) ) ;      
      while ( ( s = oBR.readLine() ) != null )
    	 System.out.println ( s ) ;
      oBR.close ( ) ;      
  }
}
