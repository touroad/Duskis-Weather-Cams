package duskis.weather.weather;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {
    @GET("data/2.5/weather")
    Single<WeatherResult> getWeather(@Query("lat") double lat,
                                    @Query("lon") double lon,
                                    @Query("units") String units,
                                    @Query("appid") String apiKey);
}
