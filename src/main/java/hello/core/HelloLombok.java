package hello.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HelloLombok {

    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok(); // 롬북 생성
        helloLombok.setName("Hello");

        System.out.println("helloLombok = " + helloLombok);
    }
}
