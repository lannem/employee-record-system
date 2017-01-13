import java.util.Date;

/**
 * Employee class - contains all information for Employee object
 * Extends Person class and so inherits fields name, gender, nin, dob, address and postcode
 * Also includes id, salary, startDate, title, email and an image
 * 
 * Also includes getters and setters for each field, a toString function and validation for inserting employee data
 * @author Lucy MacGlashan 15107763
 * @version 1.0
 */
public class Employee extends Person{
	
	private String id;
	private String salary;
	private String startDate;
	private String title;
	private String email;
	private byte[] image;
	
	public Employee(){
		
	}
	
	/**
	 * Employee constructor
	 */	
	public Employee(String name, char gender, String natInscCo, String dob, String id, String salary, String startDate, String title, String email, byte[] image){
		super(name, gender, natInscCo, dob);
		this.id = id;
		this.salary = salary;
		this.startDate = startDate;
		this.title = title;
		this.email = email;
		this.image = image;
	}
	
	//EMAIL
	/**
	 * getEmail function
	 * @return email of Employee object
	 */
	String getEmail(){
		return this.email;
	}
	/**
	 * setEmail function -
	 * sets email of person to email parameter
	 * @param email
	 */
	void setEmail(String email){
		this.email = email;
	}
	
	//TITLE
	/**
	 * getTitle function
	 * @return job title of Employee object
	 */
	String getTitle(){
		return this.title;
	}
	/**
	 * setTitle function -
	 * sets job title of person to title parameter
	 * @param title
	 */
	void setTitle(String title){
		this.title = title;
	}
	
	//ID
	/**
	 * getId function
	 * @return id number of Employee object
	 */
	String getId(){
		return this.id;
	}
	/**
	 * setId function -
	 * sets id number of person to id parameter
	 * @param id
	 */
	void setId(String id){
		this.id = id;
	}
	
	//SALARY
	/**
	 * getSalary function
	 * @return salary of Employee object
	 */
	String getSalary(){
		return this.salary;
	}
	/**
	 * setSalary function -
	 * sets salary of person to salary parameter
	 * @param salary
	 */
	void setSalary(String salary){
		this.salary = salary;
	}
	
	//START DATE
	/**
	 * getStartDate function
	 * @return start date of Employee object
	 */
	String getStartDate(){
		return this.startDate;
	}
	/**
	 * setStartDate function -
	 * sets start date of person to start date parameter
	 * @param startDate
	 */
	void setStartDate(String startDate){
		this.startDate = startDate;
	}
	
	//GET DAY/MONTH/YEAR AS INTEGERS
	/**
	 * getStartDay function
	 * @return day of start date as substring of date converted to int
	 */
	int getStartDay(){
		return Integer.parseInt(this.startDate.substring(0, 2));
	}
	/**
	 * getStartMonth function
	 * @return month of start date as substring of date converted to int
	 */
	int getStartMonth(){
		return Integer.parseInt(this.startDate.substring(3, 5));
	}
	/**
	 * getStartYear function
	 * @return year of start date as substring of date converted to int
	 */
	int getStartYear(){
		return Integer.parseInt(this.startDate.substring(6));
	}
	
	//IMAGE
	/**
	 * getImage function
	 * @return image of Employee object
	 */
	byte[] getImage(){
		return this.image;
	}
	/**
	 * setImage function -
	 * sets image of person to image parameter
	 * @param image
	 */
	void setImage(byte[] image){
		this.image = image;
	}
	
	//TO STRING
	/**
	 * toString function
	 * @return each field label and field data as one string
	 */
	public String toString(){
		return("ID: " + id +
			  " Salary: " + salary +
			  " Start date: " + startDate +
			  " Title: " + title +
			  " Email: " + email + " Gender:" + getGender());
	}
	
	//EMPLOYEE VALIDATION
	//vaildate name
	/**
	 * function to test for a valid name
	 * @return true if name length is greater than 0
	 */
	private boolean validName() {
		return getName().length() > 0;
	}
	
	//validate dob
	/**
	 * function to test for a valid birth date
	 * calculates 16 years before the current date
	 * @return true if the date of birth is less than 16 years ago
	 */
	private boolean validDob() {
		Date date = new Date();
		date.setYear(getDobYear() - 1900);
		date.setMonth(getDobMonth() - 1);
		date.setDate(getDobDay());
		
		Date validBirthdate = new Date();
		validBirthdate.setYear(validBirthdate.getYear() - 16);
				
		return date.before(validBirthdate);
	}
	
	//validate salary
	/**
	 * Function to test if a string is an integer.
	 * Converts string to int.
	 * @param input
	 * @return true if string can be converted to int
	 */
	private static boolean isInt(String input) {
		try {
			Integer.parseInt(input);
			return true;
		}
		catch (NumberFormatException ex) {
			return false;
		}
	}
	/**
	 * Function to test for a vaid salary -
	 * uses isInt function
	 * @return true if salary is an integaer
	 */
	private boolean validSalary() {
		return isInt(salary);
	}
	
	//validate national insurance number
	/**
	 * Function to test for a valid NIN
	 * @return true if NIN is in valid format (char,char,int,int,int,int,int,int,char)
	 */
	private boolean validNin() {
		return Character.isAlphabetic(getNatInscNo().charAt(0)) && Character.isAlphabetic(getNatInscNo().charAt(1)) &&
				Character.isDigit(getNatInscNo().charAt(2)) && Character.isDigit(getNatInscNo().charAt(3)) &&
				Character.isDigit(getNatInscNo().charAt(4)) && Character.isDigit(getNatInscNo().charAt(5)) &&
				Character.isDigit(getNatInscNo().charAt(6)) && Character.isDigit(getNatInscNo().charAt(7)) &&
				Character.isAlphabetic(getNatInscNo().charAt(8));
	}
	
	//validate email
	/**
	 * Function to test for a valid email
	 * @return true if email contains an @ symbol and a .
	 */
	private boolean validEmail() {
		return email.contains("@") && email.contains(".");
	}
	
	//validate employee
	/**
	 * Function to test that employee is valid - 
	 * uses validName, vaildDob, validSalary, validNin and validEmail
	 * @return true if all individual validation functions are true
	 */
	public boolean validEmployee(){
		return validName() && validDob() && validSalary() && validNin() && validEmail();
	}
	
}
