package scenario;

import astro.api.collector.dao.AstroCrallwerDao;
import astro.api.collector.dao.AstroCrallwerDaoImpl;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author oringnam
 * @since 2018. 12. 14.
 * blog : http://box0830.tistory.com/
 */
@Slf4j
public class crallwerControllerTest {
    private AstroCrallwerDao astroCrallwerDao;

    @Before
    public void daoCreator() {
        astroCrallwerDao = new AstroCrallwerDaoImpl();
    }

    @Test
    public void initDbTest() {
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

        log.info("### init db fin !! ");
        log.info("========================================================");
    }
}
