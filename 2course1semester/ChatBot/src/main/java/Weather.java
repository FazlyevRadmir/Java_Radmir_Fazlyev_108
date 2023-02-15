//https://api.openweathermap.org/data/2.5/weather?q=Kazan&appid=50da205a9c76cfaf41a554bc57768910

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Weather {

    public Map<String, String> req(String city) throws IOException {
        double celciy;
        try {
            URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + "9bf73362c913f887d7d8ff91db6ff1ba");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            Map<String, String> map = new HashMap<>();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                StringBuilder content = new StringBuilder();
                String input;
                while ((input = reader.readLine()) != null) {
                    content.append(input);
                }
                String str = content.toString();
                String[] line = str.split(",\"feels_like\"");
                line = line[0].split("\"temp\":");
                String temperature = line[line.length - 1];

                String[] line2 = str.split("\",\"cod\"");
                line2 = line2[0].split("\"name\":\"");
                String nameCity = line2[line.length - 1];
                celciy = Double.parseDouble(temperature) - 273.15;
                int celciyInt = (int) celciy;
                map.put("name", String.valueOf(nameCity));
                map.put("temperature", String.valueOf(celciyInt));
            }
            return map;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
