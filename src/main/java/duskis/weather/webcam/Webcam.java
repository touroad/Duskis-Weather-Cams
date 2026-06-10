package duskis.weather.webcam;

import java.util.List;

public record Webcam(String id, String title, List<Category> category, WebCamImages images) {
}
