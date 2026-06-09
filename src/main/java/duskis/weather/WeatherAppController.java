package duskis.weather;

import com.andrewoid.apikeys.ApiKey;
import duskis.weather.WebCam.WebCamResult;
import duskis.weather.WebCam.WebCamService;
import duskis.weather.WebCam.Webcam;
import duskis.weather.geocoding.GeocodingService;
import duskis.weather.geocoding.LocationResult;
import duskis.weather.weather.Temperature;
import duskis.weather.weather.Weather;
import duskis.weather.weather.WeatherResult;
import duskis.weather.weather.WeatherService;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import javax.swing.*;
import java.net.URI;
import java.net.URL;
import java.util.List;

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

    public void doSearch() {
        String locationInput = name.getText();
        System.out.println(locationInput);

        try{
            ApiKey openweathermap = new ApiKey("openweathermap");
            String keyString = openweathermap.get();

            Disposable disposable = service.getLocation(locationInput, 1, keyString)
                    // tells Rx to request the data on a background Thread
                    .subscribeOn(Schedulers.io())
                    // tells Rx to handle the response on Swing's main Thread
                    .observeOn(Schedulers.from(SwingUtilities::invokeLater))
                    //.observeOn(AndroidSchedulers.mainThread()) // Instead use this on Android only
                    .subscribe(
                            this::handleResponse,
                            Throwable::printStackTrace);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleResponse(LocationResult[] locationResults) {
        if(locationResults == null || locationResults.length == 0){
            System.out.println("error");
            return;
        }

        lat.setText(String.valueOf(locationResults[0].lat()));
        lon.setText(String.valueOf(locationResults[0].lon()));

        try{
            ApiKey openweathermap = new ApiKey("openweathermap");
            String keyString = openweathermap.get();

            ApiKey windy = new ApiKey("windy");
            String keyString2 = windy.get();

            //maybe shouldve just made the choices lowercase, but they look nicer uppercase
            String unit = unitMenu.getSelectedItem().toString().toLowerCase();

            WeatherResult weatherResult = service2.getWeather(locationResults[0].lat(), locationResults[0].lon(), unit, keyString).blockingGet();

            temp.setText(String.valueOf(weatherResult.temperature().temp()));
            feels_like.setText(String.valueOf(weatherResult.temperature().feels_like()));
            main.setText(String.valueOf(weatherResult.weather().get(0).main()));
            description.setText(weatherResult.weather().get(0).description());

            String includes = "categories,images,location";
            WebCamResult webCamResult = service3.getWebcamImages(locationResults[0].lat() + "," + locationResults[0].lon() + ",10", includes, 5, keyString2).blockingGet();

            picture.removeAll();
            try{
                List<Webcam> pictureNum = webCamResult.webcam();

                for(int i = 0; i < pictureNum.size(); i++){
                    Webcam current = pictureNum.get(i);
                    if(current.webcamImage() != null && current.webcamImage().preview() != null){
                        URL imgUrl = URI.create(current.webcamImage().preview()).toURL();
                        ImageIcon imageIcon = new ImageIcon(imgUrl);

                        JLabel pic = new JLabel(imageIcon);
                        picture.add(pic);
                    }
                }
            }catch (Exception e) {
                e.printStackTrace();
            }

            picture.revalidate();
            picture.repaint();

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
