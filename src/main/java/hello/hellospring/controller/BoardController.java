package hello.hellospring.controller;

import hello.hellospring.service.impl.BoardServiceImpl;
import org.springframework.stereotype.Controller;

@Controller
public class BoardController {

    private BoardServiceImpl service;

    public BoardController(BoardServiceImpl service) {
        this.service = service;
    }


}
