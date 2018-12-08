package astro.api.collector.domain;

import lombok.Data;

import java.util.Date;

/**
 * @author oringnam
 * @since 2018. 12. 8.
 * blog : http://box0830.tistory.com/
 */
@Data
public class AstroRuleMetaDomain {
    private String zone;
    private String ruleName;
    private Date ruleRegDatetime;
}
