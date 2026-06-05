package duskis.weather.retrofit;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface RetrofitService {
    @GET("api/v3/webcams")
    Single<RetrofitResult> getWebcamImages(
            @Query("nearby") String nearby,
            @Query("include") String[] include,
            @Query("limit") int limit,
            @Header("x-windy-api-key") String apiKey);
}

