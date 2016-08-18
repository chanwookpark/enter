package enter;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author chanwook
 */
public class ScriptTemplateCommonAttributeHandlerInterceptor extends HandlerInterceptorAdapter {

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        final SecurityContext context = SecurityContextHolder.getContext();
        if (context != null && context.getAuthentication() != null && context.getAuthentication().isAuthenticated()) {
            modelAndView.addObject("_user", context.getAuthentication());
        }
    }
}
