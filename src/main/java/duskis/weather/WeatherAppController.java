package duskis.weather;

import com.andrewoid.apikeys.ApiKey;
import duskis.weather.WebCam.WebCamService;
import duskis.weather.geocoding.GeocodingService;
import duskis.weather.weather.WeatherService;

import javax.swing.*;

public class WeatherAppController {
    private final GeocodingService service;
    private final WeatherService  service2;
    private final WebCamService service3;
    private final JTextField name;
    private final JPanel picture;
    private final JLabel lat;
    private final JLabel lon;
    private final JComboBox<String> unitMenu;
    private final JLabel temp;
    private final JLabel feels_like;
    private final JLabel main;
    private final JLabel description;

    public WeatherAppController(GeocodingService service, WeatherService service2, WebCamService service3, JTextField name, JPanel picture, JLabel lat, JLabel lon, JComboBox<String> unitMenu, JLabel temp, JLabel feels_like, JLabel main, JLabel description) {
        this.service = service;
        this.service2 = service2;
        this.service3 = service3;
        this.name = name;
        this.picture = picture;
        this.lat = lat;
        this.lon = lon;
        this.unitMenu = unitMenu;
        this.temp = temp;
        this.feels_like = feels_like;
        this.main = main;
        this.description = description;
    }

}
