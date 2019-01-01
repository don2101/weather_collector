package scenario;

import astro.api.collector.dao.AstroCrawlerDao;
import astro.api.collector.dao.AstroCrawlerDaoImpl;
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
    private AstroCrawlerDao astroCrawlerDao;

    @Before
    public void daoCreator() {
        astroCrawlerDao = new AstroCrawlerDaoImpl();
    }

    /**
     * /init/db
     */
    @Test
    public void initDbTest() {
        log.info("================ astro crallwer db init ================");

        astroCrawlerDao.connectionTest();
        log.info("# connection test success !! {}");

        astroCrawlerDao.dropTable();
        log.info("# drop table");

        astroCrawlerDao.createTable();
        log.info("# create table");

        astroCrawlerDao.deleteInitialTable();
        log.info("# delete table");

        Map<String, Object> map = new HashMap<>();
        DateTime dateTime = new DateTime();
        map.put("regDatetime", dateTime.toString("yyyy-MM-dd hh:mm:ss.SSS"));
        astroCrawlerDao.insertInitialTable(map);
        log.info("# insert initial data");

        log.info("### init db fin !! ");
        log.info("========================================================");
    }
}
