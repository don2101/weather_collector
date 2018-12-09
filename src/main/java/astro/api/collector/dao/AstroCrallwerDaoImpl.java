package astro.api.collector.dao;

import astro.api.collector.domain.AstroApiInfoDomain;
import astro.api.collector.domain.AstroRuleMetaDomain;

/**
 * @author oringnam
 * @since 2018. 12. 8.
 * blog : http://box0830.tistory.com/
 */
public class AstroCrallwerDaoImpl implements AstroCrallwerDao {
    private AstroDataSourcePool dataSourcePool = new AstroDataSourcePool();

    @Override
    public void connectionTest() {
        dataSourcePool.select("AstroCrallwerMapper.connectionTest",null);
    }

    @Override
    public void createTable() {

    }

    @Override
    public void insertInitialData() {

    }

    @Override
    public void insertRuleMeta(Object... args) {

    }

    @Override
    public void insertApiInfo(Object... args) {

    }

    @Override
    public void deleteRuleMeta(String zone, String ruleName) {

    }

    @Override
    public void deleteApiInfo(String ruleName) {

    }

    @Override
    public AstroRuleMetaDomain selectRuleMeta(String zone) {
        return null;
    }

    @Override
    public AstroApiInfoDomain selectApiInfo(String ruleName) {
        return null;
    }
}
