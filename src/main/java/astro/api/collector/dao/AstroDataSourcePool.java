package astro.api.collector.dao;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.jdbc.pool.DataSource;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author oringnam
 * @since 2018. 12. 8.
 * blog : http://box0830.tistory.com/
 */
@Slf4j
public class AstroDataSourcePool {
    private String driverClassName;
    private String url;
    private String id;
    private String pw;

    private DataSource dataSource;
    private SqlSessionFactory sqlSessionFactory;

    public AstroDataSourcePool(String driverClassName, String url, String id, String pw) {
        this.driverClassName = driverClassName;
        this.url = url;
        this.id = id;
        this.pw = pw;
    }

    public SqlSession getSqlSession() throws SQLException {
        sqlSessionFactory.getConfiguration()
                         .setDefaultStatementTimeout(600000);
        return sqlSessionFactory.openSession(dataSource.getConnection());
    }

    @PostConstruct
    public void init() {
        dataSource = new DataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(id);
        dataSource.setPassword(pw);
        dataSource.setTestOnBorrow(false);
        dataSource.setTestOnReturn(false);
        dataSource.setValidationQuery("select 1");
        dataSource.setValidationQueryTimeout(60);
        dataSource.setMinIdle(1);
        dataSource.setMaxIdle(1);
        dataSource.setMaxActive(1);
        dataSource.setInitialSize(1);
        dataSource.setDefaultAutoCommit(true);
        dataSource.setDefaultTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

        dataSource.setConnectionProperties("connectionTimeout=6000000");
        dataSource.setConnectionProperties("maxReconnects=5");
        dataSource.setConnectionProperties("socketTimeout=6000000");
        dataSource.setConnectionProperties("readTimeout=6000000");




    }
}
