package com.application.Remainderjava;

public class User {
	

	private int user_id;
	private String user_name;
	private String password;
	
	public User(int user_id, String user_name, String password) {
		this.user_id = user_id;
		this.user_name = user_name;
		this.password = password;
	}
	
    public User() {

    }
	
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", user_name=" + user_name + ", password=" + password + "]";
	}
}
