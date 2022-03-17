package hello.hellospring.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(@RequestParam(value = "name",required = false) String name, HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");
        name = name != null ? name:"손";
        user = user != null ? user.toString():name;
        model.addAttribute("name",user);
        return "index";
    }

    @GetMapping("hello")
    @ResponseBody
    public String hello(@RequestParam(value = "name") String name){
        return "hello " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloapi(@RequestParam("name") String name){
        Hello hello = new Hello(name);
        return hello;
    }

    @AllArgsConstructor
    static class Hello{
        private String name;
    }

}
