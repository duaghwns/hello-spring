package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
public class MemberService {

    private final MemberRepository memberRepository;
    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

//    회원가입
    public Long join(Member member) {
        validateDuplicateMember(member); // 중복회원 검색
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m->{
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    public Member login(Long id, String name){
        Optional<Member> memberId = memberRepository.findById(id);
        if(memberId != null && memberId==memberRepository.findByName(memberId.get().getName())){
            return new Member(memberRepository.findByName(memberId.get().getName()).get().getName());
        }
        throw new IllegalStateException("회원이 존재하지 않습니다.");
    }

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
