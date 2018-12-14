package unit;

import astro.api.collector.dao.AstroCrallwerDao;
import astro.api.collector.dao.AstroCrallwerDaoImpl;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author oringnam
 * @since 2018. 12. 9.
 * blog : http://box0830.tistory.com/
 */
public class AstroCrallwerDaoTest {
    AstroCrallwerDao astroCrallwerDao;

    @Before
    public void daoCreator() {
        astroCrallwerDao = new AstroCrallwerDaoImpl();
    }

    /**
     * DB 연결
     */
    @Test
    public void a_connectionTest() {
        astroCrallwerDao.connectionTest();
    }

    /**
     * DB Talbe 생성
     */
    @Test
    public void b_createTest() {
        astroCrallwerDao.createTable();
    }

    /**
     * DB Drop Test
     */
    @Test
    public void c_dropTableTest() {
        astroCrallwerDao.dropTable();
    }

    @Test
    public void d_insertIntiailTest() {
        Map<String, Object> map = new HashMap<>();

        DateTime dateTime = new DateTime();

        map.put("regDatetime", dateTime.toString("yyyy-MM-dd hh:mm:ss.SSS"));

        astroCrallwerDao.insertInitialTable(map);
    }

    @Test
    public void e_deleteInitialTest() {
        astroCrallwerDao.deleteInitialTable();
    }
}
