package astro.api.collector.api;

import astro.api.collector.common.util.HttpManager;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;

// TODO: 전체적인 동작 방식 고려할 필요 있음 --> 어떻게 uri 만들고 동작하게 할 것인가?
@Slf4j
@Service
public class ApiProcessor implements ApiInterface {
    private String request(String requestURL) {
        try {
            HttpResponse<String> response = HttpManager.getResponse(requestURL);

            return response.getBody();
        } catch (UnirestException e) {
            e.printStackTrace();
            return "api request failed";
        }
    }

    private String parse(String responseBody) {
        InputSource inputSource = new InputSource(new StringReader(responseBody));
        JSONObject jsonObject = new JSONObject();

        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(inputSource);

            NodeList dataList = document.getElementsByTagName("data");

            Node data = dataList.item(0);
            NodeList weatherDataList = data.getChildNodes();

            for (int i = 0; i < weatherDataList.getLength(); ++i) {
                Node weatherData = weatherDataList.item(i);
                jsonObject.put(weatherData.getNodeName(), weatherData.getTextContent());
            }


        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        return jsonObject.toString(1);
    }

    @Override
    public String call(String requestURL) {
        String responseBody = this.request(requestURL);
        return this.parse(responseBody);
    }
}
