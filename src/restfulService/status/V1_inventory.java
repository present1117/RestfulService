package restfulService.status;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONObject;

@Path("/v1/inventory") //removed * wildcard to make this more compatible with tomcat
public class V1_inventory {
	/**
	 * This method will return all computer parts that are listed
	 * in PC_PARTS table.
	 * 
	 * Note: This is a good method for a tutorial but probably should never
	 * has a method that returns everything from a database.  There should be
	 * built in limits.
	 * 
	 * Pre-episode 6, updated because Oracle308tube.java no longer accessible.
	 * 
	 * @return - JSON array string
	 * @throws Exception
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnAllPcParts() throws Exception {
		
		String returnString = null;
		Response rb = null;	
		String source = "{\"glossary\": {\"title\": \"example glossary\",\"GlossDiv\": "
				+ "{\"title\": \"S\",\"GlossList\": {\"GlossEntry\": {\"ID\": \"SGML\","
				+ "\"SortAs\": \"SGML\",\"GlossDef\": {\"para\": \"A meta-markup language,"
				+ " used to create markup languages such as DocBook.\",\"GlossSeeAlso\": [\"GML\""
				+ ", \"XML\"]},\"GlossSee\": \"markup\"}}}}}";
		JSONObject object = new JSONObject(source);
		try {
			returnString = object.toString();
			rb = Response.ok(returnString).build();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return rb;
	}
}
