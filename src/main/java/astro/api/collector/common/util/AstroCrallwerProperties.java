package astro.api.collector.common.util;

import astro.api.collector.deprecated.ApiConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author oringnam
 * @since 2018. 12. 9.
 * blog : http://box0830.tistory.com/
 * <p>
 * 나중에 Astro.Common. 프로젝트 개선 후, 거기서 가져다 쓸 것 (상속이 좋을 것 같음)
 */
@Slf4j
@Configuration
public class AstroCrallwerProperties {
    private Properties properties = new Properties();

    public AstroCrallwerProperties() {
        loadProperties();
    }

    @PostConstruct
    public void loadProperties() {
        String propertyFile = "properties/api-crallwer.properties";

        properties.clear();

        InputStream inputStream = ApiConfig.class.getClassLoader()
                                                 .getResourceAsStream(propertyFile);

        try {
            properties.load(inputStream);
            inputStream.close();
        } catch (IOException e) {
            log.error("Cannot load property file : {}", e.getMessage());
        }
    }

    public String getProperty(String key) {
        if (properties == null) {
            loadProperties();
        }

        return properties.getProperty(key);
    }
}
