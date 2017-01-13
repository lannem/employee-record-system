import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;
/**
 * Main form class.
 * Contains all information for the swing GUI form
 * as well as methods for when anyone interacts with it.
 * @author Lucy MacGlashan 15107763
 * @version 1.0
 */
public class MainForm extends JFrame{
	EmployeeDAO dao = new EmployeeDAO();
	String[] month = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
	//labels
	JLabel formLabel;
	JLabel nameLabel;
	JLabel genderLabel;
	JLabel dobLabel;
	JLabel salaryLabel;
	JLabel ninLabel;
	JLabel emailLabel;
	JLabel startLabel;
	JLabel jobLabel;
	
	//name
	JTextField name;
	
	//gender
	ButtonGroup genders = new ButtonGroup();
	JRadioButton genderM;
	JRadioButton genderF;
	
	//date of birth
	JComboBox dobDay;
	JComboBox dobMonth;
	JComboBox dobYear;
	
	//salary
	JTextField salary;
	
	//national insurance number
	JTextField nin;
	
	//email
	JTextField email;
	
	//start date
	JComboBox startDay;
	JComboBox startMonth;
	JComboBox startYear;
	
	//job title
	JTextField job;
	
	//image panel
	JPanel imagePanel;
	byte[] uploadedImage;
	
	//search box
	JTextField searchText;
	JComboBox searchDay;
	JComboBox searchMonth;
	JComboBox searchYear;
	ButtonGroup searchGender = new ButtonGroup();
	JRadioButton searchMale;
	JRadioButton searchFemale;
	JComboBox selectField;
	
	//menu
	JButton createButton;
	JButton viewButton;
	JButton searchButton;
	
	//buttons
	JButton enter;
	JButton delete;
	JButton previous;
	JButton next;
	JButton uploadImage;
	JButton clearImage;
	JButton search;
	
	//confirmation/error message
	JLabel message = new JLabel(" ");
	
	//booleans
	boolean viewEmployees = true;
	boolean newEmployee = false;
	boolean searchEmployees = false;
	boolean searchPressed = false;
	
	//employee list and index of
	ArrayList<Employee> employeeList;
	int index = 0;
	
