package study.demo.lifeCycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 빈 생명주기 테스트
 */
public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest() {

        /**
         * ApplicationContext에 직접 접근하는 경우 보통 닫는 기능은 자주 사용하지 않기 때문에
         * 기본 ApplicationContext에서 제공하지 않음
         *   ApplicationContext ac -> AnnotationConfigApplicationContext ac 로 변경하거나
         *   ConfigurableApplicationContext ac 사용
         */
//        ApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
//        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);

        /**
         * 객체를 생성하는 단계에는 url이 없고
         * 객체를 생성한 다음에 외부에서 수정자주입을 통해서 setUrl 호출되어야 url 생김
         */
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();

    }

    @Configuration
    static class LifeCycleConfig {

        /**
         * networkClient 생성하고  setUrl 한 결과물이 bean으로 등록됨
         * 빈 등록시 initMethod, destroyMethod 이름으로 지정 가능
         *   @Bean으로 등록할때만 발생하는 destroyMethod 속성의 특별한 기능
         *   destroyMethod 따로 설정하지 않아도 close, shutdown 이름 메서드 자동 실행함
         * Bean에 init, destroy 메서드 이름 설정하지 않고 애노테이션으로도 가능
         *   @PostConstruct, @PreDestroy
         */
//        @Bean (initMethod = "init", destroyMethod = "close")
        @Bean
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://studydemo-spring.dev");
            return networkClient;
        }
    }
}
