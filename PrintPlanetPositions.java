package com.javacraft.stockchart;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.javacraft.astro.AccuratePlaceData;
import com.javacraft.astro.Aspects;
import com.javacraft.astro.ChartInformation;
import com.javacraft.astro.FindAspect;
import com.javacraft.astro.PLANETDETAILS;
import com.javacraft.astro.PlaceData;
import com.javacraft.astro.PlanetConstants;
import com.javacraft.astro.PlanetaryPositions;
import com.javacraft.astro.Significators;
//http://www.illuminatetoday.com/astrology/8th-house-for-marriage-or-divorce-in-kp.html
public class PrintPlanetPositions {

  public static void main(String args[]) throws Exception {
    SimpleDateFormat oSDF = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
    Calendar oCalendar = Calendar.getInstance();
    oCalendar.set(Calendar.DATE, 12);
    oCalendar.set(Calendar.MONTH, 3);
    oCalendar.set(Calendar.YEAR, 1967);
    oCalendar.set(Calendar.HOUR_OF_DAY, 22);
    oCalendar.set(Calendar.MINUTE, 04);
    oCalendar.set(Calendar.SECOND, 0);

    oCalendar.set(Calendar.DATE, 9);
    oCalendar.set(Calendar.MONTH, 3);
    oCalendar.set(Calendar.YEAR, 2001);
    oCalendar.set(Calendar.HOUR_OF_DAY, 03);
    oCalendar.set(Calendar.MINUTE, 21);
    oCalendar.set(Calendar.SECOND, 0);
    

    oCalendar.set(Calendar.DATE, 29);
    oCalendar.set(Calendar.MONTH, 3);
    oCalendar.set(Calendar.YEAR, 2010);
    oCalendar.set(Calendar.HOUR_OF_DAY, 06);
    oCalendar.set(Calendar.MINUTE, 30);
    oCalendar.set(Calendar.SECOND, 0);

    oCalendar.set(Calendar.DATE, 26);
    oCalendar.set(Calendar.MONTH, 3);
    oCalendar.set(Calendar.YEAR, 2010);
    oCalendar.set(Calendar.HOUR_OF_DAY, 06);
    oCalendar.set(Calendar.MINUTE, 30);
    oCalendar.set(Calendar.SECOND, 0);

    PrintWriter oPW = new PrintWriter ( new FileWriter ( "/tmp/Planets.csv" ) ) ;
    oPW.println("Date,Ma-Mo,Me-Su,Me-Mo");
    SimpleDateFormat oDF = new SimpleDateFormat ( "yyyyMMdd") ;
      String sDate = "20090101";
      Date oDt = oDF.parse ( sDate ) ;
      oCalendar.setTime( oDt ) ;
      oCalendar.set(Calendar.HOUR_OF_DAY, 21);
      oCalendar.set(Calendar.MINUTE, 0);
      oCalendar.set(Calendar.SECOND, 0);
    Calendar oEnd = Calendar.getInstance() ;
    oEnd.add(Calendar.DATE, 120) ;
boolean bFirst = true ;
    StringBuffer oSB = new StringBuffer ( ) ;
    oSB.append("var dS1 = [");
    StringBuffer oS2 = new StringBuffer ( ) ;
    oS2.append("var dS2 = [");
    StringBuffer oS3 = new StringBuffer ( ) ;
    oS3.append("var dS3 = [");
    StringBuffer oS4 = new StringBuffer ( ) ;
    oS4.append("var dS4 = [");
    StringBuffer oS5 = new StringBuffer ( ) ;
    oS5.append("var dS5 = [");
    StringBuffer oS6 = new StringBuffer ( ) ;
    oS6.append("var dS6 = [");
    StringBuffer oSLeo = new StringBuffer ( ) ;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    while ( oCalendar.before(oEnd))
    {
      ChartInformation oCI = new ChartInformation(oCalendar.getTime(), getPlaceData());
      double b6 = oCI.getB6();
      PLANETDETAILS spd;
      PlanetaryPositions oPP = new PlanetaryPositions(b6, oCI.getYY(), spd = new PLANETDETAILS());
      Significators oSF = new Significators ( spd ) ;
      oSF.setSignStarSub();
//      oPP.adjustAyanamsa(PlanetConstants.KP_AYANAMSA);
//      spd.PlanetPositions[0] = oPP.adjustAyanamsa(PlanetConstants.KP_AYANAMSA, oCI.lagna());
//      Double2DMS oD = new Double2DMS(spd.PlanetPositions[PlanetConstants.MOON]);      
//      System.out.println("Date: " + oSDF.format(oCalendar.getTime()));
//      System.out.println("Moon: " + oD.getDeg() + "." + oD.getMin() + "." + oD.getSec());
//      HousePositions oHP = new HousePositions(oCI, spd,PlanetConstants.KP_CUSPS, 0);
//      for (int n = 0; n < PlanetConstants.MAX_CUSPS * 2; n++)
//        spd.CuspalPositions[n] = oPP.adjustAyanamsa(PlanetConstants.KP_AYANAMSA, spd.CuspalPositions[n]);
      double dRa = spd.PlanetPositions[PlanetConstants.RAHU] ;
      double dJu = spd.PlanetPositions[PlanetConstants.JUPITER] ;
      double dSa = spd.PlanetPositions[PlanetConstants.SATURN] ;
      double dVe = spd.PlanetPositions[PlanetConstants.VENUS] ;
      double dSu = spd.PlanetPositions[PlanetConstants.SUN] ;
      double dMo = spd.PlanetPositions[PlanetConstants.MOON] ;
      double dMa = spd.PlanetPositions[PlanetConstants.MARS] ;
      double dMe = spd.PlanetPositions[PlanetConstants.MERCURY] ;
      oPW.println ( oDF.format(oCalendar.getTime()) //+ "," + spd.PlanetPositions[PlanetConstants.SUN] + "," + spd.PlanetPositions[PlanetConstants.MOON] 
//    		  + "," + getDistance ( dVe, dMo ) + "," + getDistance ( dMa, dMe )
	          + "," + getDistance ( dMa, dMo ) + "," + getDistance ( dMe, dSu ) 
	          + "," + getDistance ( dMe, dMo ) 
	          + "," + getDistance ( dSu+dJu, dMo ) 
	          + "," + getDistance ( dSu+dJu+dMo, dMo ) 
	          + "," + getDistance ( dSu+dJu+dMo, dSu ) 
	          + "," + getDistance ( dSu-dJu, dMo+dJu ) 
	          + "," + getDistance ( dSu+dJu, dMo-dJu ) 
	          )  ;
       if ( bFirst == false )
       {
    	   oSB.append ( "," ) ;
    	   oS2.append ( "," ) ;
    	   oS3.append ( "," ) ;
    	   oS4.append ( "," ) ;
    	   oS5.append ( "," ) ;
    	   oS6.append ( "," ) ;
       }
/*
       if ( spd.PlanetStatus[PlanetConstants.JUPITER] == '-' )
    	   dJu = -dJu ;
       if ( spd.PlanetStatus[PlanetConstants.MERCURY] == '-' )
    	   dMe = -dMe ;
       if ( spd.PlanetStatus[PlanetConstants.MARS] == '-' )
    	   dMa = -dMa ;
       if ( spd.PlanetStatus[PlanetConstants.VENUS] == '-' )
    	   dVe = -dVe ;
//*/
       bFirst = false ;
//*
       oSB.append ( "[") ;
	   oSB.append (oCalendar.getTime().getTime()) ;
	   oSB.append ("," + new BigDecimal ( getDistance ( dMe ) ).setScale(2, BigDecimal.ROUND_UP)) ;
	   oSB.append("]") ;
//*/
//*	   
	   oS2.append ( "[") ;
	   oS2.append (oCalendar.getTime().getTime()) ;
	   oS2.append ("," + new BigDecimal ( getDistance ( dMa ) ).setScale(2, BigDecimal.ROUND_UP)) ;
	   oS2.append("]") ;
//*/	   
/*
       oS3.append ( "[") ;
	   oS3.append (oCalendar.getTime().getTime()) ;
	   oS3.append ("," + new BigDecimal ( getDistance ( dSu-dJu+dSa ) ).setScale(2, BigDecimal.ROUND_UP)) ;
	   oS3.append("]") ;

       oS4.append ( "[") ;
	   oS4.append (oCalendar.getTime().getTime()) ;
	   oS4.append ("," + new BigDecimal ( getDistance ( dMa ) ).setScale(2, BigDecimal.ROUND_UP)) ;
	   oS4.append("]") ;

//*/
//	   oS5.append ( "[") ;
//	   oS5.append (oCalendar.getTime().getTime()) ;
//	   oS5.append ("," + new BigDecimal ( getDistance ( dVe ) ).setScale(2, BigDecimal.ROUND_UP)) ;
//	   oS5.append("]") ;
//*/	   
	   StringBuffer oSBLeo = new StringBuffer ( ) ;
/*
	   if ( spd.PlanetSignLord [ PlanetConstants.SUN ] == PlanetConstants.SUN )
		   oSBLeo.append ( ",Su") ;
	   if ( spd.PlanetSignLord [ PlanetConstants.MOON ] == PlanetConstants.SUN )
		   oSBLeo.append ( ",Mp") ;
	   if ( spd.PlanetSignLord [ PlanetConstants.MERCURY ] == PlanetConstants.SUN )
		   oSBLeo.append ( ",Me") ;
	   if ( spd.PlanetSignLord [ PlanetConstants.MARS ] == PlanetConstants.SUN )
		   oSBLeo.append ( ",Ma") ;
	   if ( spd.PlanetSignLord [ PlanetConstants.JUPITER ] == PlanetConstants.SUN )
		   oSBLeo.append ( ",Ju") ;
	   if ( spd.PlanetSignLord [ PlanetConstants.SATURN ] == PlanetConstants.SUN )
		   oSBLeo.append ( ",Sa") ;
*/
	   String s = FindAspect.getAspectString(spd.PlanetPositions [ PlanetConstants.MOO ] , 0.0d ) ; //spd.PlanetPositions [ PlanetConstants.MOON]) ;
	   
	   if ( s != null ) //&& s.contains("Tr") )
	   {
		   oSBLeo.append( "," + s ) ;
	   }
//	   if ( spd.PlanetSignLord [ PlanetConstants.MERCURY ] == PlanetConstants.JUPITER )
//		   oSBLeo.append ( ",Me") ;
//	   if ( spd.PlanetSignLord [ PlanetConstants.VENUS ] == PlanetConstants.SUN )
//		   oSBLeo.append ( ",Ve") ;
	   if ( oSBLeo.length() > 0 )
	   {
	        oSLeo.append( ",{x:Date.UTC(" ).append( oCalendar.get(Calendar.YEAR) ).append ( ",") ;
	        oSLeo.append ( 1 + oCalendar.get(Calendar.MONTH) ).append( ",") ;
	        oSLeo.append ( 1 + oCalendar.get(Calendar.DATE) ).append( "),") ;
	        oSLeo.append( "title:'" + oSBLeo.substring(1)) ;
	        oSLeo.append("'}\r\n") ;
	   }
	   /*

       oS6.append ( "[") ;
	   oS6.append (oCalendar.getTime().getTime()) ;
	   oS6.append ("," + new BigDecimal ( getDistance ( dVe, dJu ) ).setScale(2, BigDecimal.ROUND_UP)) ;
	   oS6.append("]") ;
//*/
	   if ( oCalendar.get(Calendar.DAY_OF_WEEK)  == Calendar.FRIDAY )
	   {
	     oCalendar.add ( Calendar.DATE, 3 ) ;
	   }
	   else
  	     oCalendar.add ( Calendar.DATE, 1 ) ;
/*
	   boolean bWE = true ;
	   while (bWE)
	   {
		   oCalendar.add ( Calendar.DATE, 1 ) ;
	   switch (  oCalendar.get(Calendar.DAY_OF_WEEK) )
	   {
	   case Calendar.SUNDAY:
		   bWE = true;
		   break ;
	   case Calendar.SATURDAY:
		   bWE = true;
		   break ;
		default :
			bWE = false ;
			break ;
	   }
	   }
	   */
    }

    oPW.close(); 
    oPW = new PrintWriter ( new FileWriter ( "/tmp/SUN.json" ) ) ;
    oPW.println ( oSB ) ;
    oPW.println ( "];") ;      
    oPW.println ( oS2 ) ;
    oPW.println ( "];") ;      
    oPW.println ( oS3 ) ;
    oPW.println ( "];") ;      
    oPW.println ( oS4 ) ;
    oPW.println ( "];") ;      
    oPW.println ( oS5 ) ;
    oPW.println ( "];") ;      
    oPW.println ( oS6 ) ;
    oPW.println ( "];") ;      
    oPW.println ( "var dLeo = [" + oSLeo.substring(1) + "];"  ) ;
    oPW.close ( ) ;
  }//http://quotes.post1.org/historical-gold-price-chart/
  //http://www.abysse.co.jp/mt5/indicators/Demo_FileReadDouble.mq5
  static double getDistance ( double dS, double dM )
  {
      double dSM = 0.0 ;
      if ( dM > dS ) 
    	 dSM = dM - dS ;
      else
    	 dSM = dM + 360.0 - dS ;
      return getDistance ( dSM ) ;
  }
  static double getDistance ( double d )
  {
      double dSM = d ;
      if ( dSM > 90.0 )
    	 dSM = 180.0 - dSM ;
      if ( dSM < -90.0 )
    	 dSM = -(dSM + 180.0 ) ;
      if ( dSM > 90 || dSM < -90 )
    	  return getDistance ( dSM ) ;
      return dSM ;
  }
  
  static public PlaceData getPlaceData() {
  //  return new AccuratePlaceData("Singapore", 1.340156f, 103.838f);
    // return new AccuratePlaceData("Guntur", 16.30f, 80.42f );
    return new AccuratePlaceData("SingaporePools", 1.30f, 103.849f);
//    return new AccuratePlaceData ( "MumbaiColaba", 18.925334f, 72.831907f ) ;
//    return new AccuratePlaceData("Chennai", 30.116f, 80.288f);
 // return new AccuratePlaceData ( "MumbaiBombayHospital", 18.940889f, 72.827897f ) ;
  }
}


