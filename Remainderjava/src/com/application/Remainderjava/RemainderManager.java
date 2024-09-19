package com.application.Remainderjava;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class RemainderManager {
//private Remainder remainder;
private String choice;
private RemainderDao remainderDao;

private User user;
private Scanner sc;
private Remainder remainder;

public RemainderManager(User user, Scanner sc) {
	this.user = user;
	this.sc = sc;
	this.remainderDao =new  RemainderDao();
	
}

public void start() {

		this.remainder = new Remainder();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		int i = 0;
		while(i==0) {
		    System.out.println("1. Create a Remainder");
		    System.out.println("2. View All Remainders");
		    System.out.println("3. Mark a Remainder as Done");
		    System.out.println("4. Delete a Remainder");
		    System.out.println("5. Find a Remainders");
		    System.out.println("6. Exit");
		    
		    choice = sc.nextLine();
		    switch(choice) {
		    
		    case "1":
		    	System.out.println("Enter the Remainder title: ");
		    	remainder.setTitle(sc.nextLine());
		    	System.out.println("Enter remainder time(yyyy-MM-dd HH:mm): ");
		    	LocalDateTime time = LocalDateTime.parse(sc.nextLine(), formatter);
		    	remainder.setTime(time);
		    	remainder.setUser_id(user.getUser_id());
		    	remainder.setDone(false);
		    	remainderDao.save(remainder);
		    	break;
		    	
		    case "2":
		    	List<Remainder> remainders = remainderDao.getAll(user.getUser_id());
		        if (remainders.isEmpty()) {
		            System.out.println("No remainders found for User ID: " + user.getUser_id());
		        } else {
		            System.out.println("Remainders for User ID " + user.getUser_id() + ":");
		            for (Remainder r : remainders) {
		                System.out.println("Remainder Name: " + r.getTitle() + 
		                                   ", Time: " + r.getTime() + 
		                                   ", Is Done: " + (r.isDone() ? "Yes" : "No"));
		            }
		        }
		        break;
		        
		    case "3":
		    	System.out.println("Enter the Remainder title to be done: ");
		    	remainder.setTitle(sc.nextLine());
		    	remainder.setUser_id(user.getUser_id());
		    	remainderDao.update(remainder);
		    	break;
		    	
		    case "4":
		    	System.out.println("Enter the Remainder title to be deleted: ");
		    	remainder.setTitle(sc.nextLine());
		    	remainder.setUser_id(user.getUser_id());
		    	remainderDao.delete(remainder);
		    	break;
		    	
		    case "5":
		    	System.out.println("Enter the Remainder title to be deleted: ");
		    	remainder.setTitle(sc.nextLine());
		    	remainder.setUser_id(user.getUser_id());
		    	remainderDao.find(remainder);
		    	Optional<Remainder> foundRemainder = remainderDao.find(remainder);
		        if (foundRemainder.isPresent()) {
		            System.out.println("Remainder found: " + foundRemainder.get().getTitle());
		            
		        } else {
		            System.out.println("Remainder not found with the given title for user.");
		        }
		        break;
		        
		        
		    case "6":
		    	i=1;
		    	System.out.println(">>>Exit<<<");
		    	break;
		    	
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
		    	
		    }
		  
		    
		}
	}
}

