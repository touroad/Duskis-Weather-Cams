package duskis.weather.retrofit;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ReftrofitServiceFactory {
    public RetrofitService create() {
        // configure Retrofit for the dummyjson website
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.windy.com/webcams/")
                // Configure Retrofit to use Gson to turn the Json into Objects
                .addConverterFactory(GsonConverterFactory.create())
                // Configure Retrofit to use Rx
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();

        return retrofit.create(RetrofitService.class);
    }
}
