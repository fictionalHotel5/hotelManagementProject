
import java.sql.*;
import java.util.ArrayList;

public class Hotel {

	public static void main(String[] args) throws SQLException {
		Connection connection = null;
		
		connection = DatabaseHandling.getConnection(connection);

		
		Statement statement = connection.createStatement();

		ArrayList<Admin> list_of_admins = new ArrayList<>();
		ArrayList<Guest> list_of_guests = new ArrayList<>();
		ArrayList<Room> list_of_rooms = new ArrayList<>();

		DatabaseHandling.loadRoomDB(list_of_rooms, statement);
		DatabaseHandling.loadGuestDB(list_of_guests, statement);
		DatabaseHandling.loadAdminDB(list_of_admins, statement);
		for (int i = 1; i <= 100; i++) {
			Room room = new Room(i);
			list_of_rooms.add(room);
		}
		System.out.println("Done");

		DatabaseHandling.updateRoomDB(list_of_rooms, statement);
	}

}
