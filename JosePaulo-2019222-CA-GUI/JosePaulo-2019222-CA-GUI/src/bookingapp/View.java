package bookingapp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


public class View extends JFrame implements PropertyChangeListener {
	//home page email and password for registered users
	private JPanel homePane;	
    private JPanel registerPane;
    private JPanel professionP;
    private JTextField fullName;    
    private JTextField phone;
    private JTextField email;
    private JTextField writeReview;
    private JTextField profession;
    private JTextField address;    
    private JPasswordField passwordField;
    private JPasswordField confirmPassword;
    public JButton registerBtn;
    public JButton loginBtn;
    public JButton serviceProviderBtn;
    public JButton clientBtn;
    public JButton deleteBtn;
    public JButton deleteCompletedBtn;
    public JButton sendReviewBtn;
    public JButton selectBarberBtn;
    public JButton selectHairdresserBtn;
    public JButton selectNameBtn;
    public JButton logOutBtn;
    public JButton markCompletedBtn;
    public JButton acceptBtn;
    public JButton declineBtn;
    public JButton deleteCanceledBtn;
    public JButton submitDateBtn;
    public JButton deleteDateBtn;
    public JButton backtoMainBtn;
    private JComboBox appointmentTime;
    private JComboBox deleteDatesList;
	private JComboBox deleteTimeList;
	private JComboBox locationList;
	private JComboBox namesList;
	private JComboBox appointmentsDates;	
	private JComboBox appointmentsTime;
	
    private static final long serialVersionUID = 1L; //field used in the day picker
    public String selectedTime;
    JFormattedTextField dayPickerTextField = new JFormattedTextField(DateFormat.getDateInstance(DateFormat.SHORT));  //set the style of the calendar and teh dates format
	Controller controller;	
	String [][] tableAppointments; //hold the list of appontments created by the service provider
	String [][] clientAppointments; //hold the list of appontments created by the client
	
	public View(Controller controller, String [][] tableAppointments, String[][] clientAppointments) {
		
		this.controller         = controller;
		this.tableAppointments  = tableAppointments;
		this.clientAppointments = clientAppointments;
		
		frameSettings();
		
		homePageComponents();
		
		//clientHomePage();
		//finishBooking();
		//serviceProviderHomePage();
		//registerFirstTime();
		//customerForm(700, 800);
		//serviceProviderForm(700, 800);
	
		
	}
	
	public void homePageComponents() { //home page componets 
		
		this.getContentPane().removeAll();	//clean the panel and load the new information so there is o multiplu windows
		this.setSize(500, 500);
		
		homePane = new JPanel();
		
		this.add(homePane);		
		homePane.setLayout(null);
		Font f1 = new Font("TimesNewRoman", Font.BOLD, 18);
		Font f2 = new Font("TimesNewRoman", Font.PLAIN, 16);
		
		JLabel username = new JLabel("Email: ");
		email = new JTextField(20);
		username.setBounds(70, 150, 80, 25); //as it is an small window there is no layout manager
		username.setFont(f2);
		email.setBounds(150, 150, 250, 25);		
		homePane.add(username);
		homePane.add(email);		
		
		JLabel pword = new JLabel("Password: ");
		passwordField = new JPasswordField();
		//margin left = 100, margin top = 100, width = 165, height = 25 						
		passwordField.setBounds(150, 220, 250, 25);
		pword.setBounds(70, 220, 80, 25);
		pword.setFont(f2);
		homePane.add(pword);
		homePane.add(passwordField);
		
		loginBtn = new JButton("Log In");
		loginBtn.setBounds(70, 300, 330, 25);
		loginBtn.setForeground(Color.white);
		loginBtn.setBackground(Color.blue);
		loginBtn.setFont(f1);
		loginBtn.addActionListener(this.controller);
		homePane.add(loginBtn);
		
		registerBtn = new JButton("Create Account");		
		registerBtn.setBounds(70, 350, 330, 25);
		registerBtn.setForeground(Color.white);
		registerBtn.setBackground(Color.GREEN);
		registerBtn.setFont(f1);
		registerBtn.addActionListener(this.controller);
		homePane.add(registerBtn);	
		
		validation();
		
	}
	
