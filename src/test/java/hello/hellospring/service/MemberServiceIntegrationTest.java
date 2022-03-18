package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional(rollbackFor = IllegalStateException.class)
class MemberServiceIntegrationTest {

    @Autowired
    MemberService service;
    @Autowired
    MemberRepository repository;

    @Test
    public void 회원가입() throws Exception {
        // given
        Member member = new Member("hojoon");
        // when
        Long saveId = service.join(member);
        // then
        Member findMember = service.findOne(saveId).get();
        assertEquals(member.getName(),findMember.getName());
    }

    @Test
    public void 중복_회원_예외() throws Exception {
        // given
        Member member = new Member("hojoon");
        Member member1 = new Member("hojoon");
        // when
        service.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> service.join(member));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        try{
//            service.join(member);
//            fail();
//        } catch (IllegalStateException e) {
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }
        // then
    }
}
