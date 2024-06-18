package study.demo.singleton;

/**
 * 싱글톤 간단 테스트 서비스
 *   순수 자바 Singleton 패턴
 *   객체를 미리 생성해두는 가장 단순하고 안전한 방법
 */
public class SingletonService {

    /**
     * static 영역에 하나만 만들어져서 올라감
     * 자바가 뜨면서 static 영역에 있는 SingletonService instance 초기화
     *   new를 생성해서 하나를 가지고 있음
     */
    private static final SingletonService instance = new SingletonService();

    /**
     * SingletonService instance의 참조를 꺼낼 방법
     *   이 static 메서드를 통해서 조회 허용
     */
    public static SingletonService getInstance() {
        return instance;
    }

    /**
     * 생성자를 private로 선언
     *   외부에서  new SingletonService(); 객체생성 못함
     */
    private SingletonService() {
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
