package astro.api.collector.controller;

import astro.api.collector.api.WeatherApi;
import astro.api.collector.common.GrpcSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ApiCallContrller {

    @Autowired
    private static GrpcSender sender;

    @GetMapping("/")
    public String helloWorld() {
        return "Hello Spring World !!";
    }

    public static String weather() {
        String topic = "weather";

        WeatherApi weather = new WeatherApi();
        String message = weather.call();

        log.info("message check : {}", message);

        // connector 통해서 전송
        sender.send(topic, message);

        return message;
    }

    public String transportation() {
        return null;
    }
}
