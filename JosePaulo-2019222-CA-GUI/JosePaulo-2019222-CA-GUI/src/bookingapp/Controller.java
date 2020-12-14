package bookingapp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class Controller implements ActionListener{
	
	View view;
    Model model;  
    
    public String email;
	public String password;   
	public User user; 
    
    public Controller(){
 
        this.model = new Model();
        String[][] tableAppointments  = model.appointmentList();  //holds the data that came from the model appointment created by the service provider
        String[][] clientAppointments = model.appointmentListClient(); //holds the data that came from the model appointment created by the sclient
        this.view = new View(this, tableAppointments, clientAppointments); //view instance
       
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
	 	String service = "serviceProvider";
	 	String client = "customer";        
        String msg;
        String title;
        
    	
    	email = view.getEmail(); // get the information from the user hen log in
        password = view.getPassword();        
        user = new User(email, password);   
        boolean result = model.login(user);
        String table = model.tableName;
        
        if(e.getActionCommand().equals("Log In")) { //handles the login 
           
    		if(result && table.matches(client)) { //login client
    			view.clientHomePage();    			
    			
    		}else if(result && table.matches(service)) {//login service provider
    			view.serviceProviderHomePage();
    			
    		}else {
    			msg = "Try again with valid credentials!";
    			title   = "Password or username invalid";
    			
    			view.dialog(msg, title);   
    		}        	       	
        }

    
    	if(e.getActionCommand().equals("Create Account")){  //call a method when a new user wants to create an account	
    		
    		view.registerFirstTime();    	 	
    				
    	}
    	
  //=====================================================================================================   
    							//client actions
 //=====================================================================================================
 
    	if(e.getActionCommand().equals("I am a Client")) { //call the method to register clients
    		
    		view.customerForm();
    	}   
    	
    	if(e.getActionCommand().equals("Create User Account")) {  //register the user in the database  		
    		
    		msg = "Please, Enter your credentials";
			
			model.registerUserFirstTime(view, client); //register the user in the database  	
    		view.homePageComponents(); //loads the home page so they can log in
    		view.dialogSuccess(msg);  		 		
    	}
    	
    	if(e.getActionCommand().equals("Hairdresser/Barber Address")) { // book an appointment when the client want to use the location
    		
    		String location = view.getlocationList(); //get the location selected by the user in the jcombobox
    		
    		String column1 = "address";
    		
    		
    		boolean action = model.arrayRequestedClient(column1, location); //get the array with all teh information about service provider
    		System.out.println(action);
    		if(action) {
    			msg = "Thank you!!!";	
    			view.dialogSuccess(msg);
    			view.finishBooking();
    			
    		}else {
    			
    			msg = "No appointments available for this search!";
    			title   = "Sorry";
    			
    			view.dialog(msg, title);  
    			
    		}   		
    		
    	}
    	
    	if(e.getActionCommand().equals("Select by Name")) { // book an appointment when the client want to use the names
    		
    		String name = view.getNameList();   //get the names selected by the user in the jcombobox	
    		String column2 = "fullname";
    	
    		boolean action = model.arrayRequestedClient(column2, name); //get the information anout the service provider
    		
    		if(action) {
    			msg = "Thank you!!!";	
    			view.dialogSuccess(msg);
    			view.finishBooking();
    			
    		}else {
    			
    			msg = "No appointments available for this search!";
    			title   = "Sorry";
    			
    			view.dialog(msg, title);  
    			
    		}
    		
    	}
    	
    	if(e.getActionCommand().equals("Book Slot")) { //if the user want to book the slot chosen 
    		 String date = view.getDateClient(); //get the data seleceted by the client
    		 String time = view.getTimeClient(); //get the time seleceted by the client
    		 String status = "REQUEST";
    		 
    		 boolean action = model.insertAppointmentClient(user, date, time, status); //insert the information in the database
    		 
    		 if(action) {
     			msg = "Appointment has been booked! See you soon";	//app has been added to the database
     			view.dialogSuccess(msg);
     			view.clientHomePage();     			
     			
     		}else {
     			
     			msg = "Appointments has been taken";
     			title   = "Sorry";
     			
     			view.dialog(msg, title);  
     			
     		}
    		 
    	}
    	
    	if(e.getActionCommand().equals("Go Back")) { //if the user give up he can go back to the home page
    		
    		view.clientHomePage(); //client home page
    		
    	}
    	
    	
 //=====================================================================================================   
    	//service provider actions
 //=====================================================================================================
    	if(e.getActionCommand().equals("I am a Service Provider")) {
    		
    		view.serviceProviderForm(); //call the method to register the service provider
    	}
    	
    	if(e.getActionCommand().equals("Create Service Account")) {
    		
    		msg = "Please, Enter your credentials";			
    		
    		model.registerUserFirstTime(view, service); //register the service provider in the database
    		view.homePageComponents(); //loads the login page
    		
    		view.dialogSuccess(msg);
    	} 	
    	if(e.getActionCommand().equals("Submit Date")) {   //submit appointment  	
    		msg = "Appointment added successfully.";		
    		String status = "Availiable";
    		String appointmentTime = view.getAppointmentTime(); //get the time selected
    		String appointmentDate = view.getAppointmentDate(); //get the date selected by the service provider
    		System.out.println(email);
    		
    		model.insertAppointment(user ,appointmentDate, appointmentTime, status); //inert app in the database		
    		view.dialogSuccess(msg);  	
    		
    		
    	}
    	if(e.getActionCommand().equals("Delete Date")) { //delte app if the service provider wants
    		
    		String time = view.getDeleteTime(); //get time and date selected in teh jcombobox
    		String date = view.getDeleteDate();
    		
    		boolean action = model.deleteAppointments(user, date, time); //delete from the database
    		
    		if(action) {
    			msg = "Appointment deleted successfully.";	
    			view.dialogSuccess(msg);
    		}else {
    			
    			msg = "Please, Check the appointment time and date";
    			title   = "Something went Wrong";
    			
    			view.dialog(msg, title);  
    			
    		}
    		

    		
    	}

    	
    	if(e.getActionCommand().equals("Log Out")) { //handles the log out function
    		msg = "You have been logged out";	
    		view.homePageComponents();
    		view.dialogSuccess(msg);
    	}
    	
    	

    }
    	
	public String[] bookingChosen() { //return array to the client page so he can choose the service provider
		
		String[] data = model.dataRequestedByClient;
		
		return data;
	}
	
	public String[] availiableTime() { //return a array with the time slots availiable for each service provider
		
		String[] time = model.timeRequestedByClient;
		
		return time;
	}
	
	public String[] availiableDates() { //return a array with the date slots availiable for each service provider
		
		String[] dates = model.datesRequestedByClient;
		
		return dates;
	}
	

    public String[] datesToDelete() { // return an array with date when the service provider want to delete
  	  
  	  String[] datesToDelete = model.arrayofDates(user);
  	  
  	  return datesToDelete;
    }       
    
    
    public String[] timeToDelete() { // return an array with time when the service provider want to delete
    	  
    	  String[] timeToDelete = model.arrayOfTime(user);
    	  
    	  return timeToDelete;
     }
    
    public String[] serviceProviderNames() { //return an array with all the service provider names
    
		String namesQuery   = "fullname";  	  
  	  	String[] names = model.listofLocationAndNames(namesQuery);
  	  
  	  return names;
   }
    
    public String[] servicePRoviderLocations() {//return an array with all the service provider location
    	
    	String address = "address";
    	String[] location = model.listofLocationAndNames(address);
  	  
  	  return location;
   }
    
    
}


 