	public void registerFirstTime() { //loads the page where the useer can choose if he wants be a cleinte or a serice provider
		
		this.getContentPane().removeAll();	
		this.setSize(500, 500);
		
		registerPane = new JPanel();
		registerPane.setLayout(null);
		Font f1 = new Font("TimesNewRoman", Font.BOLD, 18);
		Font f2 = new Font("TimesNewRoman", Font.PLAIN, 16);
		
		clientBtn = new JButton("I am a Client");
		clientBtn.setBounds(70, 150, 330, 25);
		clientBtn.setForeground(Color.white);
		clientBtn.setBackground(Color.blue);
		clientBtn.setFont(f1);
		clientBtn.addActionListener(this.controller);
		registerPane.add(clientBtn);
		
		registerBtn = new JButton("I am a Service Provider");		
		registerBtn.setBounds(70, 300, 330, 25);
		registerBtn.setForeground(Color.white);
		registerBtn.setBackground(Color.GREEN);
		registerBtn.setFont(f1);
		registerBtn.addActionListener(this.controller);
		registerPane.add(registerBtn);	
		
		this.add(registerPane);
		validation();
		
		
	}
	
	public void serviceProviderForm() { //show the form so the service provider can enter their information 
		
		this.getContentPane().removeAll();		
		this.setSize(700, 800);
		
		
		registerPane       = new JPanel(new GridBagLayout());// in this whole program I use the gridBagLayout i think it is very easy to positioning the elements in the window	
		fullName           = new JTextField(35);    
	    email              = new JTextField(35);
	    phone              = new JTextField(35);
	    profession         = new JTextField(35);
	    address            = new JTextField(35);    
	    passwordField      = new JPasswordField(35);
	    confirmPassword    = new JPasswordField(35);
	    
		JLabel lfullName        = new JLabel("Full name: ");    
		JLabel lemail           = new JLabel("Email: ");
		JLabel lphone           = new JLabel("Phone: ");
		JLabel lprofession      = new JLabel("Profession: ");
		JLabel laddress         = new JLabel("Address: ");    
		JLabel lpasswordField   = new JLabel("Password: ");
		JLabel lConfirmPassword = new JLabel("Confirm Password: ");
		JLabel formName         = new JLabel("I am a Service Provider"); 
		
		Font f1 = new Font("TimesNewRoman", Font.BOLD, 18);
		Font f2 = new Font("TimesNewRoman", Font.PLAIN, 16);
		formName.setFont(f1);
		lfullName.setFont(f2);
		lemail.setFont(f2);
		lphone.setFont(f2);
		lprofession.setFont(f2);
		laddress.setFont(f2);
		lpasswordField.setFont(f2);
		lConfirmPassword.setFont(f2);
		

		ButtonGroup btnGroup = new ButtonGroup();
		serviceProviderBtn = new JButton("Create Service Account");
		serviceProviderBtn.setForeground(Color.white);
		serviceProviderBtn.setBackground(Color.GREEN);
		serviceProviderBtn.addActionListener(this.controller);
		serviceProviderBtn.setFont(f1);
		
		GridBagConstraints grid1 = new GridBagConstraints();  //i can choose the positin of the elemtns bassed on the X or Y posiotn in the frame
		grid1.anchor = GridBagConstraints.LINE_START;
		grid1.insets = new Insets(10, 10, 25, 25);
		grid1.gridx =0; //position X colum in the window
		grid1.gridy =0; //position y rows 
		registerPane.add(formName, grid1);
		grid1.gridx =0; 
		grid1.gridy =1;
		registerPane.add(lfullName, grid1);
		grid1.gridx =1; 
		grid1.gridy =1;
		registerPane.add(fullName, grid1);
		grid1.gridx =0; 
		grid1.gridy =2;
		registerPane.add(lemail, grid1);
		grid1.gridx =1; 
		grid1.gridy =2;
		registerPane.add(email, grid1);
		grid1.gridx =0; 
		grid1.gridy =3;
		registerPane.add(lphone, grid1);
		grid1.gridx =1; 
		grid1.gridy =3;
		registerPane.add(phone, grid1);
		grid1.gridx =0; 
		grid1.gridy =4;
		registerPane.add(lprofession, grid1);		
		grid1.gridx =1; 
		grid1.gridy =4;
		registerPane.add(profession, grid1);
		grid1.gridx =0; 
		grid1.gridy =5;
		registerPane.add(laddress, grid1);
		grid1.gridx =1; 
		grid1.gridy =5;
		registerPane.add(address, grid1);
		grid1.gridx =0; 
		grid1.gridy =6;
		registerPane.add(lpasswordField, grid1);
		grid1.gridx =1; 
		grid1.gridy =6;
		registerPane.add(passwordField, grid1);
		grid1.gridx =0; 
		grid1.gridy =7;
		registerPane.add(lConfirmPassword, grid1);
		grid1.gridx =1; 
		grid1.gridy =7;
		registerPane.add(confirmPassword, grid1);		
		grid1.gridx =0; 
		grid1.gridy =8;
		registerPane.add(serviceProviderBtn, grid1);
		this.add(registerPane);
		
		validation();
		
		
	}
	
