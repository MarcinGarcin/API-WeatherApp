import org.json.*;

import java.io.IOException;

public class Main  {


    public static void main(String[] args) throws IOException{
        String apiKey = "YOUR API KEY";
        WeatherApi weatherApi = new WeatherApi(apiKey);
        String defaultCity = "London";
        String weatherType = weatherApi.getWeatherType(defaultCity);
        double temperature = weatherApi.getTemperature(defaultCity);
        double humidity = weatherApi.getHumidity(defaultCity);
        double windSpeed = weatherApi.getWindSpeed(defaultCity);
        double pressure = weatherApi.getPressure(defaultCity);


        javax.swing.SwingUtilities.invokeLater(() -> {
            Gui gui = new Gui(defaultCity, weatherType, temperature, humidity, windSpeed,pressure, weatherApi);
            gui.showGui();


        });

    }
}