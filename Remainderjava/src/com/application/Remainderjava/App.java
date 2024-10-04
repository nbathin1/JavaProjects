package com.application.Remainderjava;

import java.sql.SQLException;
import javax.swing.*;

public class App {
    public static void main(String[] args) {
        DbConnection db = DbConnection.instance();

        SwingUtilities.invokeLater(() -> {
            try {
                db.connect();
                System.out.println("Connected Successfully");
                UserInterface ui = new UserInterface();
                ui.start();
       
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
}

