package TechEdu.Utils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.io.DataOutputStream;
import java.util.Map;
public class Request {
    public static String post(String filename, Map<String, String> data) {
        try {
            URL url = new URL("http://localhost/scripts/diploma/sem_3_project/" + filename +".php");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            StringBuilder query = new StringBuilder();
            for (Map.Entry<String, String> entry : data.entrySet()) {
                query.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
            query.deleteCharAt(query.length() - 1); // Remove the trailing "&"
            // Set request body
            byte[] postData = query.toString().getBytes(StandardCharsets.UTF_8);
            int postDataLength = postData.length;
            connection.setRequestProperty("Content-Type", "application/x-www-formurlencoded");
            connection.setRequestProperty("Content-Length",
            Integer.toString(postDataLength));
            // Send request
            DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
            outputStream.write(postData);
            outputStream.flush();
            outputStream.close();
            int responseCode = connection.getResponseCode();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            connection.disconnect();
            return response.toString();
        } 
        catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}