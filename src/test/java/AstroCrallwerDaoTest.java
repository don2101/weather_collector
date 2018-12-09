import astro.api.collector.dao.AstroCrallwerDao;
import astro.api.collector.dao.AstroCrallwerDaoImpl;
import org.junit.Test;

/**
 * @author oringnam
 * @since 2018. 12. 9.
 * blog : http://box0830.tistory.com/
 */
public class AstroCrallwerDaoTest {

    /**
     * Connection Test 진행 중... 다녀와서 마무리 할 것
     */
    @Test
    public void a_connectionTest() {
        AstroCrallwerDao astroCrallwerDao = new AstroCrallwerDaoImpl();
        astroCrallwerDao.connectionTest();

    }
}
