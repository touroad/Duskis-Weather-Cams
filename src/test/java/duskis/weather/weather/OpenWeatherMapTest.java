package duskis.weather.weather;

import com.andrewoid.apikeys.ApiKey;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class OpenWeatherMapTest {

    @Test
    void getWeatherLocation() {

        OpenWeatherMapService service = new OpenWeatherMapServiceFactory().create();

        ApiKey openweathermap = new ApiKey("openweathermap");
        String keyString = openweathermap.get();

        LocationResult[] locResult = service.getLocation("New York, NY , US", 1, keyString).blockingGet();

        LocationResult parse = locResult[0];
        assertEquals("New York", parse.name());
        assertEquals(40.7127281, parse.lat(), 0.1);
        assertEquals(-74.0060152, parse.lon(), 0.1);

        WeatherResult weatherResult = service.getWeather(parse.lat(), parse.lon(), "Imperial", keyString).blockingGet();
        assertNotNull(weatherResult);
        //hard to test weather as it changes


    }
}