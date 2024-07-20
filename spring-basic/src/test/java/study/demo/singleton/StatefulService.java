package study.demo.singleton;

/**
 * 싱글톤 방식의 중요성!! - 서비스
 *   무상태로 설계해야한다
 *   상태를 유지할 경우 발생하는 문제점 예시
 */
public class StatefulService {

    /**
     * 공유필드를 사용하는 문제 예시
     */
//    private int price;  // 상태를 유지하는 필드 (공유)
//
//    public void order(String name, int price) {
//        System.out.println("name = " + name + " price = " + price);
//        this.price = price; // 여기가 문제!!!
//    }
//
//    public int getPrice() {
//        return price;
//    }



    /**
     * 무상태로 설계
     */
    public int order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        return price;
    }
}
