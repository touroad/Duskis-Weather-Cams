package duskis.weather.geocoding;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GeocodingService {
    @GET("geo/1.0/direct")
    Single<LocationResult[]> getLocation(
            @Query("q") String q,
            @Query("limit") int limit,
            @Query("appid") String apiKey);
}
