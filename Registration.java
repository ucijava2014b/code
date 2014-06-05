import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Comparator;
import java.util.Collections;


public class Registration {

	static Scanner input = new Scanner(System.in);

	static final String STUDENT_FILE = "Students.csv";
	static final String COURSE_OFFERING_FILE = "CourseOffering.csv";
	static final String COURSES_FILE = "Courses.csv";

	public static void main(String[] args) {
		//  Declare Variables
		ArrayList<Student> students = new ArrayList<Student>();
		ArrayList<Course> courses = new ArrayList<Course>();

		String loginSelection;
		String mainSelection;
		Student currentStudent;
		Student newStudent;
		String logoutSelection = new String("N");

		//  Read Data  
		//  TODO Call Util 


		//  Test Classes  
		//  REMOVE - once a seperate test class is written

		System.out.println("Registering Main");


		// Generate some a course and some class offerings

		try{
			
			students = Util.addStudents(students, STUDENT_FILE);

			courses = Util.addCourses(courses, COURSES_FILE);

			courses = Util.addCourseOffering(courses, COURSE_OFFERING_FILE);
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
							logoutSelection = input.next();
							System.out.println("");
						}
						else{
							System.out.println("Invalid selection");
						}
					} while (!(mainSelection.equals("3") && logoutSelection.equals("Y")));
				}
			}
			else if(loginSelection.equals("2")){
				newStudent = newUser(students);
				if(newStudent != null){
					students.add(newStudent);
				}
			}    
			else if(loginSelection.equals("3")){
				System.out.println("Exiting the Registration System");
			}
			else {
				System.out.println("Invalid selection");
			}
		} while (! loginSelection.equals("3"));

		//  Save Data
		//  TODO Call Util

	}

	private static String loginMenu(){
		System.out.println("==================================");
		System.out.println("Welcome to the Registration System");
		System.out.println("Please select from the following:");
		System.out.println("1. Login");
		System.out.println("2. Request Access");
		System.out.println("3. Exit");
		System.out.print("Enter the number of you request:  "); 
		String selection = input.next();
		System.out.println("");
		return selection;
	}

	private static String mainMenu(){
		System.out.println("==================================");
		System.out.println("Main Menu");
		System.out.println("Please select from the following:");
		System.out.println("1. Display all available courses and add course");
		System.out.println("2. Display all enrolled courses and drop course");
		System.out.println("3. Logout");
		System.out.print("Enter the number of you request:  "); 
		String selection = input.next();
		System.out.println("");
		return selection;
	}


	private static void displayAvailableCourses(Student currentStudent,ArrayList<Course> courses){
		Collections.sort(courses, new courseCompare());
		ArrayList<CourseOffering> offeringNum = new ArrayList<CourseOffering>();
		int i = 0;
		
		// Loop through all the courses and print them
		for(Course course : courses) {
			System.out.println(course.toString());
			
			// If a course has available offerings display them
			if(course.getCourseOfferings() != null){
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
		}
		System.out.println("Enter the number of the course offering you would like to add");
		System.out.print("or enter zero to return to main menu:  ");
		String selection = input.next();
		int optionNum = Integer.parseInt(selection);
		if(optionNum > 0 && optionNum <= i){
			offeringNum.get(optionNum-1).enrollStudent(currentStudent);
		}
	}

	private static void displayEnrolledCourses(Student currentStudent,ArrayList<Course> courses){
		Collections.sort(courses, new courseCompare());
		ArrayList<CourseOffering> offeringNum = new ArrayList<CourseOffering>();
		int i = 0;
		for(Course course : courses) {
			if(course.getCourseOfferings() != null){
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
		}
		System.out.println("Enter the number of the course offering you would like to drop");
		System.out.print("or enter zero to return to main menu:  ");
		String selection = input.next();
		try {
			int optionNum = Integer.parseInt(selection);
			if(optionNum > 0 && optionNum <= i){
				offeringNum.get(optionNum-1).dropStudent(currentStudent);
			}
		} catch (NumberFormatException e) {

		}

	}


	private static Student studentLogin(ArrayList<Student> students){
		System.out.print("Username: ");
		String userName = input.next();
		System.out.print("Password: ");
		String password = input.next();
		Student loginStudent = null;
		for(Student studentI : students) {
			if(userName.equals(studentI.getUsername().replace("\r","")) && password.equals(studentI.getPw().replace("\r",""))){
				loginStudent = studentI;
			}
		}
		if(loginStudent == null ){
			System.out.println("Login Failed");
		}
		return loginStudent;
	}


	private static Student newUser(ArrayList<Student> students){
		Student newStudent = null;
		String usr = null;
		boolean failUsrCheck;
		System.out.println("Enter first name:");
		String firstName = input.next();
		System.out.println("Enter last name:");
		String lastName = input.next();       
		System.out.println("Enter age:");
		String age = input.next();               
		System.out.println("Enter gender:");        
		String gender = input.next();
		System.out.println("Enter SSN:");     
		String ssn = input.next();
		System.out.println("Enter address:");
		String address = input.next();
		System.out.println("Enter city:");
		String city = input.next();
		System.out.println("Enter state:");
		String state = input.next();
		System.out.println("Enter zip:");
		String zip = input.next();
		System.out.println("Enter email:");
		String email = input.next(); 
		System.out.println("Enter phone:");
		String phone = input.next();
		System.out.println("Enter ID:");
		String nId = input.next(); 
		System.out.println("Enter college:");
		String collg = input.next();
		do{
			failUsrCheck = false;
			System.out.println("Enter user name:");
			usr = input.next(); 
			for(Student studentI : students) {
				if(usr.equals(studentI.getUsername().replace("\r",""))){
					failUsrCheck = true;
				}
			}
			if(failUsrCheck){
				System.out.println("User name already used.");
			}
		}while (failUsrCheck);
		System.out.println("Enter password:");
		String pw = input.next();
		try{
			newStudent = new Student(firstName, lastName, Integer.parseInt(age), gender, ssn, address, 
					city, state, zip, email, phone, Integer.parseInt(nId), collg, usr, pw);
		} catch (NumberFormatException e) {
			newStudent = null;
			System.out.println("Login Failure.");
		}
		return newStudent;
	}

}
//  Create course comparison class to compare by course name
class courseCompare implements Comparator<Course>{
	public int compare(Course c1, Course c2) {
		return c1.getCourseName().compareTo(c2.getCourseName());
	}
}