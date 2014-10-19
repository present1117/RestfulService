package restfulService.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONObject;

public class MySQLXiao {
	public static Connection con;
	
	/**
	 * Use select to select the corresponding columns.
	 * @return select results.
	 */
	public static JSONArray mySQLConnSELECT(String SQL) {
		JSONArray resultObject = new JSONArray();
		try{
            connectionQuery();
            PreparedStatement statement =  con.prepareStatement("SELECT * from name");/*write query inside of prepared statement*/
            ResultSet result = statement.executeQuery();
            System.out.println("DataBase table accessed");
            while(result.next()){
            	JSONObject object = new JSONObject();
               object.append("lname", result.getString("lname"));
               object.append("fname", result.getString("fname"));
               resultObject.put(object);
            }
//            System.out.println(resultObject.toString());
            con.close();
        }catch(Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage().toString());
        }
		return resultObject;
	}

	
	/**
	 * Create connection to the database.
	 */
	public static void connectionQuery(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/XiaoTable","root","root");
            System.out.println("Remote DB connection established");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
            System.out.println("Remote server could not be connected");
        }catch (NullPointerException e){
            e.printStackTrace();
            System.out.println("Remote server could not be connected");
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("Remote db connection establishment error");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("False querry");
        }
    }
}