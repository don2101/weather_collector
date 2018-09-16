package astro.api.collector.weather.controller;

import astro.api.collector.weather.ApiTester;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiCallContrller {

    @GetMapping("/api")
    public String weather() {
        ApiTester tester = new ApiTester();
        return tester.call();
    }
}
