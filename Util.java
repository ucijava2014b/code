import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.ArrayList;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Locale;
import java.util.Collections;


public class Util {

    
    public static ArrayList<CourseOffering> addCourseOffering(ArrayList<CourseOffering> CourseOffering, String fileName) throws ParseException{
	File coFile;
	Scanner fileScanner;
	try {
	    coFile      = new File(fileName);
	    fileScanner = new Scanner(coFile);
	    fileScanner.useDelimiter("\n");
	    while (fileScanner.hasNext()) {
		String coLine    = fileScanner.next();
		String[] coElems = coLine.split(",");
		CourseOffering.add(new CourseOffering(new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH).parse(coElems[0]), 
						      new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH).parse(coElems[1]), 
						      Integer.parseInt(coElems[2]),null,null));
	    }
	    fileScanner.close();
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	}
	return CourseOffering;
    }

    public static ArrayList<Course> addCourses(ArrayList<Course> Courses, String courseFile) throws ParseException{
	File coFile;
	Scanner fileScanner;
	try {
	    coFile      = new File(courseFile);
	    fileScanner = new Scanner(coFile);
	    fileScanner.useDelimiter("\n");
	    while (fileScanner.hasNext()) {
		String courseLine    = fileScanner.next();
		String[] courseElems = courseLine.split(",");
		Courses.add(new Course(courseElems[0],courseElems[1],Integer.parseInt(courseElems[2]),courseElems[4],courseElems[4],null));
	    }
	    fileScanner.close();
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	}
	return Courses;
    }

    public static ArrayList<Student> addStudents(ArrayList<Student> Students, String studentFile) throws ParseException{
	File stFile;
	Scanner fileScanner;
	try {
	    stFile      = new File(studentFile);
	    fileScanner = new Scanner(stFile);
	    fileScanner.useDelimiter("\n");
	    while (fileScanner.hasNext()) {
		String studentLine    = fileScanner.next();
		String[] studentElems = studentLine.split(",");

		//0 public Student(String firstName, 
		//1  String lastName, 
		//2 int age, 
		//3 String gender, 
		//4 String ssn, 
		//5 String address, 
		//6 String city, 
		//7 String state, 
		//8 String zip, 
		//9 String email, 
		//10 String phone, 
		//11 int nId, 
		//12 String collg, 
		//13 String usr, 
		//14 String pw)
		Students.add(new Student(studentElems[0],
					 studentElems[1],
					 Integer.parseInt(studentElems[2]),
					 studentElems[3],
					 studentElems[4],
					 studentElems[5],
					 studentElems[6],
					 studentElems[7],
					 studentElems[8],
					 studentElems[9],
					 studentElems[10],
					 Integer.parseInt(studentElems[11]),
					 studentElems[12],
					 studentElems[13],
					 studentElems[14]
					 ));
	    }
	    fileScanner.close();
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	}
	return Students;
    }

    public static void saveEnrollment(Student currentStudent,ArrayList<Course> Courses, String registrationFile)  throws ParseException, IOException
    {
        File rFile = new File(registrationFile);
        Collections.sort(Courses, new courseCompare());
        ArrayList<CourseOffering> offeringNum = new ArrayList<CourseOffering>();
        int i = 0;
	String stringToWrite = "";
        for(Course course : Courses) {
            if(course.getCourseOfferings() != null){
		stringToWrite = course.toString() + "\n";
                for(CourseOffering offering : course.getCourseOfferings()) {
                    if(offering.isStudentEnrolled(currentStudent)){
			stringToWrite += (i+1) + ": " + offering.toString() + "\n";
			FileUtils.writeStringToFile(rFile,stringToWrite,true);    
                        i++;
		    }
		}
	    }
	}
    }
    public static void saveWaitlist(ArrayList<CourseOffering> Offering, String waitlistFile)  throws ParseException, IOException
    {
        File wFile = new File(waitlistFile);
	ArrayList<Student> wls = Offering.get(0).getWaitListedStudents();
	for(Student st : wls){
	    String stString = st.getId() + ":" + st.getUsername() + "\n";
	    FileUtils.writeStringToFile(wFile,stString,true);    
	}
    }

    public static String toString(String fileName)
    {
        File file = new File(fileName);
	String content = "";
        try
	    {
		content = FileUtils.readFileToString(file);
	    } catch (IOException e)
	    {
		e.printStackTrace();
	    }
	return content;

    }


}