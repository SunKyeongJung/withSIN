package study.demo.autowired;

import jakarta.annotation.Nullable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import study.demo.member.entity.Member;

import java.util.Optional;

/**
 * 자동주입 옵션 테스트
 */
public class AutowiredTest {

    @Test
    void AutowiredOption() {
        // 이렇게 하면 componentScan 하는것처럼 스프링빈 등록함
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    /**
     * 스프링 컨테이너에서 관리하지 않는 클래스로 테스트
     * Member는 스프링이 관리하는 빈이 아님
     */
    static class TestBean {

        /**
         * required = false (생략하거나 true설정시 오류 발생)
         * 의존대상이 없으면 메소드 자체가 호출이 안됨
         *   noBean1 결과 출력 안함
         */
        @Autowired(required = false)
        public void setNoBean1(Member noBean1) {
            System.out.println("noBean1 = " + noBean1);
        }

        /**
         * @Nullable
         * 메서드 호출은 되고, 결과는 null로 들어감
         */
        @Autowired
        public void setNoBean2(@Nullable Member noBean2) {
            System.out.println("noBean2 = " + noBean2);
        }

        /**
         * Optional<>
         * 메서드 호출은 되고, 결과는 Optional.empty
         */
        @Autowired
        public void setNoBean3(Optional<Member> noBean3) {
            System.out.println("noBean3 = " + noBean3);
        }
    }
}
