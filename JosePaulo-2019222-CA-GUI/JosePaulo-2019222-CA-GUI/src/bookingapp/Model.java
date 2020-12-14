package bookingapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class Model {
	
	
	public String tableName;
	public String[] dataRequestedByClient;
	public String[] timeRequestedByClient;
	public String[] datesRequestedByClient;
	
	public boolean login(User user){  //log client or service provider 
        
        boolean result = false;
        
        int index = 0;
        try {        	
        	
            String dbUser = "Jose_2019222";
            String dbPassword = "2019222";
            String dbServer = "jdbc:mysql://apontejaj.com:3306/"+ dbUser +"?useSSL=false";
            String query = "SELECT * FROM serviceProvider WHERE email = '" + user.getUsername() + "' AND password = '" + user.getPassword()  + "';"; //checks credentials for service provider
            String query1 = "SELECT * FROM customer WHERE email = '" + user.getUsername() + "' AND password = '" + user.getPassword()  + "';"; //checks credentials for clients
         
            // Get a connection to the database
            Connection conn = DriverManager.getConnection(dbServer, dbUser, dbPassword);

            // Get a statement from the connection
            Statement stmt = conn.createStatement();
            
            
            // Execute the query
            ResultSet rs; 
            do { //keep checking the credentials till find the right one
            	rs = stmt.executeQuery(query);
            	
            	if(rs.next()) { //check if the credentials given are from a service provider
            		
        			result = true; //if is a service provider return true
        			
        		}else if(!rs.next()) { //if not try the second query and check if it is a customer
        			
            		rs = stmt.executeQuery(query1); 
            		if(rs.next()) { //if is a customer return true
            			result = true;
            		}
            		
            	}else {
            		
            		System.out.println("Invalid query");
            	}   			
        	
            	
            	index = 1;  //stop the loop          
	            
	            ResultSetMetaData rsTable = rs.getMetaData();            
	        	tableName = rsTable.getTableName(2); //get name of the table in mysql database	  
            
            
        	}while(index < 1);

            // Close the result set, statement and the connection
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            System.out.println("SQL Exception:");

            //Loop through the SQL Exceptions
            while (se != null) {
                System.out.println("State  : " + se.getSQLState());
                System.out.println("Message: " + se.getMessage());
                System.out.println("Error  : " + se.getErrorCode());

                se = se.getNextException();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return result;
        
    }

	public void registerUserFirstTime(View view, String table) {	//register teh user or the service provider
	 	String service = "serviceProvider";
	 	String client = "customer";
		
		 try {			 
			 	
	            String dbUser = "Jose_2019222";
	            String dbPassword = "2019222";
	            String dbServer = "jdbc:mysql://apontejaj.com:3306/"+ dbUser +"?useSSL=false";
	  
	            // Get a connection to the database
	            Connection conn = DriverManager.getConnection(dbServer, dbUser, dbPassword);

	            // Get a statement from the connection
	            Statement stmt = conn.createStatement();

	            // Execute the query
	            int rs;	            
	 
	            if(table.equals(service)) { //the program will change the query depending on the button that the user chose to createa ccount
	            	//create account for service provider
	            	String query = "INSERT INTO " + service +"(fullname, email, phone, profession, address, password) values('" + view.getFullName()
	            	+"', '" + view.getEmail() + "', '" + view.getPhone() 
		            +"', '" + view.getProfession() + "', '" + view.getAddress() + "', '" + view.getPassword() + "');";
	            	
	            	rs = stmt.executeUpdate(query);
		            
	            }
	            if(table.equals(client)) {//create account for client
		            String query1 = "INSERT INTO " + client+ "(fullname, email, phone, password) values('" 
	            + view.getFullName() +"', '" + view.getEmail() + "', '" + view.getPhone() 
		            +"', '" + view.getPassword() + "');";
	            	
	            	rs = stmt.executeUpdate(query1);
	            
	            }
	            
	            // Close the result set, statement and the connection
	           
	            conn.close();
	            
	        } catch (SQLException se) {
	            System.out.println("SQL Exception:");

	            // Loop through the SQL Exceptions
	            while (se != null) {
	                System.out.println("State  : " + se.getSQLState());
	                System.out.println("Message: " + se.getMessage());
	                System.out.println("Error  : " + se.getErrorCode());

	                se = se.getNextException();
	            }
	        } catch (Exception e) {
	            System.out.println(e);
	        }    
		
	}
	
	public String[][] appointmentList(){ //return the the 2d array with all the data in teh appointment table created by the service provider

		String[][] appointments = null;
		
		 try {
			 	
			 	String service = "Service Provider";
			 	String client = "Client";			 	
	            String dbUser = "Jose_2019222";
	            String dbPassword = "2019222";
	            String dbServer = "jdbc:mysql://apontejaj.com:3306/"+ dbUser +"?useSSL=false";
	            String query = "SELECT appointmentDate, appointmentTime, fullname, address, email, phone, appointmentStatus FROM appointments;";       
	            
  
	            // Get a connection to the database
	            Connection conn = DriverManager.getConnection(dbServer, dbUser, dbPassword);

	            // Get a statement from the connection
	            Statement stmt = conn.createStatement();

	            // Execute the query
	            ResultSet rs = stmt.executeQuery(query);  
	              //rs.last();	            
 	            int nRow = rs.getRow(); //get the number the rows
 	            int nCol = rs.getMetaData().getColumnCount(); //get the number of colums
 	          //rs.beforeFirst();	   
 	           List<String[]> table = new ArrayList<>();
	           
	          
	            while (rs.next()) {
	            	String[] row = new String[nCol];	
	            	for(int iCol = 1; iCol <= nCol; iCol++) {
	            		Object obj = rs.getObject(iCol);
	            		row[iCol -1] = (obj == null) ?null:obj.toString(); //tenary operator if object is not null it is converted to string
	            	}
	            	table.add(row);
	            }
	            
	            appointments = new String[table.size()][]; //converts the array list in a 2d array
	            for (int i = 0; i < appointments.length; i++) {
	            	String[] row = table.get(i);
	            	appointments[i] = row;
	            }
	            
	            
	          
	         
	            conn.close();
	            
	        } catch (SQLException se) {
	            System.out.println("SQL Exception:");

	            // Loop through the SQL Exceptions
	            while (se != null) {
	                System.out.println("State  : " + se.getSQLState());
	                System.out.println("Message: " + se.getMessage());
	                System.out.println("Error  : " + se.getErrorCode());

	                se = se.getNextException();
	            }
	        } catch (Exception e) {
	            System.out.println(e);
	        }
		 
		return appointments;    
		
		
		
	}

	public String[][] appointmentListClient(){ //return the the 2d array with all the data in the appointment table created by the client

		String[][] appointments = null;
		
		 try {
			
	            String dbUser = "Jose_2019222";
	            String dbPassword = "2019222";
	            String dbServer = "jdbc:mysql://apontejaj.com:3306/"+ dbUser +"?useSSL=false";
	            String query = "SELECT appointmentDate, appointmentTime, fullname, email, phone, serviceName, appointmentStatus FROM appointmentsClient;";       
	            
  
	            // Get a connection to the database
	            Connection conn = DriverManager.getConnection(dbServer, dbUser, dbPassword);

	            // Get a statement from the connection
	            Statement stmt = conn.createStatement();

	            // Execute the query
	            ResultSet rs = stmt.executeQuery(query);  
	              //rs.last();	            
 	            int nRow = rs.getRow(); //number of rows
 	            int nCol = rs.getMetaData().getColumnCount(); //number of columns
 	          //rs.beforeFirst();	   
 	           List<String[]> table = new ArrayList<>(); 
	           
	          
	            while (rs.next()) {
	            	String[] row = new String[nCol];	
	            	for(int iCol = 1; iCol <= nCol; iCol++) {
	            		Object obj = rs.getObject(iCol);
	            		row[iCol -1] = (obj == null) ?null:obj.toString(); //tenary operator
	            	}
	            	table.add(row); //add to the array list
	            }
	            
	            appointments = new String[table.size()][]; //convert array list to a 2d array
	            for (int i = 0; i < appointments.length; i++) {
	            	String[] row = table.get(i);
	            	appointments[i] = row;
	            }
	            
	          
	         
	            conn.close();
	            
	        } catch (SQLException se) {
	            System.out.println("SQL Exception:");

	            // Loop through the SQL Exceptions
	            while (se != null) {
	                System.out.println("State  : " + se.getSQLState());
	                System.out.println("Message: " + se.getMessage());
	                System.out.println("Error  : " + se.getErrorCode());

	                se = se.getNextException();
	            }
	        } catch (Exception e) {
	            System.out.println(e);
	        }
		 
		return appointments; //return 2d array
				
		
	}
		
	public String[] arrayofDates(User user){ //return array with appointments dates used for deleting the appointments created 
	
		ArrayList<String> dates = new ArrayList<>();		
		String[] datesToDelete = new String[dates.size()]; 
		try {
			String dbUser = "Jose_2019222";
	        String dbPassword = "2019222";
	        String dbServer = "jdbc:mysql://apontejaj.com:3306/"+ dbUser +"?useSSL=false";
			
	        String query = "SELECT * FROM appointments WHERE email = '" + user.getUsername() + "';"; //select the appoitment created by the service provider 
	        																						//check the credentials and only return the especif items created by the service provider
	        
	        // Get a connection to the database
	        Connection conn = DriverManager.getConnection(dbServer, dbUser, dbPassword);
	
	        // Get a statement from the connection
	        Statement stmt = conn.createStatement();
	
	        // Execute the query
	        ResultSet rs = stmt.executeQuery(query);  
	          //rs.last();	            
	        
	        while (rs.next()) {
	        	
	        	
	        	String appointments = rs.getString("appointmentDate"); //get the column with the dates or each service provider 
	        	System.out.println(appointments);
	            dates.add(appointments);
	        }    
	        
	        
	        
	        datesToDelete = dates.toArray(datesToDelete); //converts the array list in an array
	  
	      
	     
	        conn.close();
        
	    } catch (SQLException se) {
	        System.out.println("SQL Exception:");
	
	        // Loop through the SQL Exceptions
	        while (se != null) {
	            System.out.println("State  : " + se.getSQLState());
	            System.out.println("Message: " + se.getMessage());
	            System.out.println("Error  : " + se.getErrorCode());
	
	            se = se.getNextException();
	        }
	    } catch (Exception e) {
	        System.out.println(e);
	    } 
        
        return datesToDelete; //return the array
	
	}
	
	public String[] arrayOfTime(User user) { //return an arrays with the times so the service provider can delete if necessary
		
		ArrayList<String> times = new ArrayList<>();		
		String[] timesToDelete = new String[times.size()]; 
		try {
			String dbUser = "Jose_2019222";
	        String dbPassword = "2019222";
	        String dbServer = "jdbc:mysql://apontejaj.com:3306/"+ dbUser +"?useSSL=false";
			
	        String query = "SELECT * FROM appointments WHERE email = '" + user.getUsername() + "';";   //get the time slots for each service provider   
	        
	        
	        // Get a connection to the database
	        Connection conn = DriverManager.getConnection(dbServer, dbUser, dbPassword);
	
	        // Get a statement from the connection
	        Statement stmt = conn.createStatement();
	
	        // Execute the query
	        ResultSet rs = stmt.executeQuery(query);  
	          //rs.last();	            
	        
	        while (rs.next()) {
	        	
	        	
	        	String appointments = rs.getString("appointmentTime"); //get the values from the database    	
	            times.add(appointments);
	        }    

	        timesToDelete = times.toArray(timesToDelete); //conver the array list to a array
	  

	        conn.close();
        
	    } catch (SQLException se) {
	        System.out.println("SQL Exception:");
	
	        // Loop through the SQL Exceptions
	        while (se != null) {
	            System.out.println("State  : " + se.getSQLState());
	            System.out.println("Message: " + se.getMessage());
	            System.out.println("Error  : " + se.getErrorCode());
	
	            se = se.getNextException();
	        }
	    } catch (Exception e) {
	        System.out.println(e);
	    } 
        
        return timesToDelete; //return the array
	}
		
	public boolean deleteAppointments(User user, String date, String time) { //method to delete the app created by the service provider in the database
		
		String name = null;
        String address = null;
        String email = null;
        boolean action = false;
        int phone = 0;
		 try {			 
			 	
	            String dbUser = "Jose_2019222";
	            String dbPassword = "2019222";
	            String dbServer = "jdbc:mysql://apontejaj.com:3306/"+ dbUser +"?useSSL=false";
	            String query1 = "SELECT * FROM serviceProvider WHERE email = '" + user.getUsername() + "' AND password = '" + user.getPassword()  + "';";
	            
	                
	            // Get a connection to the database
	            Connection conn = DriverManager.getConnection(dbServer, dbUser, dbPassword);

	            // Get a statement from the connection
	            Statement stmt = conn.createStatement();

	            // Execute the query
	            ResultSet rs = stmt.executeQuery(query1);
	            if (rs.next()) {	          
		           
		        
		            email   = rs.getString("email");	          
		            String query2  = "DELETE FROM appointments WHERE appointmentDate = '"+ date + "' AND appointmentTime = '" + time +"' AND email = '" +email+"';";
		            int rs1 = stmt.executeUpdate(query2); //delete the right data from the right service provider
		            action = true;
	            
	            } 
	            
	       
	            // Close the result set, statement and the connection
	            conn.close();
	                  
	        } catch (SQLException se) {
	            System.out.println("SQL Exception:");

	            // Loop through the SQL Exceptions
	            while (se != null) {
	                System.out.println("State  : " + se.getSQLState());
	                System.out.println("Message: " + se.getMessage());
	                System.out.println("Error  : " + se.getErrorCode());

	                se = se.getNextException();
	            }
	        } catch (Exception e) {
	            System.out.println(e);
	        }    
		 return action; //return the value as true
		 
	}
		
	public void insertAppointment(User user,String date, String time, String appointmentStatus) { //insert apointments in the database created by the servide provider
		String name = null;
        String address = null;
        String email = null;
        int phone = 0;
		 try {			 
			 	
	            String dbUser = "Jose_2019222";
	            String dbPassword = "2019222";
	            String dbServer = "jdbc:mysql://apontejaj.com:3306/"+ dbUser +"?useSSL=false";
	            String query1 = "SELECT * FROM serviceProvider WHERE email = '" + user.getUsername() + "' AND password = '" + user.getPassword()  + "';";
	            
	                
	            // Get a connection to the database
	            Connection conn = DriverManager.getConnection(dbServer, dbUser, dbPassword);

	            // Get a statement from the connection
	            Statement stmt = conn.createStatement();

	            // Execute the query
	            ResultSet rs = stmt.executeQuery(query1);
	            if (rs.next()) {	           
		           
		            name    = rs.getString("fullname"); //get the values necessary to create the appointments
		            address = rs.getString("address");
		            email   = rs.getString("email");
		            phone   = rs.getInt("phone");		            
		            System.out.println(name +" "+ address + email + phone);
		            String query2  = "INSERT INTO appointments(appointmentDate, appointmentTime, address, email, phone, appointmentStatus)"
		            		+ " VALUES('"+date+ "', '"+ time +"','"+ address +"','"+ email +"','"+ phone +"','"+ appointmentStatus +"');"; //insert the appointment
		            String query3 = "UPDATE appointments a INNER JOIN serviceProvider s ON a.email = s.email SET a.fullname = s.fullname;"; // link the appointment ith the right service provider
		            String query4 = "UPDATE appointments a INNER JOIN serviceProvider s ON a.email = s.email SET a.sProvider_id = s.id;"; // link the appointment ith the right service provider
		            int rs1 = stmt.executeUpdate(query2);
		            int rs2 = stmt.executeUpdate(query3);
		            int rs3 = stmt.executeUpdate(query4);     //execute all teh queries      
	            
	            } 
	            
	       
	            // Close the result set, statement and the connection
	            conn.close();
	                  
	        } catch (SQLException se) {
	            System.out.println("SQL Exception:");

	            // Loop through the SQL Exceptions
	            while (se != null) {
	                System.out.println("State  : " + se.getSQLState());
	                System.out.println("Message: " + se.getMessage());
	                System.out.println("Error  : " + se.getErrorCode());

	                se = se.getNextException();
	            }
	        } catch (Exception e) {
	            System.out.println(e);
	        }    
		
	}
	
	public boolean insertAppointmentClient(User user,String date, String time, String appointmentStatus) { // book the appintments for the client 
		String name = null;
        String address = null;
        String email = null;
        int phone = 0;
        boolean action = false;
		 try {			 
			 	
	            String dbUser = "Jose_2019222";
	            String dbPassword = "2019222";
	            String dbServer = "jdbc:mysql://apontejaj.com:3306/"+ dbUser +"?useSSL=false";
	            String query1 = "SELECT * FROM customer WHERE email = '" + user.getUsername() + "' AND password = '" + user.getPassword()  + "';";
	            
	                
	            // Get a connection to the database
	            Connection conn = DriverManager.getConnection(dbServer, dbUser, dbPassword);

	            // Get a statement from the connection
	            Statement stmt = conn.createStatement();

	            // Execute the query
	            ResultSet rs = stmt.executeQuery(query1);
	            
	            if (rs.next()) {	           
		           
		            name    = rs.getString("fullname"); //get the value from teh database		            
		            email   = rs.getString("email");
		            phone   = rs.getInt("phone");		            
		            String serviceName = dataRequestedByClient[1]; //get the name of the service provider that the user choose
		            
		            String query2  = "INSERT INTO appointmentsClient(appointmentDate, appointmentTime, fullname, email, phone, serviceName, appointmentStatus)"
		            		+ " VALUES('"+date+ "', '"+ time +"','"+ name +"','"+ email +"','"+ phone +"','"+ serviceName+"','" + appointmentStatus +"');"; //inser the information in the database
		            String query4 = "UPDATE appointmentsClient a INNER JOIN customer c ON a.email = c.email SET a.customer_id = c.id;"; //link the the appointment with the client that created
		            int rs1 = stmt.executeUpdate(query2);
		            int rs3 = stmt.executeUpdate(query4); 
		            action = true;
	            
	            } 
	            
	       
	            // Close the result set, statement and the connection
	            conn.close();
	                  
	        } catch (SQLException se) {
	            System.out.println("SQL Exception:");

	            // Loop through the SQL Exceptions
	            while (se != null) {
	                System.out.println("State  : " + se.getSQLState());
	                System.out.println("Message: " + se.getMessage());
	                System.out.println("Error  : " + se.getErrorCode());

	                se = se.getNextException();
	            }
	        } catch (Exception e) {
	            System.out.println(e);
	        }    
		 	return action; //return true or false
		
	}
		
	public String[] listofLocationAndNames(String parameter){ // return an array so the client can choose the service provider and see the availiable appointment for the choosen one
		
		ArrayList<String> name_location = new ArrayList<>();		
		String[] nameLocation = new String[name_location.size()]; 
		String address = "address";
		String names   = "fullname";
		
		try {
			String dbUser = "Jose_2019222";
	        String dbPassword = "2019222";
	        String dbServer = "jdbc:mysql://apontejaj.com:3306/"+ dbUser +"?useSSL=false";			
	        String query = "SELECT * FROM serviceProvider;";      
	        
	        
	        // Get a connection to the database
	        Connection conn = DriverManager.getConnection(dbServer, dbUser, dbPassword);
	
	        // Get a statement from the connection
	        Statement stmt = conn.createStatement();
	
	        // Execute the query
	        ResultSet rs = stmt.executeQuery(query);  
	          //rs.last();	            
	        
	        while (rs.next()) {
	        	
	        	
	        	if(parameter.equals(address)) { //return a list with location of the service provider
	        		
	        		String appointmentLocation = rs.getString("address");
	        		name_location.add(appointmentLocation);
	        		
	        	}else if(parameter.equals(names)) {//return a list with names of all service provider
	        		String appointmentNames    = rs.getString("fullname");
	        		name_location.add(appointmentNames);
	        	}
	        
	        	
	        }            
	        
	        
	        nameLocation = name_location.toArray(nameLocation);  //convert it to an array
	  
	      
	     
	        conn.close();
        
	    } catch (SQLException se) {
	        System.out.println("SQL Exception:");
	
	        // Loop through the SQL Exceptions
	        while (se != null) {
	            System.out.println("State  : " + se.getSQLState());
	            System.out.println("Message: " + se.getMessage());
	            System.out.println("Error  : " + se.getErrorCode());
	
	            se = se.getNextException();
	        }
	    } catch (Exception e) {
	        System.out.println(e);
	    } 
        
        return nameLocation; //retun the array with the chosen information
	
	}
	
	public boolean arrayRequestedClient(String field, String parameter) { //return information to load to the client before he book the slots
		
		boolean action = false;
		ArrayList<String> element    = new ArrayList<>();	
		ArrayList<String> timeClient = new ArrayList<>();	
		ArrayList<String> dateClient = new ArrayList<>();	
		String column1 = "address";
		String column2 = "fullname";
		try {
			String dbUser = "Jose_2019222";
	        String dbPassword = "2019222";
	        String dbServer = "jdbc:mysql://apontejaj.com:3306/"+ dbUser +"?useSSL=false";
			
	        // Get a connection to the database
	        Connection conn = DriverManager.getConnection(dbServer, dbUser, dbPassword);
	
	        // Get a statement from the connection
	        Statement stmt = conn.createStatement();
	
	        // Execute the query
	        String query = "SELECT * FROM appointments WHERE "+ field + "= '" + parameter + "';";      
	        ResultSet rs = stmt.executeQuery(query);  
	          //rs.last();	        
	        
	      
	        
	        while (rs.next()) {
	        	
	        	String profession = rs.getString("profession");
	        	String name       = rs.getString("fullname");
	        	String address    = rs.getString("address");
	        	String phone      = rs.getString("phone").toString();
	        	String email      = rs.getString("email"); 
	        	
	        	String time 	  = rs.getString("appointmentTime");	     
	        	String date 	  = rs.getString("appointmentDate");
	        	
	        	element.add(profession); //add the information to an arraylist
	        	element.add(name);
	        	element.add(address);
	        	element.add(phone);
	        	element.add(email);
	        	
	        	timeClient.add(time);
	        	dateClient.add(date);	        	

	        	action = true;
	        	
	        }
	        dataRequestedByClient  = new String[element.size()]; 
	    	timeRequestedByClient  = new String[timeClient.size()];
	    	datesRequestedByClient = new String[dateClient.size()];
	        

	        dataRequestedByClient  = element.toArray(dataRequestedByClient); //convert arraylist to a array
	    	timeRequestedByClient  = timeClient.toArray(timeRequestedByClient); //this method return to true and also safe date and time from the appointments in these arrays
	    	datesRequestedByClient = dateClient.toArray(datesRequestedByClient); 
	    	

	    	
	     
	        conn.close();
        
	    } catch (SQLException se) {
	        System.out.println("SQL Exception:");
	
	        // Loop through the SQL Exceptions
	        while (se != null) {
	            System.out.println("State  : " + se.getSQLState());
	            System.out.println("Message: " + se.getMessage());
	            System.out.println("Error  : " + se.getErrorCode());
	
	            se = se.getNextException();
	        }
	    } catch (Exception e) {
	        System.out.println(e + " from Exception");
	    } 
        
        return action; //return true or false 
	}

	

}

