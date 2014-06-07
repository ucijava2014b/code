import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/*
 * // Student Class
 * // Defines a student to be used for registration. 
 */
class Student extends Person {
	/*
	 * // Member variables
	 */
	// make sure to update the Student(string) and writeLine() functions when
	// adding members
	private int ID;
	private String college;
	private String username;
	private String password;

	/*
	 * // Constructors
	 */
	
	// Constructor Method
	public Student(String firstName, String lastName, int age, String gender,
			String ssn, String address, String city, String state, String zip,
			String email, String phone, int ID, String college,
			String username, String password) {
		super(firstName, lastName, age, gender, ssn, address, city, state, zip,
				email, phone);
		setID(ID);
		setCollege(college);
		setUsername(username);
		setPassword(password);
	}

	// Creates an object based on a comma delimited string in the same format as
	// what writeLine() produces
	public Student(String s) {
		super(s);
		// Break the string into different tokens for each member
		List<String> memberListString = Arrays.asList(s.split(","));

		// Check to make sure we have enough tokens
		if (memberListString.size() != 15)
			throw new IllegalArgumentException(
					"String did not contain the correct number of tokens.");

		try {
			setID(Integer.parseInt(memberListString.get(11)));
			setCollege(memberListString.get(12));
			setUsername(memberListString.get(13));
			setPassword(memberListString.get(14));
		} catch (Exception e) {
			throw new IllegalArgumentException(
					"Parameter string was not an expected format.");
		}
	}
	
	/*
	 * // Set functions
	 */
	public void setID(int ID) {
		this.ID = ID;
	}
	
	public void setCollege(String college) {
		this.college = college;
	}

	public void setUsername(String userName) {
		this.username = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/*
	 * // Get functions
	 */
	
	public int getID() {
		return this.ID;
	}


	public String getCollege() {
		return this.college;
	}


	public String getUsername() {
		return this.username;
	}

	public String getPassword() {
		return this.password;
	}

	/*
	 * // Member functions
	 */

	// ByLastName
	// comparator to sort by last name
	private static class ByLastName implements Comparator<Student> {
		public int compare(Student a, Student b) {
			return a.getLastName().compareTo(b.getLastName());
		}
	}

	// writeLine
	// Serializes the object for output. The string constructor expects the input string to be in this format
	public String writeLine() {
		return super.writeLine() + "," + ID + "," + college + "," + username
				+ "," + password;
	}

	// toString
	// Displays the object in a user friendly format
	public String toString() {
		// return String.format("%10s %10d %10s %10s", getFullName() , getAge()
		// , getGender() , getSsn() );
		return super.toString() + ",\n" + ID + ",\n College = " + college;
	}

}
