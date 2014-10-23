package restfulService.status;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import restfulService.dao.MySQLXiao;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.*;


@Path("/v1/status")
public class V1_Status {

	private static final String api_version = "00.01.00";
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnTitle(){
		return "<p>Java Web Service</p>";
	}
	
	@Path("/version")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnVersion(){
		return "<p>Version:</p>" + api_version;
	}
	
	
	@Path("/income")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response REST(InputStream incomingData){
		StringBuilder sb = new StringBuilder();
		try {
            BufferedReader in = new BufferedReader(new InputStreamReader(incomingData));
            String line = null;
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception e) {
            System.out.println("Error Parsing: - ");
        }
        System.out.println("Data Received: " + sb.toString());
        return Response.status(200).entity(sb.toString()).build();
	}
	
	@Path("/database/SELECT")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response RESTSELECT(InputStream incomingData){
		StringBuilder sb = new StringBuilder();
		try {
            BufferedReader in = new BufferedReader(new InputStreamReader(incomingData));
            String line = null;
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception e) {
            System.out.println("Error Parsing: - ");
        }
		String result = MySQLXiao.mySQLConnSELECT(sb.toString()).toString();
        System.out.println("Data Received: " + result);
        return Response.status(200).entity(sb.toString()).build();
	}
	
	@Path("/database/INSERT")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response RESTINSERT(InputStream incomingData){
		StringBuilder sb = new StringBuilder();
		try {
            BufferedReader in = new BufferedReader(new InputStreamReader(incomingData));
            String line = null;
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception e) {
            System.out.println("Error Parsing: - ");
        }
		try {
			MySQLXiao.mySQLConnINSERT(sb.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//        System.out.println("Data Received: " + result);
        return Response.status(200).entity("Insert Success").build();
	}
	
	@Path("/database")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnDatabaseStatus() throws Exception {
		String myString = null;
		try {
			myString = MySQLXiao.mySQLConnSELECT("").toString();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return formatTable(myString);
	}
	
	
	public String formatTable(String rawData){
		String result = "<table border=\"1\"><tr><th>FirstName</th><th>LastName</th></tr>";
		try {
			JSONArray jsondata = new JSONArray(rawData);
			for(int i = 0; i < jsondata.length(); ++i){
				
				result += "<tr>";
				result+="<td>";
				result += ((JSONObject)jsondata.get(i)).get("fname");
				result += "</td><td>";
				result += ((JSONObject)jsondata.get(i)).get("lname");
				result +="</td></tr>";
			}
			result+="</table>";
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
}
