import java.util.ArrayList;

public class Registration {

    public static void main(String[] args) {
        //  Declare Variables
        ArrayList<Student> students = new ArrayList<Student>();
        ArrayList<Course> courses = new ArrayList<Course>();
        
        //  Test Classes
        students.add(0, new Student());
        courses.add(0,new Course());
        System.out.println("Registrating Main");
        System.out.println(Util.test());
        System.out.println(students.get(0).toString());
        System.out.println(courses.get(0).toString());

    }

}