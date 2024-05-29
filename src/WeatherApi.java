import org.json.*;

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


    public JSONObject getWeatherData(String city) throws IOException{
        URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q="+city+"&appid="+apiKey);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        Scanner scanner = new Scanner(connection.getInputStream());
        StringBuilder response = new StringBuilder();
        while (scanner.hasNext()){
            response.append(scanner.nextLine());
        }
        scanner.close();
        connection.disconnect();
        return new JSONObject(response.toString());
    }

    private double convertKelvinToCelsius(double kelvin){
        return kelvin-273.15;
    }
    public String getWeatherType(String city){
        try {
            JSONObject jsonObject = getWeatherData(city);
            return (jsonObject.getJSONArray("weather").getJSONObject(0).getString("main"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public double getTemperature(String city){
        try {
            JSONObject jsonObject = getWeatherData(city);
            return convertKelvinToCelsius(jsonObject.getJSONObject("main").getDouble("temp"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public double getHumidity(String city){
        try {
            JSONObject jsonObject = getWeatherData(city);
            return jsonObject.getJSONObject("main").getDouble("humidity");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public double getWindSpeed(String city){
        try {
            JSONObject jsonObject = getWeatherData(city);
            return jsonObject.getJSONObject("wind").getDouble("speed");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public double getPressure(String city){
        try {
            JSONObject jsonObject = getWeatherData(city);
            return jsonObject.getJSONObject("main").getInt("pressure");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}
