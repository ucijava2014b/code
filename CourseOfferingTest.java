import static org.junit.Assert.*;

import org.junit.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import org.junit.Test;

@SuppressWarnings("unused")
public class CourseOfferingTest {

	private CourseOffering setupClass() throws ParseException {

		ArrayList<Student> enrolledList = new ArrayList<Student>();

		ArrayList<Student> waitlistList = new ArrayList<Student>();

		enrolledList.add(new Student("Dave", "Johnson", 25, "M", "555121234",
				"123 Pine", "Anaheim", "CA", "92592", "dave.johnson@yahoo.com",
				"714-123-1234", 123, "Big City University", "djohnson",
				"password"));
		enrolledList.add(new Student("Tim", "Smith", 34, "M", "123424123",
				"123 Pine", "Anaheim", "CA", "92592", "tim.smith@yahoo.com",
				"714-123-1234", 124, "Big City University", "tsmith",
				"password"));
		enrolledList.add(new Student("Monica", "Jackson", 53, "F", "999123213",
				"123 Pine", "Anaheim", "CA", "92592",
				"monica.jackson@yahoo.com", "714-123-1234", 125,
				"Big City University", "monica", "password"));
		waitlistList
				.add(new Student("Teresa", "Beta", 23, "F", "999121663",
						"123 Pine", "Anaheim", "CA", "92592",
						"t.beta@yahoo.com", "714-123-1234", 126,
						"Big City University", "tbeta", "password"));
		waitlistList.add(new Student("Bill", "Apple", 26, "M", "994123243",
				"123 Pine", "Anaheim", "CA", "92592", "bill.apple@yahoo.com",
				"714-123-1234", 127, "Big City University", "bill.apple",
				"password"));

		CourseOffering test = new CourseOffering("300", new SimpleDateFormat(
				"MM/dd/yyyy", Locale.ENGLISH).parse("11/21/2014"),
				new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH)
						.parse("11/22/2014"), 3, enrolledList, waitlistList);

