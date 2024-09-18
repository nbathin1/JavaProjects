package com.application.Remainderjava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
private static DbConnection db = new DbConnection();
private String URL = "jdbc:mysql://localhost:3306/application";
private Connection conn;

public static DbConnection instance() {
	return db;
}
public void connect() throws SQLException {
	this.conn = DriverManager.getConnection(URL, "root", "Got@1234");
}

public Connection getConnection() {
	return conn;
}

public void close() throws SQLException {
	conn.close();	
}
}

