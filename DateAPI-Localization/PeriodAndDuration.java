package test;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
public class PeriodAndDuration{
	public static void main(String[] args){
	LocalDate date=LocalDate.of(2016, 10, 31);
	// crate a new period object
	Period period=Period.ofYears(20);
	date=date.plus(period);
	System.out.println(date); //print 2036-10-31
	
	// chain methods cannot be used to create periods , only the last methods will be used 
	Period oneYear= period.ofYears(1).ofDays(1);  // this line actually creates one day period.
	System.out.println(oneYear); // print a period
	// add a date period on time object will cause exception
	
	// Create Duration object
	Duration tenDays= Duration.ofDays(10);
	System.out.println(tenDays);// outputs pt240H (print duration will not omit day unit)
	
	// Duration object can also created by using chronounite
	Duration hourly = Duration.of(1, ChronoUnit.HOURS); // this is duration of one hour
	
	// choronounit can also be used to show difference between date/time objects
	// for example if we want to know how many days are there between 1970.01.01 and 2000.04.30
	System.out.println(ChronoUnit.DAYS.between(LocalDate.of(1970, 01, 01), LocalDate.of(2000, 04, 30)));//11077 days
	
	// the way of using duration and using period are basically same, but there is ont important thing to notice
	// in duration, one day is not "one day" , it is 24 hours(contains time units): 
	//date = date.plus(Duration.ofDays(1));
	//System.out.println(date);  // cause exception since date does not contain times
	
	//instant class represent a specific moment in time in the GMT time zone
	Instant now=Instant.now();
	Instant later=Instant.now();
	Duration duration=Duration.between(now, later);
	System.out.println(duration.toMillis());
	
	// LocalDateTime cannot be converted to instant since it doesn't include time zone.
	// zonedDateTime can be converted to instant since it is universally recognized around the world as the same moment
	ZonedDateTime moment = ZonedDateTime.now();
	Instant currentMoment= moment.toInstant();
	System.out.println(moment + "\n"+currentMoment);
	
	// week or larger unit cannot be added to instant.
	//currentMoment.plus(1, ChronoUnit.WEEKS);  // cause exception
	
	//about daylight saving time 
	//for 2016 , DST is from March 13th to November 6th so: 
	LocalDate DST1= LocalDate.of(2016, 03, 13);
	LocalDate DST2= LocalDate.of(2016, 11, 6);
	LocalTime DSTtime=LocalTime.of(1, 30);
	ZoneId zone=ZoneId.of("US/Eastern");
	ZonedDateTime MarchOne = ZonedDateTime.of(DST1, DSTtime, zone);
	ZonedDateTime NovemberOne = ZonedDateTime.of(DST2, DSTtime, zone);
	System.out.println(MarchOne.plusHours(1)); // 3:30 now
	System.out.println(NovemberOne.plusHours(1)); // 1:30 now
	// very interesting feature.Notice that from GMT's view , there is no such a thing called daylight saving time. 
	
	}
}