import java.sql.*;
import java.util.ArrayList;
/**
 * Employee DAO class.
 * Creates database connection, contains all SQL CRUD queries
 * @author Lucy MacGlashan 15107763
 * @version 1.0
 */
public class EmployeeDAO{
	Connection dbConnection;
	Statement statement;
	ResultSet resultSet;
	ArrayList<Employee> searchResults;
	
	/**
	 * Employee DAO constructor
	 */
	EmployeeDAO(){
		
	}
	
	//CREATE DATABASE CONNECTION
	/**
	 * tries to connect with database - returns error message if failed
	 * @return database connection
	 */
	public Connection getDBConnection(){
		dbConnection = null;
		try {
			Class.forName("org.sqlite.JDBC");
		}
		catch (ClassNotFoundException e){
			System.out.println(e.getMessage());
		}
		
		try{
			String dbURL = "jdbc:sqlite:assignment.sqlite";
			dbConnection = DriverManager.getConnection(dbURL);
			statement = dbConnection.createStatement();
			return dbConnection;
			
		}
		catch (SQLException e){
			System.out.println(e.getMessage());
		}
				
		return dbConnection;
	}
	
	/**
	 * closes database connection
	 */
	public void closeConnection(){
		try{
			dbConnection.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Cycles through result set and adds each result to the arraylist as an employee.
	 */
	private ArrayList<Employee> getEmployeesFromResultSet(ResultSet resultSet) throws SQLException {
		ArrayList<Employee> employeeList = new ArrayList<Employee>();
		while(resultSet.next()) {
			employeeList.add(new Employee(
					resultSet.getString("Name"),
					resultSet.getString("Gender").charAt(0),
					resultSet.getString("NIN"),
					resultSet.getString("DOB"),
					resultSet.getString("ID"),
					resultSet.getString("Salary"),
					resultSet.getString("StartDate"),
					resultSet.getString("JobTitle"),
					resultSet.getString("Email"),
					resultSet.getBytes("Image"))
			);
		}
		return employeeList;
	}
	
	//SELECT ALL EMPLOYEES
	/**
	 * Select all employees function.
	 * Creates arraylist, loops through all results and adds them to list.
	 * @return arraylist containing all employees within the database
	 */
	public ArrayList<Employee> selectAllEmployees(){
		ArrayList<Employee> list = new ArrayList<Employee>();
		try{
			resultSet = statement.executeQuery("SELECT * FROM employees");
			list = getEmployeesFromResultSet(resultSet);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//SELECT EMPLOYEE BY NAME
	/**
	 * Select employee by name function
	 * @param name
	 * @return employee with name that matches parameter
	 */
	public Employee selectEmployeeByName(String name){
		Employee newEmployee = null;
		
		try{
			resultSet = statement.executeQuery("SELECT * FROM employees WHERE Name = '" + name + "'");
			newEmployee = getEmployeesFromResultSet(resultSet).get(0);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return newEmployee;
	}
	
	//SELECT EMPLOYEE BY ID
	/**
	 * Select employee by id function
	 * @param id
	 * @return employee with id that matches parameter
	 */
		public Employee selectEmployeeById(String id){
			Employee newEmployee = null;
			
			try{
				resultSet = statement.executeQuery("SELECT * FROM employees WHERE ID = '" + id + "'");
				newEmployee = getEmployeesFromResultSet(resultSet).get(0);
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
			return newEmployee;
		}
	
	//INSERT EMPLOYEE
	/**
	 * Insert employee function.
	 * Creates new employee/new row in table.
	 * Tests if employee is valid, auto incrementes new id number, inserts using SQL statement
	 * @param newEmployee
	 * @return true if employee is valid and is therefore added to the database
	 */
	public boolean insertEmployee(Employee newEmployee){
		try{
			if(newEmployee.validEmployee()){
				if (newEmployee.getId() == null) {
					ArrayList<Employee> employees = selectAllEmployees();
					newEmployee.setId(Integer.toString(Integer.parseInt(employees.get(employees.size() - 1).getId()) + 1));
				}
								
				String sql = "INSERT INTO employees VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
				PreparedStatement ps = dbConnection.prepareStatement(sql);
				
				ps.setString(1, newEmployee.getId());
				ps.setString(2, newEmployee.getName());
				ps.setString(3, Character.toString(newEmployee.getGender()));
				ps.setString(4, newEmployee.getDob());
				ps.setString(5, newEmployee.getAddress());
				ps.setString(6, newEmployee.getPostcode());
				ps.setString(7, newEmployee.getNatInscNo());
				ps.setString(8, newEmployee.getTitle());
				ps.setString(9, newEmployee.getStartDate());
				ps.setString(10, newEmployee.getSalary());
				ps.setString(11, newEmployee.getEmail());
				ps.setBytes(12, newEmployee.getImage());
				ps.executeUpdate();
				ps.close();
				
				return true;
			}
			else{
				return false;
			}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//UPDATE EMPLOYEE AT ID
	/**
	 * Update employee function.
	 * Updates employee already in table.
	 * Tests if employee is valid before updating at specific ID using SQL statement
	 * @param newEmployee, employeeID
	 * @return true if employee information is valid and can therefore be updated on the database
	 */
	public boolean insertEmployeeAtID(Employee newEmployee, String employeeID){
		try{
			if(newEmployee.validEmployee()){
				String sql = "UPDATE employees SET ID = ?, Name = ?, Gender = ?, DOB = ?, Address = ?, Postcode = ?, NIN = ?, JobTitle = ?, StartDate = ?, Salary = ?, Email = ?, Image = ? WHERE ID = ?";
				PreparedStatement ps = dbConnection.prepareStatement(sql);
				
				ps.setString(1, employeeID);
				ps.setString(2, newEmployee.getName());
				ps.setString(3, Character.toString(newEmployee.getGender()));
				ps.setString(4, newEmployee.getDob());
				ps.setString(5, newEmployee.getAddress());
				ps.setString(6, newEmployee.getPostcode());
				ps.setString(7, newEmployee.getNatInscNo());
				ps.setString(8, newEmployee.getTitle());
				ps.setString(9, newEmployee.getStartDate());
				ps.setString(10, newEmployee.getSalary());
				ps.setString(11, newEmployee.getEmail());
				ps.setBytes(12, newEmployee.getImage());
				ps.setString(13, employeeID);
				ps.executeUpdate();
				ps.close();
					
				return true;
			}
			else {
				return false;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//DELETE EMPLOYEE
	/**
	 * Delete employee function.
	 * Removes employee from table at ID passed as parameter
	 * @param id
	 * @return true if employee is successfully deleted from database
	 */
	public boolean deleteEmployeeByID(String id){
		try{
			statement.executeUpdate("DELETE FROM employees WHERE ID = " + id);
				
			return true;
				
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//SEARCH FOR EMPLOYEE BY STRING
	/**
	 * Search for employee function.
	 * Selects all employees from table where the field includes the search term
	 * that is passed in as a parameter.
	 * Adds each result to an arraylist when found.
	 * @param searchTerm entered into search box
	 * @param searchField - what to search by eg. name, job title
	 * @return arraylist of results from the search
	 */
	public ArrayList<Employee> searchByString(String searchTerm, String searchField){
		searchResults = new ArrayList<Employee>();
		try{
			resultSet = statement.executeQuery("SELECT * FROM employees WHERE " + searchField + " LIKE '%" + searchTerm + "%'");
			return getEmployeesFromResultSet(resultSet);
				
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return searchResults;
	}
	
	//SEARCH FOR EMPLOYEE BY RADIO BUTTON
	/**
	 * Search for employee function.
	 * Selects all employees from table where the field includes the search term
	 * that is passed in as a parameter.
	 * Adds each result to an arraylist when found.
	 * @param searchTerm entered into search box
	 * @param searchField - what to search by i.e. gender
	 * @return arraylist of results from the search
	 */
	public ArrayList<Employee> searchByRadio(String searchTerm, String searchField){
		searchResults = new ArrayList<Employee>();
		try{
			resultSet = statement.executeQuery("SELECT * FROM employees WHERE " + searchField + " = '" + searchTerm + "'");
			return getEmployeesFromResultSet(resultSet);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return searchResults;
	}
	
		//SEARCH FOR EMPLOYEE BY COMBO BOX
		/**
		 * Search for employee function.
		 * Selects all employees from table where the field includes the search term
		 * that is passed in as a parameter.
		 * Adds each result to an arraylist when found.
		 * @param searchDate entered into search box
		 * @param searchField - what to search by eg. start date, dob
		 * @return arraylist of results from the search
		 */
		public ArrayList<Employee> searchByCombo(String searchDate, String searchField){
			searchResults = new ArrayList<Employee>();
			try{
				resultSet = statement.executeQuery("SELECT * FROM employees WHERE " +searchField+ " = '" +searchDate+ "'");
				return getEmployeesFromResultSet(resultSet);
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
			return searchResults;
		}
	
	
	
}
