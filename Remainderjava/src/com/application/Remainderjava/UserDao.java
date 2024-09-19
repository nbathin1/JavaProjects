package com.application.Remainderjava;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


public class UserDao implements DAO<User> {

	@Override
	public void save(User t) {
	
		var conn = DbConnection.instance().getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO users (user_name, password) VALUES (?, ?)");
			pstmt.setString(1,t.getUser_name());
			pstmt.setString(2, t.getPassword());
			pstmt.executeUpdate();
			System.out.println("New user created.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(User t) {
		
		var conn = DbConnection.instance().getConnection();
		
		try {
			PreparedStatement pstmt = conn.prepareStatement("UPDATE users SET password = ? WHERE user_id = ?");
			pstmt.setString(1, t.getPassword());
			pstmt.setInt(2, t.getUser_id());
			
			pstmt.executeUpdate();
			
			System.out.println("Password Changed Scessfully");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
	}

	@Override
	public void delete(User t) {
			var conn = DbConnection.instance().getConnection();
			try {
				PreparedStatement pstmt = conn.prepareStatement("DELETE FROM users WHERE user_id = ?");
				pstmt.setInt(1, t.getUser_id());
				pstmt.executeUpdate();
				
				System.out.println("User account has been deleted");
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
	}

	@Override
	public Optional<User> find(User t) {
		var conn = DbConnection.instance().getConnection();
		Optional<User> userOptional = Optional.empty();
		try {
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM users WHERE user_name = ? AND password = ?");
			pstmt.setString(1,t.getUser_name());
			pstmt.setString(2, t.getPassword());
			
			ResultSet rs = pstmt.executeQuery();
	        
	        if (rs.next()) {
	  
	            User user = new User(rs.getInt("user_id"), rs.getString("user_name"), rs.getString("password"));
	            userOptional = Optional.of(user);
	        }

			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return userOptional;
	}

	@Override
	public List<User> getAll(int t) {
		return null;
	}
	

}
