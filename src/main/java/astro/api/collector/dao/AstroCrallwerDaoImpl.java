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
    public <T> List<? extends T> connectionTest() {
        return dataSourcePool.select("AstroCrallwerMapper.connectionTest", null);
    }

    @Override
    public Integer createTable() {
        return dataSourcePool.update("AstroCrallwerMapper.createTable", null);
    }


    @Override
    public Integer dropTable() {
        return dataSourcePool.update("AstroCrallwerMapper.dropTable", null);
    }

    @Override
    public Integer insertInitialTable(Map<String, Object> map) {
        return dataSourcePool.update("AstroCrallwerMapper.insertInitialTable", map);
    }

    @Override
    public Integer deleteInitialTable() {
        return dataSourcePool.update("AstroCrallwerMapper.deleteInitialTable", null);

    }

    @Override
    public Integer insertRuleMeta(Map<String, Object> map) {
        return dataSourcePool.update("AstroCrallwerMapper.insertRuleMeta", map);
    }

    @Override
    public Integer insertApiInfo(Map<String, Object> map) {
        return dataSourcePool.update("AstroCrallwerMapper.insertApiInfo", map);
    }

    @Override
    public Integer deleteTableData(Map<String, Object> map) {
        return dataSourcePool.update("AstroCrallwerMapper.deleteTableData", map);
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