	public void customerForm() { //client can enter thei infromation also using the gridBagLAyout
		
		this.getContentPane().removeAll();	
		this.setSize(700, 700);
		
		registerPane       = new JPanel(new GridBagLayout());
		fullName           = new JTextField(35);    
	    email              = new JTextField(35);
	    phone              = new JTextField(35);
	    passwordField      = new JPasswordField(35);
	    confirmPassword    = new JPasswordField(35);
	    
	    JLabel lfullName        = new JLabel("Full name: ");    
		JLabel lemail           = new JLabel("Email: ");
		JLabel lphone           = new JLabel("Phone: ");	
		JLabel lpasswordField   = new JLabel("Password: ");
		JLabel lConfirmPassword = new JLabel("Confirm Password: ");
		JLabel formName         = new JLabel("I am a Customer");
		

		Font f1 = new Font("TimesNewRoman", Font.BOLD, 18);
		Font f2 = new Font("TimesNewRoman", Font.PLAIN, 16);
		formName.setFont(f1);
		lfullName.setFont(f2);
		lemail.setFont(f2);
		lphone.setFont(f2);
		lpasswordField.setFont(f2);
		lConfirmPassword.setFont(f2);
		
		registerBtn = new JButton("Create User Account");
//		registerBtn.setActionCommand("button_click");
		registerBtn.addActionListener(this.controller);
		registerBtn.setForeground(Color.white);
		registerBtn.setBackground(Color.GREEN);
		registerBtn.setFont(f1);
		
		GridBagConstraints grid1 = new GridBagConstraints();
		grid1.anchor = GridBagConstraints.LINE_START;
		grid1.insets = new Insets(10, 10, 25, 25);
		grid1.gridx =0; //position X colum
		grid1.gridy =0; //position y rows
		registerPane.add(formName, grid1);
		grid1.gridx =0; 
		grid1.gridy =1;
		registerPane.add(lfullName, grid1);
		grid1.gridx =1; 
		grid1.gridy =1;
		registerPane.add(fullName, grid1);
		grid1.gridx =0; 
		grid1.gridy =2;
		registerPane.add(lemail, grid1);
		grid1.gridx =1; 
		grid1.gridy =2;
		registerPane.add(email, grid1);
		grid1.gridx =0; 
		grid1.gridy =3;
		registerPane.add(lphone, grid1);
		grid1.gridx =1; 
		grid1.gridy =3;
		registerPane.add(phone, grid1);
		grid1.gridx =0; 
		grid1.gridy =4;
		registerPane.add(lpasswordField, grid1);
		grid1.gridx =1; 
		grid1.gridy =4;
		registerPane.add(passwordField, grid1);
		grid1.gridx =0; 
		grid1.gridy =5;
		registerPane.add(lConfirmPassword, grid1);
		grid1.gridx =1; 
		grid1.gridy =5;
		registerPane.add(confirmPassword, grid1);		
		grid1.gridx =0; 
		grid1.gridy =6;
		registerPane.add(registerBtn, grid1);
		this.add(registerPane);
		
		validation();
		
	}
		
