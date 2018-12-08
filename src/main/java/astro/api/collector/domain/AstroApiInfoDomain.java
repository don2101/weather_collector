package astro.api.collector.domain;

import lombok.Data;

/**
 * @author oringnam
 * @since 2018. 12. 8.
 * blog : http://box0830.tistory.com/
 */
@Data
public class AstroApiInfoDomain {
    private String ruleName;
    private String apiUrl;
    private String apiParameters;
    private boolean apiHasDate;
    private int apiDateOffset;
}
