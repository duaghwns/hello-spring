package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
@Controller
public class MemberController {

    private MemberService service;
    @Autowired
    public MemberController(MemberService service) {
        this.service = service;
    }

    @GetMapping("/members/new")
    public String join(){

        return "members/createNewMember";
    }

    @PostMapping("/members/new")
    public String newMember(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        service.join(member);
        return "redirect:/";
    }

    @GetMapping("members")
    public String list(Model model){
        List<Member> members = service.findMembers();
        model.addAttribute("members",members);
        return "members/memberList";
    }

    @GetMapping("/members/login")
    public String loginView(){
        return "members/login";
    }

    @PostMapping("/members/login")
    public String login(HttpServletRequest request, Long id, String name){
        HttpSession session = request.getSession();
        Member loginMember = service.login(id, name);

        if(loginMember != null){
            session.setAttribute("user",loginMember);
            return "redirect:/";
        } else {
            return "members/login";

        }

    }
}
