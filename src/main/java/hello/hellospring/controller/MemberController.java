package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;

public class MemberController {

    private MemberService service;
    @Autowired
    public MemberController(MemberService service) {
        this.service = service;
    }
}
