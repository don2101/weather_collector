package astro.api.collector.api;

import astro.api.collector.common.util.HttpManager;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


// TODO: 전체적인 동작 방식 고려할 필요 있음 --> 어떻게 uri 만들고 동작하게 할 것인가?
@Slf4j
@Service
public class ApiProcessor implements ApiInterface {
    ApiParser apiParser;

    private String request(String requestURL) {
        try {
            HttpResponse<String> response = HttpManager.getResponse(requestURL);

            return response.getBody();
        } catch (UnirestException e) {
            e.printStackTrace();
            return "api request failed";
        }
    }

    @Override
    public String call(String requestURL, String rule) {
        String responseBody = this.request(requestURL);
        return apiParser.parse(responseBody, rule);
    }
}
