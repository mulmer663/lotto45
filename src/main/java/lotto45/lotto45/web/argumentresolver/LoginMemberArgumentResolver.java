package lotto45.lotto45.web.argumentresolver;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import lotto45.lotto45.domain.member.Member;
import lotto45.lotto45.web.SessionConst;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;


@Slf4j
public class LoginMemberArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {

        // 파라미터가 Login 어노테이션을 가지고 있는지
        boolean hasLoginAnno = parameter.hasParameterAnnotation(Login.class);
        // 파라미터가 Member 클래스인지
        boolean isMemberType = Member.class.isAssignableFrom(parameter.getParameterType());

        return hasLoginAnno && isMemberType;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {

        // * webRequest에서 세션을 꺼내 해당 세션이 가지고 있는 member 객체를 반환하게 만듬 --> 즉 @Login을 붙인 Member에
        // * 세션에서 꺼낸 맴버 정보가 들어가게 된다!
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        HttpSession session = request.getSession(false);

        if (session == null) {
            return null;
        }

        // * 만일 관리자 계정으로 로그인하면 관리자 1차 2차 세션에 저장했으므로 여기서 꺼내서 연결 시켜 준다.
        if (session.getAttribute(SessionConst.LOGIN_MASTER_1ST_AUTH) != null
            && session.getAttribute(SessionConst.LOGIN_MASTER_2ND_AUTH) != null) {
//            log.info("MASTER = {}", session.getAttribute(SessionConst.LOGIN_MASTER_2nd_AUTH));
            return session.getAttribute(SessionConst.LOGIN_MASTER_2ND_AUTH);
        }

        return session.getAttribute(SessionConst.LOGIN_MEMBER);
    }
}
