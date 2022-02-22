package hello.core.singleton;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.*;

class StatefulServiceTest {

    @Test
    @DisplayName("기존 스프링 빈")
    void statefulServiceSington(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // ThreadA : A사용자가 10000원 주문
        statefulService1.order("userA", 10000);
        // ThreadB : B사용자가 20000원 주문
        statefulService2.order("userB", 20000);

        //ThreadA : 사용자A 주문 금액 조회
        int price = statefulService1.getPrice();
        System.out.println("price = " + price); // 20000원 < 중간에 호출한 B의 주문 금액이 나옴

        assertThat(statefulService1.getPrice()).isEqualTo(20000); // ok
    }


    @Test
    @DisplayName("무상태로 설계한 스프링 빈")
    void statefulServiceSington2(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService2 statefulService1 = ac.getBean(StatefulService2.class);
        StatefulService2 statefulService2 = ac.getBean(StatefulService2.class);

        // ThreadA : A사용자가 10000원 주문
        int userAPrice = statefulService1.order("userA", 10000);
        // ThreadB : B사용자가 20000원 주문
        int userBPrice = statefulService2.order("userB", 20000);

        //사용자A 주문 금액 조회
        System.out.println("price = " + userAPrice);

        assertThat(userAPrice).isEqualTo(10000); // ok
    }


    static class TestConfig{
        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
        @Bean
        public StatefulService2 statefulService2(){
            return new StatefulService2();
        }
    }

}