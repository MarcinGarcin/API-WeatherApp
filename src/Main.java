import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class Main  {


    public static void main(String[] args) throws IOException, ParseException {
        WeatherApi wa = new WeatherApi("ff7b4c92efcb61c1cce266736babfdb9");
        wa.getLocationData("LONDON");

    }
}