package unit.astro.api.collector.dao;

import astro.api.collector.dao.AstroCrawlerDao;
import astro.api.collector.dao.AstroCrawlerDaoImpl;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author oringnam
 * @since 2018. 12. 9.
 * blog : http://box0830.tistory.com/
 */
@Slf4j
public class AstroCrawlerDaoTest {
    private AstroCrawlerDao astroCrawlerDao;

    @Before
    public void daoCreator() {
        astroCrawlerDao = new AstroCrawlerDaoImpl();
    }

    /**
     * DB 연결
     */
    @Test
    public void a_connectionTest() {
        astroCrawlerDao.connectionTest();
    }

    /**
     * DB Talbe 생성
     */
    @Test
    public void b_createTest() {
        astroCrawlerDao.createTable();
    }

    /**
     * DB Drop Test
     */
    @Test
    public void c_dropTableTest() {
        astroCrawlerDao.dropTable();
    }

    /**
     * 테스트용 초기 데이터 넣기 (날씨)
     */
    @Test
    public void d_insertIntiailTest() {
        Map<String, Object> map = new HashMap<>();

        DateTime dateTime = new DateTime();

        map.put("regDatetime", dateTime.toString("yyyy-MM-dd HH:mm:ss.SSS"));

        astroCrawlerDao.insertInitialTable(map);
    }

    /**
     * 테스트용 초기 데이터 제거 (테이블 전체)
     */
    @Test
    public void e_deleteInitialTest() {
        astroCrawlerDao.deleteInitialTable();
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

        astroCrawlerDao.insertRuleMeta(map);
    }

    @Test
    public void g_insertApiInfoTest() {
        Map<String, Object> map = new HashMap<>();

        map.put("ruleName", "api_weather");
        map.put("apiUrl","http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=%s");
        map.put("apiParameters", "1144071000");
        map.put("apiHasDate", false);
        map.put("apiDateOffset", null);


        astroCrawlerDao.insertApiInfo(map);
    }

    @Test
    public void h_deleteTableTest() {
        Map<String, Object> map = new HashMap<>();
        map.put("tableName", "astro_crallwer.api_info");

        astroCrawlerDao.deleteTableData(map);
    }

    @Test
    public void i_selectRuleMetaTest() {
        List<?> result = astroCrawlerDao.selectRuleMeta();
        log.info("result : {}", result.toString());
    }

    @Test
    public void j_selectApiInfoTest() {
        Map<String, Object> map = new HashMap<>();
        map.put("ruleName", "api_weather");

        List<?> result = astroCrawlerDao.selectApiInfo(map);
        log.info("result : {}", result.toString());
    }
}
