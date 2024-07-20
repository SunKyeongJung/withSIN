package study.demo.xml;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import study.demo.member.MemberService;

import static org.assertj.core.api.Assertions.*;

/**
 * XML 기반의 환경설정 테스트
 */
public class XmlAppContext {

    @Test
    void xmlAppContext() {
        /**
         * 클래스패스 가져와서 resources 경로 아래에 있는 appConfig 파일 가져옴
         */
        ApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        assertThat(memberService).isInstanceOf(MemberService.class);
    }

}
