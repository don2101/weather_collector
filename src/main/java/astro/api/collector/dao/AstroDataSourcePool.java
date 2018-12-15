package astro.api.collector.dao;

import astro.api.collector.common.AstroCrallwerProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.tomcat.jdbc.pool.DataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

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

    private int timeout = 600000;

    public AstroDataSourcePool() {
        AstroCrallwerProperties astroCrallwerProperties = new AstroCrallwerProperties();

        driverClassName = astroCrallwerProperties.getProperty("astro.api.crallwer.driverClassName");
        url = astroCrallwerProperties.getProperty("astro.api.crallwer.url");
        id = astroCrallwerProperties.getProperty("astro.api.crallwer.id");
        pw = astroCrallwerProperties.getProperty("astro.api.crallwer.pw");

        init();
    }

    public SqlSession getSqlSession() {
        sqlSessionFactory.getConfiguration()
                         .setDefaultStatementTimeout(timeout);
        return sqlSessionFactory.openSession(ExecutorType.SIMPLE, borrowConnection());
    }


    /**
     * ## datasource config info
     * -- http://tomee.apache.org/datasource-config.html
     */
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
        dataSource.setMinIdle(2);
        dataSource.setMaxIdle(5);
        dataSource.setMaxActive(5);
        dataSource.setInitialSize(2);
        dataSource.setDefaultAutoCommit(true);
        dataSource.setDefaultTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

        dataSource.setConnectionProperties("connectionTimeout=" + timeout);
        dataSource.setConnectionProperties("maxReconnects=5");
        dataSource.setConnectionProperties("socketTimeout=" + timeout);
        dataSource.setConnectionProperties("readTimeout=" + timeout);

        createSqlSession();

        initDisplay();
    }

    private void initDisplay() {
        log.info("=============== AstroDataSourcePool init ===============");
        log.info("driverClassName : {} ", dataSource.getDriverClassName());
        log.info("db url : {} ", dataSource.getUrl());
        log.info("user name : {} ", dataSource.getUsername());
        log.info("datasource idle : {} ", dataSource.getIdle());
        log.info("timeout : {} ", timeout);
        log.info("========================================================");
    }

    private void createSqlSession() {
        String resource = "mybatis-config.xml";
        InputStream inputStream;

        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            log.error("mybatis-config.xml read err : {}", e.getMessage());
            return;
        }

        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        sqlSessionFactory = builder.build(inputStream);
    }

    public Connection borrowConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            try {
                log.warn("connection borrow 1 failed : {}", e.getMessage());
                init();
                return dataSource.getConnection();
            } catch (SQLException e1) {
                log.error("connection borrowing is failed : {}", e.getMessage());
            }
        }

        return null;
    }

    public <T> List<T> select(String mapperName, Object param) {
        SqlSession sqlSession = getSqlSession();
        List<T> result = sqlSession.selectList(mapperName, param);
        sqlSession.close();

        return result;

    }

    public Integer update(String mapperName, Object param) {
        SqlSession sqlSession = getSqlSession();
        Integer result = sqlSession.update(mapperName, param);
        sqlSession.close();

        return result;
    }
}
