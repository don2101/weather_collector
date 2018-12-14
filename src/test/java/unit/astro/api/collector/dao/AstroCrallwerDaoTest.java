package unit.astro.api.collector.dao;

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
    private AstroCrallwerDao astroCrallwerDao;

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

    /**
     * 테스트용 초기 데이터 넣기 (날씨)
     */
    @Test
    public void d_insertIntiailTest() {
        Map<String, Object> map = new HashMap<>();

        DateTime dateTime = new DateTime();

        map.put("regDatetime", dateTime.toString("yyyy-MM-dd HH:mm:ss.SSS"));

        astroCrallwerDao.insertInitialTable(map);
    }

    /**
     * 테스트용 초기 데이터 제거 (테이블 전체)
     */
    @Test
    public void e_deleteInitialTest() {
        astroCrallwerDao.deleteInitialTable();
    }

    /**
     * rule_meta table insert
     */
    @Test
    public void f_insertRuleMetaTest() {
        Map<String, Object> map = new HashMap<>();

        map.put("ruleName", "api_weather");

        DateTime dateTime = new DateTime();
        map.put("regDatetime", dateTime.toString("yyyy-MM-dd HH:mm:ss.SSS"));

        astroCrallwerDao.insertRuleMeta(map);
    }

    @Test
    public void g_insertApiInfoTest() {
        Map<String, Object> map = new HashMap<>();

        map.put("ruleName", "api_weather");
        map.put("apiUrl","http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=%s");
        map.put("apiParameters", "1144071000");
        map.put("apiHasDate", false);
        map.put("apiDateOffset", null);


        astroCrallwerDao.insertApiInfo(map);
    }

    @Test
    public void h_deleteTableTest() {
        Map<String, Object> map = new HashMap<>();
        map.put("tableName", "astro_crallwer.api_info");

        astroCrallwerDao.deleteTableData(map);
    }
}
