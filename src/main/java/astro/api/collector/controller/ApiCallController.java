package astro.api.collector.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface ApiCallController {
    @GetMapping("/api/{rule}")
    public String callApi(@PathVariable String rule);

    @GetMapping("/init/db")
    public String initDb();
}
