//anil
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.commons.codec.binary.Base64;
public class Sms {
    public static void main(String[] args) {
        try {
            String phoneNumber = "9059334489";
            String appKey = "miracle82";
            String appSecret = "51a50e3be88d46a0a606b656a519079a";
            String message = "Hello, world!";
            URL url = new URL("	https://api.clxcommunications.com/xms/v1/miracle82/" + phoneNumber);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            String userCredentials = "application\\" + appKey + ":" + appSecret;
            byte[] encoded = Base64.encodeBase64(userCredentials.getBytes());
            String basicAuth = "Basic " + new String(encoded);
            connection.setRequestProperty("Authorization", basicAuth);
            String postData = "{\"Message\":\"" + message + "\"}";
            OutputStream os = connection.getOutputStream();
            os.write(postData.getBytes());
            StringBuilder response = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ( (line = br.readLine()) != null)
                response.append(line);
            br.close();
            os.close();
            System.out.println(response.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}