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
	Date courseStartDate;
	Date courseEndDate;
	int maxStudents;
	ArrayList<Student> enrolledStudents;
	ArrayList<Student> waitlistedStudents;
	
	static final DateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");
		
	/*
	// Constructors
	*/
	
	CourseOffering (Date courseStartDate,
					Date courseEndDate,
					int maxStudents,
					ArrayList<Student> enrolledStudents,
					ArrayList<Student> waitlistedStudents)
	{
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
		if(memberListString.size() != 3)
			throw new IllegalArgumentException("String did not contain the correct number of tokens.");
			
		try{
			setCourseStartDate(DATE_FORMAT.parse(memberListString.get(0)));
			setCourseEndDate(DATE_FORMAT.parse(memberListString.get(1)));
			setMaxStudents(Integer.parseInt(memberListString.get(2)));
		}
		catch(Exception e) {
			throw new IllegalArgumentException("Parameter string was not an expected format.");
		}		
	}
	
	/*
	// Set functions
	*/

	void setCourseStartDate(Date courseStartDate) {
		this.courseStartDate = courseStartDate;
	}
	
	void setCourseEndDate(Date courseEndDate){
		this.courseEndDate = courseEndDate;
	}
	
	void setMaxStudents(int maxStudents){
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
	
	// Writeln
	// Returns a comma delimited string containing all members of the class except for the student lists
	String writeln() {
		return 	DATE_FORMAT.format(courseStartDate) + "," +
				DATE_FORMAT.format(courseEndDate) + "," +
				maxStudents;
	}
	
	public String toString() {  
        return writeln();
    }
}
