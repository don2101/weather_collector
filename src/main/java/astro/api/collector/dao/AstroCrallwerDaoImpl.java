package astro.api.collector.dao;

import astro.api.collector.domain.AstroApiInfoDomain;
import astro.api.collector.domain.AstroRuleMetaDomain;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public void insertRuleMeta(Map<String, Object> map) {
        Integer result = dataSourcePool.update("AstroCrallwerMapper.insertRuleMeta", map);
    }

    @Override
    public void insertApiInfo(Map<String, Object> map) {
        Integer result = dataSourcePool.update("AstroCrallwerMapper.insertApiInfo", map);
    }

    @Override
    public void deleteTableData(Map<String, Object> map) {
        Integer result = dataSourcePool.update("AstroCrallwerMapper.deleteTableData", map);
    }


    @Override
    public List<AstroRuleMetaDomain> selectRuleMeta() {
        return dataSourcePool.select("AstroCrallwerMapper.selectRuleMeta", null);
    }

    @Override
    public List<AstroApiInfoDomain> selectApiInfo() {
        return dataSourcePool.select("AstroCrallwerMapper.selectApiInfo", null);
    }
}
