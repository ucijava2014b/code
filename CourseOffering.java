import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class CourseOffering {
	/*
	// Member variables
	*/
	private String courseOfferingID; 
	private Date courseStartDate;
	private Date courseEndDate;
	private int maxStudents;
	private ArrayList<Student> enrolledStudents;
	private ArrayList<Student> waitlistedStudents;
	
	static final DateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");
		
	/*
	// Constructors
	*/
	
	CourseOffering (String courseOfferingID,
					Date courseStartDate,
					Date courseEndDate,
					int maxStudents,
					ArrayList<Student> enrolledStudents,
					ArrayList<Student> waitlistedStudents)
	{
		setCourseOfferingID(courseOfferingID);
		setCourseStartDate(courseStartDate);
		setCourseEndDate(courseEndDate);
		setMaxStudents(maxStudents);
		setEnrolledStudents(enrolledStudents);
		setWaitListedStudents(waitlistedStudents);
	}

	CourseOffering (String s)
	{
		// Break the string into different tokens for each member		
		List<String> memberListString = Arrays.asList(s.split(","));
			
		// Check to make sure we have enough tokens
		if(memberListString.size() != 4)
			throw new IllegalArgumentException("String did not contain the correct number of tokens.");
			
		try{
			setCourseOfferingID(memberListString.get(0));
			setCourseStartDate(DATE_FORMAT.parse(memberListString.get(1)));
			setCourseEndDate(DATE_FORMAT.parse(memberListString.get(2)));
			setMaxStudents(Integer.parseInt(memberListString.get(3)));
		}
		catch(Exception e) {
			throw new IllegalArgumentException("Parameter string was not an expected format.");
		}		
	}
	
	/*
	// Set functions
	*/

	void setCourseOfferingID(String courseOfferingID) {
		this.courseOfferingID = courseOfferingID;
	}
	
	void setCourseStartDate(Date courseStartDate) {
		this.courseStartDate = courseStartDate;
	}
	
	void setCourseEndDate(Date courseEndDate){
		this.courseEndDate = courseEndDate;
	}
	
	void setMaxStudents(int maxStudents){
		// Should never set the max students to be less than the number currently enrolled. 
		// If changing the number enrolled, move to waitlist first then decrease the student count
		if(enrolledStudents != null && maxStudents < enrolledStudents.size())
			throw new IllegalArgumentException("Max students can never be less than the number of enrolled students.");
				
		this.maxStudents = maxStudents;
	}
	
	void setEnrolledStudents(ArrayList<Student> enrolledStudents){
		// Check to see if we are exceeding the max number of students
		if(enrolledStudents != null && enrolledStudents.size() > maxStudents)
			throw new IllegalArgumentException("Too many students attempted to be enrolled.");
		
		this.enrolledStudents = enrolledStudents;
	}
	
	void setWaitListedStudents(ArrayList<Student> waitlistedStudents){
		this.waitlistedStudents = waitlistedStudents;
	}
	
	/*
	// Get functions
	*/

	String getCourseOfferingID() {
		return this.courseOfferingID;
	}
	
	Date getCourseStartDate() {
		return this.courseStartDate;
	}
	
	Date getCourseEndDate(){
		return this.courseEndDate;
	}
	
	int getMaxStudents(){
		return this.maxStudents;
	}
	
	ArrayList<Student> getEnrolledStudents(){
		return this.enrolledStudents;
	}
	
	ArrayList<Student> getWaitListedStudents(){
		return this.waitlistedStudents;
	}
	
	/*
	// Member functions
	*/
	
	// enrollStudent
	// Enrolls a student. If there are no more spaces left it will add the student to the waitlist
	void enrollStudent(Student enrollStudent)
	{
		// Check to see if the student is already enrolled or waitlisted
		if(isStudentEnrolled(enrollStudent) || isStudentWaitlisted(enrollStudent)) {
			throw new IllegalArgumentException("Student should never be enrolled twice or enrolled if on waitlist"); 
		}
		
		// If the class is full add to the waitlist
		if(enrolledStudents.size() >= maxStudents)
			waitlistedStudents.add(enrollStudent);
		else
			enrolledStudents.add(enrollStudent);
	}
	
	// dropStudent
	// drops any student listed matching by ID in both enrolled and waitlisted
	boolean dropStudent(Student dropStudent)
	{
		// Search the waitlisted & enrolled students
		return removeFromList(waitlistedStudents, dropStudent) || removeFromList(enrolledStudents, dropStudent);
	}

	// removeFromList
	// Removes a student from a list passed in
	private boolean removeFromList(ArrayList<Student> studentList, Student studentToRemove) {
		boolean found = false;
		
		for(int i = 0; i < studentList.size(); i++)
		{
			// Is there a match in the list for the ID
			if(studentList.get(i).getId() == studentToRemove.getId()) {
				studentList.remove(i);
				found = true;
				// We removed one from the list so we need to decrease the counter
				i--;
			}
		}
		
		return found;
	}
	
	// isStudentEnrolled
	// Returns true if a matching student ID is found in the enrolled students
	boolean isStudentEnrolled(Student studentToFind)
	{
		return isInList(enrolledStudents, studentToFind);		
	}

	// isStudentWaitListed
	// Returns true if a matching student ID is found in the waitlisted students
	boolean isStudentWaitlisted(Student studentToFind)
	{
		return isInList(waitlistedStudents, studentToFind);
	}
	
	// isInList
	// Returns true if a student is found in a list
	private boolean isInList(ArrayList<Student> studentList, Student studentToFind) {
		for(Student currentStudent : studentList) {
			if(currentStudent.getId() == studentToFind.getId())
				return true;
		}
		return false;	
	}
			
	// writeln
	// Returns a comma delimited string containing all members of the class except for the student lists
	String writeln() {
		return 	DATE_FORMAT.format(courseStartDate) + "," +
				DATE_FORMAT.format(courseEndDate) + "," +
				maxStudents;
	}
	
	// toString
	// Returns a comma delimited string containing all members of the class except for the student lists
	public String toString() {  
        return writeln();
    }
}
