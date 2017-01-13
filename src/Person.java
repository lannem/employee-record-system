/**
 * Person class - contains all information for Person object.
 * Includes name, gender, nin, dob, address and postcode.
 * Also includes getters and setters for each field and a toString function.
 * @author Lucy MacGlashan 15107763
 * @version 1.0
 */
public class Person {
	private String name;
	private char gender;
	private String natInscNo;
	private String dob;
	private String address;
	private String postcode;
	
	public Person(){
		
	}
	
	/**
	 * Person Constructor
	 * 
	 */
	public Person(String name, char gender, String natInscNo, String dob){
		this.name = name;
		this.gender = gender;
		this.natInscNo = natInscNo;
		this.dob = dob;
	}
	
	//NAME
	/**
	 * getName function
	 * @return name of Person object
	 */
	String getName(){
		return this.name;
	}
	/**
	 * setName function -
	 * sets name of person to name parameter
	 * @param name
	 */
	void setName(String name){
		this.name = name;
	}
	
	//GENDER
	/**
	 * getGender function
	 * @return gender of Person object
	 */
	char getGender(){
		return this.gender;
	}
	/**
	 * setGender function -
	 * sets gender of person to gender parameter
	 * @param gender
	 */
	void setGender(char gender){
		this.gender = gender;
	}
	
	//NAT INSURANCE NO
	/**
	 * getNatInscNo function
	 * @return national insurance number of Person object
	 */
	String getNatInscNo(){
		return this.natInscNo;
	}
	/**
	 * setNatInscNo function -
	 * sets nin of person to nin parameter
	 * @param natInscNo
	 */
	void setNatInscNo(String natInscNo){
		this.natInscNo = natInscNo;
	}
	
	//DATE OF BIRTH
	/**
	 * getDob function
	 * @return date of birth of Person object
	 */
	String getDob(){
		return this.dob;
	}
	/**
	 * setDob function -
	 * sets dob of person to dob parameter
	 * @param dob
	 */
	void setDob(String dob){
		this.dob = dob;
	}
	
	//GET DAY/MONTH/YEAR AS INTEGERS
	/**
	 * getDobDay function
	 * @return day of dob as substring of date converted to int
	 */
	int getDobDay(){
		return Integer.parseInt(this.dob.substring(0, 2));
	}
	/**
	 * getDobMonth function
	 * @return month of dob as substring of date converted to int
	 */
	int getDobMonth(){
		return Integer.parseInt(this.dob.substring(3, 5));
	}
	/**
	 * getDobYear function
	 * @return year of dob as substring of date converted to int
	 */
	int getDobYear(){
		return Integer.parseInt(this.dob.substring(6));
	}
	
	//ADDRESS
	/**
	 * getAddress function
	 * @return address of Person object
	 */
	String getAddress(){
		return this.address;
	}
	/**
	 * setAddress function -
	 * sets address of person to address parameter
	 * @param address
	 */
	void setAddress(String address){
		this.address = address;
	}
	
	//POSTCODE
	/**
	 * getPostcode function -
	 * @return postcode of Person object
	 */
	String getPostcode(){
		return this.postcode;
	}
	/**
	 * setPostcode function -
	 * sets postcode of person to postcode parameter
	 * @param postcode
	 */
	void setPostcode(String postcode){
		this.postcode = postcode;
	}
	
	//TO STRING
	/**
	 * toString function
	 * @return each field label and field data as one string
	 */
	public String toString(){
		return("Name: " + name + 
			  " Gender: " + gender +
			  " National Insurance: " + natInscNo +
			  " Date of Birth: " + dob +
			  " Address: " + address +
			  " Postcode: "+ postcode);
	}
	
}
