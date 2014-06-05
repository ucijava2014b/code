import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.junit.Test;

@SuppressWarnings("unused")
public class CourseTest {

	private Course setupClass() throws ParseException {
		// Generate some a course and some class offerings
		ArrayList<CourseOffering> CS510Offerings = new ArrayList<CourseOffering>();
		CS510Offerings.add(new CourseOffering("300",
											  new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH).parse("01/14/2014"), 
		  									  new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH).parse("05/23/2014"),
		   									  25, 
		   									  null,
		   									  null));
		        
		CS510Offerings.add(new CourseOffering("400",
											  new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH).parse("05/13/2014"), 
				  		   					  new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH).parse("09/23/2014"),
				  		   					  30, 
				  		   					  null,
				  		   					  null));
		        
		        
		Course test = new Course("0001", 
		        				 "Intro to C++ Programming", 
		        				 510, 
		        				 "Provides an intro to computer programming using the C++ langugage.", 
		        				 "Computer Science", 
		        				  CS510Offerings);
		
		return test;
	}	
		
	
	@Test
	public void testCourseStringStringIntStringStringArrayListOfCourseOffering() throws ParseException {
		Course test = setupClass();
	
	}

	@Test
	public void testCourseString() {
		Course test = new Course("123,Class of Something,532,A class about something,Something Department");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testCourseStringException1() {
		// Not enough parameters
		Course test = new Course("123,Class of Something,532,A class about something");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testCourseStringException2() {
		// Too many parameters
		Course test = new Course("123,Class of Something,532,A class about something,Something Department,1");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testCourseStringException3() {
		// Wrong type
		Course test = new Course("123,Class of Something,Wrong Type,A class about something,Something Department");
	}

	@Test
	public void testCourseID() throws ParseException {
		Course test = setupClass();
		
		test.setCourseID("New ID");
		
		assertTrue(test.getCourseID().compareTo("New ID") == 0);
	}

	@Test
	public void testCourseName() throws ParseException {
		Course test = setupClass();
		
		test.setCourseName("New Name");
		
		assertTrue(test.getCourseName().compareTo("New Name") == 0);		
	}

	@Test
	public void testCourseNumber() throws ParseException {
		Course test = setupClass();
		
		test.setCourseNumber(999);
		
		assertTrue(test.getCourseNumber() == 999);
	}

	@Test
	public void testCourseOverview() throws ParseException {
		Course test = setupClass();
		
		test.setCourseOverview("This is some overview");
		
		assertTrue(test.getCourseOverview().compareTo("This is some overview") == 0);
	}

	@Test
	public void testDepartment() throws ParseException {
		Course test = setupClass();
		
		test.setDepartment("This is some department");
		
		assertTrue(test.getDepartment().compareTo("This is some department") == 0);
	}

	@Test
	public void testCourseOfferings() throws ParseException {
		Course test = setupClass();
		
		assertTrue(test.getCourseOfferings().size() == 2);
		
		ArrayList<CourseOffering> CS510Offerings = new ArrayList<CourseOffering>();
		CS510Offerings.add(new CourseOffering("400",
											  new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH).parse("01/14/2014"), 
		  									  new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH).parse("05/23/2014"),
		   									  25, 
		   									  null,
		   									  null));
		
		test.setCourseOfferings(CS510Offerings);
		
		assertTrue(test.getCourseOfferings().size() == 1);
	}

	@Test
	public void testWriteln() {
		Course test = new Course("123,Class of Something,532,A class about something,Something Department");
		
		assertTrue(test.writeln().compareTo("123,Class of Something,532,A class about something,Something Department") == 0);
	}

	@Test
	public void testToString() {
		Course test = new Course("123,Class of Something,532,A class about something,Something Department");
			
		String s = "Department: Something Department\r532 - Class of Something\rCourse Overview: A class about something";
		
		assertTrue(test.toString().compareTo(s) == 0);
	}
}
