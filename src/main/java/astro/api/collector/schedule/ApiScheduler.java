package astro.api.collector.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ApiScheduler {

    @Scheduled(cron = " 1 * * * * * ")
    public void cornTest() {
        log.info("schedule test");
    }

    @Scheduled(initialDelay = 10000, fixedDelay = 10000)
    public void otherTest() {
        log.info("schedule test2");
    }


}
