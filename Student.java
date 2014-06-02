   /**
    *  Team B
	*  Shamima Huq
    *  2014 JUNE 01 
    * 	
	*  Student class Extends Person class
	*
    *
    */
//Student class, subclass of Person
import java.io.*;
import java.util.Comparator;
public final class Student extends Person
{
	private int id;
	private String college;
	private String username;
	private String password;
			 			
	public int getId(){return this.id;}
	public void setId(int argId){this.id=argId;}
			
	public String getCollege(){return this.college;}
	public void setCollege(String argCollege){this.college=argCollege;}
						 
	public String getUsername(){return this.username;}
	public void setUsername(String argUsernm){this.username=argUsernm;}
			 
	public String getPw(){return this.password;}
	public void setPw(String argPw){this.password=argPw;}
			 
	//Constructor Method
	public Student(String firstName, String lastName, int age, String gender, String ssn, String address, 
	 String city, String state, String zip, String email, String phone, int nId, String collg, String usr, String pw)
	 {   
		super(firstName, lastName, age, gender, ssn, address, city, state, zip, email, phone);
	    this.id = nId;
		this.college = collg;
		this.username = usr;	
        this.password = pw;				  
	 }
			
	// comparator to sort by last name
	private static class ByLastName implements Comparator<Student> {
		public int compare(Student a, Student b) {
		return a.getLastName().compareTo(b.getLastName());
		}
	}
 
	public String display()
	 {
	 //return String.format("%10s %10d %10s %10s", getFullName() , getAge() , getGender() , getSsn() );
	  return super.toString() + ",\n" + id 
	                          + ",\n College = " + college;
	 }
	
	//I've unit tested body of this section of the code and it works...login student with 3 max tries
	// but I'm not sure how to tie this together with the checklogin --- can Registration.java test this out?
	// passing student to the Login process is throwing an error
	private void Login() throws IOException{
   
        	String un, pw;
			Boolean isLoggedIn = false;
			//Read input buffer
			BufferedReader inpt = new BufferedReader (new InputStreamReader(System.in));
            
            // Allow maximum of three tries			
			for(int trial=1; trial<=3; trial++)
			{
				System.out.print("Username: ");
				un = inpt.readLine();

				System.out.print("Password: ");
				pw = inpt.readLine();

				System.out.println("");
				if (un.equals(this.username) && pw.equals(this.password)){
					System.out.println("You have successfully logged in!");
					isLoggedIn = true;
					break;
				}else{
					System.out.println("Sorry, Incorrect Username/Password");
					if (trial < 3) {System.out.println("Please Try Again");
					}else{ 
					System.out.println("More than 3 tries : you cannot be logged in");  
					System.out.println("");	
					System.exit(0);
					}     
					System.out.println("");
					}
			}
			System.out.println("");
		 } 			 
			
}
