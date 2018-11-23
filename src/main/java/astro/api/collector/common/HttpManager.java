package astro.api.collector.common;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class HttpManager {

    public static HttpResponse<String> getResponse(String url) throws UnirestException {
        return Unirest.get(url).asString();
    }
}
