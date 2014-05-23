import java.util.ArrayList;

public class Course {
    ArrayList<CourseOffering> courseOfferings = new ArrayList<CourseOffering>();
    public String toString() {  
        String courseReturnString = "Course Class";
        courseOfferings.add(0,new CourseOffering());
        return (courseReturnString + " - " + courseOfferings.get(0).toString());
    }
}
