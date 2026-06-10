package duskis.weather.WebCam;

import java.util.List;

public record WebCamResult(List<Webcam> webcams, String message, String error, int statusCode) {
}
