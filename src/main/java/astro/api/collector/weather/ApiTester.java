package astro.api.collector.weather;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class ApiTester {

    public String call() {
        try {
            HttpResponse<String> response = Unirest.get("http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1159068000")
                                                   .header("cache-control", "no-cache")
                                                   .header("postman-token", "ad43b6de-c0bd-4ab9-b54e-3c398f02616e")
                                                   .asString();

            return response.getBody();
        } catch (UnirestException e) {
            e.printStackTrace();
            return "api call failed";
        }
    }
}
