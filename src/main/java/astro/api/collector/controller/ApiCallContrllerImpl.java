package astro.api.collector.controller;

import astro.api.collector.api.ApiProcessor;
import astro.api.collector.api.UriCreator;
import astro.api.collector.common.util.GrpcSender;
import astro.api.collector.dao.AstroCrawlerDaoImpl;
import astro.api.collector.common.domain.AstroApiInfoDomain;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class ApiCallContrllerImpl {

    @Autowired
    private GrpcSender sender;

    @Autowired
    private ApiProcessor apiProcessor;

    @Autowired
    private AstroCrawlerDaoImpl astroCrallwerDao;

    //TODO: 실행 인터페이스를 생성하여 밖으로 빼낼 것
    @GetMapping("/api/{rule}")
    public String callApi(@PathVariable String rule) {
        Map<String, Object> input = new HashMap<>();
        input.put("ruleName", rule);

        List<AstroApiInfoDomain> list = astroCrallwerDao.selectApiInfo(input);

        if (list.size() == 0) {
            log.warn("query size was zero !! please check the rule name [{}] !!", rule);
            return "query size was zero !!";
        }

        AstroApiInfoDomain apiInfoDomain = list.get(0);

        log.debug("# domain : {}", apiInfoDomain.toString());

        UriCreator creator = new UriCreator(apiInfoDomain);

        ArrayList<String> uris = creator.getUris();

        for (String uri : uris) {
            String body = apiProcessor.call(uri, rule);
            sender.send(rule, body);
            log.debug("# {} rule send message success : {}", rule, body);
        }

        String message = String.format("[%s] rule process fin !!", rule);

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
        map.put("regDatetime", dateTime.toString("yyyy-MM-dd HH:mm:ss.SSS"));
        astroCrallwerDao.insertInitialTable(map);
        log.info("# insert initial data");

        log.info("================ init api rule db fin !! ================");
        return astroCrallwerDao.toString();
    }

    public String transportation() {
        return null;
    }
}
