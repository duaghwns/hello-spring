package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.ObjectAssert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }


    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();

        System.out.println("result: " + (result == member));

        Assertions.assertEquals(result, member);
        assertThat(member).isEqualTo(result);

    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("hojooon");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("joonho");
        repository.save(member2);

        Member member = repository.findByName("hojooon").get();
        assertThat(member).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member = new Member();
        member.setName("hojoon");
        repository.save(member);

        Member member1 = new Member();
        member1.setName("joonho");
        repository.save(member1);

        List<Member> members = repository.findAll();

        assertThat(members.size()).isEqualTo(2);
    }

}