		return test;
	}

	@Test
	public void testCourseOfferingDateDateIntArrayListOfStudentArrayListOfStudent()
			throws ParseException {
		CourseOffering test = setupClass();
	}

	@Test
	public void testCourseOfferingString() {
		CourseOffering test = new CourseOffering("300,11/21/2014,11/22/2014,25");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCourseOfferingStringException1() {
		// Throws an exception - too many parameters
		CourseOffering test = new CourseOffering(
				"300,11/21/2014,11/22/2014,25,Too many");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCourseOfferingStringException2() {
		// Throws an exception - too few parameters
		CourseOffering test = new CourseOffering("300,11/21/2014,11/22/2014");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCourseOfferingStringException3() {
		// Throws an exception - not a number
		CourseOffering test = new CourseOffering(
				"300,11/21/2014,11/22/2014,Not a number");
	}

	@Test
	public void testCourseOfferingID() throws ParseException {
		CourseOffering test = setupClass();

		test.setCourseOfferingID("5425");

		assertTrue(test.getCourseOfferingID().compareTo("5425") == 0);
	}

	@Test
	public void testCourseStartDate() throws ParseException {
		CourseOffering test = setupClass();

		test.setCourseStartDate(new SimpleDateFormat("MM/dd/yyyy",
				Locale.ENGLISH).parse("02/02/2014"));

		assertTrue(test.getCourseStartDate().compareTo(
				new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH)
						.parse("02/02/2014")) == 0);
	}

	@Test
	public void testCourseEndDate() throws ParseException {
		CourseOffering test = setupClass();

		test.setCourseEndDate(new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH)
				.parse("01/02/2014"));

		assertTrue(test.getCourseEndDate().compareTo(
				new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH)
						.parse("01/02/2014")) == 0);
	}

	@Test
	public void testMaxStudents() throws ParseException {
		CourseOffering test = setupClass();

		// Should not be a problem
		test.setMaxStudents(25);

		assertTrue(test.getMaxStudents() == 25);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testMaxStudentsException() throws ParseException {
		CourseOffering test = setupClass();

		// Should throw an exception
		test.setMaxStudents(2);
	}

	@Test
	public void testEnrolledStudents() throws ParseException {
		CourseOffering test = setupClass();

		ArrayList<Student> enrolledList = new ArrayList<Student>();

		enrolledList.add(new Student("Dave", "Johnson", 25, "M", "555121234",
				"123 Pine", "Anaheim", "CA", "92592", "dave.johnson@yahoo.com",
				"714-123-1234", 123, "Big City University", "djohnson",
				"password"));
		enrolledList.add(new Student("Tim", "Smith", 34, "M", "123424123",
				"123 Pine", "Anaheim", "CA", "92592", "tim.smith@yahoo.com",
				"714-123-1234", 124, "Big City University", "tsmith",
				"password"));

		// Should not be a problem
		test.setEnrolledStudents(enrolledList);

		assertTrue(test.getEnrolledStudents().size() == 2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetEnrolledStudentsException() throws ParseException {
		CourseOffering test = setupClass();

		ArrayList<Student> enrolledList = new ArrayList<Student>();

		enrolledList.add(new Student("Dave", "Johnson", 25, "M", "555121234",
				"123 Pine", "Anaheim", "CA", "92592", "dave.johnson@yahoo.com",
				"714-123-1234", 123, "Big City University", "djohnson",
				"password"));
		enrolledList.add(new Student("Tim", "Smith", 34, "M", "123424123",
				"123 Pine", "Anaheim", "CA", "92592", "tim.smith@yahoo.com",
				"714-123-1234", 124, "Big City University", "tsmith",
				"password"));
		enrolledList.add(new Student("Monica", "Jackson", 53, "F", "999123213",
				"123 Pine", "Anaheim", "CA", "92592",
				"monica.jackson@yahoo.com", "714-123-1234", 125,
				"Big City University", "monica", "password"));
		enrolledList
				.add(new Student("Teresa", "Beta", 23, "F", "999121663",
						"123 Pine", "Anaheim", "CA", "92592",
						"t.beta@yahoo.com", "714-123-1234", 126,
						"Big City University", "tbeta", "password"));
		enrolledList.add(new Student("Bill", "Apple", 26, "M", "994123243",
				"123 Pine", "Anaheim", "CA", "92592", "bill.apple@yahoo.com",
				"714-123-1234", 127, "Big City University", "bill.apple",
				"password"));

		// Should throw an exception
		test.setEnrolledStudents(enrolledList);
	}

	@Test
	public void testWaitListedStudents() throws ParseException {
		CourseOffering test = setupClass();

		ArrayList<Student> waitlistList = new ArrayList<Student>();

		waitlistList.add(new Student("Dave", "Johnson", 25, "M", "555121234",
				"123 Pine", "Anaheim", "CA", "92592", "dave.johnson@yahoo.com",
				"714-123-1234", 123, "Big City University", "djohnson",
				"password"));
		waitlistList.add(new Student("Tim", "Smith", 34, "M", "123424123",
				"123 Pine", "Anaheim", "CA", "92592", "tim.smith@yahoo.com",
				"714-123-1234", 124, "Big City University", "tsmith",
				"password"));

		test.setWaitListedStudents(waitlistList);

		assertTrue(test.getWaitListedStudents().size() == 2);
	}

	@Test
	public void testEnrollStudent() throws ParseException {
		CourseOffering test = setupClass();

		assertTrue(test.getEnrolledStudents().size() == 3);

		test.setMaxStudents(4);

		test.enrollStudent(new Student("Teresa", "Beta", 23, "F", "999121663",
				"123 Pine", "Anaheim", "CA", "92592", "t.beta@yahoo.com",
				"714-123-1234", 128, // <==============================================
				"Big City University", "tbeta", "password"));

		assertTrue(test.getEnrolledStudents().size() == 4);
	}

	@Test
	public void testEnrollStudentWaitlist() throws ParseException {
		CourseOffering test = setupClass();

		assertTrue(test.getEnrolledStudents().size() == 3);
		assertTrue(test.getWaitListedStudents().size() == 2);

		// Should add the student to the waitlist instead of enrolled
		test.enrollStudent(new Student("Teresa", "Beta", 23, "F", "999121663",
				"123 Pine", "Anaheim", "CA", "92592", "t.beta@yahoo.com",
				"714-123-1234", 128, // <==========================================
				"Big City University", "tbeta", "password"));

		assertTrue(test.getEnrolledStudents().size() == 3);
		assertTrue(test.getWaitListedStudents().size() == 3);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testEnrollStudentException1() throws ParseException {
		CourseOffering test = setupClass();

		assertTrue(test.getEnrolledStudents().size() == 3);
		assertTrue(test.getWaitListedStudents().size() == 2);

		// Should throw an exception as we are adding a student already enrolled
		test.enrollStudent(new Student("Teresa", "Beta", 23, "F", "999121663",
				"123 Pine", "Anaheim", "CA", "92592", "t.beta@yahoo.com",
				"714-123-1234", 126, "Big City University", "tbeta", "password"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testEnrollStudentException2() throws ParseException {
		CourseOffering test = setupClass();

		assertTrue(test.getEnrolledStudents().size() == 3);
		assertTrue(test.getWaitListedStudents().size() == 2);

		// Should throw an exception as we are adding a student already
		// waitlisted
		test.enrollStudent(new Student("Teresa", "Beta", 23, "F", "999121663",
				"123 Pine", "Anaheim", "CA", "92592", "t.beta@yahoo.com",
				"714-123-1234", 126, "Big City University", "tbeta", "password"));
	}

	@Test
	public void testDropStudent1() throws ParseException {
		CourseOffering test = setupClass();

		assertTrue(test.getEnrolledStudents().size() == 3);
		assertTrue(test.getWaitListedStudents().size() == 2);

		// Dropping the enrolled students and then the waitlisted
		assertTrue(test.dropStudent(new Student("Dave", "Johnson", 25, "M",
				"555121234", "123 Pine", "Anaheim", "CA", "92592",
				"dave.johnson@yahoo.com", "714-123-1234", 123,
				"Big City University", "djohnson", "password")));
		assertTrue(test.getEnrolledStudents().size() == 2);
		assertTrue(test.dropStudent(new Student("Monica", "Jackson", 53, "F",
				"999123213", "123 Pine", "Anaheim", "CA", "92592",
				"monica.jackson@yahoo.com", "714-123-1234", 125,
				"Big City University", "monica", "password")));
		assertTrue(test.getEnrolledStudents().size() == 1);
		assertTrue(test.dropStudent(new Student("Tim", "Smith", 34, "M",
				"123424123", "123 Pine", "Anaheim", "CA", "92592",
				"tim.smith@yahoo.com", "714-123-1234", 124,
				"Big City University", "tsmith", "password")));
		assertTrue(test.getEnrolledStudents().size() == 0);

		assertTrue(test.dropStudent(new Student("Teresa", "Beta", 23, "F",
				"999121663", "123 Pine", "Anaheim", "CA", "92592",
				"t.beta@yahoo.com", "714-123-1234", 126, "Big City University",
				"tbeta", "password")));
		assertTrue(test.getWaitListedStudents().size() == 1);
		assertTrue(test.dropStudent(new Student("Bill", "Apple", 26, "M",
				"994123243", "123 Pine", "Anaheim", "CA", "92592",
				"bill.apple@yahoo.com", "714-123-1234", 127,
				"Big City University", "bill.apple", "password")));
		assertTrue(test.getWaitListedStudents().size() == 0);
	}

	@Test
	public void testDropStudent2() throws ParseException {
		CourseOffering test = setupClass();

		assertTrue(test.getEnrolledStudents().size() == 3);
		assertTrue(test.getWaitListedStudents().size() == 2);

		// Dropping the waitlisted and then enrolled
		assertTrue(test.dropStudent(new Student("Teresa", "Beta", 23, "F",
				"999121663", "123 Pine", "Anaheim", "CA", "92592",
				"t.beta@yahoo.com", "714-123-1234", 126, "Big City University",
				"tbeta", "password")));
		assertTrue(test.getWaitListedStudents().size() == 1);
		assertTrue(test.dropStudent(new Student("Bill", "Apple", 26, "M",
				"994123243", "123 Pine", "Anaheim", "CA", "92592",
				"bill.apple@yahoo.com", "714-123-1234", 127,
				"Big City University", "bill.apple", "password")));
		assertTrue(test.getWaitListedStudents().size() == 0);

		assertTrue(test.dropStudent(new Student("Dave", "Johnson", 25, "M",
				"555121234", "123 Pine", "Anaheim", "CA", "92592",
				"dave.johnson@yahoo.com", "714-123-1234", 123,
				"Big City University", "djohnson", "password")));
		assertTrue(test.getEnrolledStudents().size() == 2);
		assertTrue(test.dropStudent(new Student("Monica", "Jackson", 53, "F",
				"999123213", "123 Pine", "Anaheim", "CA", "92592",
				"monica.jackson@yahoo.com", "714-123-1234", 125,
				"Big City University", "monica", "password")));
		assertTrue(test.getEnrolledStudents().size() == 1);
		assertTrue(test.dropStudent(new Student("Tim", "Smith", 34, "M",
				"123424123", "123 Pine", "Anaheim", "CA", "92592",
				"tim.smith@yahoo.com", "714-123-1234", 124,
				"Big City University", "tsmith", "password")));
		assertTrue(test.getEnrolledStudents().size() == 0);
	}

	@Test
	public void testDropStudent3() throws ParseException {
		CourseOffering test = setupClass();

		assertTrue(test.getEnrolledStudents().size() == 3);
		assertTrue(test.getWaitListedStudents().size() == 2);

		// Mixing it up
		// Waitlisted
		assertTrue(test.dropStudent(new Student("Teresa", "Beta", 23, "F",
				"999121663", "123 Pine", "Anaheim", "CA", "92592",
				"t.beta@yahoo.com", "714-123-1234", 126, "Big City University",
				"tbeta", "password")));
		assertTrue(test.getWaitListedStudents().size() == 1);

		// Enrolled
		assertTrue(test.dropStudent(new Student("Dave", "Johnson", 25, "M",
				"555121234", "123 Pine", "Anaheim", "CA", "92592",
				"dave.johnson@yahoo.com", "714-123-1234", 123,
				"Big City University", "djohnson", "password")));
		assertTrue(test.getEnrolledStudents().size() == 2);
		assertTrue(test.dropStudent(new Student("Monica", "Jackson", 53, "F",
				"999123213", "123 Pine", "Anaheim", "CA", "92592",
				"monica.jackson@yahoo.com", "714-123-1234", 125,
				"Big City University", "monica", "password")));
		assertTrue(test.getEnrolledStudents().size() == 1);

		// Waitlisted
		assertTrue(test.dropStudent(new Student("Bill", "Apple", 26, "M",
				"994123243", "123 Pine", "Anaheim", "CA", "92592",
				"bill.apple@yahoo.com", "714-123-1234", 127,
				"Big City University", "bill.apple", "password")));
		assertTrue(test.getWaitListedStudents().size() == 0);

		// Enrolled
		assertTrue(test.dropStudent(new Student("Tim", "Smith", 34, "M",
				"123424123", "123 Pine", "Anaheim", "CA", "92592",
				"tim.smith@yahoo.com", "714-123-1234", 124,
				"Big City University", "tsmith", "password")));
		assertTrue(test.getEnrolledStudents().size() == 0);
	}

	public void testDropStudentNotFound() throws ParseException {
		CourseOffering test = setupClass();

		assertTrue(test.getEnrolledStudents().size() == 3);
		assertTrue(test.getWaitListedStudents().size() == 2);

		// Not going to find a student with this ID
		assertFalse(test.dropStudent(new Student("Teresa", "Beta", 23, "F",
				"999121663", "123 Pine", "Anaheim", "CA", "92592",
				"t.beta@yahoo.com", "714-123-1234", 300, // <================================
				"Big City University", "tbeta", "password")));
		assertTrue(test.getEnrolledStudents().size() == 3);
		assertTrue(test.getWaitListedStudents().size() == 2);
	}

	@Test
	public void testIsStudentEnrolled() throws ParseException {
		CourseOffering test = setupClass();

		// Enrolled student
		assertTrue(test.isStudentEnrolled(new Student("Dave", "Johnson", 25,
				"M", "555121234", "123 Pine", "Anaheim", "CA", "92592",
				"dave.johnson@yahoo.com", "714-123-1234", 123,
				"Big City University", "djohnson", "password")));

		// Waitlisted student
		assertFalse(test.isStudentEnrolled(new Student("Teresa", "Beta", 23,
				"F", "999121663", "123 Pine", "Anaheim", "CA", "92592",
				"t.beta@yahoo.com", "714-123-1234", 126, "Big City University",
				"tbeta", "password")));
	}

	@Test
	public void testIsStudentWaitlisted() throws ParseException {
		CourseOffering test = setupClass();

		// Enrolled student
		assertFalse(test.isStudentWaitlisted(new Student("Dave", "Johnson", 25,
				"M", "555121234", "123 Pine", "Anaheim", "CA", "92592",
				"dave.johnson@yahoo.com", "714-123-1234", 123,
				"Big City University", "djohnson", "password")));

		// Waitlisted student
		assertTrue(test.isStudentWaitlisted(new Student("Teresa", "Beta", 23,
				"F", "999121663", "123 Pine", "Anaheim", "CA", "92592",
				"t.beta@yahoo.com", "714-123-1234", 126, "Big City University",
				"tbeta", "password")));
	}

	@Test
	public void testWriteln() throws ParseException {
		CourseOffering test = setupClass();

		assertTrue(test.writeln().compareTo("300,11/21/2014,11/22/2014,3") == 0);
	}

	@Test
	public void testToString() throws ParseException {
		CourseOffering test = setupClass();

		assertTrue(test
				.toString()
				.compareTo(
						"Start Date: 11/21/2014,End Date: 11/22/2014 3/3 Students Enrolled 2 Waitlisted Students") == 0);
	}
}
