package astro.api.collector.dao;

import astro.api.collector.domain.AstroApiInfoDomain;
import astro.api.collector.domain.AstroRuleMetaDomain;

import java.util.List;
import java.util.Map;

/**
 * @author oringnam
 * @since 2018. 12. 8.
 * blog : http://box0830.tistory.com/
 *
 *
 * Crallwer 서버 전반적인 정보를 가지게 할 까 생각했는데
 * 그건 아닌거 같음 (서버 구동단계에서 설정 정보가 필요함 / db는 서버 구동 후 들어가야 함)
 *
 * 걍 Api의 정보만 가지게 하자
 * 그리고 서버의 기본적인 설정 정보는 프로퍼티로 떨구는게 맞을 것 같음 (서버 구동단계에서 필요하니까)
 */
public interface AstroCrallwerDao {
    /**
     * 운영 편의성 개선
     * - 환경 세팅
     * - rule 관리자 (추가, 제거)
     *
     * < DB 생성 쿼리는 직접 날릴 것 />
     * CREATE DATABASE astro_crallwer
     * DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
     */
    // connetion test
    <T> List<? extends T> connectionTest();

    // 초기 환경 세팅용
    Integer createTable();

    // Table drop
    Integer dropTable();

    // 초기 환경 세팅용
    Integer insertInitialTable(Map<String, Object> map);

    // db table 초기화용
    Integer deleteInitialTable();

    // rule_meta 테이블 row 추가
    Integer insertRuleMeta(Map<String, Object> map);

    // api_info 테이블 row 추가
    Integer insertApiInfo(Map<String, Object> map);

    // rule_meta 테이블 row 제거
    Integer deleteTableData(Map<String, Object> map);


    /**
     * Schedule 동작 시 필요한 것
     */
    // rule_meta table select
    List<AstroRuleMetaDomain> selectRuleMeta();

    // api_info table select
    List<AstroApiInfoDomain> selectApiInfo(Map<String, Object> map);
}
