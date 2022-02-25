package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {

        // 회원 정보 조회
        Member member = memberRepository.findById(memberId);  // comm + option + v
        // 할인 정책에 회원 정보, 물건 가격을 넘김
        int discountPrice = discountPolicy.discount(member, itemPrice);
        // 단일 체계 원칙을 잘 지킴

        return new Order(memberId, itemName, itemPrice, discountPrice);

    }

    // 테스트 용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
