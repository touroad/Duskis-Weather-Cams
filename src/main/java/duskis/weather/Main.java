package duskis.weather;

import duskis.weather.geocoding.GeocodingService;
import duskis.weather.geocoding.GeocodingServiceFactory;
import duskis.weather.geocoding.LocationResult;
import duskis.weather.WebCam.WebCamServiceFactory;
import duskis.weather.WebCam.WebCamService;
import duskis.weather.weather.WeatherResult;
import duskis.weather.weather.WeatherService;
import duskis.weather.weather.WeatherServiceFactory;

/*
public class Main {
    public static void main(String[] args) {
        GeocodingService geocodingService = new GeocodingServiceFactory().create();
        LocationResult[] loc = geocodingService.getLocation("New York, NY, US", 1, args[0]).blockingGet();
        WeatherService weatherService = new WeatherServiceFactory().create();
        WeatherResult res = weatherService.getWeather(loc[0].lat(), loc[0].lon(),"metric", args[0]).blockingGet();
        WebCamService retrofitService = new WebCamServiceFactory().create();
        //RetrofitResult retro = retrofitService.getWebcamImages(
                //String.format("%d,%d,10", loc[0].lat(), loc[0].lon()),
                //new String[]{"categories","images", "location"}, args[0]).blockingGet();
        System.out.println(loc[0]);
        System.out.println(res);
        //System.out.println(retro);

    }
}
 */