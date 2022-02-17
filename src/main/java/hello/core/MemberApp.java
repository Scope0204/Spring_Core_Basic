package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;

public class MemberApp {

    //psvm
    public static void main(String[] args) {

        // 이전에는 직접 메인메서드에서 MemberService 생성
        //MemberService memberService = new MemberServiceImpl();

        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();

        // command + option + v
        Member member = new Member(1L, "memberA", Grade.VIP); // id가 long타입이므로 뒤에 L을 붙여줘야 함
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());// soutv
        System.out.println("findMember = " + findMember.getName());
    }
}
