   /**
    *  Team B
	*  Shamima Huq
    *  2014 MAY 29 
    * 	
	*  Student class Extends Person class
	*
    *
    */
// Define Student class, subclass of Person
		public final class Student extends Person
		{
			 private int id;
			 private String college;
			 private String dorm;
			 private String username;
			 private String password;
			 private String verify;
			
			 public int getId(){return this.id;}
			 public void setId(int argId){this.id=argId;}
			
			 public String getCollege(){return this.college;}
			 public void setCollege(String argCollege){this.college=argCollege;}
			
			 public String getDorm(){return this.dorm;}
			 public void setDorm(String argDorm){this.dorm=argDorm;}
			 
			 //Constructor Method
			 public Student(String firstName, String lastName, int age, String gender, String ssn, int nId, String collg, String drm)
			  {   super(firstName, lastName, age, gender, ssn);
				  
				  this.id = nId;
				  this.college = collg;
				  this.dorm = drm;       
			  }
			 
			public String display()
			 {
			 //return String.format("%10s %10d %10s %10s", getFullName() , getAge() , getGender() , getSsn() );
			  return super.toString() + "ID = " + id + ", College = " + college	+ ", Dorm = " + dorm ;
			 }
		}
