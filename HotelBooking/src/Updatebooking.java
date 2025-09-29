import java.sql.*;
public class Updatebooking {
	
	public static void update() throws Exception {
		String url="jdbc:mysql://127.0.0.1:3306/hotel";
		String Username="root";
		String password="viji";
		String query= "Update room set status='Available' where room_id=1";
		Connection con=DriverManager.getConnection(url, Username, password);
		PreparedStatement pst=con.prepareStatement(query);
		pst.executeUpdate();
		con.close();
		
	}
	public static void main(String[]args) throws Exception {
		update();

	}
}
