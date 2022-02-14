package hello.core.member;

public class MemberApp {

    //psvm
    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        // command + option + v
        Member member = new Member(1L, "memberA", Grade.VIP); // id가 long타입이므로 뒤에 L을 붙여줘야 함
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());// soutv
        System.out.println("findMember = " + findMember.getName());
    }
}
