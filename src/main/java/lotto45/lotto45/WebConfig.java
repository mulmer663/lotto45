package lotto45.lotto45;

import lotto45.lotto45.web.argumentresolver.LoginMemberArgumentResolver;
import lotto45.lotto45.web.interceptor.LoginMasterFirstInterceptor;
import lotto45.lotto45.web.interceptor.LoginMasterSecondInterceptor;
import lotto45.lotto45.web.interceptor.LoginMemberInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new LoginMemberArgumentResolver());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new LoginMemberInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/", "/lotto45", "/save-lotto_win_info", "/masterLogin", "/masterHome",
                        "/members", "/members/**",
                        "/members/add", "/login", "/logout",
                        "/css/**", "/*.ico", "/error");

        registry.addInterceptor(new LoginMasterFirstInterceptor())
                .order(2)
                .addPathPatterns("/masterLogin");

        registry.addInterceptor(new LoginMasterSecondInterceptor())
                .order(3)
                .addPathPatterns("/save-lotto_win_info", "/masterHome", "/members", "/members/**")
                .excludePathPatterns("/", "/css/**", "/*.ico", "/error" , "/members/add" , "/members/edit/**");
    }
}
