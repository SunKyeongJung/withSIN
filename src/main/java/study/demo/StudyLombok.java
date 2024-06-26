package study.demo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 롬복 라이브러리 확인용 클래스
 *   별도 getter, setter 메소드 없이 getXxxx, setXxxx 가능
 */
@Getter
@Setter
@ToString
public class StudyLombok {

    private String name;
    private int age;

    public static void main(String[] args) {
        StudyLombok studyLombok = new StudyLombok();
        studyLombok.setName("testName");

        String name = studyLombok.getName();
        System.out.println("name = " + name);

        // @ToString 사용시 결과: studyLombok = StudyLombok(name=testName, age=0)
        System.out.println("studyLombok = " + studyLombok);
    }
}
