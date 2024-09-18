package com.application.Remainderjava;

import java.sql.SQLException;

public class App {
    public static void main(String[] args) {
        DbConnection db = DbConnection.instance();
        
        try {
			db.connect();
			System.out.println("Connected Sucessfully");
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
