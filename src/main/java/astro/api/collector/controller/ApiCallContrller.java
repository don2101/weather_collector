package astro.api.collector.controller;

import astro.api.collector.api.ApiConfig;
import astro.api.collector.api.ApiProcessor;
import astro.api.collector.common.GrpcSender;
import astro.api.collector.dao.AstroCrallwerDaoImpl;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class ApiCallContrller {

    @Autowired
    private GrpcSender sender;

    private ApiConfig apiConfig = new ApiConfig();

    private ApiProcessor apiProcessor = new ApiProcessor();

    @Autowired
    private AstroCrallwerDaoImpl astroCrallwerDao;


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


    @GetMapping("/init/db")
    public String initDb() {
        log.info("================ astro crallwer db init ================");

        astroCrallwerDao.connectionTest();
        log.info("# connection test success !! {}");

        astroCrallwerDao.dropTable();
        log.info("# drop table");

        astroCrallwerDao.createTable();
        log.info("# create table");

        astroCrallwerDao.deleteInitialTable();
        log.info("# delete table");

        Map<String, Object> map = new HashMap<>();
        DateTime dateTime = new DateTime();
        map.put("regDatetime", dateTime.toString("yyyy-MM-dd hh:mm:ss.SSS"));
        astroCrallwerDao.insertInitialTable(map);
        log.info("# insert initial data");

        log.info("================ init api rule db fin !! ================");
        return astroCrallwerDao.toString();
    }
    public String transportation() {
        return null;
    }
}
