package restfulService.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONObject;

public class Client {
    public static void main(String[] args) {
        String string = "{\"glossary\": {\"title\": \"example glossary\",\"GlossDiv\": "
        		+ "{\"title\": \"S\",\"GlossList\": {\"GlossEntry\": {\"ID\": \"SGML\",\"SortAs\": "
        		+ "\"SGML\",\"GlossDef\": {\"para\": \"A meta-markup language, used to create markup "
        		+ "languages such as DocBook.\",\"GlossSeeAlso\": [\"GML\", \"XML\"]},\"GlossSee\": \"markup\"}}}}}";
        try {
            // Step1: Let's get json Object from 
            JSONObject jsonObject = new JSONObject(string);
            System.out.println(jsonObject);
            // Step2: Now pass JSON File Data to REST Service
            try {
                URL url = new URL("http://localhost:8080/restfulService/api/v1/status/database/SELECT");
                URLConnection connection = url.openConnection();
                connection.setDoOutput(true);
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setConnectTimeout(5000);
                connection.setReadTimeout(5000);
                OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
                out.write(jsonObject.toString());
                out.close();
 
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while (in.readLine() != null) {
                }
                System.out.println("\nREST Service Invoked Successfully..");
                in.close();
            } catch (Exception e) {
                System.out.println("\nError while calling REST Service");
                System.out.println(e);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