	public void serviceProviderHomePage() { //loads the service provider home page also using a gridBagLAyout
		
		this.getContentPane().removeAll();	
		this.setSize(1100, 800);
		this.setTitle("Test");
		JPanel mainPane = new JPanel();
		JPanel datePane = new JPanel();
		homePane = new JPanel(new GridBagLayout());
		CalendarFrame calendarFrame = new CalendarFrame(); //create the calendar frame
		calendarFrame.addPropertyChangeListener(this);
		
		calendarFrame.setVisible(true);
		
		JLabel upcoming          = new JLabel("Upcoming Appointments");
		JLabel completed         = new JLabel("Completed Appointments");	
		JLabel requested         = new JLabel("Appointmnts Request");
		JLabel cancelled         = new JLabel("Appointment Cancelled");
		JLabel availableDates    = new JLabel("Add more Dates");
		JLabel deleteDates       = new JLabel("Delete Appointment");
		JLabel msg               = new JLabel("Welcome User");
		
		dayPickerTextField.setValue(new Date()); //set the current date as first date in the calendar
		dayPickerTextField.setPreferredSize(new Dimension(140, 30)); //the size of the text field
		JButton dayPicker = new JButton("Pick a Date"); //btn that opends the calendar
		dayPicker.addActionListener(new ActionListener() { //acrtion listener has to be in this class because there is loasds of diferents elements that are not
														   //accepted in the controller		
			@Override
			public void actionPerformed(ActionEvent e) {
				calendarFrame.setLocation(dayPickerTextField.getLocationOnScreen().x, //the position of the calendar when loads
	    		(dayPickerTextField.getLocationOnScreen().y + dayPickerTextField.getHeight()));
	    		
				Date selectedDate = (Date) dayPickerTextField.getValue(); //get the date has to be a double click
				calendarFrame.resetSelection(selectedDate);
	    		calendarFrame.setVisible(true);
				
			}

		});
		datePane.add(dayPickerTextField); 
		datePane.add(dayPicker);		
		
		String[] datesToDelete = controller.datesToDelete(); //array to delete teh dates
		String[] timeToDelete  = controller.timeToDelete();	//array to delete teh time oth in teh jcombobox
		
		String[] quarterHours = {"00","15","30","45"};
		String[] times = new String[96]; //this is how i choose to show the hours for the appointments with interval of 15 minutes
		int count = 0;
		for(int i = 0; i < 24; i++) {
					
			int j;
		    for( j = 0; j < 4; j++) {
		    	String time = i + ":" + quarterHours[j];
		        if(i < 10) {
		            time = "0" + time;
		        }		        
			    times[count] = time;
			    count++;		        
		    }		
		}
		
		appointmentTime = new JComboBox(times);	
		appointmentTime.setPreferredSize(new Dimension(250,25));		
		
		deleteDatesList = new JComboBox(datesToDelete); 
		deleteDatesList.setPreferredSize(new Dimension(250,25));
		
		deleteTimeList = new JComboBox(timeToDelete); 
		deleteDatesList.setPreferredSize(new Dimension(250,25));
		
		JPanel deleteAppointments = new JPanel(new GridLayout(2,1, 0, 10));
		deleteAppointments.add(deleteDatesList);
		deleteAppointments.add(deleteTimeList);

		
		String[] columnNames = {"Date", "Time", "Client", "Email", "Phone", "Barber/Hairdresser", "Status"};	
		
		JTable appointments    = new JTable(clientAppointments, columnNames); ///jtable with the appointments I did not had time to finish the program so the jtables
		appointments.setRowSelectionAllowed(true);								// are not displayin all teh correct data
		appointments.setPreferredScrollableViewportSize(new Dimension(750, 150)); //I thouthg the Ca was due on the 17/12 but i was worng 
		JScrollPane scrollBar = new JScrollPane(appointments);		
		
		JTable requestAppointments = new JTable(clientAppointments, columnNames);
		requestAppointments.setRowSelectionAllowed(true);
		requestAppointments.setPreferredScrollableViewportSize(new Dimension(750, 150));
		JScrollPane scrollBar1 = new JScrollPane(requestAppointments);
		
		JTable cancelledAppointments = new JTable(clientAppointments, columnNames);
		cancelledAppointments.setRowSelectionAllowed(true);
		cancelledAppointments.setPreferredScrollableViewportSize(new Dimension(750, 150));
		JScrollPane scrollBar2 = new JScrollPane(cancelledAppointments);
		
		JTable completedAppointments = new JTable(clientAppointments, columnNames);
		completedAppointments.setRowSelectionAllowed(true);
		completedAppointments.setPreferredScrollableViewportSize(new Dimension(750, 150));
		JScrollPane scrollBar3 = new JScrollPane(completedAppointments);
		
		
		deleteBtn = new JButton("Cancel");		//does not work i could not finish this part of the program		
		deleteBtn.setForeground(Color.white);
		deleteBtn.setBackground(Color.RED);	
		
		markCompletedBtn = new JButton("Completed");//does not work i could not finish this part of the program		
		markCompletedBtn.setForeground(Color.white);
		markCompletedBtn.setBackground(Color.DARK_GRAY);	
		JPanel btnGroup = new JPanel(new GridLayout(2,1, 0, 10));
		btnGroup.add(markCompletedBtn);
		btnGroup.add(deleteBtn);
		
		acceptBtn    = new JButton("Accept");//does not work i could not finish this part of the program		
		acceptBtn.setPreferredSize(new Dimension(100, 25));
		acceptBtn.setForeground(Color.white);
		acceptBtn.setBackground(Color.DARK_GRAY);
		
		declineBtn  = new JButton("Decline");//does not work i could not finish this part of the program		
		declineBtn.setForeground(Color.white);
		declineBtn.setBackground(Color.RED);
		JPanel btnGroup2 = new JPanel(new GridLayout(2,1, 0, 10));
		btnGroup2.add(acceptBtn);
		btnGroup2.add(declineBtn);
		
		deleteCompletedBtn = new JButton("Delete");//does not work i could not finish this part of the program		
		deleteCompletedBtn.setPreferredSize(new Dimension(100, 25));
		deleteCompletedBtn.setForeground(Color.white);
		deleteCompletedBtn.setBackground(Color.RED);
	    
		deleteCanceledBtn = new JButton("Delete!");//does not work i could not finish this part of the program		
		deleteCanceledBtn.setPreferredSize(new Dimension(100, 25));
		deleteCanceledBtn.setForeground(Color.white);
		deleteCanceledBtn.setBackground(Color.RED);
		
		submitDateBtn = new JButton("Submit Date"); //this button works fine and submit the date for the appointments and insert in the database
		submitDateBtn.addActionListener(this.controller);
		submitDateBtn.setPreferredSize(new Dimension(150 ,25));
		submitDateBtn.setForeground(Color.white);
		submitDateBtn.setBackground(Color.GREEN);
		
		deleteDateBtn = new JButton("Delete Date"); //this button works fine and submit the date for the appointments and delete in from database
		deleteDateBtn.addActionListener(this.controller);
		deleteDateBtn.setPreferredSize(new Dimension(150 ,25));
		deleteDateBtn.setForeground(Color.white);
		deleteDateBtn.setBackground(Color.RED);
		
	    logOutBtn = new JButton("Log Out"); //log out
	    logOutBtn.addActionListener(this.controller);
	    logOutBtn.setPreferredSize(new Dimension(150 ,25));
	    logOutBtn.setForeground(Color.black);
	    logOutBtn.setBackground(Color.orange);
		
		
		GridBagConstraints grid = new GridBagConstraints();
		grid.anchor = GridBagConstraints.LINE_START;
		grid.insets = new Insets(20, 20, 20, 20);
		grid.gridx =0; //position X colum
		grid.gridy =0; //position y rows
		homePane.add(msg, grid);
		grid.gridx =0;
		grid.gridy =1;
		homePane.add(upcoming, grid); 
		grid.gridx =0;
		grid.gridy =2;
		homePane.add(scrollBar, grid); 
		grid.gridx = 1;
		grid.gridy = 2;
		homePane.add(btnGroup, grid);
		grid.gridx = 0;
		grid.gridy = 3;
		homePane.add(requested, grid);
		grid.gridx = 0;
		grid.gridy = 4;
		homePane.add(scrollBar1, grid);
		grid.gridx = 1;
		grid.gridy = 4;
		homePane.add(btnGroup2, grid);
		grid.gridx = 0;
		grid.gridy = 5;
		homePane.add(cancelled, grid);
		grid.gridx = 0;
		grid.gridy = 6;
		homePane.add(scrollBar2, grid);
		grid.gridx = 1;
		grid.gridy = 6;
		homePane.add(deleteCanceledBtn, grid);
		grid.gridx = 0;
		grid.gridy = 7;
		homePane.add(completed, grid);
		grid.gridx = 0;
		grid.gridy = 8;
		homePane.add(scrollBar3, grid);
		grid.gridx = 1;
		grid.gridy = 8;
		homePane.add(deleteCompletedBtn, grid);
		grid.gridx = 0;
		grid.gridy = 9;
		homePane.add(availableDates, grid);
		grid.gridx = 0;
		grid.gridy = 10;
		homePane.add(datePane, grid);
		grid.gridx = 0;
		grid.gridy = 11;
		homePane.add(appointmentTime, grid);
		grid.gridx = 0;
		grid.gridy = 12;
		homePane.add(submitDateBtn, grid);
		grid.gridx = 0;
		grid.gridy = 13;
		homePane.add(deleteDates, grid);
		grid.gridx = 0;
		grid.gridy = 14;
		homePane.add(deleteAppointments, grid);
		grid.gridx = 0;
		grid.gridy = 15;
		homePane.add(deleteDateBtn, grid);	
		grid.gridx = 1;
		grid.gridy = 15;
		homePane.add(logOutBtn, grid);
		
		JScrollPane scrollBarPane = new JScrollPane(homePane, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollBarPane.getViewport().setMinimumSize(new Dimension(1000, 750)); //as there is multiple elements in the main panel i added a oter panel and a extra scroll bar
		scrollBarPane.getViewport().setPreferredSize(new Dimension(1000, 750));
		mainPane.add(scrollBarPane);	
		
		this.add(mainPane);
		validation();
		
	}
	
	public String getAppointmentTime() {
		
		selectedTime = String.valueOf(appointmentTime.getSelectedItem()); //hold the time selected for the appointments
		
		return selectedTime;
	}
	
	public String getAppointmentDate() {	//hold the date selected for the appointments	
		
		return dayPickerTextField.getText();
		
	}
	
	public String getDeleteTime() {//hold the time selected for the appointments to delete from teh database
		
		String deletedTime = String.valueOf(deleteTimeList.getSelectedItem());
		
		return deletedTime;
	}
	
	public String getDeleteDate() { //hold the date selected for the appointments to delete from teh database
		
		String deletedDate = String.valueOf(deleteDatesList.getSelectedItem());
		
		return deletedDate;
		
	}
	
	public void clientHomePage() { // loads the homepage for the clients
		

		this.getContentPane().removeAll();	
		this.setSize(1100, 800);
		this.setTitle("Test");
		JPanel mainPane = new JPanel();
		homePane = new JPanel(new GridBagLayout());
	
		
		JLabel upcoming          = new JLabel("Upcoming Appointments");
		JLabel completed         = new JLabel("Completed Appointments");	
		JLabel searchHairdresser = new JLabel("Search by Hairdresser/Barber Location");
		JLabel searchByName      = new JLabel("Search by Name");
		JLabel reviewBox         = new JLabel("Tell us abou your Experience with: ");
		JLabel msg               = new JLabel("Welcome User");	
		
		String[] names = controller.serviceProviderNames();
		String[] location = controller.servicePRoviderLocations();

		locationList = new JComboBox(location); 
		locationList.setPreferredSize(new Dimension(250,25));
		namesList       = new JComboBox(names);
		namesList.setPreferredSize(new Dimension(250,25));
		
		JComboBox review          = new JComboBox(names);
		review.setPreferredSize(new Dimension(250,25));

		
		Font f1 = new Font("TimesNewRoman", Font.BOLD, 18);
		Font f2 = new Font("TimesNewRoman", Font.PLAIN, 16);

		
		String[] columnNames          = {"Date", "Time", "Barber/Hairdresser", "Address", "Email", "Phone", "Status"};	
		String[] columnNamesSProvider = {"Date", "Time", "Client", "Email", "Phone", "Barber/Hairdresser", "Status"};
		JTable appointments    = new JTable(tableAppointments, columnNamesSProvider);	
		appointments.setRowSelectionAllowed(true);
		appointments.setPreferredScrollableViewportSize(new Dimension(750, 100));	
		JScrollPane scrollBar = new JScrollPane(appointments);		
		
		JTable completedAppointments = new JTable(clientAppointments, columnNames);
		completedAppointments.setRowSelectionAllowed(true);
		completedAppointments.setPreferredScrollableViewportSize(new Dimension(750, 100));
		JScrollPane scrollBar2 = new JScrollPane(completedAppointments);	
		
		
		deleteBtn = new JButton("Cancel");	//does not work i could not finish this part of the program				
		deleteBtn.setForeground(Color.white);
		deleteBtn.setBackground(Color.RED);		
		
		deleteCompletedBtn = new JButton("Delete");		//does not work i could not finish this part of the program	
		deleteCompletedBtn.setForeground(Color.white);
		deleteCompletedBtn.setBackground(Color.RED);	
		
		writeReview    = new JTextField(25);//does not work i could not finish this part of the program	
		sendReviewBtn  = new JButton("Send");
		sendReviewBtn.setPreferredSize(new Dimension(150 ,25));
		sendReviewBtn.setForeground(Color.white);
		sendReviewBtn.setBackground(Color.BLUE);
		

	    selectHairdresserBtn = new JButton("Hairdresser/Barber Address"); //select the service provider by location
	    selectHairdresserBtn.addActionListener(this.controller);
	    selectHairdresserBtn.setPreferredSize(new Dimension(250 ,25));
	    selectHairdresserBtn.setForeground(Color.white);
	    selectHairdresserBtn.setBackground(Color.GREEN);
	    
	    selectNameBtn = new JButton("Select by Name");	 //select the service provider by name
	    selectNameBtn.addActionListener(this.controller);
	    selectNameBtn.setPreferredSize(new Dimension(250 ,25));
	    selectNameBtn.setForeground(Color.white);
	    selectNameBtn.setBackground(Color.GREEN);
	    
	    logOutBtn = new JButton("Log Out"); 
	    logOutBtn.addActionListener(this.controller);
	    logOutBtn.setPreferredSize(new Dimension(150 ,25));
	    logOutBtn.setForeground(Color.black);
	    logOutBtn.setBackground(Color.orange);
		
		
		GridBagConstraints grid = new GridBagConstraints();
		grid.anchor = GridBagConstraints.LINE_START;
		grid.insets = new Insets(20, 20, 20, 20);
		grid.gridx =0; //position X colum
		grid.gridy =0; //position y rows
		homePane.add(msg, grid);
		grid.gridx =0;
		grid.gridy =1;
		homePane.add(upcoming, grid); 
		grid.gridx =0;
		grid.gridy =2;
		homePane.add(scrollBar, grid); 
		grid.gridx = 1;
		grid.gridy = 2;
		homePane.add(deleteBtn, grid);			
		grid.gridx = 0;
		grid.gridy = 3;
		homePane.add(completed, grid);
		grid.gridx = 0;
		grid.gridy = 4;
		homePane.add(scrollBar2, grid);
		grid.gridx = 1;
		grid.gridy = 4;
		homePane.add(deleteCompletedBtn, grid);
		grid.gridx = 0;
		grid.gridy = 8;
		homePane.add(searchHairdresser, grid);
		grid.gridx = 0;
		grid.gridy = 9;
		homePane.add(locationList, grid);
		grid.gridx = 0;
		grid.gridy = 10;
		homePane.add(selectHairdresserBtn, grid);
		grid.gridx = 0;
		grid.gridy = 11;
		homePane.add(searchByName, grid);
		grid.gridx = 0;
		grid.gridy = 12;
		homePane.add(namesList, grid);
		grid.gridx = 0;
		grid.gridy = 13;
		homePane.add(selectNameBtn, grid);
		grid.gridx = 0;
		grid.gridy = 14;
		homePane.add(reviewBox, grid);
		grid.gridx = 0;
		grid.gridy = 15;
		homePane.add(review, grid);
		grid.gridx = 0;
		grid.gridy = 16;
		homePane.add(writeReview, grid);
		grid.gridx = 0;
		grid.gridy = 17;
		homePane.add(sendReviewBtn, grid);	
		grid.gridx = 1;
		grid.gridy = 17;
		homePane.add(logOutBtn, grid);
		
		JScrollPane scrollBarPane = new JScrollPane(homePane, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollBarPane.getViewport().setMinimumSize(new Dimension(1000, 750)); //a extra scroll bar 
		scrollBarPane.getViewport().setPreferredSize(new Dimension(1000, 750));
		mainPane.add(scrollBarPane);
	
		
		this.add(mainPane);		
		validation();
	}
	
	public String getlocationList() { //get location list so can check in the database
		
		String location = String.valueOf(locationList.getSelectedItem());
		
		return location;
	}
		
	public String getNameList() { //get name list so can check in the database
		
		String nameList = String.valueOf(namesList.getSelectedItem());
		
		return nameList;
	}

	public void finishBooking() { //before book the client can see the information about the servide provider 
		
		this.getContentPane().removeAll();		
		this.setSize(800, 800);
	
		registerPane       = new JPanel(new GridBagLayout());	
		
		String[] bookingChosen = controller.bookingChosen(); //show the time, data and the service provider information
 		String[] timeSlots	   = controller.availiableTime();
		String[] datesSlots    = controller.availiableDates();
	
		
		JTextArea serviceType    = new JTextArea(2, 25); //show the information in the text field
		serviceType.setEditable(false);
		serviceType.setText(bookingChosen[0]);
		JTextArea serviceName    = new JTextArea(2, 25);
		serviceName.setEditable(false);
		serviceName.setText(bookingChosen[1]);
		JTextArea serviceAddress = new JTextArea(2, 25); 
		serviceAddress.setEditable(false);
		serviceAddress.setText(bookingChosen[2]);
		JTextArea servicePhone   = new JTextArea(2, 25);
		servicePhone.setEditable(false);
		servicePhone.setText(bookingChosen[3]);
		JTextArea serviceEmail   = new JTextArea(2, 25);
		serviceEmail.setEditable(false);
		serviceEmail.setText(bookingChosen[4]);
		
		String[] names = {"Jose", "Pauloasdasdasd", "Lauraasdasdasd", "Aurelio", "Chato"}; //reviws do not work 
			
		JComboBox reviewList     = new JComboBox(names);
		reviewList.setPreferredSize(new Dimension(250,25));
		
		appointmentsDates   = new JComboBox(datesSlots);
		appointmentsDates.setPreferredSize(new Dimension(250,25));
		appointmentsTime   = new JComboBox(timeSlots);
		appointmentsTime.setPreferredSize(new Dimension(250,25));

	    JLabel lmsg             = new JLabel("Welcome, User");
	    JLabel formName         = new JLabel("You Selected: "); 
		JLabel lfullName        = new JLabel("Full name: "); 
		JLabel laddress         = new JLabel("Address: ");
		JLabel lphone           = new JLabel("Phone: ");
		JLabel lemail           = new JLabel("Email: ");
		JLabel lreview          = new JLabel("Reviews: ");
		JLabel lappointments    = new JLabel("Appointments Dates/Time: ");

		Font f1 = new Font("TimesNewRoman", Font.BOLD, 18);
		Font f2 = new Font("TimesNewRoman", Font.PLAIN, 16);

	    submitDateBtn = new JButton("Book Slot");
	    submitDateBtn.addActionListener(this.controller);
	    submitDateBtn.setForeground(Color.white);
	    submitDateBtn.setBackground(Color.GREEN);
	    
	    backtoMainBtn = new JButton("Go Back");
	    backtoMainBtn.addActionListener(this.controller);
	    backtoMainBtn.setForeground(Color.white);
	    backtoMainBtn.setBackground(Color.BLUE);

		GridBagConstraints grid1 = new GridBagConstraints();
		grid1.anchor = GridBagConstraints.LINE_START;
		grid1.insets = new Insets(10, 10, 25, 25);
		grid1.gridx =0; //position X colum
		grid1.gridy =0; //position y rows
		registerPane.add(lmsg, grid1);
		grid1.gridx =0; 
		grid1.gridy =1;
		registerPane.add(formName, grid1);
		grid1.gridx =1; 
		grid1.gridy =1;
		registerPane.add(serviceType, grid1);
		grid1.gridx =0; 
		grid1.gridy =2;
		registerPane.add(lfullName, grid1);
		grid1.gridx =1; 
		grid1.gridy =2;
		registerPane.add(serviceName, grid1);
		grid1.gridx =0; 
		grid1.gridy =3;
		registerPane.add(laddress, grid1);
		grid1.gridx =1; 
		grid1.gridy =3;
		registerPane.add(serviceAddress, grid1);
		grid1.gridx =0; 
		grid1.gridy =4;
		registerPane.add(lphone, grid1);		
		grid1.gridx =1; 
		grid1.gridy =4;
		registerPane.add(servicePhone, grid1);
		grid1.gridx =0; 
		grid1.gridy =5;
		registerPane.add(lemail, grid1);
		grid1.gridx =1; 
		grid1.gridy =5;
		registerPane.add(serviceEmail, grid1);
		grid1.gridx =0; 
		grid1.gridy =6;
		registerPane.add(lreview, grid1);
		grid1.gridx =1; 
		grid1.gridy =6;
		registerPane.add(reviewList, grid1);
		grid1.gridx =0; 
		grid1.gridy =7;
		registerPane.add(lappointments, grid1);
		grid1.gridx =0; 
		grid1.gridy =8;
		registerPane.add(appointmentsDates, grid1);		
		grid1.gridx =0; 
		grid1.gridy =9;
		registerPane.add(appointmentsTime, grid1);
		grid1.gridx = 0;
		grid1.gridy = 10;
		registerPane.add(submitDateBtn, grid1);
		grid1.gridx = 2;
		grid1.gridy = 10;
		registerPane.add(backtoMainBtn, grid1);
		this.add(registerPane);
		
		validation();
		
		
	}
	
	public String getDateClient() { //get selected date from the client
		
		String date = String.valueOf(appointmentsDates.getSelectedItem());
		
		return date;
	}
	
	public String getTimeClient() { //get selected time from the client
		
		String time = String.valueOf(appointmentsTime.getSelectedItem());
		
		return time;
	}
		
	private void frameSettings() { // framse settings
		
		this.setVisible(true);
		this.setTitle("Booking Application");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
	}
	
	private void validation() { //validate and repaint
		this.validate();
		this.repaint();
	}
	
	public void dialog(String msg, String title) { //display msgs
		 
		JOptionPane.showMessageDialog(this,msg, title, JOptionPane.WARNING_MESSAGE);
	}
	
	public void dialogSuccess(String msg) {//display msgs
		
		JOptionPane.showMessageDialog(this,msg);
	}
		
	//===============================================================
	//getters for the form
	
	public String getFullName() {
		return fullName.getText();
	}
	
	public String getPhone() {
		return phone.getText();
	}
	
	public String getEmail() {
		return email.getText();
	}
	public String getPassword() {
		return passwordField.getText();
	}

	public String getAddress() {
		return address.getText();
	}

	public String getProfession() {
		return profession.getText();
	}
//================================================================
	//method to select a new date in the calendar could be clicked or typed
	@Override
	public void propertyChange(PropertyChangeEvent evt) {


		if(evt.getPropertyName().equals("selectedDate")) {
			
			java.util.Calendar cal = (java.util.Calendar) evt.getNewValue();
			Date selDate = cal.getTime();
			
			dayPickerTextField.setValue(selDate);
		}
		
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
