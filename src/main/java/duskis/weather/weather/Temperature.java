package duskis.weather.weather;

import com.google.gson.annotations.SerializedName;

public record Temperature(double temp, @SerializedName("feels_like") double feelslike) {
}
