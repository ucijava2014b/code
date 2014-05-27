import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Course {
	/*
	// Member variables
	*/
	
	String courseID;
	String courseName;
	int courseNumber;
	String courseOverview;
	String department;
	
	ArrayList<CourseOffering> courseOfferings = new ArrayList<CourseOffering>();
	
	/*
	// Constructors
	*/
	
	// Constructor
	public Course(String courseID, 
				  String courseName, 
				  int courseNumber, 
				  String courseOverview, 
				  String department, 
				  ArrayList<CourseOffering> courseOfferings) {
		setCourseID(courseID);
		setCourseName(courseName);
		setCourseNumber(courseNumber);
		setCourseOverview(courseOverview);
		setDepartment(department);
		setCourseOfferings(courseOfferings);		
	}
	
	// Constructor which takes a comma delimited string in the same format as writeln
	// Does not set the course offering
	public Course(String s) {
		// Break the string into different tokens for each member		
		List<String> memberListString = Arrays.asList(s.split(","));
			
		// Check to make sure we have enough tokens
		if(memberListString.size() != 5)
			throw new IllegalArgumentException("String did not contain the correct number of tokens.");
			
		try{
			setCourseID(memberListString.get(0));
			setCourseName(memberListString.get(1));
			setCourseNumber(Integer.parseInt(memberListString.get(2)));
			setCourseOverview(memberListString.get(3));
			setDepartment(memberListString.get(4));
		}
		catch(Exception e) {
			throw new IllegalArgumentException("Parameter string was not an expected format.");
		}
	}	
	
	/*
	// Set functions
	*/
	
	void setCourseID(String courseID){
		this.courseID = courseID;
	}
	
	void setCourseName(String courseName){
		this.courseName = courseName;
	}
	
	void setCourseNumber(int courseNumber) {
		this.courseNumber = courseNumber;
	}
	
	void setCourseOverview(String courseOverview){
		this.courseOverview = courseOverview;
	}
	
	void setDepartment(String department){
		this.department = department;
	}
	
	void setCourseOfferings(ArrayList<CourseOffering>  courseOfferings){
		this.courseOfferings = courseOfferings;
	}
		
	/*
	// Get functions
	*/
	
	String getCourseID(){
		return this.courseID;
	}
	
	String getCourseName(){
		return this.courseName;
	}
	
	int getCourseNumber() {
		return this.courseNumber;
	}
	
	String setCourseOverview(){
		return this.courseOverview;
	}
	
	String setDepartment(){
		return this.department;
	}
	
	ArrayList<CourseOffering> getCourseOfferings(){
		return this.courseOfferings;
	}
	
	/*
	// Member functions
	*/
	
	// removeCourseOffering
	// Removes a course offering from the list 
	boolean removeCourseOffering(CourseOffering courseOffer){
		for(CourseOffering course : courseOfferings) {
			// TODO
		}
		// didn't find the object to remove
		return false;
	}
	
	// Writeln
	// Returns a comma delimited string containing all members of the class except for the course offerings
	String writeln() {
		return courseID + "," +
			   courseName + "," +
			   courseNumber + "," +
			   courseOverview + "," +
			   department;
	}

    public String toString() {  
        return writeln();
    }
}