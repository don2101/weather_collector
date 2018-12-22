package astro.api.collector.api;

import astro.api.collector.common.domain.AstroApiInfoDomain;
import lombok.AllArgsConstructor;

import java.util.ArrayList;

/**
 * @author oringnam
 * @since 2018. 12. 22.
 * blog : http://box0830.tistory.com/
 */
@AllArgsConstructor
public class UriCreator {
    private AstroApiInfoDomain domain;

    public ArrayList<String> getUris() {
        ArrayList<String> results = new ArrayList<>();

        String[] params = domain.getApiParameters().split(",");
        String beforeUri = domain.getApiUrl();
        for (String param : params) {
            String uri = String.format(beforeUri, param);
            results.add(uri);
        }

        return results;
    }
}
