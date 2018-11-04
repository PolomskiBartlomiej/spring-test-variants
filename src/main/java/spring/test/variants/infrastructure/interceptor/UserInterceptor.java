package spring.test.variants.infrastructure.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import spring.test.variants.domain.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws Exception {

        request.setAttribute("user", new User("user"));
        return true;
    }
}
