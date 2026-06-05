package duskis.weather.retrofit;

import java.util.List;

public record Webcam(String id, String title, List<Category> category, WebcamImage webcamImage) {
}
