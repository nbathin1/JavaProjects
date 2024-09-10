package application;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RemainderManager {
	private Connection conn;
	private int user_id;

    public RemainderManager(int user_id, Connection conn){
    	this.user_id = user_id;
    	this.conn = conn;
    }
    
    
    public void addRemainder(Remainder remainder) throws SQLException{
    	
    	String query = "INSERT INTO remainder (user_id, remainder, time, isDone) values (?,?,?,?)";
    	PreparedStatement pstmt = conn.prepareStatement(query);

    	pstmt.setInt(1, user_id);                                  
    	pstmt.setString(2, remainder.getTitle());                  
    	pstmt.setTimestamp(3, Timestamp.valueOf(remainder.getTime()));  
    	pstmt.setBoolean(4, remainder.isDone());                   

    	// Execute the update
    	pstmt.executeUpdate();
        
        System.out.println("Remainder added : " + remainder.getTitle());
    }

    public void deleteRemainder(String deleteTitle) throws SQLException{
        String delete = deleteTitle ;
        String query = "DELETE FROM remainder WHERE remainder_name = ? AND user_id = ?";
        PreparedStatement pstm2 = conn.prepareStatement(query);
        pstm2.setString(1, delete);
        pstm2.setInt(2, user_id);
        pstm2.executeUpdate();
        System.out.println("Remainder is deleted " + delete);
        
    }

    public  List<Remainder> getRemainder() throws SQLException{
        List<Remainder> remainders = new ArrayList<>();
        
        String query = "SELECT * FROM remainder WHERE user_id = ?";
        PreparedStatement pstmt1 = conn.prepareStatement(query);
        pstmt1.setInt(1, user_id);
        ResultSet rs = pstmt1.executeQuery();
        
        while (rs.next()) {
            
            @SuppressWarnings("unused")
			int id = rs.getInt("id");
            String title = rs.getString("remainder");
            LocalDateTime time = rs.getTimestamp("time").toLocalDateTime();
            boolean isDone = rs.getBoolean("isDone");

            Remainder remainder = new Remainder(title, time, isDone);
            remainders.add(remainder); 
        }
        
        return remainders; 
    }

    public void markRemainderAsDone(String donetitle) throws SQLException{
    	String update = donetitle ;
    	String query = "UPDATE remainder SET isdone = ? WHERE remainder_name = ? AND user_id = ?";
    	PreparedStatement pstmt3 = conn.prepareStatement(query);
    	pstmt3.setBoolean(1, true);
    	pstmt3.setString(2, update);
    	pstmt3.setInt(3, user_id);
        System.out.println("Remainder marked as done: " + update);
    }
}