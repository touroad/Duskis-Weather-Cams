package duskis.weather;

import duskis.weather.geocoding.GeocodingService;
import duskis.weather.geocoding.GeocodingServiceFactory;
import duskis.weather.geocoding.LocationResult;
import duskis.weather.weather.WeatherResult;
import duskis.weather.weather.WeatherService;
import duskis.weather.weather.WeatherServiceFactory;
import io.reactivex.rxjava3.core.Single;

public class Main {
    public static void main(String[] args) {
        GeocodingService geocodingService = new GeocodingServiceFactory().create();
        LocationResult[] loc = geocodingService.getLocation("New York, NY, US", 1, args[0]).blockingGet();
        WeatherService weatherService = new WeatherServiceFactory().create();
        WeatherResult res = weatherService.getWeather(loc[0].lat(), loc[0].lon(), args[0]).blockingGet();
        System.out.println(loc[0]);
        System.out.println(res);

    }
}