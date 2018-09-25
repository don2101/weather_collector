package astro.api.collector.schedule;

import astro.api.collector.controller.ApiCallContrller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ApiScheduler {

    @Autowired
    private ApiCallContrller apiCallContrller;

    @Scheduled(cron = " 1 * * * * * ")
    public void weatherSchduler() {
        log.debug("weather schedule start");
        apiCallContrller.weather();
        log.info("weather schedule fin");
    }

    @Scheduled(initialDelay = 60000, fixedDelay = 60000)
    public void otherTest() {
        log.debug("schedule test2");
    }
}
