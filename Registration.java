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
    
    public static void main(String[] args) throws ParseException{
        //  Declare Variables
        ArrayList<Student> students = new ArrayList<Student>();
        ArrayList<Course> courses = new ArrayList<Course>();

        int loginSelection;
        int mainSelection;
        Student currentStudent;
        Student newStudent;
        String logoutSelection = new String("N");
        
        //  Read Data  
        //  TODO Call Util 
        
        
        //  Test Classes  
        //  REMOVE - once a seperate test class is written
        students.add(0, new Student());
        System.out.println("Registrating Main");
	//        System.out.println(Util.test());
        System.out.println(students.get(0).toString());
        
        // Generate some a course and some class offerings

        ArrayList<CourseOffering> CS510Offerings = new ArrayList<CourseOffering>();
	String CourseOfferingFile = "CourseOffering.csv";
	String CoursesFile        = "Courses.csv";

	CS510Offerings = Util.addCourseOffering(CS510Offerings,CourseOfferingFile);
	courses        = Util.addCourses(courses,CoursesFile);

        System.out.println(courses.get(0).writeln());
        System.out.println(courses.get(1).toString());


        //  Login Prompt
        do{   
            loginSelection = loginMenu();
            if(loginSelection == 1){
                currentStudent = studentLogin(students);
                //  Main Menu Prompt
                do{
                    mainSelection = mainMenu();
                    if (mainSelection == 1){
                        displayAvailableCourses(courses);
                    }
                    else if(mainSelection == 2){
                        displayEnrolledCourses(currentStudent,courses);
                    }
                    else if(mainSelection == 3){
                        System.out.print("Please confirm you would like to logout (Y/N):  ");
                        logoutSelection = input.next();
                        System.out.println("");
                    }
                    else{
                        System.out.println("Invalid selection");
                    }
                } while (!(mainSelection == 3 && logoutSelection.equals("Y")));
            }
            else if(loginSelection == 2){
                newStudent = newUser(students);
                if(newStudent != null){
                    students.add(newStudent);
                }
            }    
            else if(loginSelection == 3){
                System.out.println("Exiting the Registration System");
            }
            else {
                System.out.println("Invalid selection");
            }
        } while (loginSelection != 3);
        
        //  Save Data
        //  TODO Call Util

    }
    
    private static int loginMenu(){
        System.out.println("==================================");
        System.out.println("Welcome to the Registration System");
        System.out.println("Please select from the following:");
        System.out.println("1. Login");
        System.out.println("2. Request Access");
        System.out.println("3. Exit");
        System.out.print("Enter the number of you request:  "); 
        int selection = input.nextInt();
        System.out.println("");
        return selection;
    }
    
    private static int mainMenu(){

        System.out.println("==================================");
        System.out.println("Main Menu");
        System.out.println("Please select from the following:");
        System.out.println("1. Display all available courses");
        System.out.println("2. Display all enrolled courses");
        System.out.println("3. Logout");
        System.out.print("Enter the number of you request:  "); 
        int selection = input.nextInt();
        System.out.println("");
        return selection;
    }
    
    
    private static void displayAvailableCourses(ArrayList<Course> courses){
        Collections.sort(courses, new courseCompare());
        for(Course course : courses) {
            System.out.println(course.writeln());
            if(course.getCourseOfferings() != null){
                for(CourseOffering offering : course.getCourseOfferings()) {
                    System.out.println(offering.writeln());
                }
            }
        }
    }
    
    private static void displayEnrolledCourses(Student currentStudent,ArrayList<Course> courses){
        System.out.println("Display Enrolled Courses");
        System.out.println(currentStudent.toString());
        displayAvailableCourses(courses);
    //  TODO ADD STUDENT LOGIN 
    }
    
    
    private static Student studentLogin(ArrayList<Student> students){
        System.out.println("Student Login");
        return students.get(0);
    //  TODO ADD STUDENT LOGIN 
    }
    
    
    private static Student newUser(ArrayList<Student> students){
        Student newStudent = null;
        System.out.println("Request new user access");
        return newStudent;
    //  TODO ADD NEW USER ACCESS
    }

}
//  Create course comparison class to compare by course name
class courseCompare implements Comparator<Course>{
    public int compare(Course c1, Course c2) {
        return c1.getCourseName().compareTo(c2.getCourseName());
    }
}
