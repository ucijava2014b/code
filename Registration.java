import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class Registration {

    public static void main(String[] args) throws ParseException {
        //  Declare Variables
        ArrayList<Student> students = new ArrayList<Student>();
        ArrayList<Course> courses = new ArrayList<Course>();
        
        //  Test Classes
        students.add(0, new Student());
              
        System.out.println("Registrating Main");
        System.out.println(Util.test());
        System.out.println(students.get(0).toString());
        
        
        // Generate some a course and some class offerings
        ArrayList<CourseOffering> CS510Offerings = new ArrayList<CourseOffering>();
        CS510Offerings.add(new CourseOffering(new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH).parse("01/14/2014"), 
        									  new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH).parse("05/23/2014"),
        									  25, 
        									  null,
        									  null));
        
        CS510Offerings.add(new CourseOffering(new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH).parse("05/13/2014"), 
				  		   					  new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH).parse("09/23/2014"),
				  		   					  30, 
				  		   					  null,
				  		   					  null));
        
        
        courses.add(new Course("0001", 
        					   "Intro to C++ Programming", 
        					   510, 
        					   "Provides an intro to computer programming using the C++ langugage.", 
        					   "Computer Science", 
        					   CS510Offerings));   

        System.out.println(courses.get(0).writeln());
        
        for(CourseOffering offering : courses.get(0).getCourseOfferings()) {
        	System.out.println(offering.writeln());
        }

    }

}