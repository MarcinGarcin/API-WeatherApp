import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Gui extends JFrame {
    private String city;
    private String weatherType;
    private double temperature;
    private double humidity;
    private double windSpeed;
    private WeatherApi weatherApi;
    private JPanel mainPanel;
    private JTextField cityText;
    private JButton updateButton;
    private JLabel weatherTypeLabel;
    private JLabel temperatureLabel;
    private static Image searchImg;
    private static Image rainImg;
    private static Image cloudImg;
    private static Image snowImg;
    private static Image clearImg;
    private static Image stormImg;
    private static Image atmosphereImg;
    private static Image drizzleImg;


    public Gui(String city, String weatherType, double temperature, double humidity, double windSpeed, WeatherApi weatherApi) {
        this.city = city;
        this.weatherType = weatherType;
        this.temperature = temperature;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.weatherApi = weatherApi;
    }



    public void showGui() {
        setTitle("Weather");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);
        loadIcons();
        createPanel();
    }
    public void loadIcons(){
        try {
            searchImg = ImageIO.read(getClass().getResource("Assets/search.png"));
            cloudImg = ImageIO.read(getClass().getResource("Assets/cloud.png"));
            clearImg = ImageIO.read(getClass().getResource("Assets/clear.png"));
            rainImg = ImageIO.read(getClass().getResource("Assets/rain.png"));
            snowImg = ImageIO.read(getClass().getResource("Assets/snow.png"));
            stormImg = ImageIO.read(getClass().getResource("Assets/storm.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void createPanel() {
        mainPanel = new JPanel();
        mainPanel.setSize(400,500);
        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.black);

        cityText = new JTextField();
        cityText.setBounds(30,10,300,40);
        cityText.setFont(new Font("MONOSPACED",Font.BOLD,20));

        updateButton = new JButton();
        updateButton.setIcon(new ImageIcon(searchImg));
        updateButton.setBounds(340,10,40,40);
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                city = cityText.getText();
                weatherTypeLabel.setIcon(new ImageIcon(getPreparedImg(weatherApi.getWeatherType(city))));
                temperatureLabel.setText(String.valueOf(weatherApi.getTemperature(city)));

            }
        });

        weatherTypeLabel = new JLabel();
        weatherTypeLabel.setIcon(new ImageIcon(getPreparedImg(weatherType)));
        weatherTypeLabel.setBounds(50,50,300,300);

        temperatureLabel = new JLabel();
        temperatureLabel.setText(String.valueOf(temperature));
        temperatureLabel.setBounds(50,370,50,20);

        mainPanel.add(temperatureLabel);
        mainPanel.add(weatherTypeLabel);
        mainPanel.add(updateButton);
        mainPanel.add(cityText);

        add(mainPanel);
    }

    public static Image getPreparedImg(String weatherType) {
        System.out.println(weatherType);
        if (weatherType.equals("Clouds")) {
            return cloudImg;
        } else if (weatherType.equals("Clear")) {
            return clearImg;
        } else if (weatherType.equals("atmosphere")) {
            return atmosphereImg;
        } else if (weatherType.equals("snow")) {
            return snowImg;
        } else if (weatherType.equals("rain")) {
            return rainImg;
        } else if (weatherType.equals("drizzle")) {
            return drizzleImg;
        } else {
            return stormImg; // Default case
        }
    }
}
