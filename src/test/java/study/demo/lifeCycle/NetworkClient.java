package study.demo.lifeCycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * 외부 네트워크에 미리 연결하는 테스트용 객체
 */
// public class NetworkClient implements InitializingBean, DisposableBean {
public class NetworkClient {

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
//        connect();
//        call("초기화 연결 메세지");
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // 서비스 시작 시 호출
    public void connect() {
        System.out.println("connect: " + url);
    }

    public void call(String message) {
        System.out.println("call: " + url + " message = " + message);
    }

    // 서비스 종료 시 호출
    public void disconnect() {
        System.out.println("close: " + url);
    }

    /**
     * @Bean initMethod 설정
     * @PostConstruct 애노테이션으로도 가능
     */
    @PostConstruct
    public void init() {
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메세지");
    }

    /**
     * @Bean destroyMethod 설정
     * @PreDestroy 애노테이션으로도 가능
     */
    @PreDestroy
    public void close() {
        System.out.println("NetworkClient.close");
        disconnect();
    }

    /**
     * implements InitializingBean
     *   afterPropertiesSet: 의존관계 주입이 끝나면 호출함
     */
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        System.out.println("NetworkClient.afterPropertiesSet");
//        connect();
//        call("초기화 연결 메세지");
//    }

    /**
     * implements DisposableBean
     *   destroy: 빈이 종료될 때 호출
     */
//    @Override
//    public void destroy() throws Exception {
//        System.out.println("NetworkClient.destroy");
//        disconnect();
//    }
}
