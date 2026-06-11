package duskis.weather;

import com.andrewoid.apikeys.ApiKey;
import duskis.weather.weather.*;
import duskis.weather.webcam.WebCamService;
import duskis.weather.webcam.WebCamResult;
import duskis.weather.webcam.Webcam;
import duskis.weather.webcam.WebCamImages;
import duskis.weather.webcam.WebCamImage;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

import javax.swing.*;
import java.awt.*;
import java.net.URI;
import java.net.URL;
import java.util.List;

public class WeatherAppController {
    private final OpenWeatherMapService weatherservice;
    private final WebCamService webcamservice;
    private final JTextField name;
    private final JPanel picture;
    private final JLabel lat;
    private final JLabel lon;
    private final JComboBox<String> unitMenu;
    private final JLabel temp;
    private final JLabel feelslike;
    private final JLabel main;
    private final JLabel description;

    public WeatherAppController(OpenWeatherMapService weatherservice, WebCamService webcamservice,
                                JTextField name, JPanel picture, JLabel lat, JLabel lon, JComboBox<String> unitMenu,
                                JLabel temp, JLabel feelslike, JLabel main, JLabel description) {
        this.weatherservice = weatherservice;
        this.webcamservice = webcamservice;
        this.name = name;
        this.picture = picture;
        this.lat = lat;
        this.lon = lon;
        this.unitMenu = unitMenu;
        this.temp = temp;
        this.feelslike = feelslike;
        this.main = main;
        this.description = description;
    }

    public void doSearch() {
        String locationInput = name.getText();

        try {
            ApiKey openweathermap = new ApiKey("openweathermap");
            String keyString = openweathermap.get();

            Disposable disposable = weatherservice.getLocation(locationInput + ", US", 1, keyString)
                    // tells Rx to request the data on a background Thread
                    .subscribeOn(Schedulers.io())
                    // tells Rx to handle the response on Swing's main Thread
                    .observeOn(Schedulers.from(SwingUtilities::invokeLater))
                    //.observeOn(AndroidSchedulers.mainThread()) // Instead use this on Android only
                    .subscribe(
                            this::handleResponse,
                            Throwable::printStackTrace);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleResponse(LocationResult[] locationResults) {
        if (locationResults == null || locationResults.length == 0) {
            System.out.println("error");
            return;
        }

        double latval = locationResults[0].lat();
        double lonval = locationResults[0].lon();
        lat.setText(String.valueOf(latval));
        lon.setText(String.valueOf(lonval));

        //using something similar to dispose above, a consumer
        try {
            ApiKey openweathermap = new ApiKey("openweathermap");
            String keyString = openweathermap.get();

            //maybe shouldve just made the choices lowercase, but they look nicer uppercase
            String unit = unitMenu.getSelectedItem().toString().toLowerCase();

            weatherservice.getWeather(
                            latval, lonval, unit, keyString)
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.from(SwingUtilities::invokeLater))
                    .subscribe(new Consumer<WeatherResult>() {
                        @Override
                        public void accept(WeatherResult weatherResult) throws Exception {
                            temp.setText(String.valueOf(weatherResult.main().temp()));
                            feelslike.setText(String.valueOf(weatherResult.main().feelslike()));
                            main.setText(String.valueOf(weatherResult.weather().get(0).main()));
                            description.setText(weatherResult.weather().get(0).description());
                        }
                    }, Throwable::printStackTrace);


            ApiKey windy = new ApiKey("windy");
            String keyString2 = windy.get();


            String includes = "categories,images,location";
            webcamservice.getWebcamImages(
                            latval + "," + lonval + ",10", includes, 5,
                            keyString2)
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.from(SwingUtilities::invokeLater))
                    .subscribe(new Consumer<WebCamResult>() {
                        @Override
                        public void accept(WebCamResult webCamResult) throws Exception {
                            picture.removeAll();
                            try {
                                List<Webcam> pictureNum = webCamResult.webcams();

                                for (int i = 0; i < pictureNum.size(); i++) {
                                    Webcam webcam = pictureNum.get(i);
                                    if (webcam == null || webcam.images() == null) {
                                        continue;
                                    }
                                    WebCamImages imagesContainer = webcam.images();
                                    if (webcam.images().current() == null) {
                                        continue;
                                    }
                                    WebCamImage current = imagesContainer.current();
                                    if (current.preview() != null) {
                                        URL imgUrl = URI.create(current.preview()).toURL();
                                        ImageIcon imageIcon = new ImageIcon(imgUrl);

                                        //ai on making the images wider
                                        Image rawImage = imageIcon.getImage();
                                        int targetWidth = 400;
                                        int targetHeight = -1;
                                        // -1 tells Java to calculate height automatically
                                        Image scaledImage = rawImage.getScaledInstance(targetWidth, targetHeight,
                                                Image.SCALE_SMOOTH);
                                        ImageIcon largeIcon = new ImageIcon(scaledImage);

                                        JLabel pic = new JLabel(largeIcon);
                                        picture.add(pic);
                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            picture.revalidate();
                            picture.repaint();
                        }
                    }, Throwable::printStackTrace);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
