package astro.api.collector.controller;

import astro.api.collector.api.WeatherApi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiCallContrller {

    @GetMapping("/")
    public String helloWorld() {
        return "Hello Spring World !!";
    }

    @GetMapping("/weather")
    public String weather() {
        WeatherApi weather = new WeatherApi();

        // connector 통해서 전송

        //

        String result = "send success";
        return weather.call();
    }
}
