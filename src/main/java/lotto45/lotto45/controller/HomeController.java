package lotto45.lotto45.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lotto45.lotto45.domain.member.Member;
import lotto45.lotto45.web.SessionConst;
import lotto45.lotto45.web.argumentresolver.Login;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(@Login Member loginMember, Model model, HttpServletRequest request) {

        HttpSession session = request.getSession();

        // * ArgumentResolvers 활용해 바로 로그인한 회원에 접근할 수 있고 만약 그 값이 null이면
        // * 비회원이므로 비회원 홈페이지에 연결합니다.
        if (loginMember == null) {
            return "home";

        // * 만약 관리자 인증 세션을 두 개 다 가지고 있다면 masterHome 홈페이지에 연결합니다.
        } else if (session.getAttribute(SessionConst.LOGIN_MASTER_1ST_AUTH) != null &&
                   session.getAttribute(SessionConst.LOGIN_MASTER_2ND_AUTH) != null) {
            return "redirect:/masterHome";
        }

        // * 그 외의 경우에는 회원 홈페이지에 연결합니다.
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
