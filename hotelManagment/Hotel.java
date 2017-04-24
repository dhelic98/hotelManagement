package hotelManagment;

import hotelManagment.Guest.Builder;

import java.util.ArrayList;
import java.util.Scanner;

public class Hotel {

	public static void main(String[] args) {
		// Main method
		displayWelcome();
		ArrayList<Room> allRooms = new ArrayList<>();
		ArrayList<Guest> listOfLoggedInGuests = new ArrayList<>();
		ArrayList<Admin> listOfLoggedInAdmins = new ArrayList<>();

		while (true) {
			try {
				System.out.println("1)Login as ADMIN");
				System.out.println("2)Loign as GUEST");
				int option = Integer.parseInt(RoomService.input.nextLine());
				if (option < 1 || option > 2) {
					System.out.println("Wrong input");
					continue;
				}

				// INPUT BY USER
				System.out.println("Enter username");
				String username = RoomService.input.nextLine();
				System.out.println("Enter password");
				String password = RoomService.input.next();

				if (option == 1) {
					// ADMIN USER
					AdminApplication.runAdmin(username, password, listOfLoggedInAdmins,listOfLoggedInGuests);
				} else if (option == 2) {
					// GUEST USER

					GuestApplication.runGuest(username, password, allRooms, listOfLoggedInGuests);
				}

				break;

			} catch (Exception ex) {
				System.out.println("Exception");
				// RoomService.input.nextLine();
			}
		}

	}

	public static void displayWelcome() {
		// Welcome display
		System.out.println("-------------");
		System.out.println("  Welcome to ");
		System.out.println("   Fictional ");
		System.out.println("     Hotel   ");
		System.out.println("-------------");

	}

}
