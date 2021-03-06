import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/*
 * // CourseOffering Class
 * // Class which describes an individual offering of a course. 
 */
public class CourseOffering {
	/*
	 * // Member variables
	 */
	// make sure to update the CourseOffering(string) and writeLine() functions when
	// adding members
	private String courseOfferingID;
	private Date courseStartDate;
	private Date courseEndDate;
	private int maxStudents;
	private ArrayList<Student> enrolledStudents;
	private ArrayList<Student> waitlistedStudents;

	static final DateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");

	/*
	 * // Constructors
	 */

	CourseOffering(String courseOfferingID, Date courseStartDate,
			Date courseEndDate, int maxStudents,
			ArrayList<Student> enrolledStudents,
			ArrayList<Student> waitlistedStudents) {
		setCourseOfferingID(courseOfferingID);
		setCourseStartDate(courseStartDate);
		setCourseEndDate(courseEndDate);
		setMaxStudents(maxStudents);
		setEnrolledStudents(enrolledStudents);
		setWaitListedStudents(waitlistedStudents);
	}

	CourseOffering(String s) {
		// Break the string into different tokens for each member
		List<String> memberListString = Arrays.asList(s.split(","));

		// Check to make sure we have enough tokens
		if (memberListString.size() != 5)
			throw new IllegalArgumentException(
					"String did not contain the correct number of tokens.");

		try {
			// Discard the courseID - memberListString.get(0)
			setCourseOfferingID(memberListString.get(1));
			setCourseStartDate(DATE_FORMAT.parse(memberListString.get(2)));
			setCourseEndDate(DATE_FORMAT.parse(memberListString.get(3)));
			setMaxStudents(Integer.parseInt(memberListString.get(4)));
			setEnrolledStudents(null);
			setWaitListedStudents(null);
		} catch (Exception e) {
			throw new IllegalArgumentException(
					"Parameter string was not an expected format.");
		}
	}

	/*
	 * // Set functions
	 */

	void setCourseOfferingID(String courseOfferingID) {
		this.courseOfferingID = courseOfferingID;
	}

	void setCourseStartDate(Date courseStartDate) {
		this.courseStartDate = courseStartDate;
	}

	void setCourseEndDate(Date courseEndDate) {
		this.courseEndDate = courseEndDate;
	}

	void setMaxStudents(int maxStudents) {
		// Should never set the max students to be less than the number
		// currently enrolled.
		// If changing the number enrolled, move to waitlist first then decrease
		// the student count
		if (enrolledStudents != null && maxStudents < enrolledStudents.size())
			throw new IllegalArgumentException(
					"Max students can never be less than the number of enrolled students.");

		this.maxStudents = maxStudents;
	}

	void setEnrolledStudents(ArrayList<Student> enrolledStudents) {
		// Check for a null list
		if (enrolledStudents == null)
			enrolledStudents = new ArrayList<Student>();

		// Check to see if we are exceeding the max number of students
		if (enrolledStudents.size() > maxStudents)
			throw new IllegalArgumentException(
					"Too many students attempted to be enrolled.");

		this.enrolledStudents = enrolledStudents;
	}

	void setWaitListedStudents(ArrayList<Student> waitlistedStudents) {
		// Check for a null list
		if (waitlistedStudents != null)
			this.waitlistedStudents = waitlistedStudents;
		else
			this.waitlistedStudents = new ArrayList<Student>();
	}

	/*
	 * // Get functions
	 */

	String getCourseOfferingID() {
		return this.courseOfferingID;
	}

	Date getCourseStartDate() {
		return this.courseStartDate;
	}

	Date getCourseEndDate() {
		return this.courseEndDate;
	}

	int getMaxStudents() {
		return this.maxStudents;
	}

	ArrayList<Student> getEnrolledStudents() {
		return this.enrolledStudents;
	}

	ArrayList<Student> getWaitListedStudents() {
		return this.waitlistedStudents;
	}

	/*
	 * // Member functions
	 */

	// enrollStudent
	// Enrolls a student. If there are no more spaces left it will add the
	// student to the waitlist
	void enrollStudent(Student enrollStudent) {
		// Check to see if the student is already enrolled or waitlisted
		// If the class is full add to the waitlist
		if (enrolledStudents.size() >= maxStudents)
			waitlistedStudents.add(enrollStudent);
		else
			enrolledStudents.add(enrollStudent);
	}
	
	// enrollStudent
	// Enrolls or waitlists a student. enrollStudent(Student) shold normally be used instead. This is here for forcing during loading from a file.
	void enrollStudent(Student enrollStudent, Boolean isWaitlist) {
		if (isWaitlist)
			waitlistedStudents.add(enrollStudent);
		else
			enrolledStudents.add(enrollStudent);
	}	

	// dropStudent
	// drops any student listed matching by ID in both enrolled and waitlisted
	boolean dropStudent(Student dropStudent) {
		// Search the waitlisted & enrolled students
		return removeFromList(waitlistedStudents, dropStudent)
				|| removeFromList(enrolledStudents, dropStudent);
	}

	// removeFromList
	// Removes a student from a list passed in
	private boolean removeFromList(ArrayList<Student> studentList,
			Student studentToRemove) {
		boolean found = false;

		for (int i = 0; i < studentList.size(); i++) {
			// Is there a match in the list for the ID
			if (studentList.get(i).getID() == studentToRemove.getID()) {
				studentList.remove(i);
				found = true;
				// We removed one from the list so we need to decrease the
				// counter
				i--;
			}
		}
		return found;
	}

	// isStudentEnrolled
	// Returns true if a matching student ID is found in the enrolled students
	boolean isStudentEnrolled(Student studentToFind) {
		return isInList(enrolledStudents, studentToFind);
	}

	// isStudentWaitListed
	// Returns true if a matching student ID is found in the waitlisted students
	boolean isStudentWaitlisted(Student studentToFind) {
		return isInList(waitlistedStudents, studentToFind);
	}

	// isInList
	// Returns true if a student is found in a list
	private boolean isInList(ArrayList<Student> studentList,
			Student studentToFind) {
		if (studentList != null)
			for (Student currentStudent : studentList) {
				if (currentStudent.getID() == studentToFind.getID())
					return true;
			}
		return false;
	}

	// writeln
	// Returns a comma delimited string containing all members of the class
	// except for the student lists
	String writeln() {
		return  // We don't have the course ID
				courseOfferingID + "," + DATE_FORMAT.format(courseStartDate)
				+ "," + DATE_FORMAT.format(courseEndDate) + "," + maxStudents;
	}

	// toString
	// Provides a user friendly string to display
	public String toString() {
		String s = "Start Date: " + DATE_FORMAT.format(courseStartDate) + ","
				+ "End Date: " + DATE_FORMAT.format(courseEndDate) + " ";

		// Get the number of enrolled students
		int count = 0;
		if (enrolledStudents != null)
			count = enrolledStudents.size();

		s = s.concat(count + "/" + maxStudents + " Students Enrolled");

		// Display number of waitlisted students
		if (waitlistedStudents != null && waitlistedStudents.size() > 0)
			s = s.concat(" " + waitlistedStudents.size()
					+ " Waitlisted Students");

		return s;
	}
}
