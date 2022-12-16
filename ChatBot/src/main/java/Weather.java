//https://api.openweathermap.org/data/2.5/weather?q=Kazan&appid=50da205a9c76cfaf41a554bc57768910


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Weather {

    public Map<String, String> parser(String json) throws JsonProcessingException {
        Map<String, String> map = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(json);
        map.put("name", jsonNode.get("name").asText());
        int temperature = (int)(jsonNode.get("main").get("temp").asDouble() - 273.15);
        map.put("temperature", String.valueOf(temperature));
        return map;
    }
    public Map<String, String> req(String city) throws IOException {
        URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=50da205a9c76cfaf41a554bc57768910");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        StringBuilder stringBuilder = new StringBuilder();
        InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());
        try (BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
            String input;
            while ((input = bufferedReader.readLine()) != null) {
                stringBuilder.append(input);
            }
        }
        return parser(stringBuilder.toString());
    }
}
