package lotto45.lotto45.web.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import lotto45.lotto45.web.SessionConst;
import org.springframework.web.servlet.HandlerInterceptor;


@Slf4j
public class LoginMasterFirstInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestURI = request.getRequestURI();
//        log.info("LoginMasterFirst 인터셉터 실행 {}", requestURI);

        HttpSession session = request.getSession();

        if (session == null || session.getAttribute(SessionConst.LOGIN_MASTER_1ST_AUTH) == null) {
            log.info("관리자 첫 번째 인증 미승인");

            response.sendRedirect("/login?redirectURL=" + requestURI);
            return false;
        }

//        log.info("관리자 1번째 인증 통과");
        return true;
    }
}