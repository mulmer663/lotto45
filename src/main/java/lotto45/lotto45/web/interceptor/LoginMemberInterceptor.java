package lotto45.lotto45.web.interceptor;

import lombok.extern.slf4j.Slf4j;
import lotto45.lotto45.web.SessionConst;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class LoginMemberInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestURI = request.getRequestURI();
        log.info("LoginMember 인터셉터 실행 {}", requestURI);

        HttpSession session = request.getSession();

        if (session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null) {
            log.info("미인증 사용자");

            // 로그인 화면으로 보내주되 원래 접속하려고 했던 주소를 파라미터로 넘겨줌
            response.sendRedirect("/login?redirectURL=" + requestURI);
            return false;
        }

        log.info("회원입니다.");
        return true;
    }
}
