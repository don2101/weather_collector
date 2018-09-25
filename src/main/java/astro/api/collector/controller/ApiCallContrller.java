package astro.api.collector.controller;

import astro.api.collector.api.WeatherApi;
import astro.api.collector.common.GrpcSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiCallContrller {

    @Autowired
    private GrpcSender sender;

    @GetMapping("/")
    public String helloWorld() {
        return "Hello Spring World !!";
    }

    @GetMapping("/weather")
    public String weather() {
        String topic = "weather";

        WeatherApi weather = new WeatherApi();
        String message = weather.call();

        // connector 통해서 전송
        sender.send(topic, message);

        return message;
    }

    @GetMapping("/tranportation")
    public String transportation() {
        return null;
    }
}
