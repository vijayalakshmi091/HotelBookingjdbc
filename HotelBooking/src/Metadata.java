import java.sql.*;
public class Metadata {
	public static void main(String[]args) throws Exception {
		String url="jdbc:mysql://127.0.0.1:3306/hotel";
		String Username="root";
		String password="viji";
		String query= "Select * from  room ";
		Connection con=DriverManager.getConnection(url, Username, password);
		Statement st=con.createStatement();
		ResultSet rt=st.executeQuery(query);
		ResultSetMetaData rsmd=rt.getMetaData();
		int columncount=rsmd.getColumnCount();
		System.out.println("Number of columns"+columncount );
		for(int i=1;i<=columncount;i++) {
			System.out.println("column"+i+":"+rsmd.getColumnName(i)+"("+rsmd.getColumnTypeName(i)+")");
			
			
			
		}
		
		
	}

}
