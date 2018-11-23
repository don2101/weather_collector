package astro.api.collector.api;

import lombok.extern.log4j.Log4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Log4j
public class ApiConfig {
    private String url;

    private Properties properties = new Properties();
    private String propertyFile;

    public void loadProperties(String propertyFile) {
        this.propertyFile = propertyFile;
        properties.clear();

        InputStream inputStream = ApiConfig.class.getClassLoader().getResourceAsStream(propertyFile);

        try {
            properties.load(inputStream);
            inputStream.close();
        } catch (IOException e) {
            log.error("Cannot load property file");
        }
    }

    public String get(String key) {
        String value = (String) properties.get(key);

        if(value == null) {
            log.error("Cannot find value");

            return null;
        }

        return value;
    }

    public String makeUrl() {
        String url = get("default.url");

        return url;
    }

    public String setServiceKey(String key) {
        url.concat(key);

        return url;
    }

    public String setServiceVaule(String value) {
        url.concat(value);


        return url;
    }

}
