package hotelManagment;

import java.sql.SQLException;
import java.util.ArrayList;

public class GuestApplication {
	/**
	 * @author Dzenan
	 * @param username
	 * @param password
	 * @param allRooms
	 * @param listOfLoggedInGuests
	 * @throws SQLException
	 */

	public static void runGuest(String username, String password,
			ArrayList<Room> allRooms, ArrayList<Guest> listOfLoggedInGuests)
			throws SQLException {
		// LOGIN METHOD
		Guest guest = DatabaseHandling.getGuestData(username);
		if (guest.getUserName().equals(username)
				&& (guest.getPassword() == Integer.parseInt(password))) {
			// CONDITION FOR LOGIN
			System.out.println("Login successfull");
			listOfLoggedInGuests.add(guest);
			RoomService.displayMenu(allRooms, guest);

		} else {
			System.out.println("Wrong username and/or password");
		}
	}

}
