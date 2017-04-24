package hotelManagment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
/**
 * 
 * @author Sipo
 *
 */
public final class Admin {
	//pojednostavljena klasa radi lakseg citanja koda
	private String username;
	private String password;

	// constructor with data fields
	/**
	 * @param username
	 * @param password
	 */
	
	public Admin(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public Admin() {
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

}
