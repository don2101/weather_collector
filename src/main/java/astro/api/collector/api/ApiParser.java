package astro.api.collector.api;

import lombok.extern.log4j.Log4j2;
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

/**
 * @author don2101
 * @since 2019. 01. 01
 */

@Log4j2
public class ApiParser {
    String rule;
    String apiUrl;

    public static String parse(String responseBody, String rule) {
        String result = null;


        result = weatherParse(responseBody);
        log.info(result);
        log.info(rule);


        return result;
    }

    public static String weatherParse(String responseBody) {
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

}
