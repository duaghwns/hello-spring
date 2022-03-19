package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StringDataJpaMemberRepository extends JpaRepository<Member,Long>, MemberRepository {
    Optional<Member> findByName(String name);
}
