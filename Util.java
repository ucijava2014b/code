import java.io.*;
import java.util.ArrayList;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Locale;


public class Util {

    
    public static ArrayList addCourseOffering(ArrayList CourseOffering, String fileName) throws ParseException{
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

    public static ArrayList addCourses(ArrayList Courses, String courseFile) throws ParseException{
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
}

