package com.application.Remainderjava;

import java.sql.SQLException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        DbConnection db = DbConnection.instance();
        
        try(Scanner sc = new Scanner(System.in)) {
			db.connect();
			System.out.println("Connected Sucessfully");
			UserInterface ui = new UserInterface(sc);
			ui.start();
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
        
        try {
			db.close();
			System.out.println("Closed Sucessfully");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
        
        
    }
}
