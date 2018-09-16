package astro.api.collector.common;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class HttpManager {

    public static HttpResponse<String> getResponse(String url) throws UnirestException {
        return Unirest.get(url)
                      .header("cache-control", "no-cache")
                      .header("postman-token", "ad43b6de-c0bd-4ab9-b54e-3c398f02616e")
                      .asString();
    }
}
