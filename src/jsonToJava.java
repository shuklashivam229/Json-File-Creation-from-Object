import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class jsonToJava {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, JsonGenerationException, JsonMappingException, IOException {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = null;
		//customerDetails c = new customerDetails();
		con = DriverManager.getConnection("jdbc:mysql://localhost:3307/ssdb", "root", "admin");
		ArrayList<customerDetails> a = new ArrayList<customerDetails>();
		
		
		//Object of statement class to run sql query
		Statement st = con.createStatement();
		ResultSet result  = st.executeQuery("select * from CustomerInfo where Location = 'Africa' and PurchaseDate = curdate();");
		while(result.next()) 
		{
			//Set------------------------------------------------
			customerDetails c = new customerDetails();
			c.setCourseName(result.getString(1));
			c.setPurchasedDate(result.getString(2));
			c.setAmount(result.getInt(3));
			c.setLocation(result.getString(4));
			a.add(c);
			
			//Get------------------------------------------------
			
			/*System.out.println(c.getCourseName());
			System.out.println(c.getPurchasedDate());
			System.out.println(c.getAmount());
			System.out.println(c.getLocation());*/
			
			//Old Code of printing the result go to above code of setters and getters
			
			/*System.out.println(result.getString(1));
			System.out.println(result.getString(2));
			System.out.println(result.getInt(3));
			System.out.println(result.getString(4));*/
			
		}
		
		for(int i = 0; i<a.size();i++) {
			
			ObjectMapper o = new ObjectMapper();
			o.writeValue(new File("D:\\eclipse_java_code\\JsonJava\\customerinfo"+i+".json"), a.get(i));
		}

		
		con.close();

	}

}
