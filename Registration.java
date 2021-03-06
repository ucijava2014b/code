import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Comparator;
import java.util.Collections;

/*
 * // Registration Class
 * // Main class for the application 
 */
public class Registration {

	/*
	 * // Member variables
	 */
	static Scanner input = new Scanner(System.in);
	static final String STUDENT_FILE         = "Student.csv";
	static final String COURSE_OFFERING_FILE = "CourseOffering.csv";
	static final String COURSES_FILE        = "Course.csv";
	static final String REGISTRATION_FILE   = "StudenttoCourseMapping.csv";

	static Student currentStudent;
	static ArrayList<Student> students = new ArrayList<Student>();
	static ArrayList<Course> courses = new ArrayList<Course>();

	/*
	 * // Member functions
	 */
	
	// main
	// The main entry point for the application
	public static void main(String[] args) {

		//  Declare Variables
		String loginSelection;
		String mainSelection;
		Student newStudent;
		String logoutSelection = new String("N");
		//  Read Data  
		// Generate some a course and some class offerings

		try{

			students = Util.addStudents(students, STUDENT_FILE);

			courses = Util.addCourses(courses, COURSES_FILE);

			courses = Util.addCourseOffering(courses, COURSE_OFFERING_FILE);
			
			courses = Util.readEnrollment(courses, students, REGISTRATION_FILE);
		}
		catch (Exception e) {
			// Something went horribly wrong
			System.out.println("Error loading configuration.");
			e.printStackTrace();
			return;
		}

		//  Login Prompt
		do{   
			loginSelection = loginMenu();
			if(loginSelection.equals("1")){
				currentStudent = studentLogin(students);
				//  Main Menu Prompt
				if(currentStudent != null){
					do{
						mainSelection = mainMenu();
						if (mainSelection.equals("1")){
							displayAvailableCourses(currentStudent,courses);
						}
						else if(mainSelection.equals("2")){
							displayEnrolledCourses(currentStudent,courses);
						}
						else if(mainSelection.equals("3")){
							System.out.print("Please confirm you would like to logout (Y/N):  ");
							logoutSelection = input.nextLine();
							System.out.println("");
						}
						else{
							System.out.println("Invalid selection");
						}
					} while (!(mainSelection.equals("3") && logoutSelection.toUpperCase().equals("Y")));
				}
			}
			else if(loginSelection.equals("2")){
				newStudent = newUser(students);
				if(newStudent != null){
					students.add(newStudent);
				}
			}    
			else if(loginSelection.equals("3")){
				// Save enrollment details before exiting
				try {
					Util.saveEnrollment(courses, REGISTRATION_FILE);
					Util.saveStudents(students, STUDENT_FILE);
				} catch (IOException e){
					System.out.println("Error saving configuration.");
					e.printStackTrace();
				}
				System.out.println("Exiting the Registration System");
			}
			else {
				System.out.println("Invalid selection");
			}
		} while (! loginSelection.equals("3"));
	}

	// loginMenu
	// Displays the login menu to the user
	private static String loginMenu(){
		System.out.println("==================================");
		System.out.println("Welcome to the Registration System");
		System.out.println("Please select from the following:");
		System.out.println("1. Login");
		System.out.println("2. Request Access");
		System.out.println("3. Exit");
		System.out.print("Enter the number of you request:  "); 
		String selection = input.nextLine();
		System.out.println("");
		return selection;
	}

	// mainMenu
	// Displays the main menu to the user
	private static String mainMenu(){
		System.out.println("==================================");
		System.out.println("Main Menu");
		System.out.println("Please select from the following:");
		System.out.println("1. Display all available courses and add course");
		System.out.println("2. Display all enrolled courses and drop course");
		System.out.println("3. Logout");
		System.out.print("Enter the number of you request:  "); 
		String selection = input.nextLine();
		System.out.println("");
		return selection;
	}

	// displayAvailableCourses
	// Displays all courses which are available to the student but they are not enrolled in
	private static void displayAvailableCourses(Student currentStudent,ArrayList<Course> courses){
		Collections.sort(courses, new courseCompare());
		ArrayList<CourseOffering> offeringNum = new ArrayList<CourseOffering>();
		int i = 0;

		// Loop through all the courses and print them
		for(Course course : courses) {
			System.out.println(course.toString());

			// If a course has available offerings display them
			for(CourseOffering offering : course.getCourseOfferings()) {
				// if the current student is not enrolled, then display the offering
				if(!offering.isStudentEnrolled(currentStudent) && !offering.isStudentWaitlisted(currentStudent)) {
					System.out.print("\t"+(i+1) + ":  ");
					// Display if they are enrolling vs. adding to waitlist
					if(offering.getEnrolledStudents().size() < offering.getMaxStudents())
						System.out.print("Add->");
					else
						System.out.print("Waitlist->");
					System.out.println(offering.toString());
					offeringNum.add(i, offering);
					i++;
				}
			}
		}

		// Check to see if there are no available courses
		if(offeringNum.size() == 0) {
			System.out.println("No available courses.");
			return;    
		}

		// Prompt the user for their selection
		System.out.println("Enter the number of the course offering you would like to add");
		System.out.print("or enter zero to return to main menu:  ");
		String selection = input.nextLine();
		try{
			int optionNum = Integer.parseInt(selection);
			if(optionNum > 0 && optionNum <= i){
				offeringNum.get(optionNum-1).enrollStudent(currentStudent);
			}    
		}
		catch (NumberFormatException e) {
			System.out.println("Invalid selection. Returning to main menu.");
		}
	}

