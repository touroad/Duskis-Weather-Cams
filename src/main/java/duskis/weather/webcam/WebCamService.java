package duskis.weather.webcam;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface WebCamService {
    @GET("webcams/api/v3/webcams")
    Single<WebCamResult> getWebcamImages(
            @Query("nearby") String nearby,
            @Query("include") String include,
            @Query("limit") int limit,
            @Header("x-windy-api-key") String apiKey);
}

