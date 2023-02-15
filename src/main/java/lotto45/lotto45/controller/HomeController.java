package lotto45.lotto45.controller;

import lotto45.lotto45.domain.member.Member;
import lotto45.lotto45.web.SessionConst;
import lotto45.lotto45.web.argumentresolver.Login;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(@Login Member loginMember, Model model, HttpServletRequest request) {

        HttpSession session = request.getSession();

        if (loginMember == null) {
            return "home";
        } else if (session.getAttribute(SessionConst.LOGIN_MASTER_1ST_AUTH) != null &&
                   session.getAttribute(SessionConst.LOGIN_MASTER_2ND_AUTH) != null) {
            return "redirect:/masterHome";
        }

        model.addAttribute("member", loginMember);
        return "loginHome";
    }

    @GetMapping("/masterHome")
    public String masterHome(@Login Member loginMember, Model model, HttpServletRequest request) {

        HttpSession session = request.getSession();

        if (session.getAttribute(SessionConst.LOGIN_MASTER_1ST_AUTH) != null
            && session.getAttribute(SessionConst.LOGIN_MASTER_2ND_AUTH) != null) {

            model.addAttribute("member", loginMember);
            return "master/masterHome";
        } else {
            return "home";
        }
    }
}
