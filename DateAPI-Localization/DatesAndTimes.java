package test;
import java.time.*;
public class DatesAndTimes{
	public static void main(String[] args){
		//create time, date , datetime , and zonedDatetime
		LocalDate date = LocalDate.of(2016, 10, 30);
		LocalTime time = LocalTime.of(16, 39, 00);
		LocalDateTime dateTime= LocalDateTime.of(date, time);
		ZonedDateTime zonedDateTime=ZonedDateTime.of(dateTime, ZoneId.of("US/Eastern"));
		
		// if we are not sure about zone strings we can use functional programmingto to search for it 
		// for example, if we want to get tokyo's zone:
		ZoneId.getAvailableZoneIds().stream().filter(z->z.contains("Tokyo")).sorted().forEach(System.out::println);
		
		// date , time are immutable class, so we need to reassign the value with "="
		date = date.plusDays(2);
		System.out.println(date);   // plus 2 days to 10/30 , make it 11/1
		// java is also smart enough to deal with special case such as leap year.
		
		// when manipulating time , java is also smart enough to decide when each unit is needed or not
		time= time.minusNanos(1);
		System.out.println(time); // even though original doesn't include Nanos, it will be added when necessary
		
		// sure we can also use methods chaining
		dateTime =dateTime.minusDays(1).minusDays(1);
		System.out.println(dateTime);  // output 2016-10-28T16:39 (minus two days)
		
	}
}
