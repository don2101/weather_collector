package astro.api.collector.controller;

import astro.api.collector.api.WeatherApi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiCallContrller {

    @GetMapping("/api")
    public String weather() {
        WeatherApi tester = new WeatherApi();
        return tester.call();
    }
}
