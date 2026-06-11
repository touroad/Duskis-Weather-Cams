package duskis.weather.geocoding;

import com.andrewoid.apikeys.ApiKey;
import static org.junit.jupiter.api.Assertions.*;

import duskis.weather.weather.LocationResult;
import duskis.weather.weather.OpenWeatherMapService;
import duskis.weather.weather.OpenWeatherMapServiceFactory;
import org.junit.jupiter.api.Test;

class GeocodingServiceTest {

    @Test
    public void getLocation() {
        OpenWeatherMapService service = new OpenWeatherMapServiceFactory().create();

        ApiKey openweathermap = new ApiKey("openweathermap");
        String keyString = openweathermap.get();

        LocationResult[] results = service.getLocation("Buffalo,NY,US", 1, keyString).blockingGet();

        // 3. Assert: Print out what came back so you can see it with your own eyes
        System.out.println("Test Results Array Length: " + (results == null ? "NULL" : results.length));

        if (results != null && results.length > 0) {
            System.out.println("City Found: " + results[0].name());
            System.out.println("Lat: " + results[0].lat());
            System.out.println("Lon: " + results[0].lon());
        }

        // This will pass the test if the array isn't empty, and fail if it is empty!
        assertNotNull(results);
        assertTrue(results.length > 0, "The API returned an empty array!");
    }
}