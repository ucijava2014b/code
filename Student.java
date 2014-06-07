import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	private int ID = -1;
	private String college = null;
	private String username = null;
	private String password = null;

	// Based on
	// http://www.mkyong.com/regular-expressions/how-to-validate-password-with-regular-expression/
	// (?=.*\d) # must contains one digit from 0-9
	// (?=.*[a-z]) # must contains one lowercase characters
	// (?=.*[A-Z]) # must contains one uppercase characters
	// (?=.*[&~!@#$%]) # must contains one special symbols in the list "&~!@#$%"
	// {6,128} # length at least 6 characters and maximum of 128
	private final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[&~!@#$%]).{6,20})";
	public final String PASSWORD_REQUIREMENTS = "Must contains one digit from 0-9\nMust contains one lowercase characters\nmust contains one uppercase characters\nmust contains one special symbols in the list \"&~!@#$%\"\nlength at least 6 characters and maximum of 128";
	private Pattern pattern;
	private Matcher matcher;

	/*
	 * // Constructors
	 */

	// Default constructor
	public Student() {

	}

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
		if (passwordValidator(password) == false) {
			throw new IllegalArgumentException(PASSWORD_REQUIREMENTS);
		}

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
	// Serializes the object for output. The string constructor expects the
	// input string to be in this format
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

	private boolean passwordValidator(final String password) {
		pattern = Pattern.compile(PASSWORD_PATTERN);
		matcher = pattern.matcher(password);
		return matcher.matches();
	}

}
