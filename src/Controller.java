import java.sql.*;
/**
 * Controller class - contains main method.
 * Tests CRUD methods
 * @author Lucy MacGlashan 15107763
 * @version 1.0
 */
public class Controller{

	public static void main(String[] args) throws SQLException{
		EmployeeDAO dao = new EmployeeDAO();
		dao.getDBConnection();
		
		//create new employee
		Employee Andrew = new Employee();
		Andrew.setName("Andrew Jones");
		Andrew.setGender('M');
		Andrew.setDob("09/08/1974");
		Andrew.setAddress("78 Whitworth Close, Manchester");
		Andrew.setPostcode("M4 7KM");
		Andrew.setNatInscNo("HK375457L");
		Andrew.setTitle("Marketing");
		Andrew.setStartDate("23/10/2004");
		Andrew.setSalary("25000");
		Andrew.setEmail("AJ@gmail.co.uk");
		dao.insertEmployee(Andrew);
		
		Employee Dave = new Employee(
				"Dave Royle",
				'M',
				"PA346532F",
				"12/03/1965",
				"",
				"14000",
				"25/05/2005",
				"UX Designer",
				"Dave-R@gmail.com",
				null
		);
		
		//select employee by name and prints to console
		Employee emp = dao.selectEmployeeByName("Andrew Jones");
		System.out.println(emp.toString());
		
		//update startdate of employee
		emp.setStartDate("04/06/2004");
		dao.insertEmployeeAtID(emp, emp.getId());
		System.out.println(emp.toString());
		
		//delete employee at id
		System.out.println(dao.deleteEmployeeByID("18"));
		
	}
	
	/**
	 * Controller constructor
	 */
	public Controller(){
		
	}

}
