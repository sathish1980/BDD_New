package Javaprogramstest;
import java.sql.*;  


public class sqlconnect {


		// TODO Auto-generated method stub
		
		
		 
		public static void main(String args[]){  
		try{  
		//step1 load the driver class  
			System.out.println("Entered");
		Class.forName("oracle.jdbc.driver.OracleDriver");  
		  
		//step2 create  the connection object  
		Connection con=DriverManager.getConnection(  
				"jdbc:oracle:thin:@localhost:53569:devl","U911829933","EYT#9933sa26");   
		  
		//step3 create the statement object  
		Statement stmt=con.createStatement();  
		  
		//step4 execute query  
		ResultSet rs=stmt.executeQuery("select * from ssrexcl");  
		while(rs.next())  
		System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  
		  
		//step5 close the connection object 
		stmt.getConnection().close();
		con.close();  
		  
		}catch(Exception e){ System.out.println(e);}  
		  
		}  
		

}
