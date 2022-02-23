package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    /*
    <예상 호출>
     1번째 : memberService를 호출할 때,
     "call AppConfig.memberService"
     "call AppConfig.memberRepository" -> 1번

     2번쨰 : memberRepository를 호출할 때,
     "call AppConfig.memberRepository" -> 2번

     3번째 : orderSerive를 호출할 때,
     "call AppConfig.orderService"
     "call AppConfig.memberRepository" -> 총 3번 호출

     <실제 호출>
      call AppConfig.memberService
      call AppConfig.memberRepository
      call AppConfig.orderService
     -> 스프링이 어떠한 방법을 써서 싱글톤을 보장해 줬다는 것
     */

    @Bean
    public MemberService memberService(){
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService(){
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy(){
        //return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
