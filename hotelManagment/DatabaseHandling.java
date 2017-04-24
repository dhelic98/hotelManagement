package hotelManagment;

import hotelManagment.Guest.Builder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class DatabaseHandling {

	// Input connector za povezivanje na bazu podataka
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";
	private static final String DB = "hotel";

	public static Connection connectToDB() throws SQLException {
		Connection connection = null;
		try {
			// SQL Driver
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/" + DB, USERNAME, PASSWORD);
			// System.out.println("Successfully connected to database!\n");
		} catch (Exception e) {
			System.err.println("Error! Check program!");
		}
		return connection;
	}

	// Method for READING ALL ROOMS

	public static ArrayList<Room> readAllRooms(ArrayList<Room> allRooms) {
		try {
			PreparedStatement statement = connectToDB().prepareStatement(
					"SELECT * FROM rooms");

			ResultSet result = statement.executeQuery();
			while (result.next()) {
				Room room = new Room(result.getInt("room_nmb"),
						result.getBoolean("is_booked"));
				allRooms.add(room);
			}

		} catch (Exception ex) {
			System.out.println("Exception in loadin rooms");
		}

		return allRooms;

	}

	// Admin username and password

	public static Admin getAdminData(String username) {
		Admin admin = new Admin();
		try {
			PreparedStatement statement = connectToDB().prepareStatement(
					"SELECT * FROM admin_users WHERE admin_username = '"
							+ username + "';");
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				admin = new Admin(result.getString("admin_username"),
						result.getString("admin_password"));
			}
		} catch (Exception e) {
			System.err.println(e.toString());
		}
		return admin;
	}

	// Guest username and password

	public static Guest getGuestData(String username) {

		try {
			PreparedStatement statement = connectToDB()
					.prepareStatement(
							"SELECT * FROM guest WHERE user_name = '"
									+ username + "';");
			ResultSet result = statement.executeQuery();

			return new Guest.Builder().userName(result.getString("user_name"))
					.password(result.getInt("user_password")).build();

		} catch (Exception e) {
			System.err.println(e.toString());
		}
		return null;

	}

	// read all guests

	public static ArrayList<Guest> readAll() {
		ArrayList<Guest> listOfGuests = new ArrayList<>();
		int counter = 0;
		try {
			PreparedStatement statement = connectToDB().prepareStatement(
					"SELECT * FROM guest;");
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				Guest guest = new Guest.Builder()
						.firstName(result.getString("first_name"))
						.lastName(result.getString("last_name"))
						.gender(result.getString("gender"))
						.idNumber(result.getString("id_number"))
						.age(result.getInt("age"))
						.roomNumber(result.getInt("room_number"))
						.roomType(result.getString("room_type"))
						.userName(result.getString("user_name"))
						.password(result.getInt("user_password"))
						.balance(result.getDouble("balance"))
						.numOfDays(result.getInt("number_of_days"))
						.timesGymUsed(result.getInt("times_gym_used"))
						.timesPoolUsed(result.getInt("times_pool_used"))
						.timesRestaurantUsed(
								result.getInt("times_restaurant_used"))
						.timesSaunaUsed(result.getInt("times_sauna_used"))
						.timesCinemaUsed(result.getInt("times_cinema_used"))
						.isCheckedIn(result.getBoolean("guest_is_checked_in	"))
						.build();

				listOfGuests.add(guest);

				counter++;
			}
		} catch (Exception e) {
			System.err.println(e.toString());
		}
		return listOfGuests;

	}

	public static void checkOut(String username, Guest guest) {
		try {
			PreparedStatement statement = connectToDB().prepareStatement(
					"REPLACE INTO archive"
							+ "SELECT * FROM guests WHERE user_name = \""
							+ username + "\";\n"
							+ "DELETE * FROM guest WHERE user_name = \""
							+ username + "\";"

			);
			statement.executeUpdate();

		} catch (Exception ex) {
			System.err.println(ex.toString());
		}
	}

	// guest username i password ako je u bazi by idnumber

	public static Builder archiveCheck(String idnumber) {

		try {
			PreparedStatement statement = connectToDB().prepareStatement(
					"SELECT * FROM archive WHERE id_number = \'" + idnumber
							+ "\';");
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				return new Guest.Builder().userName(
						result.getString("user_name")).password(
						result.getInt("user_password"));
			}
		} catch (Exception e) {
			System.err.println(e.toString());
		}
		return null;
	}

	// guest check in
	/*
	 * Added room & username checking before adding guest
	 */
	public static void addGuest(Guest guest) {
		boolean zauzeto = false;
		try {

			String userName = guest.getUserName();
			PreparedStatement statement = connectToDB().prepareStatement(
					"SELECT * FROM guests WHERE user_name = \"" + userName
							+ "\";");
			ResultSet result1 = statement.executeQuery();
			while (result1.next()) {
				String usernameDB = result1.getString("user_name");
				if (usernameDB.equals(userName)) {
					zauzeto = true;
					System.out.println("Username already taken!");
				}
			}
			int roomNumber = guest.getRoomNumber();
			statement = connectToDB().prepareStatement(
					"SELECT * FROM guests WHERE room_number = \"" + roomNumber
							+ "\";");
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				int brojSobeDB = result.getInt("room_number");
				if (brojSobeDB == roomNumber) {
					zauzeto = true;
					System.out.println("Room occupied");
				}
			}

			if (zauzeto != true) {
				System.out.println("Pushing");
				PreparedStatement statement1 = connectToDB().prepareStatement(
						"REPLACE INTO guests(first_name,last_name,gender,"
						+"id_number,age,room_number,room_type,user_name,"
						+ "user_password,balance,number_of_days,times_gym_used,"
						+ "times_pool_used,times_restaurant_used,times_sauna_used,"
						+ "times_cinema_used, guest_is_checked_in) "
						+ "VALUES(" + guest.getFirstName()
								+ "','" + guest.getLastName() + "\',\'"
								+ guest.getGender() + "','"
								+ guest.getIdNumber() + "'," + guest.getAge()
								+ "," + guest.getRoomNumber() + ",'"
								+ guest.getRoomType() + "','"

								+ guest.getUserName() + "',"
								+ guest.getPassword() + ","
								+ guest.getBalance() + ","
								+ guest.getNumOfDays() + ","
								+ guest.getTimesGymUsed() + ","
								+ guest.getTimesPoolUsed() + ","
								+ guest.getTimesRestaurantUsed() + ","
								+ guest.getTimesSaunaUsed() + ","
								+ guest.getTimesCinemaUsed() + ","
								+ guest.isCheckedIn() + ",'"
								+ guest.getTimeCheckedIn()

								+ "');");

				statement1.executeUpdate();
				System.out.println("Update suc");
			}

		} catch (Exception e) {
			System.err.println("Ne radi addGuest check.");
		}
	}

	// setting username for room number guest checked in
	public static void inRoom(String username, int roomnumber) {
		boolean zauzeto = false;
		try {
			if (zauzeto != true) {
				PreparedStatement statement = connectToDB().prepareStatement(
						"UPDATE room SET username = '" + username
								+ "' WHERE number = " + roomnumber + ";");
				statement.executeUpdate();
			}
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	// update servisa hotela
	public static void checkServices(String username, String service) {
		try {
			PreparedStatement statement1a = connectToDB().prepareStatement(
					"UPDATE guest SET times_gym_used = '" + service
							+ "' WHERE user_name = '" + username + "' ;");
			PreparedStatement statement1b = connectToDB().prepareStatement(
					"UPDATE guest SET times_pool_used = '" + service
							+ "' WHERE user_name = '" + username + "' ;");
			PreparedStatement statement1c = connectToDB().prepareStatement(
					"UPDATE guest SET times_restaurant_used = '" + service
							+ "' WHERE user_name = '" + username + "' ;");
			PreparedStatement statement1d = connectToDB().prepareStatement(
					"UPDATE guest SET times_sauna_used = '" + service
							+ "' WHERE user_name = '" + username + "' ;");
			PreparedStatement statement1e = connectToDB().prepareStatement(
					"UPDATE guest SET times_cinema_used = '" + service
							+ "' WHERE user_name = '" + username + "' ;");
			statement1a.executeUpdate();
			statement1b.executeUpdate();
			statement1c.executeUpdate();
			statement1d.executeUpdate();
			statement1e.executeUpdate();
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	// guest updates

	public static void updateRoomNumber(String username, int roomnumber) {
		try {
			PreparedStatement statement1 = connectToDB().prepareStatement(
					"UPDATE room SET is_booked='0' WHERE room_nmb = '"
							+ roomnumber + "';");
			PreparedStatement statement = connectToDB().prepareStatement(
					"UPDATE guest SET room_number = " + roomnumber
							+ " WHERE user_name = '" + username + "';");
			statement1.executeUpdate();
			statement.executeUpdate();
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	public static void updateRoomType(String username, String roomtype,
			int roomnumber) {
		try {
			PreparedStatement statement = connectToDB().prepareStatement(
					"UPDATE guest SET room_type = '" + roomtype
							+ "' WHERE user_name = '" + username + "';");

			PreparedStatement statement2 = connectToDB().prepareStatement(
					"UPDATE room SET user_name = '" + username
							+ "' WHERE room_number = " + roomnumber + ";");
			statement.executeUpdate();

			statement2.executeUpdate();
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	public static void updateDays(String username, int numofdays) {
		try {
			PreparedStatement statement = connectToDB().prepareStatement(
					"UPDATE guest SET number_of_days = " + numofdays
							+ " WHERE user_name = '" + username + "';");
			statement.executeUpdate();
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	// guest bill

	public static Guest guestBill(String username) {
		Guest guest = null;
		try {
			PreparedStatement statement = connectToDB()
					.prepareStatement(
							"SELECT * FROM guest WHERE user_name = '"
									+ username + "';");
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				guest = new Guest.Builder()
						.firstName(result.getString("first_name"))
						.lastName(result.getString("last_name"))
						.gender(result.getString("gender"))
						.idNumber(result.getString("id_number"))
						.age(result.getInt("age"))
						.roomNumber(result.getInt("room_number"))
						.roomType(result.getString("room_type"))
						.userName(result.getString("user_name"))
						.password(result.getInt("user_password"))
						.balance(result.getDouble("balance"))
						.numOfDays(result.getInt("number_of_days"))
						.timesGymUsed(result.getInt("times_gym_used"))
						.timesPoolUsed(result.getInt("times_pool_used"))
						.timesRestaurantUsed(
								result.getInt("times_restaurant_used"))
						.timesSaunaUsed(result.getInt("times_sauna_used"))
						.timesCinemaUsed(result.getInt("times_cinema_used"))
						.isCheckedIn(result.getBoolean("guest_is_checked_in	"))
						.build();

			}
		} catch (Exception e) {
			System.err.println(e.toString());
		}
		return guest;
	}

	// guset check out

	public static void guestCheckOut(int roomNumber, String username) {
		try {
			PreparedStatement statement1 = connectToDB().prepareStatement(
					"UPDATE room SET is_booked = '0' WHERE room_nmb = '"
							+ roomNumber + "';");
			PreparedStatement statement = connectToDB().prepareStatement(
					"DELETE FROM guest WHERE user_name = '" + username + "' ;");
			statement.executeUpdate();
			statement1.executeUpdate();
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	// arhiviranje gosta
	public static void Archive(String idnumber, String username, int password) {
		try {
			PreparedStatement statement = connectToDB().prepareStatement(
					"INSERT INTO archive(id_number, user_name, user_password) VALUES('"
							+ idnumber + "','" + username + "','" + password
							+ "')");
			statement.executeUpdate();
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	// notifikacija
	public static void notify(String username) {
		try {
			PreparedStatement statement = connectToDB().prepareStatement(
					"INSERT INTO notifications(view) VALUES('" + username
							+ "')");
			statement.executeUpdate();
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	public static void notifyClear(String username) {
		try {
			PreparedStatement statement = connectToDB().prepareStatement(
					"DELETE FROM notifications WHERE view = '" + username
							+ "';");
			statement.executeUpdate();
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	public static HashSet<String> viewNot() {
		HashSet<String> notifications = new HashSet<String>();
		try {
			PreparedStatement statement = connectToDB().prepareStatement(
					"SELECT * FROM notifications WHERE view IS NOT NULL ;");
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				notifications.add((result.getString("view")));
			}
		} catch (Exception e) {
			System.err.println(e.toString());
		}
		return notifications;
	}

	// Guest login / Logoff
	/** sets guest Status to 1 or 0, true or false */
	public static void setStatus(String username, boolean status) {
		try {
			PreparedStatement statement = connectToDB().prepareStatement(
					"UPDATE guest SET active = " + status
							+ " WHERE user_name = '" + username + "';");
			statement.executeUpdate();
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	public static ArrayList<Guest> bindTable() {

		// creating a new list of type Guest
		ArrayList<Guest> list = new ArrayList<Guest>();
		// declaring a PreparedStatement and ResultSet
		try {
			PreparedStatement statement = connectToDB()
					.prepareStatement(
							"SELECT user_name, first_name, last_name, gender,"
									+ " id_number, age, room_number, room_type FROM guest WHERE active = 1 ");
			ResultSet result = statement.executeQuery();
			Guest guest = null;
			while (result.next()) {
				new Guest.Builder().firstName(result.getString("first_name"))
						.lastName(result.getString("last_name"))
						.gender(result.getString("gender"))
						.idNumber(result.getString("id_number"))
						.age(result.getInt("age"))
						.roomNumber(result.getInt("room_number"))
						.roomType(result.getString("room_type"))
						.userName(result.getString("user_name")).build();

				list.add(guest);
			}

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		// return list
		return list;
	}
}
