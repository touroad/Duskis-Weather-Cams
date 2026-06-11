package duskis.weather;

import duskis.weather.weather.OpenWeatherMapService;
import duskis.weather.weather.OpenWeatherMapServiceFactory;
import duskis.weather.webcam.WebCamService;
import duskis.weather.webcam.WebCamServiceFactory;

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

    public WeatherAppFrame() {
        setSize(600, 800);
        setTitle("Weather App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new GridBagLayout());

        GridBagConstraints constraints;

        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.NORTHWEST;
        constraints.insets = new Insets(6, 8, 6, 8);
        inputLabel = new JLabel("City Name, State Code: ");
        add(inputLabel, constraints);


        constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.NORTHWEST;
        constraints.insets = new Insets(6, 8, 6, 8);
        name = new JTextField("New York, NY");
        add(name, constraints);

        constraints = new GridBagConstraints();
        constraints.gridx = 2;
        constraints.gridy = 0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.NORTHWEST;
        constraints.insets = new Insets(6, 8, 6, 8);
        JButton searchButton = new JButton("Search");
        searchButton.setFocusPainted(false);
        add(searchButton, constraints);

        constraints = new GridBagConstraints();
        constraints.gridx = 2;
        constraints.gridy = 1;
        constraints.gridheight = 7;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 3;
        constraints.weighty = 1;
        JPanel picture = new JPanel(new GridLayout(0, 1, 0, 15));
        JScrollPane scrollPane = new JScrollPane(picture);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane, constraints);
        //got help from ai for the scrolling bit

        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.NORTHWEST;
        constraints.insets = new Insets(6, 8, 6, 8);
        latitudeLabel = new JLabel("Latitude: ");
        add(latitudeLabel, constraints);


        constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.NORTHWEST;
        constraints.insets = new Insets(6, 8, 6, 8);
        JLabel lat = new JLabel();
        add(lat, constraints);

        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.NORTHWEST;
        constraints.insets = new Insets(6, 8, 6, 8);
        longitudeLabel = new JLabel("Longitude: ");
        add(longitudeLabel, constraints);

        constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.NORTHWEST;
        constraints.insets = new Insets(6, 8, 6, 8);
        JLabel lon = new JLabel();
        add(lon, constraints);

        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.NORTHWEST;
        constraints.insets = new Insets(6, 8, 6, 8);
        unitLabel = new JLabel("Metric, Imperial or Standard");
        add(unitLabel, constraints);

        constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.NORTHWEST;
        constraints.insets = new Insets(6, 8, 6, 8);
        String[] choices = {"Imperial", "Metric", "Standard"};
        unitMenu = new JComboBox<>(choices);
        add(unitMenu, constraints);
        //and help for the combo box

        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.NORTHWEST;
        constraints.insets = new Insets(6, 8, 6, 8);
        temperatureLabel = new JLabel("Temperature: ");
        add(temperatureLabel, constraints);

        constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 4;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.NORTHWEST;
        constraints.insets = new Insets(6, 8, 6, 8);
        JLabel temp = new JLabel();
        add(temp, constraints);

        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.NORTHWEST;
        constraints.insets = new Insets(6, 8, 6, 8);
        feelsLikeLabel = new JLabel("Feels Like: ");
        add(feelsLikeLabel, constraints);

        constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 5;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.NORTHWEST;
        constraints.insets = new Insets(6, 8, 6, 8);
        JLabel feelslike = new JLabel();
        add(feelslike, constraints);

        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 6;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.NORTHWEST;
        constraints.insets = new Insets(6, 8, 6, 8);
        mainLabel = new JLabel("Main Description: ");
        add(mainLabel, constraints);

        constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 6;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.NORTHWEST;
        constraints.insets = new Insets(6, 8, 6, 8);
        JLabel main = new JLabel();
        add(main, constraints);

        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 7;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.NORTHWEST;
        constraints.insets = new Insets(6, 8, 6, 8);
        descriptionLabel = new JLabel("Description: ");
        add(descriptionLabel, constraints);

        constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 7;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.NORTHWEST;
        constraints.insets = new Insets(6, 8, 6, 8);
        JLabel description = new JLabel();
        add(description, constraints);

        OpenWeatherMapService weatherservice = new OpenWeatherMapServiceFactory().create();
        WebCamService webcamservice = new WebCamServiceFactory().create();
        WeatherAppController weatherAppController = new WeatherAppController(weatherservice, webcamservice,
                name, picture, lat, lon, unitMenu, temp, feelslike, main, description);

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
