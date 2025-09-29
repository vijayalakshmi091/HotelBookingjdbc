import java.sql.*;
public class Deletebooking {
	
	public static void delete()  throws Exception {
		String url="jdbc:mysql://127.0.0.1:3306/hotel";
		String Username="root";
		String password="viji";
		String query= "delete  from bookings where booking_id=2";
		Connection con=DriverManager.getConnection(url, Username, password);
		PreparedStatement pst=con.prepareStatement(query);
		pst.executeUpdate();
		con.close();
		
	}
	public static void main(String[]args) throws Exception {
		delete();
		
	}

}
