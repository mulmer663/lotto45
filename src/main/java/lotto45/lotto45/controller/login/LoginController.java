package lotto45.lotto45.controller.login;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotto45.lotto45.domain.member.Member;
import lotto45.lotto45.service.Encryption;
import lotto45.lotto45.service.login.LoginService;
import lotto45.lotto45.web.SessionConst;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;
    private final Encryption encryption;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm") LoginForm form) {
        return "login/loginForm";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("loginForm") LoginForm form, BindingResult bindingResult,
                        @RequestParam(defaultValue = "/") String redirectURL,
                        HttpServletRequest request) {

        // 로그인form 검증 후 오류가 있으면 다시 form으로
        if (bindingResult.hasErrors()) {
            return "login/loginForm";
        }

        // 검증 후 오류가 없으면 로직 수행
        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());

        // 반환된 로그인 객체가 null이면 매칭되지 않는 것이므로 ObjectError 반환
        if (loginMember == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "login/loginForm";
        }

        HttpSession session = request.getSession();

        // + 마스터 계정으로 접근 시 1차 인증 세션을 넣고 2차 로그인 페이지로 리다이렉트
        if (loginMember.getLoginId().equals("master")) {
            log.info("redirectURL = {}", redirectURL);
            session.setAttribute(SessionConst.LOGIN_MASTER_1ST_AUTH, loginMember);

            if (redirectURL.equals("/")) {
                return "redirect:/masterLogin";
            }

            return "redirect:/masterLogin?redirectURL=" + redirectURL;
        } else {
            session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);
        }

        return "redirect:" + redirectURL;
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {

        log.info("로그아웃 실행");
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }

        return "redirect:/";
    }

    @GetMapping("/masterLogin")
    public String loginMaster(Model model) {

        model.addAttribute("secondPassword", "");

        return "master/masterLogin";
    }

    @PostMapping("/masterLogin")
    public String loginMaster(@ModelAttribute("secondPassword") String secondPassword,
                              @RequestParam(defaultValue = "/") String redirectURL,
                              HttpServletRequest request) {

        HttpSession session = request.getSession();
        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MASTER_1ST_AUTH);

        String encrypted2ndPW = this.encryption.encryptPassword(secondPassword);
        Member secondMaster = this.loginService.getMember("SecondMaster");

        if (encrypted2ndPW.equals(secondMaster.getPassword())) {
//            log.info("redirectURL = {}", redirectURL);
            session.setAttribute(SessionConst.LOGIN_MASTER_2ND_AUTH, loginMember);
            return "redirect:" + redirectURL;
        } else {
            if (redirectURL.equals("/")) {
                return "master/masterLogin";
            } else {
                return "redirect:/masterLogin?redirectURL=" + redirectURL;
            }
        }
    }

    @GetMapping("/logout")
    public String logoutGet(HttpServletRequest request) {

        log.info("로그아웃 실행");
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }

        return "redirect:/";
    }
}
