package application;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class UserInterface {

    private RemainderManager manager;
    private RemainderNotifier notifier;
	private Scanner scanner;
	//private int user_id;
    

    public UserInterface(int user_id, Connection conn, Scanner scanner){
        this.manager = new RemainderManager(user_id, conn);
        this.notifier = new RemainderNotifier();
        this.scanner = scanner;
    }
    
    public void start() throws SQLException{
        try (Scanner sc = new Scanner(System.in)) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

			while(true){
			    System.out.println("1. Create a Remainder");
			    System.out.println("2. View All Remainders");
			    System.out.println("3. Mark a Remainder as Done");
			    System.out.println("4. Delete a Remainder");
			    System.out.println("5. Check Remainders");
			    System.out.println("6. Exit");

			    String c = scanner.nextLine();

			    

			    switch(c){

			        case "1":
			            System.out.println("Enter the Remainder title: ");
			            String title = scanner.nextLine();

			            System.out.println("Enter remainder time(yyyy-MM-dd HH:mm): ");
			            String timeInput = scanner.nextLine();
			            LocalDateTime time = LocalDateTime.parse(timeInput, formatter);

			            Remainder remainder = new Remainder(title,time,false);
			            manager.addRemainder(remainder);
			            break;

			        case "2":
			            try {
			                List<Remainder> remainders = manager.getRemainder();
			                if (remainders == null) {
			                    System.out.println("Error: Remainders list is null.");
			                } else if (remainders.isEmpty()) {
			                    System.out.println("No remainders found.");
			                } else {
			                    for (Remainder r : remainders) {
			                        System.out.println(r.toString());
			                    }
			                }
			            } catch (Exception e) {
			                System.out.println("An error occurred: " + e.getMessage());
			                e.printStackTrace();
			            }
			            break;

			        case "3":
			            System.out.println("Enter the title of the remainder to mark done :");
			            String doneTitle = scanner.nextLine();
			            manager.markRemainderAsDone(doneTitle);
			            break;
			            
			        case "4":
			            System.out.println("Enter the title of the Remainder to be deleted :");
			            String deleteTitle = scanner.nextLine();
			            manager.deleteRemainder(deleteTitle);
			            
			            break;
			        
			        case "5":
			            notifier.checkRemainders(manager);
			            break;
			        
			        case "6":
			            System.out.println("Exiting... ");
			            break;
			            
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
			            
			    }
			    if(c.equals("6")) {
			    	break;
			    }
			    
			}
		}
    }
    
}