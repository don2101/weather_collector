package astro.api.collector.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestHelloController {

    @GetMapping("/")
    public String helloWorld() {
        return "Hello Spring World !!";
    }
}
