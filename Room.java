package hotel;

public class Room {

	/*
	 * Room class contains variables and methods related to hotel room
	 */

	private int roomNumber;
	private String roomType;
	private boolean isAvailable;
	private int costPerDay;

	// no-arg constructor
	public Room() {

	}

	Room(int roomNumber, boolean isAvailable) {
		// CONSTRUCTOR FOR LOADING ROOM FROM DATABASE
		this.roomNumber = roomNumber;
		this.isAvailable = isAvailable;

		if (this.roomNumber <= 45) {
			this.roomType = "JEDNOKREVETNA";
			this.costPerDay = 20;
		} else if (this.roomNumber < 90) {
			this.roomType = "DVOKREVETNA";
			this.costPerDay = 40;
		} else {
			this.roomType = "APARTMAN";
			this.costPerDay = 60;
		}

	}

	Room(int roomNumber) {
		// CONSTRUCTOR FOR ROOM
		this.roomNumber = roomNumber;
		this.isAvailable = true;

		if (this.roomNumber <= 45) {
			this.roomType = "JEDNOKREVETNA";
			this.costPerDay = 20;
		} else if (this.roomNumber < 90) {
			this.roomType = "DVOKREVETNA";
			this.costPerDay = 40;
		} else {
			this.roomType = "APARTMAN";
			this.costPerDay = 60;
		}
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public int getCostPerDay() {
		return costPerDay;
	}

	public void setCostPerDay(int costPerDay) {
		this.costPerDay = costPerDay;
	}

	public int isAvailable() {
		if (isAvailable)
			return 1;
		else
			return 0;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public String toString() {
		return "Room :" + getRoomNumber() + "/nRoom type: " + getRoomType()
				+ "/nThis room cost: " + getCostPerDay()
				+ "$ per night/nRoom is available: " + isAvailable();
	}

   	/**
	 * @author vojo
	 * 
	 * */
	// method return list of available rooms
	public ArrayList<Room> allAvailableRooms(ArrayList<Room> listRoms) {

		ArrayList<Room> availableRooms = new ArrayList<>();
		for (int numberRoom = 0; numberRoom < listRoms.size(); numberRoom++) {

			// if single room and available add in list, and prints
			if (this.isAvailable && this.roomNumber <= 45) {
				availableRooms.add(listRoms.get(numberRoom));
				System.out
						.print(listRoms.get(numberRoom).getRoomNumber() + " ");
				// if double room and available add in list and prints
			} else if (this.isAvailable
					&& this.roomNumber < 90) {
				availableRooms.add(listRoms.get(numberRoom));
				System.out
						.print(listRoms.get(numberRoom).getRoomNumber() + " ");
				// if apartment and available , add in list and prints
			} else if (this.isAvailable
					&& this.roomNumber >= 90) {
				availableRooms.add(listRoms.get(numberRoom));
				System.out
						.print(listRoms.get(numberRoom).getRoomNumber() + " ");
			}
		}
		// return list
		return availableRooms;
	}
}

