package astro.api.collector.controller;

import astro.api.collector.api.ApiConfig;
import astro.api.collector.api.ApiProcessor;
import astro.api.collector.common.GrpcSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ApiCallContrller {

    @Autowired
    private GrpcSender sender;

    private ApiConfig apiConfig = new ApiConfig();

    private ApiProcessor apiProcessor = new ApiProcessor();


/*    @GetMapping("/")
    public String helloWorld() {
        log.info("call start");
        String topic = "weather";

        WeatherApi weather = new WeatherApi();
        String message = weather.call();

        // connector 통해서 전송
        sender.send(topic, message);

        return message;
    }*/

    @GetMapping("/weather")
    public String weather() {
        String topic = "weather";

        apiConfig.loadProperties(topic+".properties");

        String url = apiConfig.makeUrl()
                              .concat(apiConfig.get("tag.name"))
                              .concat(apiConfig.get("seoul.mapo.yonnam"));

        String message = apiProcessor.call(url);

        log.debug("message check : {}", message);

        // connector 통해서 전송
        sender.send(topic, message);

        return message;
    }

    public String transportation() {
        return null;
    }
}
