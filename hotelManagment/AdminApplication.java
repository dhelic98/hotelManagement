package hotelManagment;

import java.util.ArrayList;
import java.util.Date;

public class AdminApplication {

	/*
	 * @author Dzenan
	 */
	public static void runAdmin(String username, String password,
			ArrayList<Admin> listOfLoggedInAdmins,
			ArrayList<Guest> listOfLoggedInGuests) {

		// Main app for admin
		Admin admin = DatabaseHandling.getAdminData(username);

		// Condition for login for using app
		if (admin.getUsername().equals(username)
				&& admin.getPassword().equals(password)) {

			
			
			System.out.println(admin.getUsername() + " welcome!");
			System.out.println("--------------------");
			RoomService.input.nextLine();

			// Printing menut
			while (true) {
				printAdminMenu();
				try {
					int adminOption = Integer.parseInt(RoomService.input
							.nextLine());

					if (adminOption == 1) {
						addNewGuest();
						System.out.println("Successfully added new guest");
					} else {
						System.out.println("Wrong input");
						continue;
					}

				} catch (Exception ex) {
					System.out.println("Admin menu exception");
				}
			}

		} else {
			System.out.println("Wrong username and/or password");
		}
	}

	/*
	 * Printing out admin main menu
	 */
	public static void printAdminMenu() {
		System.out.println("What would you like to do?");
		System.out.println("1) New guest");
		System.out.println("2) Change guest room/utilites");
		System.out.println("3) Check user utilites balance");
		System.out.println("4) Guest check-out");
		System.out.println("5) See logged in users");
		System.out.println("6) Search database ");
		System.out.println("7) Log out");
	}

	public static void addNewGuest() {
		try {
			

			System.out.println("Enter first name");
			String firstName = RoomService.input.nextLine();
			System.out.println("Enter last name");
			String lastName = RoomService.input.nextLine();
			System.out.println("Enter gender");
			String gender = RoomService.input.nextLine();
			System.out.println("Enter ID number");
			String idNumber = RoomService.input.nextLine();
			System.out.println("Enter age");
			int age = Integer.parseInt(RoomService.input.nextLine());
			System.out.println("Enter room number");
			int roomNumber = Integer.parseInt(RoomService.input.nextLine());
			Room room = new Room(roomNumber);
			System.out.println("Enter username");
			String username = RoomService.input.nextLine();
			System.out.println("Enter password");
			int password = getPassword();
			System.out.println("Enter number of days guest is staying");
			int days = Integer.parseInt(RoomService.input.nextLine());
			Date checkedIn=new Date();
			
			Guest guest = new Guest.Builder().firstName(firstName)
					.lastName(lastName).gender(gender).idNumber(idNumber)
					.age(age).roomNumber(roomNumber)
					.roomType(room.getRoomType()).userName(username)
					.password(password).balance(0).numOfDays(days)
					.timesGymUsed(0).timesPoolUsed(0).timesRestaurantUsed(0)
					.timesSaunaUsed(0).timesCinemaUsed(0).isCheckedIn(true)
					.timeCheckedIn(checkedIn)
					.build();

			
			//RoomService.input.nextLine();
			DatabaseHandling.addGuest(guest);

			//System.out.println("Successfully added new guest");
		} catch (Exception ex) {
			System.out.println("Exception in adding new guest");
		}
	}

	public static int getPassword() {
		while (true) {
			int password = Integer.parseInt(RoomService.input.nextLine());
			if (password < 1000 || password > 9999) {
				System.out.println("Enter password from 1000 to 9999");
				continue;
			}

			return password;
		}

	}

}
