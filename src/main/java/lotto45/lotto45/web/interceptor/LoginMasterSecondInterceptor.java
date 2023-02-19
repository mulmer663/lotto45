package lotto45.lotto45.web.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import lotto45.lotto45.web.SessionConst;
import org.springframework.web.servlet.HandlerInterceptor;


@Slf4j
public class LoginMasterSecondInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestURI = request.getRequestURI();
        log.info("LoginMasterSecond 인터셉터 실행 {}", requestURI);

        HttpSession session = request.getSession();

        if (session == null || session.getAttribute(SessionConst.LOGIN_MASTER_2ND_AUTH) == null) {
            log.info("미인증 관리자 요청");

            response.sendRedirect("/login?redirectURL=" + requestURI);
            return false;
        }

        log.info("관리자 2번째 인증 통과");
        log.warn("당신은 관리자 권환을 획득 하였습니다.");
        return true;
    }
}