	//MAIN FORM
	/**
	 * Contains all information to set up swing GUI form including
	 * layout and action listeners for buttons.
	 */
	public MainForm(){
		super("Employee Record System");
		
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		//TITLE
		formLabel = new JLabel("Employee Information");
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.gridheight = 1;
		c.insets = new Insets(2,2,2,2);
		this.getContentPane().add(formLabel, c);
		
		c.anchor = GridBagConstraints.LINE_START;
		
		//NAME
		nameLabel = new JLabel("Name:");
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(2,2,2,2);
		this.getContentPane().add(nameLabel, c);
		name = new JTextField(15);
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.gridheight = 1;
		c.insets = new Insets(2,2,2,2);
		this.getContentPane().add(name, c);
		
		//GENDER
		genderLabel = new JLabel("Gender:");
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(2,2,2,2);
		this.getContentPane().add(genderLabel, c);
		genderM = new JRadioButton("Male");
		genders.add(genderM);
		c.gridx = 1;
		c.gridy = 3;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(2,2,2,2);
		this.getContentPane().add(genderM, c);
		genderF = new JRadioButton("Female");
		genders.add(genderF);
		c.gridx = 2;
		c.gridy = 3;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(2,2,2,2);
		this.getContentPane().add(genderF, c);
		
		//DATE OF BIRTH
		dobLabel = new JLabel("Date of Birth:");
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(2,2,2,2);
		this.getContentPane().add(dobLabel, c);

		dobDay = new JComboBox();
		dobDay.addItem("Day");
		//Adds the numbers 1-31 to dobDay comboBox
			for(int i=1; i<=31; i++){
				dobDay.addItem(i);
			};
		c.gridx = 1;
		c.gridy = 4;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(2,2,2,2);
		this.getContentPane().add(dobDay, c);
		
		dobMonth = new JComboBox();
		dobMonth.addItem("Month");
		//Adds the months Jan-Dec to dobMonth comboBox
			for(int j=0; j<month.length; j++){
				dobMonth.addItem(month[j]);
			};
		c.gridx = 2;
		c.gridy = 4;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(2,2,2,2);
		this.getContentPane().add(dobMonth, c);
		
		dobYear = new JComboBox();
		dobYear.addItem("Year");
		//Adds the numbers 1940-2000 to dobYear comboBox
			for(int k=2000; k>1940; k--){
				dobYear.addItem(k);
			}
		c.gridx = 3;
		c.gridy = 4;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(2,2,2,2);
		this.getContentPane().add(dobYear, c);
		
		//SALARY
		salaryLabel = new JLabel("Salary:");
		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(2,2,2,2);
		this.getContentPane().add(salaryLabel, c);

		salary = new JTextField(8);
		c.gridx = 1;
		c.gridy = 5;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.gridheight = 1;
		c.insets = new Insets(2,2,2,2);
		this.getContentPane().add(salary, c);
		
		//NIN
		ninLabel = new JLabel("NIN:");
		c.gridx = 0;
		c.gridy = 6;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(2,2,2,2);
		this.getContentPane().add(ninLabel, c);

		nin = new JTextField(8);
		c.gridx = 1;
		c.gridy = 6;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.gridheight = 1;
		c.insets = new Insets(2,2,2,2);
		this.getContentPane().add(nin, c);
		
		//EMAIL
		emailLabel = new JLabel("Email:");
		c.gridx = 0;
		c.gridy = 7;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(2,2,2,2);
		this.getContentPane().add(emailLabel, c);

		email = new JTextField(20);
		c.gridx = 1;
		c.gridy = 7;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.gridheight = 1;
		c.insets = new Insets(2,2,2,2);
		this.getContentPane().add(email, c);
		
		//START DATE
		startLabel = new JLabel("Start Date:");
		c.gridx = 0;
		c.gridy = 8;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(2,2,2,2);
		this.getContentPane().add(startLabel, c);
		
		startDay = new JComboBox();
		startDay.addItem("Day");
		//Adds the numbers 1-31 to startDay comboBox
		for(int i=1; i<=31; i++){
			startDay.addItem(i);
		};
		c.gridx = 1;
		c.gridy = 8;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(2,2,2,2);
		this.getContentPane().add(startDay, c);

		startMonth = new JComboBox();
		startMonth.addItem("Month");
		//Adds the months Jan-Dec to startMonth comboBox
		for(int j=0; j<month.length; j++){
			startMonth.addItem(month[j]);
		};
		c.gridx = 2;
		c.gridy = 8;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(2,2,2,2);
		this.getContentPane().add(startMonth, c);

		startYear = new JComboBox();
		startYear.addItem("Year");
		//adds the numbers 1985 to 2017 to startYear comboBox
		for(int k=2017; k>1985; k--){
			startYear.addItem(k);
		}
		c.gridx = 3;
		c.gridy = 8;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(2,2,2,2);
		this.getContentPane().add(startYear, c);
		
		//JOB TITLE
		jobLabel = new JLabel("Job Title:");
		c.gridx = 0;
		c.gridy = 9;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(2,2,2,2);
		this.getContentPane().add(jobLabel, c);

		job = new JTextField(20);
		c.gridx = 1;
		c.gridy = 9;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.gridheight = 1;
		c.insets = new Insets(2,2,2,2);
		this.getContentPane().add(job, c);
		
		//IMAGE
		imagePanel = new JPanel(new BorderLayout());
		c.gridx = 5;
		c.gridy = 2;
		c.gridwidth = 1;
		c.gridheight = 8;
		c.insets = new Insets(2,2,2,2);
		this.getContentPane().add(imagePanel, c);
		
		//SEARCH TEXT BOX
		searchText = new JTextField(20);
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.gridheight = 1;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(2,2,2,2);
		this.getContentPane().add(searchText, c);
		
		//SEARCH GENDER
		searchMale = new JRadioButton("Male");
		searchGender.add(searchMale);
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(2,2,2,2);
		this.getContentPane().add(searchMale, c);
		searchFemale = new JRadioButton("Female");
		searchGender.add(searchFemale);
		c.gridx = 2;
		c.gridy = 2;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(2,2,2,2);
		this.getContentPane().add(searchFemale, c);
		
		//SEARCH DATE
		searchDay = new JComboBox();
		searchDay.addItem("Day");
		//Adds the numbers 1-31 to searchDay comboBox
		for(int i=1; i<=31; i++){
			searchDay.addItem(i);
		};
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(2,2,2,2);
		this.getContentPane().add(searchDay, c);

		searchMonth = new JComboBox();
		searchMonth.addItem("Month");
		//Adds the months Jan-Dec to searchMonth comboBox
		for(int j=0; j<month.length; j++){
			searchMonth.addItem(month[j]);
		};
		c.gridx = 2;
		c.gridy = 2;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(2,2,2,2);
		this.getContentPane().add(searchMonth, c);

		searchYear = new JComboBox();
		searchYear.addItem("Year");
		//adds the numbers 1940 to 2017 to searchYear comboBox
		for(int k=2017; k>1940; k--){
			searchYear.addItem(k);
		}
		c.gridx = 3;
		c.gridy = 2;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(2,2,2,2);
		this.getContentPane().add(searchYear, c);
		
		//SEARCH COMBO BOX
		selectField = new JComboBox();
		selectField.addItem("Name");
		selectField.addItem("Gender");
		selectField.addItem("DOB");
		selectField.addItem("Salary");
		selectField.addItem("NIN");
		selectField.addItem("Email");
		selectField.addItem("Start Date");
		selectField.addItem("Job Title");
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(2,2,2,2);
		this.getContentPane().add(selectField, c);
		
		selectField.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		        searchInput();
		        searchPressed = false;
		        searchText.setText("");
		    }
		});
		
		//MESSAGE
		c.gridx = 0;
		c.gridy = 10;
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.gridheight = 1;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(2,2,2,2);
		this.getContentPane().add(message, c);
		 
		//BUTTONS
		//enter
		enter = new JButton("Enter");
		c.gridx = 0;
		c.gridy = 11;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(2,2,2,2);
		this.getContentPane().add(enter, c);
		
		enter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateEmployee();
			}
		});;
	
		//delete
		delete = new JButton("Delete");
		c.gridx = 2;
		c.gridy = 11;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(2,2,2,2);
		this.getContentPane().add(delete, c);
		
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteEmployee();
				message.setText("Employee Deleted");
			}
		});;
		
		//previous
		previous = new JButton("Previous");
		c.gridx = 0;
		c.gridy = 12;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(2,2,2,2);
		this.getContentPane().add(previous, c);
		
		previous.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				previousEmployee();
				message.setText(" ");
			}
		});;
		
		//next
		next = new JButton("Next");
		c.gridx = 2;
		c.gridy = 12;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(2,2,2,2);
		this.getContentPane().add(next, c);
		
		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nextEmployee();
				message.setText(" ");
			}
		});;
		
		//upload image
		uploadImage = new JButton("Upload Image");
		c.gridx = 5;
		c.gridy = 11;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.insets = new Insets(2,2,2,2);
		this.getContentPane().add(uploadImage, c);
		
		uploadImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				uploadImage();
			}
		});;
		
		//clear image
		clearImage = new JButton("Clear Image");
		c.gridx = 5;
		c.gridy = 12;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.insets = new Insets(2,2,2,2);
		this.getContentPane().add(clearImage, c);
		
		clearImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearImage();
			}
		});;
		
		//search
		search = new JButton("Search");
		c.gridx = 0;
		c.gridy = 11;
		c.gridwidth = 6;
		c.gridheight = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(2,2,2,2);
		this.getContentPane().add(search, c);
		
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				message.setText(" ");
				searchPressed = true;
				formVisible(true);
				next.setVisible(true);
				previous.setVisible(true);
				delete.setVisible(true);
				enter.setVisible(true);
				uploadImage.setVisible(true);
				search.setVisible(false);
				
				listEmployees();
				displayDetails();
			}
		});;
		
		//MENU
		//new employee
		createButton = new JButton("New Employee");
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(2,2,2,2);
		this.getContentPane().add(createButton, c);
				
		createButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				message.setText(" ");
				clearForm();
				newEmployee = true;
				viewEmployees = false;
				searchPressed = false;
				
				formVisible(true);
				next.setVisible(false);
				previous.setVisible(false);
				delete.setVisible(false);
				enter.setVisible(true);
				uploadImage.setVisible(true);
				clearImage.setVisible(true);
				search.setVisible(false);
				
				clearImage();
			}
		});;
				
		//view employees
		viewButton = new JButton("View Employees");
		c.gridx = 2;
		c.gridy = 0;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(2,2,2,2);
		this.getContentPane().add(viewButton, c);
		
		viewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				message.setText(" ");
				searchPressed = false;
				listEmployees();
				displayDetails();
				newEmployee = false;
				viewEmployees = true;
				
				formVisible(true);
				next.setVisible(true);
				previous.setVisible(true);
				delete.setVisible(true);
				enter.setVisible(true);
				uploadImage.setVisible(true);
				clearImage.setVisible(true);
				search.setVisible(false);
			}
		});;
		
		//search employees
		searchButton = new JButton("Search Employees");
		c.gridx = 4;
		c.gridy = 0;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(2,2,2,2);
		this.getContentPane().add(searchButton, c);
		
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				message.setText(" ");
				searchPressed = false;
				listEmployees();
				
				searchEmployees = true;
				newEmployee = false;
				viewEmployees = false;
				
				formVisible(false);
				next.setVisible(false);
				previous.setVisible(false);
				delete.setVisible(false);
				enter.setVisible(false);
				uploadImage.setVisible(false);
				clearImage.setVisible(false);
				searchInput();
				search.setVisible(true);
			}
		});;
		
		dao.getDBConnection();
		listEmployees();
		displayDetails();
		
		search.setVisible(false);
		searchText.setVisible(false);
		searchDay.setVisible(false);
		searchMonth.setVisible(false);
		searchYear.setVisible(false);
		searchMale.setVisible(false);
		searchFemale.setVisible(false);
		selectField.setVisible(false);

	}
	
	//SET FORM TO VISIBLE/INVISIBLE
	/**
	 * formVisible function.
	 * Sets form fields visible or not visible depending on what is required -
	 * e.g. the search box
	 * @param formVisible
	 */
	void formVisible(Boolean formVisible){
		if(formVisible == true){
			formLabel.setVisible(true);
			nameLabel.setVisible(true);
			genderLabel.setVisible(true);
			dobLabel.setVisible(true);
			salaryLabel.setVisible(true);
			ninLabel.setVisible(true);
			emailLabel.setVisible(true);
			startLabel.setVisible(true);
			jobLabel.setVisible(true);
			
			name.setVisible(true);
			genderM.setVisible(true);
			genderF.setVisible(true);
			dobDay.setVisible(true);
			dobMonth.setVisible(true);
			dobYear.setVisible(true);
			salary.setVisible(true);
			nin.setVisible(true);
			email.setVisible(true);
			startDay.setVisible(true);
			startMonth.setVisible(true);
			startYear.setVisible(true);
			job.setVisible(true);
			imagePanel.setVisible(true);
			
			searchText.setVisible(false);
			searchDay.setVisible(false);
			searchMonth.setVisible(false);
			searchYear.setVisible(false);
			searchMale.setVisible(false);
			searchFemale.setVisible(false);
			
			selectField.setVisible(false);
		}
		else{
			formLabel.setVisible(false);
			nameLabel.setVisible(false);
			genderLabel.setVisible(false);
			dobLabel.setVisible(false);
			salaryLabel.setVisible(false);
			ninLabel.setVisible(false);
			emailLabel.setVisible(false);
			startLabel.setVisible(false);
			jobLabel.setVisible(false);
			
			name.setVisible(false);
			genderM.setVisible(false);
			genderF.setVisible(false);
			dobDay.setVisible(false);
			dobMonth.setVisible(false);
			dobYear.setVisible(false);
			salary.setVisible(false);
			nin.setVisible(false);
			email.setVisible(false);
			startDay.setVisible(false);
			startMonth.setVisible(false);
			startYear.setVisible(false);
			job.setVisible(false);
			imagePanel.setVisible(false);
			
			searchText.setVisible(true);
			searchDay.setVisible(false);
			searchMonth.setVisible(false);
			searchYear.setVisible(false);
			searchMale.setVisible(false);
			searchFemale.setVisible(false);
			
			selectField.setVisible(true);
		}
	}
	
	//LIST EMPLOYEES
	/**
	 * Calls either selectAllEmployees or a search function from EmployeeDAO
	 * depending on whether search button has been pressed or displaying all employees.
	 * Rewrites the employeeList.
	 */
	void listEmployees(){
		if(searchPressed == true){
			if(selectField.getSelectedItem() == "DOB" || selectField.getSelectedItem() == "Start Date"){
				if(searchDay.getSelectedIndex() == 0 || searchMonth.getSelectedIndex() == 0 || searchYear.getSelectedIndex() == 0){
						searchText.setText("Please complete all date fields");
						formVisible(false);
						next.setVisible(false);
						previous.setVisible(false);
						delete.setVisible(false);
						enter.setVisible(false);
						uploadImage.setVisible(false);
						clearImage.setVisible(false);
						search.setVisible(true);
						searchDay.setVisible(false);
						searchMonth.setVisible(false);
						searchYear.setVisible(false);
						searchMale.setVisible(false);
						searchFemale.setVisible(false);
						searchText.setVisible(true);
				}
				else{
					String formattedDate = formatDate(searchDay, searchMonth, searchYear);
					employeeList = dao.searchByCombo(formattedDate, selectedSearchField());
					
					searchDay.setSelectedIndex(0);
					searchMonth.setSelectedIndex(0);
					searchYear.setSelectedIndex(0);
				}
			}
			else if(selectField.getSelectedItem() == "Gender"){
				String selectedGender;
				if(searchMale.isSelected() == true){
					selectedGender = "M";
				}
				else{
					selectedGender = "F";
				}
				employeeList = dao.searchByRadio(selectedGender, selectedSearchField());
				
				searchMale.setSelected(false);
				searchFemale.setSelected(false);
			}
			else{
				employeeList = dao.searchByString(searchText.getText(), selectedSearchField());
			}
		}
		else{
			employeeList = dao.selectAllEmployees();
		}
	}
	
	//DISPLAY IMAGE
	/**
	 * Clears image panel and adds new image if available
	 * @param image
	 */
	void displayImage(byte[] image) {
		imagePanel.removeAll();
		
		if (image != null){
			ImageIcon imageIcon;
			imageIcon = new ImageIcon(image);
			ImageIcon scaledIcon = scaleImage(imageIcon, imageIcon.getIconWidth(), imageIcon.getIconHeight());
			JLabel label = new JLabel("", scaledIcon, JLabel.CENTER);
			imagePanel.add(label, BorderLayout.CENTER );
		}
		
		imagePanel.revalidate();
		imagePanel.repaint();
	}
	
	//CLEAR IMAGE
	/**
	 * Clears image panel.
	 */
	void clearImage() {
		imagePanel.removeAll();
		imagePanel.revalidate();
		imagePanel.repaint();
	}

	
	//DISPLAY DETAILS
	/**
	 * If employeeList is empty show search box and show error message.
	 * Otherwise display employee info in form fields.
	 */
	void displayDetails(){
		//if no search results found
		if (employeeList.isEmpty() == true){
			formVisible(false);
			
			next.setVisible(false);
			previous.setVisible(false);
			delete.setVisible(false);
			enter.setVisible(false);
			uploadImage.setVisible(false);
			selectField.setVisible(true);
			search.setVisible(true);
			searchText.setVisible(true);
			
			searchText.setText("No results found");
		}
		else{
	 		Employee selectedEmployee = employeeList.get(index);
			if (selectedEmployee != null){
				//put employee info into form fields
				name.setText(selectedEmployee.getName());
				
				genderM.setSelected(selectedEmployee.getGender() == 'M' || selectedEmployee.getGender() == 'm');
				genderF.setSelected(selectedEmployee.getGender() == 'F' || selectedEmployee.getGender() == 'f');
				
				dobDay.setSelectedIndex(selectedEmployee.getDobDay());
				dobMonth.setSelectedIndex(selectedEmployee.getDobMonth());
				dobYear.setSelectedItem(selectedEmployee.getDobYear());
				
				salary.setText(selectedEmployee.getSalary());
				
				nin.setText(selectedEmployee.getNatInscNo());
				
				email.setText(selectedEmployee.getEmail());
				
				startDay.setSelectedIndex(selectedEmployee.getStartDay());
				startMonth.setSelectedIndex(selectedEmployee.getStartMonth());
				startYear.setSelectedItem(selectedEmployee.getStartYear());
				
				job.setText(selectedEmployee.getTitle());
				
				displayImage(selectedEmployee.getImage());
				
			}
		}
	}
		
	//PREVIOUS EMPLOYEE
	/**
	 * Takes one off id number when scrolling through -
	 * shows previous employee.
	 * Loops back round after reaching beginning.
	 */
	void previousEmployee(){
		if(index == 0){
			index = employeeList.size()-1;
		}
		else{
			index--;
		}
		
		displayDetails();
	}
	
	//NEXT EMPLOYEE
	/**
	 * Adds one to id number when scrolling through -
	 * shows next employee.
	 * Loops back round after reaching end.
	 */
	void nextEmployee(){
		if(index == employeeList.size()-1){
			index = 0;
		}
		else{
			index++;
		}
		
		displayDetails();
	}
	
	//CLEAR FORM
	/**
	 * Sets all text fields in form to empty.
	 * Sets comboBoxes back to first option.
	 */
	void clearForm(){
		name.setText("");
		
		genderM.setSelected(false);
		genderF.setSelected(false);
		
		dobDay.setSelectedIndex(0);
		dobMonth.setSelectedIndex(0);
		dobYear.setSelectedIndex(0);
		
		salary.setText("");
		nin.setText("");
		email.setText("");
		
		startDay.setSelectedIndex(0);
		startMonth.setSelectedIndex(0);
		startYear.setSelectedIndex(0);
		
		job.setText("");
	}
	
	//UPDATE EMPLOYEE
	/**
	 * When enter button pressed.
	 * Checks if all date fields are selected.
	 * Checks if new employee or if it already exists.
	 * Pulls info from form and uses it in either insertEmployee to create new
	 * or in insertEmployeeeAtId to update existing.
	 */
	void updateEmployee(){
		Employee employeeUpdate;
		
		if(dobDay.getSelectedIndex() == 0 || dobMonth.getSelectedIndex() == 0 || dobYear.getSelectedIndex() == 0 ||
			startDay.getSelectedIndex() == 0 || startMonth.getSelectedIndex() == 0 || startYear.getSelectedIndex() == 0){
			message.setText("Please complete all date fields");
		}
		else{
			if(newEmployee == false){
				employeeUpdate = employeeList.get(index);
			}
			else{
				employeeUpdate = new Employee();
			}
			
			//get new details from form
			employeeUpdate.setName(name.getText());
			
			if(genderM.isSelected()){
				employeeUpdate.setGender('M');
			}
			else if(genderF.isSelected()){
				employeeUpdate.setGender('F');
			}
			
			employeeUpdate.setDob(formatDate(dobDay, dobMonth, dobYear));
			
			employeeUpdate.setSalary(salary.getText());
			employeeUpdate.setNatInscNo(nin.getText());
			employeeUpdate.setEmail(email.getText());
			
			employeeUpdate.setStartDate(formatDate(startDay, startMonth, startYear));
			
			employeeUpdate.setTitle(job.getText());
			
			if (uploadedImage != null) {
				employeeUpdate.setImage(uploadedImage);
				uploadedImage = null;
			}
			
			//update info at id
			if(newEmployee == true){
				if(dao.insertEmployee(employeeUpdate)){
					listEmployees();
					message.setText("Employee Created");
				}
				else{
					message.setText("Error in creating Employee");
				}
				
			}
			else{
				if(dao.insertEmployeeAtID(employeeUpdate, employeeUpdate.getId())){
					message.setText("Employee Updated");
				}
				else{
					message.setText("Error in update");
				}
			}
		}
	}
	
	//DELETE EMPLOYEE
	/**
	 * Calls deleteEmployeeById from EmployeeDAO.
	 * Displays first available employee.
	 */
	void deleteEmployee(){
		String id = employeeList.get(index).getId();
		
		dao.deleteEmployeeByID(id);
		
		employeeList.remove(index);
		
		index = 0;
		displayDetails();
	}
	
	//SEARCH RESULTS
	/**
	 * Sets employee list to contain results from search.
	 */
	void searchResults(){
		employeeList = dao.searchByString("", selectedSearchField());
	}
	
	//FORMAT DATE
	/**
	 * Pulls comboBox selections from form and concats them into one date string to go into database.
	 * @param day
	 * @param month
	 * @param year
	 * @return date as string in format dd/mm/yyyy
	 */
	static String formatDate(JComboBox day, JComboBox month, JComboBox year){
		return String.format("%02d", day.getSelectedItem()) + "/" + String.format("%02d", month.getSelectedIndex()) + "/" + year.getSelectedItem().toString();
	}
	
	//SCALE IMAGE
	/**
	 * Scales image to appropriate size.
	 * Detemines whether width or height of image is larger.
	 * Sets larger side to max value and scales the other appropriately.
	 * @param imageIcon
	 * @param width
	 * @param height
	 * @return newly scaled imageicon
	 */
	public ImageIcon scaleImage(ImageIcon imageIcon, int width, int height){
		int newWidth;
		int newHeight;
		
		int maxWidth = 150;
		int maxHeight = 200;
		
		if(height >= width){
			newHeight = 200;
			newWidth = 150;
			
		}
		else{
			newWidth = 200;
			newHeight = 150;
		}
		
		Image image = imageIcon.getImage();
		Image scaledImage = image.getScaledInstance(newWidth, newHeight, java.awt.Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(scaledImage);
		
		return imageIcon;
	}
	
	//UPLOAD IMAGE
	/**
	 * Called when upload image button is pressed.
	 * Allows user to select image using file chooser.
	 * Adds file to database as blob type.
	 */
	public void uploadImage(){
		JFileChooser fileChooser = new JFileChooser();
		int returnVal = 0;
		returnVal = fileChooser.showOpenDialog(this);
		
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File imageFile = fileChooser.getSelectedFile();
			byte[] imageBytes = new byte[(int) imageFile.length()];
			try {
				FileInputStream fis = new FileInputStream(imageFile);
				fis.read(imageBytes);
				uploadedImage = imageBytes;
				displayImage(uploadedImage);
			} 
			catch (FileNotFoundException e) {
				e.printStackTrace();
				//error message
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	//SELECTED SEARCH FIELD
	/**
	 * Converts combo box selection to a string for SQL query if needed.
	 * Only required on job title and start date,
	 * others don't need to be changed.
	 * @return field name as string to be inserted into sql statement
	 */
	public String selectedSearchField(){
		if(selectField.getSelectedItem() == "Job Title"){
			return "JobTitle";
		}
		else if(selectField.getSelectedItem() == "Start Date"){
			return "StartDate";
		}
		else{
			return (String)selectField.getSelectedItem();
		}
	}
	
	//SEARCH INPUT
	/**
	 * When on the search tab, sets the input to what is required
	 * for the field e.g. text box for name, combo box for date.
	 */
	public void searchInput(){
		if(selectField.getSelectedItem() == "DOB" || selectField.getSelectedItem() == "Start Date"){
			searchDay.setVisible(true);
			searchMonth.setVisible(true);
			searchYear.setVisible(true);
			searchMale.setVisible(false);
			searchFemale.setVisible(false);
			searchText.setVisible(false);
		}
		else if(selectField.getSelectedItem() == "Gender"){
			searchDay.setVisible(false);
			searchMonth.setVisible(false);
			searchYear.setVisible(false);
			searchMale.setVisible(true);
			searchFemale.setVisible(true);
			searchText.setVisible(false);
		}
		else{
			searchDay.setVisible(false);
			searchMonth.setVisible(false);
			searchYear.setVisible(false);
			searchMale.setVisible(false);
			searchFemale.setVisible(false);
			searchText.setVisible(true);
		}
	}
}
