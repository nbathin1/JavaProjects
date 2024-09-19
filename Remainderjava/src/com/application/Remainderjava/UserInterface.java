package com.application.Remainderjava;

import java.util.Optional;
import java.util.Scanner;


public class UserInterface {

	private String choice;	
	private User user;	
	private UserDao userdao;
	private Scanner sc;
	
    public UserInterface(Scanner sc) {
    	this.sc = sc;
        this.userdao = new UserDao();  
    }

	public void start() {
			int i = 0;
			while (i==0) {
			     System.out.println("1. Create new User.....");
			     System.out.println("2. User Login.....");
			     System.out.println("3. Quit.....");
			     
			     choice = sc.nextLine().trim();
			     
			     switch(choice) {
			     case "1": 
			    	 user = new User();
			    	 System.out.println("Enter new User_name....");
			    	  user.setUser_name(sc.nextLine());
			    	  System.out.println("Enter new Password....");
			    	  user.setPassword(sc.nextLine());
			    	  
			    	  userdao.save(user);
			    	  break;
			    	  
			     case "2":
			    	 user = new User();
			    	 System.out.println("Enter Your User_name....");
			    	 user.setUser_name(sc.nextLine());
			    	 System.out.println("Enter Your Password....");
			    	 user.setPassword(sc.nextLine());
			    	 
			    	 Optional<User> foundUser = userdao.find(user);
			    	 
			         if (foundUser.isPresent()) {
			             user = foundUser.get();
			             System.out.println("User found: ID = " + user.getUser_id());
			             RemainderManager rm = new RemainderManager(user,sc);
			             rm.start();
			             
			         } else {
			             System.out.println("User not found with the provided credentials.");
			         }
			         break;
			    	 
			     case "3":
			    	 
			    	 System.out.println("....Bye....");
			    	 i=1;
			    	 break;
			    	 
                 default:
                     System.out.println("Invalid choice. Please try again.");
                     break;
			    	 
			     }
			     
			 }
	}
}
