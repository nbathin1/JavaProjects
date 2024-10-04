package com.swingapplication.tasks;

import javax.swing.SwingUtilities;

import controller.Controller;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Controller::new);
    }
}
