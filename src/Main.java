import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class Main  {


    public static void main(String[] args) throws IOException, ParseException {

        javax.swing.SwingUtilities.invokeLater(() -> {
            new Gui().show();
        });

    }
}