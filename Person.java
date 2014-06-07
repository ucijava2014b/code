/**
 *  Team B
 *  Shamima Huq
 *  2014 JUNE 01 
 * 	
 *  PersonClass Modified so collections.sort can be used
 *  currently only sorts by lastname
 *
 *
 */
import java.util.Arrays;
import java.util.List;

/*
 * // Person Class
 * // Class which describes a individual. Should normally use children classes such as Student. 
 */
class Person implements Comparable<Person> {
	/*
	 * // Member variables
	 */
	// make sure to update the Person(string) and writeLine() functions when
	// adding members
	private String firstName = null;
	private String lastName = null;
	private int age = -1;
	private String gender = null;
	private String ssn = null;
	private String address = null;
	private String city = null;
	private String state = null;
	private String zip = null;
	private String email = null;
	private String phone = null;

	private static final int SSN_LENGTH = 9;
	private static final int MIN_AGE = 0;
	private static final int MAX_AGE = 150;

	/*
	 * // Constructors
	 */
	
	// Default constructor
	public Person() {
		
	}
	
	// Constructor Method
	public Person(String firstName, String lastName, int age, String gender,
			String ssn, String address, String city, String state, String zip,
			String email, String phone) {
		setFirstName(firstName);
		setLastName(lastName);
		setAge(age);
		setGender(gender);
		setSSN(ssn);
		setAddress(address);
		setCity(city);
		setState(state);
		setZip(zip);
		setEmail(email);
		setPhone(phone);
	}

	// Creates an object based on a comma delimited string in the same format as
	// what writeLine() produces
	public Person(String s) {
		// Break the string into different tokens for each member
		List<String> memberListString = Arrays.asList(s.split(","));

		// Check to make sure we have enough tokens
		// Allow more than 10 for subclasses
		if (memberListString.size() < 11)
			throw new IllegalArgumentException(
					"String did not contain the correct number of tokens.");

		try {
			setFirstName(memberListString.get(0));
			setLastName(memberListString.get(1));
			setAge(Integer.parseInt(memberListString.get(2)));
			setGender(memberListString.get(3));
			setSSN(memberListString.get(4));
			setAddress(memberListString.get(5));
			setCity(memberListString.get(6));
			setState(memberListString.get(7));
			setZip(memberListString.get(8));
			setEmail(memberListString.get(9));
			setPhone(memberListString.get(10));
		} catch (Exception e) {
			throw new IllegalArgumentException(
					"Parameter string was not an expected format.");
		}
	}

	/*
	 * // Set functions
	 */

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setAge(int age) {
		// Check to see if we have an invalid age
		if (age < MIN_AGE || age > MAX_AGE)
			throw new IllegalArgumentException("Invalid age");

		this.age = age;
	}

	public void setGender(String gender) {
		// Convert to uppercase
		gender = gender.toUpperCase();
		
		// Check to see if we have an invalid gender
		if (!(gender.compareTo("M") == 0) && !(gender.compareTo("F") == 0))
			throw new IllegalArgumentException("Invalid gender");

		this.gender = gender;
	}

	public void setSSN(String ssn) {
		// remove all non-numeric characters
		ssn = ssn.replaceAll("[^\\d.]", "");
		
		// Check to see if the SSN is valid
		if (!(ssn.length() == SSN_LENGTH && isNumber(ssn)))
			throw new IllegalArgumentException("Invalid SSN");

		this.ssn = ssn;
	}

	public void setAddress(String address) {
		this.address = address;
	}


	public void setCity(String city) {
		this.city = city;
	}

	public void setState(String state) {
		// Must be two character state
		if (state.length() != 2)
			throw new IllegalArgumentException("Invalid State");

		// Convert to upper case
		this.state = state.toUpperCase();
	}

	public void setZip(String zip) {
		// remove all non-numeric characters
		zip = zip.replaceAll("[^\\d.]", "");
		
		// Must either be 5 or 9 digits
		if (zip.length() != 5 && zip.length() != 9)
			throw new IllegalArgumentException("Invalid zip");

		this.zip = zip;
	}

	public void setEmail(String email) {
		// Do some basic validation. If this was real would use the
		// InternetAddress type but that would require javax
		if (!(email.contains("@") && email.contains(".")))
			throw new IllegalArgumentException("Invalid email address");

		this.email = email;
	}

	public void setPhone(String phone) {
		// Must be numeric
		if (!isNumber(phone))
			throw new IllegalArgumentException("Invalid Phone Number");

		this.phone = phone;
	}

	/*
	 * // Get functions
	 */
	
	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public int getAge() {
		return age;
	}

	public String getGender() {
		return gender;
	}


	public String getSSN() {
		return ssn;
	}

	public String getAddress() {
		return address;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getZip() {
		return zip;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}

	public String getFullName() {
		return firstName + " " + lastName;
	}

	/*
	 * // Member functions
	 */
	
	// Talk
	// Prints a friendly message with the person's name
	public void talk() {
		System.out.println("Hello! My name is:" + firstName + " " + lastName);
	}

	// writeLine
	// Returns a comma delimited string containing all members of the class
	public String writeLine() {
		return firstName + "," + lastName + "," + age + "," + gender + ","
				+ ssn + "," + address + "," + city + "," + state + "," + zip
				+ "," + email + "," + phone;
	}

	// toString
	// Provides a user friendly string to display
	public String toString() {
		// return String.format("%10s %10d %10s %10s", getFullName() , getAge()
		// , getGender() , getSsn() );
		return firstName + "," + lastName + ",\n" + age + ",\n" + gender
				+ ",\n" + ssn + ",\n" + address + ",\n" + city + ",\n" + state
				+ ", " + zip + ",\n" + email + ",\n" + phone + "\n";
	}

	// compareTo
	// Provides lastname comparison
	public int compareTo(Person person) {
		return this.lastName.compareTo(person.getLastName());
	}

	// isNumber
	// Returns true if a string is only numeric characters
	private boolean isNumber(String s) {
		return s.matches("^[0-9]+$");
	}
}
