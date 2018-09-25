package astro.api.collector.api;

import astro.api.collector.common.HttpManager;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;

public class WeatherApi implements ApiInterface {

    private String url = "http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1154561000";

    public String call() {
        String responseBody = this.request();
        return this.parse(responseBody);
    }

    public String request() {
        try {
            HttpResponse<String> response = HttpManager.getResponse(url);

            return response.getBody();
        } catch (UnirestException e) {
            e.printStackTrace();
            return "api request failed";
        }
    }

    public String parse(String responseBody) {
        return responseBody;
    }
}
