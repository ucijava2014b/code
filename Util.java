import java.io.*;
import java.util.ArrayList;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Collections;

/*
 * // Util Class
 * // Provides utility functions for dealing with reading and updating files 
 */
public class Util {

	/*
	 * // Member functions
	 */
	
	// addCourseOffering
	// reads in the courseOffering file and assigns to the correct courses
	public static ArrayList<Course> addCourseOffering(
			ArrayList<Course> courses, String fileName) throws ParseException {
		File coFile;
		Scanner fileScanner;
		try {
			coFile = new File(fileName);
			fileScanner = new Scanner(coFile);
			fileScanner.useDelimiter("\n");

			CourseOffering currentOffering;

			// Discard the header record
			fileScanner.next();

			while (fileScanner.hasNext()) {
				String coLine = fileScanner.next();
				// Remove the end of line characters
				coLine = coLine.replace("\n", "").replace("\r", "");

				currentOffering = new CourseOffering(coLine);

				// Need to split this here because we need to get the course ID
				// to associate
				String[] coElems = coLine.split(",");
				String courseID = coElems[0];

				// Look for the matching course
				for (Course currentCourse : courses) {
					if (currentCourse.getCourseID().compareTo(courseID) == 0) {
						currentCourse.getCourseOfferings().add(currentOffering);
					}
				}
			}
			fileScanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("Unable to locate file:" + fileName);
			e.printStackTrace();
			System.exit(1);
		}
		return courses;
	}

	// addCourses
	// reads in the courses file and builds the list
	public static ArrayList<Course> addCourses(ArrayList<Course> Courses,
			String courseFile) throws ParseException {
		File coFile;
		Scanner fileScanner;
		try {
			coFile = new File(courseFile);
			fileScanner = new Scanner(coFile);
			fileScanner.useDelimiter("\n");

			// Discard the header record
			fileScanner.next();

			while (fileScanner.hasNext()) {
				String courseLine = fileScanner.next();
				// Remove the end of line characters
				courseLine = courseLine.replace("\n", "").replace("\r", "");

				Courses.add(new Course(courseLine));
			}
			fileScanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("Unable to locate file:" + courseFile);
			e.printStackTrace();
			System.exit(1);
		}
		return Courses;
	}

	// addStudents
	// reads in the student file and builds the list
	public static ArrayList<Student> addStudents(ArrayList<Student> Students,
			String studentFile) throws ParseException {
		File stFile;
		Scanner fileScanner;
		try {
			stFile = new File(studentFile);
			fileScanner = new Scanner(stFile);
			fileScanner.useDelimiter("\n");

			// Discard the header record
			fileScanner.next();

			while (fileScanner.hasNext()) {
				String studentLine = fileScanner.next();
				// Remove the end of line characters
				studentLine = studentLine.replace("\n", "").replace("\r", "");

				Students.add(new Student(studentLine));
			}
			fileScanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("Unable to locate file:" + studentFile);
			e.printStackTrace();
			System.exit(1);
		}
		return Students;
	}

	// addCourses
	// reads in the enrollment file and assigns students to the correct course offering
	public static ArrayList<Course> readEnrollment(
			ArrayList<Course> courseList, ArrayList<Student> studentList,
			String enrollmentFile) {
		File stFile;
		Scanner fileScanner;
		try {
			stFile = new File(enrollmentFile);
			fileScanner = new Scanner(stFile);
			fileScanner.useDelimiter("\n");

			// Discard the header record
			fileScanner.next();

			while (fileScanner.hasNext()) {
				String enrollmentLine = fileScanner.next();
				// Remove the end of line characters
				enrollmentLine = enrollmentLine.replace("\n", "").replace("\r",
						"");

				// 0 StudentId
				// 1 ENROLLED/WAITLIST
				// 2 CourseOfferingID
				List<String> memberListString = Arrays.asList(enrollmentLine
						.split(","));

				// Lets find the course offering
				for (Course currentCourse : courseList) {
					for (CourseOffering currentOffering : currentCourse
							.getCourseOfferings()) {
						if (currentOffering.getCourseOfferingID().compareTo(
								memberListString.get(2)) == 0) {

							// Now lets find the student
							for (Student currentStudent : studentList) {
								if (currentStudent.getID() == Integer
										.parseInt(memberListString.get(0))) {
									// Found the student and the offering. Need
									// to determine if we are enrolling or
									// waitlisting
									if (memberListString.get(1).compareTo(
											"ENROLLED") == 0)
										currentOffering.enrollStudent(
												currentStudent, false);
									else
										currentOffering.enrollStudent(
												currentStudent, true);
								}
							}

						}
					}
				}

			}
			fileScanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("Warning - unable to locate registration file:" + enrollmentFile);
			System.out.println("First time using the system will see this warning.");
		}

		return courseList;
	}

	// saveEnrollment
	// Creates a file mapping of students to course offerings
	public static void saveEnrollment(ArrayList<Course> Courses,
			String registrationFile) throws IOException {

		File rFile = new File(registrationFile);
		PrintWriter outFile = new PrintWriter(rFile);
		Collections.sort(Courses, new courseCompare());

		String stringToWrite;

		// Write a header record
		outFile.println("studentID, ENROLLED/WAITLIST, courseOfferingID");

		for (Course course : Courses) {
			for (CourseOffering offering : course.getCourseOfferings()) {
				// Write the list of enrolled students
				for (Student currentStudent : offering.getEnrolledStudents()) {
					stringToWrite = currentStudent.getID() + "," + "ENROLLED,"
							+ offering.getCourseOfferingID();
					outFile.println(stringToWrite);
				}

				// Write the list of waitlisted students
				for (Student currentStudent : offering.getWaitListedStudents()) {
					stringToWrite = currentStudent.getID() + "," + "WAITLIST,"
							+ offering.getCourseOfferingID();
					outFile.println(stringToWrite);
				}
			}
		}

		outFile.close();
	}
	
	// saveStudents
	// Saves the students to the student file
	public static void saveStudents(ArrayList<Student> students,
			String studentFile) throws IOException {

		File rFile = new File(studentFile);
		PrintWriter outFile = new PrintWriter(rFile);

		// Write a header record
		outFile.println("firstName, lastName, age, gender, SSN, address, city, state, ZIP, email, phone, ID, college, username, password");
		
		for (Student currentStudent : students){
			outFile.println(currentStudent.writeLine());
		}

		outFile.close();
	}
	

}
