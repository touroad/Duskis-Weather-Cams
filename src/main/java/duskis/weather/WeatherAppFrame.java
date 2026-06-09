package duskis.weather;

import duskis.weather.WebCam.WebCamService;
import duskis.weather.WebCam.WebCamServiceFactory;
import duskis.weather.geocoding.GeocodingService;
import duskis.weather.geocoding.GeocodingServiceFactory;
import duskis.weather.weather.WeatherService;
import duskis.weather.weather.WeatherServiceFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WeatherAppFrame extends JFrame {
    public JLabel inputLabel;
    public JTextField name;
    public JLabel latitudeLabel;
    public JLabel longitudeLabel;
    public JLabel unitLabel;
    public JComboBox<String> unitMenu;
    public JLabel temperatureLabel;
    public JLabel feelsLikeLabel;
    public JLabel mainLabel;
    public JLabel descriptionLabel;

    public WeatherAppFrame(){
        setSize(600, 800);
        setTitle("Weather App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new GridBagLayout());

        GridBagConstraints constraints;

        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        inputLabel = new JLabel("City Name, State Code: ");
        add(inputLabel, constraints);


        constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 0;
        name = new JTextField("New York, NY");
        add(name, constraints);

        constraints = new GridBagConstraints();
        constraints.gridx = 2;
        constraints.gridy = 0;
        JButton searchButton = new JButton("Search");
        add(searchButton, constraints);

        constraints = new GridBagConstraints();
        constraints.gridx = 2;
        constraints.gridy = 1;
        constraints.gridheight = 7;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 0.65;
        JPanel picture = new JPanel(new GridLayout(0, 1, 0, 15));
        JScrollPane scrollPane = new JScrollPane(picture);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane, constraints);
        //got help from ai for the scrolling bit

        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 1;
        latitudeLabel = new JLabel("Latitude: ");
        add(latitudeLabel, constraints);


        constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 1;
        JLabel lat = new JLabel();
        add(lat, constraints);

        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 2;
        longitudeLabel = new JLabel("Longitude: ");
        add(longitudeLabel, constraints);

        constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 2;
        JLabel lon = new JLabel();
        add(lon, constraints);

        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 3;
        unitLabel = new JLabel("Metric, Imperial or Standard");
        add(unitLabel, constraints);

        constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 3;
        String[] choices = { "Imperial", "Metric", "Standard" };
        unitMenu = new JComboBox<>(choices);
        add(unitMenu, constraints);
        //and help for the combo box

        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 4;
        temperatureLabel = new JLabel("Temperature: ");
        add(temperatureLabel, constraints);

        constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 4;
        JLabel temp = new JLabel();
        add(temp, constraints);

        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 5;
        feelsLikeLabel = new JLabel("Feels Like: ");
        add(feelsLikeLabel, constraints);

        constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 5;
        JLabel feels_like = new JLabel();
        add(feels_like, constraints);

        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 6;
        mainLabel = new JLabel("Main Description: ");
        add(mainLabel, constraints);

        constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 6;
        JLabel main = new JLabel();
        add(main, constraints);

        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 7;
        descriptionLabel = new JLabel("Description: ");
        add(descriptionLabel, constraints);

        constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 7;
        JLabel description = new JLabel();
        add(description, constraints);

        GeocodingService service = new GeocodingServiceFactory().create();
        WeatherService service2 = new WeatherServiceFactory().create();
        WebCamService service3 = new WebCamServiceFactory().create();
        WeatherAppController weatherAppController = new WeatherAppController(service, service2, service3,
                name, picture, lat, lon, unitMenu, temp, feels_like,main, description);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                weatherAppController.doSearch();
            }
        });
    }

    public static void main(String[] args) {
        WeatherAppFrame frame = new WeatherAppFrame();
        frame.setVisible(true);
    }
}
