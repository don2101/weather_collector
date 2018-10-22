package astro.api.collector.api;

import astro.api.collector.common.HttpManager;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;
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
        InputSource inputSource = new InputSource(new StringReader(responseBody));
        JSONObject jsonObject = new JSONObject();

        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(inputSource);

            NodeList dataList = document.getElementsByTagName("data");

            /* 전체 데이터 전송
            for(int i = 0; i < DataList.getLength(); ++i) {
                Node data = dataList.item(i);
                NodeList weatherDataList = data.getChildNodes();

                for(int j = 0; j < weatherDataList.getLength(); ++j) {
                    Node weatherData = weatherDataList.item(j);
                    message += weatherData.getTextContent();
                    message += "_";
                }
            }*/

            Node data = dataList.item(0);
            NodeList weatherDataList = data.getChildNodes();

            for(int i = 0; i < weatherDataList.getLength(); ++i) {
                Node weatherData = weatherDataList.item(i);
                jsonObject.put(weatherData.getNodeName(), weatherData.getTextContent());
            }


        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return jsonObject.toString(1);
    }
}
