import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class DropboxAPI {
    private static final String ACCESS_TOKEN = System.getenv("ACCESS_TOKEN");

    public static void main(String[] args) throws Exception {

        // Setup the http connection by providing URLs and Headers
        URL url = new URL("https://api.dropboxapi.com/2/team/members/list");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", "Bearer " + ACCESS_TOKEN);
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        // Add data to the body of the request
        String jsonInput = "{\"limit\": 5}";
        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonInput.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        // Read the buffer of the json
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
        String responseLine;
        StringBuilder response = new StringBuilder();
        while ((responseLine = br.readLine()) != null) {
            response.append(responseLine.trim());
        }

        // Print the complete response
        System.out.println(response.toString());
    }
}
