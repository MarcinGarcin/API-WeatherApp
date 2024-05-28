
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import java.util.Scanner;

public class WeatherApi {
    private String apiKey;

    public WeatherApi(String apiKey) {
        this.apiKey = apiKey;
    }

    public JSONObject getLocationData(String city) throws IOException, ParseException {
        URL url = new URL("http://api.openweathermap.org/geo/1.0/direct?q=London&limit=1&appid="+apiKey);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        Scanner scanner = new Scanner(connection.getInputStream());
        StringBuilder response = new StringBuilder();
        while (scanner.hasNext()){
            response.append(scanner.nextLine());
        }
        scanner.close();
        connection.disconnect();
        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(response.toString());
        return  (JSONObject) jsonArray.getFirst();

    }
    public JSONObject getWeatherData(String city) throws IOException, ParseException {
        JSONObject locationData = getLocationData(city);

        URL url = new URL("https://api.openweathermap.org/data/3.0/onecall?lat="+locationData.get("lat")+"&lon="+locationData.get("lon")+"&exclude=hourly,daily,minutely,alerts&appid="+apiKey);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        Scanner scanner = new Scanner(connection.getInputStream());
        StringBuilder response = new StringBuilder();
        while (scanner.hasNext()){
            response.append(scanner.nextLine());
        }
        scanner.close();
        connection.disconnect();
        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(response.toString());
        return  (JSONObject) jsonArray.getFirst();
    }

}
