package hotelManagment;

import hotelManagment.Guest.Builder;

import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Guest {

	static Scanner input = new Scanner(System.in);
	/**
	 * @author Sipo
	 */

	private String firstName;
	private String lastName;
	private String userName;
	private int password;
	private String idNumber;
	private String sex;
	private int age;
	private int roomNumber;
	private String roomType;
	private Date timeCheckedIn;
	private double balance;
	private int numOfDays;
	private int timesGymUsed;
	private int timesPoolUsed;
	private int timesRestaurantUsed;
	private int timesSaunaUsed;
	private int timesCinemaUsed;
	private boolean isCheckedIn;

	public Guest(Builder builder) {
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		this.userName = builder.userName;
		this.password = builder.password;
		this.idNumber = builder.idNumber;
		this.sex = builder.sex;
		this.age = builder.age;
		this.roomNumber = builder.roomNumber;
		this.roomType = builder.roomType;
		this.balance = builder.balance;
		this.numOfDays = builder.numOfDays;
		this.timesGymUsed = builder.timesGymUsed;
		this.timesPoolUsed = builder.timesPoolUsed;
		this.timesRestaurantUsed = builder.timesRestaurantUsed;
		this.timesSaunaUsed = builder.timesSaunaUsed;
		this.timesCinemaUsed = builder.timesCinemaUsed;
		this.timeCheckedIn = builder.timeCheckedIn;
		this.isCheckedIn = builder.isCheckedIn;

	}

	/** setters **/

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(int password) {
		this.password = password;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public void setTimeCheckedIn(Date timeCheckedIn) {
		this.timeCheckedIn = timeCheckedIn;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public void setNumOfDays(int numOfDays) {
		this.numOfDays = numOfDays;
	}

	public void setTimesGymUsed(int timesGymUsed) {
		this.timesGymUsed = timesGymUsed;
	}

	public void setTimesPoolUsed(int timesPoolUsed) {
		this.timesPoolUsed = timesPoolUsed;
	}

	public void setTimesRestaurantUsed(int timesRestaurantUsed) {
		this.timesRestaurantUsed = timesRestaurantUsed;
	}

	public void setTimesSaunaUsed(int timesSaunaUsed) {
		this.timesSaunaUsed = timesSaunaUsed;
	}

	public void setTimesCinemaUsed(int timesCinemaUsed) {
		this.timesCinemaUsed = timesCinemaUsed;
	}

	public void setCheckedIn(boolean isCheckedIn) {
		this.isCheckedIn = isCheckedIn;
	}

	/** getters */
	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getUserName() {
		return userName;
	}

	public int getPassword() {
		return password;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public String getGender() {
		return sex;
	}

	public int getAge() {
		return age;
	}

	public String getRoomType() {
		return roomType;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public Date getTimeCheckedIn() {
		return timeCheckedIn;
	}

	public double getBalance() {
		return balance;
	}

	public int getNumOfDays() {
		return numOfDays;
	}

	public int getTimesGymUsed() {
		return timesGymUsed;
	}

	public int getTimesPoolUsed() {
		return timesPoolUsed;
	}

	public int getTimesRestaurantUsed() {
		return timesRestaurantUsed;
	}

	public int getTimesSaunaUsed() {
		return timesSaunaUsed;
	}

	public int getTimesCinemaUsed() {
		return timesCinemaUsed;
	}

	public int isCheckedIn() {
		return (isCheckedIn) ? 1 : 0;
	}

	public static class Builder {

		private String firstName;
		private String lastName;
		private String userName;
		private int password;
		private String idNumber;
		private String sex;
		private String roomType;
		private int age;
		private int roomNumber;
		private double balance;
		private int numOfDays;
		private int timesGymUsed;
		private int timesPoolUsed;
		private int timesRestaurantUsed;
		private int timesSaunaUsed;
		private int timesCinemaUsed;
		private boolean isCheckedIn;
		private Date timeCheckedIn;

		public Builder() {

		}

		/**
		 * @Jasmina getters
		 */
		public String getFirstName() {
			return firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public String getUserName() {
			return userName;
		}

		public int getPassword() {
			return password;
		}

		public String getIdNumber() {
			return idNumber;
		}

		public String getGender() {
			return sex;
		}

		public int getAge() {
			return age;
		}

		public String getRoomType() {
			return roomType;
		}

		public int getRoomNumber() {
			return roomNumber;
		}

		public Date getTimeCheckedIn() {
			return timeCheckedIn;
		}

		public double getBalance() {
			return balance;
		}

		public int getNumOfDays() {
			return numOfDays;
		}

		public int getTimesGymUsed() {
			return timesGymUsed;
		}

		public int getTimesPoolUsed() {
			return timesPoolUsed;
		}

		public int getTimesRestaurantUsed() {
			return timesRestaurantUsed;
		}

		public int getTimesSaunaUsed() {
			return timesSaunaUsed;
		}

		public int getTimesCinemaUsed() {
			return timesCinemaUsed;
		}

		public boolean isCheckedIn() {
			return isCheckedIn;
		}

		/** setters */
		public Builder firstName(String firstName) {
			this.firstName = firstName;
			return this;
		}

		public Builder lastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		public Builder userName(String userName) {
			this.userName = userName;
			return this;
		}

		public Builder password(int password) {
			this.password = password;
			return this;
		}

		public Builder idNumber(String idNumber) {
			this.idNumber = idNumber;
			return this;
		}

		public Builder gender(String sex) {
			this.sex = sex;
			return this;
		}

		public Builder age(int age) {
			this.age = age;
			return this;
		}

		public Builder roomType(String type) {
			this.roomType = type;
			return this;
		}

		public Builder roomNumber(int room) {
			this.roomNumber = room;
			return this;
		}

		public Builder timeCheckedIn(Date checkedIn) {
			this.timeCheckedIn = checkedIn;
			return this;
		}

		public Builder isCheckedIn(boolean isChecked) {
			this.isCheckedIn = isChecked;
			return this;
		}

		public Builder balance(double balance) {
			this.balance = balance;
			return this;
		}

		public Builder numOfDays(int numOfDays) {
			this.numOfDays = numOfDays;
			return this;
		}

		public Builder timesGymUsed(int timesGymUsed) {
			this.timesGymUsed = timesGymUsed;
			return this;
		}

		public Builder timesPoolUsed(int timesPoolUsed) {
			this.timesPoolUsed = timesPoolUsed;
			return this;
		}

		public Builder timesSaunaUsed(int timesSaunaUsed) {
			this.timesSaunaUsed = timesSaunaUsed;
			return this;
		}

		public Builder timesRestaurantUsed(int timesRestaurantUsed) {
			this.timesRestaurantUsed = timesRestaurantUsed;
			return this;
		}

		public Builder timesCinemaUsed(int timesCinemaUsed) {
			this.timesCinemaUsed = timesCinemaUsed;
			return this;
		}

		/** @ Jasmina */

		public Guest build() {
			if (firstName == null) {
				throw new IllegalStateException(" No first name!");
			}
			if (lastName == null) {
				throw new IllegalStateException(" No last name!");
			}
			if (userName == null) {
				throw new IllegalStateException(" No user name!");
			}
			if (password < 1000 || password > 9999) {
				throw new IllegalStateException(" No password!");
			}
			if (idNumber == null) {
				throw new IllegalStateException(" No ID number!");
			}
			if (sex == null) {
				throw new IllegalStateException(" No sex!");
			}
			if (age < 18 || age > 120) {
				throw new IllegalStateException(" Age out of range!");
			}
			if (roomNumber < 1) {
				throw new IllegalStateException(" Room number does not exists!");
			}

			if (timeCheckedIn == null) {
				throw new IllegalStateException(" No time checked in!");
			}

			return new Guest(this);
		}

	}

	public void setNewRoom(int newRoom) {
		this.roomNumber = newRoom;
	}

	public void addBalance(double newBalance) {
		this.balance += newBalance;
	}

}