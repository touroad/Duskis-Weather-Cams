package duskis.weather.weather;

import java.util.List;

public record WeatherResult(List<Weather> weather, Temperature temperature) {

}
