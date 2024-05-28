import org.json.*;

import java.io.IOException;

public class Main  {


    public static void main(String[] args) throws IOException{
        String apiKey = "ff7b4c92efcb61c1cce266736babfdb9";
        WeatherApi weatherApi = new WeatherApi(apiKey);
        String defaultCity = "London";
        String weatherType = weatherApi.getWeatherType(defaultCity);
        double temperature = weatherApi.getTemperature(defaultCity);
        double humidity = weatherApi.getHumidity(defaultCity);
        double windSpeed = weatherApi.getWindSpeed(defaultCity);

        javax.swing.SwingUtilities.invokeLater(() -> {
            Gui gui = new Gui(defaultCity, weatherType, temperature, humidity, windSpeed, weatherApi);
            gui.showGui();


        });

    }
}