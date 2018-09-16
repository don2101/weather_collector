package astro.api.collector.api;

import astro.api.collector.common.HttpManager;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;

public class WeatherApi implements ApiInterface {

    private String url = "http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1159068000";

    public String call() {

        try {
            HttpResponse<String> response = HttpManager.getResponse(url);

            return response.getBody();
        } catch (UnirestException e) {
            e.printStackTrace();
            return "api call failed";
        }
    }
}
