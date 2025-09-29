
import java.sql.*;
import java.util.Scanner;
public class Createbooking {
	public  static void create() {
		String url="jdbc:mysql://127.0.0.1:3306/hotel";
		String Username="root";
		String password="viji";
		
		
		Scanner sc = new Scanner(System.in);

	        try (	Connection con=DriverManager.getConnection(url, Username, password)) {
	             while (true) {
	                System.out.println("\n--- Hotel Booking System ---");
	                System.out.println("1. View Available room");
	                System.out.println("2. Book a room");
	                System.out.println("3. Exit");
	                System.out.print("Enter choice: ");
	                int choice = sc.nextInt();

	                if (choice == 1) {
	                    viewAvailableroom(con);
	                } else if (choice == 2) {
	                    bookroom(con, sc);
	                } else {
	                    System.out.println("Goodbye!");
	                    break;
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    // Show available rooms
	    
	private static void viewAvailableroom(Connection con) throws SQLException {
        String query = "SELECT * FROM room WHERE status = 'Available'";
        try(
				  Statement stmt = con.createStatement();
				  ResultSet rs = stmt.executeQuery(query)) {
	            System.out.println("\nAvailable Rooms:");
	            while (rs.next()) {
	                System.out.println("Room ID: " + rs.getInt("room_id") +
	                                   ", Number: " + rs.getString("room_number") +
	                                   ", Type: " + rs.getString("type") +
	                                   ", Price: " + rs.getDouble("price"));
	            }
	        }
	    }

	    // Book a room
	    private static void bookroom(Connection con, Scanner sc) throws SQLException {
	        sc.nextLine(); // consume newline
	        System.out.print("Enter Customer Name: ");
	        String name = sc.nextLine();
	        System.out.print("Enter Email: ");
	        String email = sc.nextLine();
	        System.out.print("Enter Phone: ");
	        String phone = sc.nextLine();

	        // Insert customer
	        String insertcustomer = "INSERT INTO customers (name, email, phone) VALUES (?, ?, ?)";
	        PreparedStatement pst = con.prepareStatement(insertcustomer, Statement.RETURN_GENERATED_KEYS);
	        pst.setString(1, name);
	        pst.setString(2, email);
	        pst.setString(3, phone);
	        pst.executeUpdate();
	        ResultSet rs = pst.getGeneratedKeys();
	        rs.next();
	        int customerId = rs.getInt(1);

	        // Select room
	        System.out.print("Enter Room ID to book: ");
	        int roomId = sc.nextInt();
	        sc.nextLine();
	        System.out.print("Enter Check-in Date (YYYY-MM-DD): ");
	        String checkIn = sc.nextLine();
	        System.out.print("Enter Check-out Date (YYYY-MM-DD): ");
	        String checkOut = sc.nextLine();

	        // Insert booking
	        String insertbookings = "INSERT INTO bookings (customer_id, room_id, check_in, check_out) VALUES (?, ?, ?, ?)";
	        PreparedStatement pst2 = con.prepareStatement(insertbookings);
	        pst2.setInt(1, customerId);
	        pst2.setInt(2, roomId);
	        pst2.setString(3, checkIn);
	        pst2.setString(4, checkOut);
	        pst2.executeUpdate();

	    

	        System.out.println("Room booked successfully!");
	    }

	    
	
	public static void main(String[] args) {
		create();
		
	}
	}

