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
        WeatherApi weather = new WeatherApi();
        String result = weather.call();

        // connector 통해서 전송

        //

        return result;
    }
}
