package application;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
    	Scanner scanner = new Scanner(System.in);
            LoginInterface loginInterface = new LoginInterface(scanner);
            loginInterface.start(); 
    }
}
