package hotelManagementProject;

import java.sql.*;
import java.util.ArrayList;

public class Hotel {

	public static void main(String[] args) throws SQLException {
		Connection connection = null;
		connection = Database_handling.getConnection(connection);

		Statement statement = connection.createStatement();

		ArrayList<Admin> list_of_admins = new ArrayList<>();
		ArrayList<Guest> list_of_guests = new ArrayList<>();
		ArrayList<Room> list_of_rooms = new ArrayList<>();

		Database_handling.load_roomdb(list_of_rooms, statement);
		Database_handling.load_guestdb(list_of_guests, statement);
		Database_handling.load_admindb(list_of_admins, statement);
		for (int i = 1; i <= 100; i++) {
			Room room = new Room(i);
			list_of_rooms.add(room);
		}
		System.out.println("Done");

		Database_handling.update_roomdb(list_of_rooms, statement);
	}

}