	// displayEnrolledCourses
	// Displays all the courses the student is enrolled or waitlisted
	private static void displayEnrolledCourses(Student currentStudent,ArrayList<Course> courses){
		Collections.sort(courses, new courseCompare());
		ArrayList<CourseOffering> offeringNum = new ArrayList<CourseOffering>();
		int i = 0;

		// Loop through the enrolled courses
		for(Course course : courses) {
			for(CourseOffering offering : course.getCourseOfferings()) {
				if(offering.isStudentEnrolled(currentStudent) || offering.isStudentWaitlisted(currentStudent)){
					System.out.println(course.toString());
					System.out.print("\t" + (i+1) + ":  ");
					// Display if they are dropping vs. removing from waitlist
					if(offering.isStudentEnrolled(currentStudent))
						System.out.print("Drop->");
					else
						System.out.print("Remove from waitlist->");
					System.out.println(offering.toString());
					offeringNum.add(i, offering);
					i++;
				}
			}
		}

		// Check to see if the list of courses is empty
		if(offeringNum.size() == 0){
			System.out.println("No enrolled or waitlisted courses.");
			return;
		}

		// Prompt the user for their selection
		System.out.println("Enter the number of the course offering you would like to drop");
		System.out.print("or enter zero to return to main menu:  ");
		String selection = input.nextLine();
		try {
			int optionNum = Integer.parseInt(selection);
			if(optionNum > 0 && optionNum <= i){
				offeringNum.get(optionNum-1).dropStudent(currentStudent);
			}
		} catch (NumberFormatException e) {
			System.out.println("Invalid selection. Returning to main menu.");
		}

	}

	// studentLogin
	// Prompts the user for their username and password and confirms it matches the database
	private static Student studentLogin(ArrayList<Student> students){
		System.out.print("Username: ");
		String userName = input.nextLine();
		System.out.print("Password: ");
		String password = input.nextLine();
		Student loginStudent = null;
		for(Student studentI : students) {
			if(userName.equals(studentI.getUsername().replace("\r","")) && password.equals(studentI.getPassword().replace("\r",""))){
				loginStudent = studentI;
			}
		}
		if(loginStudent == null ){
			System.out.println("Login Failed");
		}
		return loginStudent;
	}

	// newUser
	// prompts the user for all information required to become a student user
	private static Student newUser(ArrayList<Student> students){
		Student newStudent = new Student();
	
		do{
			try{
				System.out.println("Enter first name:");
				newStudent.setFirstName(input.nextLine());
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} while(newStudent.getFirstName() == null);

		do{
			try{
				System.out.println("Enter last name:");
				newStudent.setLastName(input.nextLine());
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} while(newStudent.getLastName() == null);
		
		do{
			try{
				System.out.println("Enter age:");
				newStudent.setAge(Integer.parseInt(input.nextLine()));
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} while(newStudent.getAge() == -1);

		do{
			try{
				System.out.println("Enter gender(M/F):");
				newStudent.setGender(input.nextLine());
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} while(newStudent.getGender() == null);

		do{
			try{
				System.out.println("Enter SSN:");
				newStudent.setSSN(input.nextLine());
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} while(newStudent.getSSN() == null);
		        
		do{
			try{
				System.out.println("Enter address:");
				newStudent.setAddress(input.nextLine());
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} while(newStudent.getAddress() == null);
		
		do{
			try{
				System.out.println("Enter city:");
				newStudent.setCity(input.nextLine());
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} while(newStudent.getCity() == null);		

		do{
			try{
				System.out.println("Enter state(two character abbreviation):");
				newStudent.setState(input.nextLine());
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} while(newStudent.getState() == null);

		do{
			try{
				System.out.println("Enter zip:");
				newStudent.setZip(input.nextLine());
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} while(newStudent.getZip() == null);

		do{
			try{
				System.out.println("Enter email:");
				newStudent.setEmail(input.nextLine());
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} while(newStudent.getEmail() == null);
		
		do{
			try{
				System.out.println("Enter phone:");
				String temp = input.nextLine();
				// remove all non-numeric characters
				temp = temp.replaceAll("[^\\d.]", "");
				newStudent.setPhone(temp);
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} while(newStudent.getPhone() == null);

		do{
			try{
				System.out.println("Enter ID (Numeric):");
				newStudent.setID(Integer.parseInt(input.nextLine()));
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} while(newStudent.getID() == -1);
		
		do{
			try{
				System.out.println("Enter college:");
				newStudent.setCollege(input.nextLine());
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} while(newStudent.getCollege() == null);

		String usr = null;
		boolean failUsrCheck = false;
		
		do{

			System.out.println("Enter user name:");
			usr = input.nextLine(); 
			for(Student studentI : students) {
				if(usr.equals(studentI.getUsername().replace("\r",""))){
					failUsrCheck = true;
				}
			}
			if(failUsrCheck){
				System.out.println("User name already used.");
			}
		}while (failUsrCheck);
		newStudent.setUsername(usr);

		// Display the block of text with the password requirements
		System.out.println(newStudent.PASSWORD_REQUIREMENTS);
		do{
			try{
				System.out.println("Enter password:");
				newStudent.setPassword(input.nextLine());
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} while(newStudent.getPassword() == null);
		
		return newStudent;
	}

}

// courseCompare
//  Create course comparison class to compare by course name
class courseCompare implements Comparator<Course>{
	public int compare(Course c1, Course c2) {
		return c1.getCourseName().compareTo(c2.getCourseName());
	}
}