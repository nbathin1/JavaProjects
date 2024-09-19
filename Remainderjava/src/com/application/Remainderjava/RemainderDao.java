package com.application.Remainderjava;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RemainderDao implements DAO<Remainder> {
	public Connection conn = DbConnection.instance().getConnection();
	@Override
	public void save(Remainder t) {
		
		try {
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO remainder (user_id, remainder, time, isDone) values (?,?,?,?)");
			pstmt.setInt(1, t.getUser_id());
			pstmt.setString(2, t.getTitle());
			pstmt.setTimestamp(3, Timestamp.valueOf(t.getTime()));
			pstmt.setBoolean(4, t.isDone());
			
			pstmt.executeUpdate();
			
			System.out.println("You have entered Remainder " + t.toString() + " sucessfully");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(Remainder t) {
		try {
			PreparedStatement pstmt = conn.prepareStatement("UPDATE remainder SET isdone = ? WHERE remainder = ? AND user_id = ?");
			pstmt.setBoolean(1, true);
			pstmt.setString(2,t.getTitle());
			pstmt.setInt(3, t.getUser_id());
			
			pstmt.executeUpdate();
			
			System.out.println("The Remainder " + t.getTitle() + " is Done......");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Remainder t) {
		try {
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM remainder WHERE remainder = ? AND user_id = ?");
			pstmt.setString(1, t.getTitle());
			pstmt.setInt(2, t.getUser_id());
			
			pstmt.executeUpdate();
			System.out.println("Remainder "+ t.getTitle() + " Deleted sucessfully");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
    public Optional<Remainder> find(Remainder t) {
        Optional<Remainder> remainderOptional = Optional.empty();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {            
            pstmt = conn.prepareStatement("SELECT * FROM remainder WHERE remainder = ? AND user_id = ?");
            pstmt.setString(1, t.getTitle());
            pstmt.setInt(2, t.getUser_id());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                Remainder remainder = new Remainder(
             
                    rs.getInt("user_id"),
                    rs.getString("remainder_name"),
                    rs.getTimestamp("time").toLocalDateTime(),
                    rs.getBoolean("isDone")
                );
                remainderOptional = Optional.of(remainder);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return remainderOptional;
    }


	@Override
	public List<Remainder> getAll(int t) {
        List<Remainder> remainders = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {

            pstmt = conn.prepareStatement("SELECT * FROM remainder where user_id = ?");
            pstmt.setInt(1, t);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Remainder remainder = new Remainder(
                    rs.getInt("user_id"),
                    rs.getString("remainder"),
                    rs.getTimestamp("time").toLocalDateTime(), 
                    rs.getBoolean("isDone")
                );
                remainders.add(remainder); 
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        } 
        return remainders; 
    }

}
