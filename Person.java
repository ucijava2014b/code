   /**
    *  Team B
	*  Shamima Huq
    *  2014 MAY 29 
    * 	
	*  PersonClass Modified so collections.sort can be used
	*
    *
    */
import java.util.Comparator;

    class Person implements Comparable<Person>{

		//Instance Variables
		private String firstName;
		private String lastName;
		private int age;
		private String gender;
		private String ssn;
		private String address;
		private String address2;
		private String city;
		private String state;
		private String zip;
		private String email;
		private String phone;
		
		//Constructor Method
		public Person(String firstName, String lastName, int age, String gender, String ssn, String address, String address2,
		String city, String state, String zip, String email, String phone) 
		{
			this.firstName = firstName;
			this.lastName = lastName;
			this.age = age;
			this.gender = gender;
			this.ssn = ssn;
			this.address = address;
			this.address2 = address2;
			this.city = city;
			this.state = state;
			this.zip = zip;
			this.email = email;
			this.phone = phone;
		} 


		//Instance Methods
		public String getFirstName(){ 
		   return firstName;
		}

		public void setFirstName(String firstName){ 
		   this.firstName = firstName;
		}

		public String getLastName(){ 
		   return lastName;
		}

		public void setLastName(String lastName){ 
		   this.lastName = lastName;
		}

		public int getAge(){ 
		   return age;
		}

		public void setAge(int age){ 
		   this.age = age;
		}

		public String getGender(){ 
		   return gender;
		}

		public void setGender(String gender){ 
		   this.gender = gender;
		}

		public String getSsn(){ 
		   return ssn;
		}

		public void setSsn(String ssn){ 
		   this.ssn = ssn;
		}
		
		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getAddress2() {
			return address2;
		}

		public void setAddress2(String address2) {
			this.address2 = address2;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		public String getZip() {
			return zip;
		}

		public void setZip(String zip) {
			this.zip = zip;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public String getFullName()
		{ 
		   return firstName + " " + lastName;
		}

		public void talk()
		{
		System.out.println("Hello! My name is:" + firstName + " " + lastName);
		}
		 
		public String toString()
		{
		//return String.format("%10s %10d %10s %10s", getFullName() , getAge() , getGender() , getSsn() );
		  return firstName + "," + lastName
				+ ",\n" + age + ",\n" + gender + ",\n" + ssn 
				+ ",\n" + address + "," + address2 
				+ ",\n" + city 
				+ ",\n" + state + ", " + zip 
				+ ",\n" + email 
				+ ",\n" + phone + "\n";
		}

        public int compareTo(Person person) {
		   return this.lastName.compareTo(person.getLastName());
		}
	}
	