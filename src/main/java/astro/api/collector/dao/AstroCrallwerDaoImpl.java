package astro.api.collector.dao;

import astro.api.collector.domain.AstroApiInfoDomain;
import astro.api.collector.domain.AstroRuleMetaDomain;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author oringnam
 * @since 2018. 12. 8.
 * blog : http://box0830.tistory.com/
 */
@Service
public class AstroCrallwerDaoImpl implements AstroCrallwerDao {
    private AstroDataSourcePool dataSourcePool = new AstroDataSourcePool();

    @Override
    public void connectionTest() {
        dataSourcePool.select("AstroCrallwerMapper.connectionTest",null);
    }

    @Override
    public void createTable() {
        Integer result = dataSourcePool.update("AstroCrallwerMapper.createTable", null);
    }


    @Override
    public void dropTable() {
        Integer result = dataSourcePool.update("AstroCrallwerMapper.dropTable", null);
    }

    @Override
    public void insertInitialTable(Map<String, Object> map) {
        Integer result = dataSourcePool.update("AstroCrallwerMapper.insertInitialTable", map);
    }

    @Override
    public void deleteInitialTable() {
        Integer result = dataSourcePool.update("AstroCrallwerMapper.deleteInitialTable", null);

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
