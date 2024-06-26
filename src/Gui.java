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
    private double pressure;
    private WeatherApi weatherApi;
    private JPanel mainPanel;
    private JTextField cityText;
    private JButton updateButton;
    private JLabel weatherTypeLabel;
    private JLabel temperatureText;
    private JLabel temperatureLabel;
    private JLabel humidityText;
    private JLabel humidityLabel;
    private JLabel windSpeedText;
    private JLabel windSpeedLabel;
    private JLabel pressureText;
    private JLabel pressureLabel;
    private static Image searchImg;
    private static Image temperatureImg;
    private static Image humidityImg;
    private static Image windSpeedImg;
    private static Image pressureImg;
    private static Image rainImg;
    private static Image cloudImg;
    private static Image snowImg;
    private static Image clearImg;
    private static Image stormImg;
    private static Image drizzleImg;



    public Gui(String city, String weatherType, double temperature, double humidity, double windSpeed, double pressure, WeatherApi weatherApi) {
        this.city = city;
        this.weatherType = weatherType;
        this.temperature = temperature;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.pressure = pressure;
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
            pressureImg = ImageIO.read(getClass().getResource("Assets/pressure.png"));
            temperatureImg = ImageIO.read(getClass().getResource("Assets/temperature.png"));
            searchImg = ImageIO.read(getClass().getResource("Assets/search.png"));
            cloudImg = ImageIO.read(getClass().getResource("Assets/cloud.png"));
            clearImg = ImageIO.read(getClass().getResource("Assets/clear.png"));
            rainImg = ImageIO.read(getClass().getResource("Assets/rain.png"));
            snowImg = ImageIO.read(getClass().getResource("Assets/snow.png"));
            stormImg = ImageIO.read(getClass().getResource("Assets/storm.png"));
            drizzleImg = ImageIO.read(getClass().getResource("Assets/drizzle.png"));
            humidityImg = ImageIO.read(getClass().getResource("Assets/humidity.png"));
            windSpeedImg = ImageIO.read(getClass().getResource("Assets/windSpeed.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void createPanel() {
        mainPanel = new JPanel();
        mainPanel.setSize(400,600);
        mainPanel.setLayout(null);
        mainPanel.setBackground(new Color(245, 245, 220));

        cityText = new JTextField("London");
        cityText.setBounds(25,10,300,40);
        cityText.setFont(new Font("MONOSPACED",Font.BOLD,20));

        updateButton = new JButton();
        updateButton.setIcon(new ImageIcon(searchImg));
        updateButton.setBounds(340,10,40,40);
        updateButton.setBackground(new Color(245, 245, 220));
        updateButton.setBorder(null);
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!cityText.getText().isEmpty()){
                    city = cityText.getText().replace(' ','_');
                    weatherTypeLabel.setIcon(new ImageIcon(getPreparedImg(weatherApi.getWeatherType(city))));
                    temperatureText.setText(String.format("%.1f", weatherApi.getTemperature(city) )+"°C");
                    humidityText.setText(String.format("%.1f", weatherApi.getHumidity(city) )+" %");
                    windSpeedText.setText(String.format("%.1f", weatherApi.getWindSpeed(city) )+"km/h");
                    pressureText.setText(String.format("%.0f", weatherApi.getPressure(city) )+"hPa");
                }
            }
        });

        weatherTypeLabel = new JLabel();
        weatherTypeLabel.setIcon(new ImageIcon(getPreparedImg(weatherType)));
        weatherTypeLabel.setBounds(50,50,300,300);

        temperatureLabel = new JLabel();
        temperatureLabel.setIcon(new ImageIcon(temperatureImg));
        temperatureLabel.setBounds(23, 350, 45, 45);

        temperatureText = new JLabel();
        temperatureText.setText(String.format("%.1f", temperature )+"°C");
        temperatureText.setForeground(Color.BLACK);
        temperatureText.setFont(new Font("Dialog",Font.BOLD,30));
        temperatureText.setBounds(65,355,150,35);

        humidityLabel = new JLabel();
        humidityLabel.setIcon(new ImageIcon(humidityImg));
        humidityLabel.setBounds(15,400,45,45);

        humidityText = new JLabel();
        humidityText.setText(String.format("%.1f", humidity )+" %");
        humidityText.setForeground(Color.BLACK);
        humidityText.setFont(new Font("Dialog",Font.BOLD,30));
        humidityText.setBounds(65,405,150,35);

        windSpeedLabel = new JLabel();
        windSpeedLabel.setIcon(new ImageIcon(windSpeedImg));
        windSpeedLabel.setBounds(195,350,45,45);

        windSpeedText = new JLabel();
        windSpeedText.setText(String.format("%.1f", windSpeed )+"km/h");
        windSpeedText.setForeground(Color.BLACK);
        windSpeedText.setFont(new Font("Dialog",Font.BOLD,30));
        windSpeedText.setBounds(245,355,150,35);

        pressureLabel = new JLabel();
        pressureLabel.setIcon(new ImageIcon(pressureImg));
        pressureLabel.setBounds(195,400,45,45);

        pressureText = new JLabel();
        pressureText.setText(String.format("%.0f", pressure )+"hPa");
        pressureText.setForeground(Color.BLACK);
        pressureText.setFont(new Font("Dialog",Font.BOLD,30));
        pressureText.setBounds(245,405,150,35);

        mainPanel.add(pressureText);
        mainPanel.add(pressureLabel);
        mainPanel.add(windSpeedText);
        mainPanel.add(windSpeedLabel);
        mainPanel.add(humidityText);
        mainPanel.add(humidityLabel);
        mainPanel.add(temperatureLabel);
        mainPanel.add(temperatureText);
        mainPanel.add(weatherTypeLabel);
        mainPanel.add(updateButton);
        mainPanel.add(cityText);

        add(mainPanel);
    }

    public static Image getPreparedImg(String weatherType) {
        return switch (weatherType) {
            case "Clouds" -> cloudImg;
            case "Clear" -> clearImg;
            case "Atmosphere" -> cloudImg;
            case "Snow" -> snowImg;
            case "Rain" -> rainImg;
            case "Drizzle" -> drizzleImg;
            default -> stormImg;
        };
    }
}
