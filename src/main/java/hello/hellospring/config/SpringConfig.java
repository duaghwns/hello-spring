package hello.hellospring.config;

import hello.hellospring.controller.MemberController;
import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final MemberRepository repository;


    public SpringConfig(@Qualifier("stringDataJpaMemberRepository") MemberRepository repository) {
        this.repository = repository;
    }

    @Bean
    public MemberService service(){
        return new MemberService(repository);
    }
    /*
    private EntityManager em;
    private DataSource dataSource;

    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public MemberService service(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource);
//        return new JdbcTemplateMemberRepository(dataSource);
        return new JpaMemberRepository(em);
    }
*/

}
