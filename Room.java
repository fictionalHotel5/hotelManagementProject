package hotel;

/*
 * Room class contains variables and methods related to hotel room
 */

public class Room {

	private int roomNumber;
	private String roomType;
	private boolean isAvailable;
	private int costPerDay;

	// no-arg constructor
	public Room() {

	}

	public Room(int roomNumber, String roomType, boolean isAvailable, int costPerDay) {
		this.roomNumber = roomNumber;
		this.roomType = roomType;
		this.isAvailable = isAvailable;
		this.costPerDay = costPerDay;
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

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public String toString() {
		return "Room :" + getRoomNumber() + "/nRoom type: " + getRoomType() + "/nThis room cost: " + getCostPerDay()
				+ "$ per night/nRoom is available: " + isAvailable();
	}

}
