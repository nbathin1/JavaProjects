package com.app.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
private static Database db = new Database();
private String URL = "jdbc:mysql://localhost:3306/application";
private Connection conn;

public static Database instance() {
	return db;
}
public void connect() throws SQLException {
	this.conn = DriverManager.getConnection(URL, "root", "Got@1234");
}

public void close() throws SQLException {
	conn.close();	
}
}
