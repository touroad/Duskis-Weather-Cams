package duskis.weather.weather;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OpenWeatherMapService {
    @GET("geo/1.0/direct")
    Single<LocationResult[]> getLocation(
            @Query("q") String q,
            @Query("limit") int limit,
            @Query("appid") String apiKey);

    @GET("data/2.5/weather")
    Single<WeatherResult> getWeather(@Query("lat") double lat,
                                     @Query("lon") double lon,
                                     @Query("units") String units,
                                     @Query("appid") String apiKey);

}
