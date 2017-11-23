package priv.zwc.AopService;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;
import priv.zwc.Common.AuthException;
import priv.zwc.Common.AuthUser;
import priv.zwc.Common.Constants;
import priv.zwc.Module.UserInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by admin on 2016/6/2.
 */
@Aspect
@Component
public class AuthService {
    @Pointcut("@annotation(priv.zwc.Annotation.Auth)")
    public void methodPointcut(){

    }

    @Before("methodPointcut()")
    public void before() throws AuthException {
        ServletRequestAttributes requestAttributes= (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request= requestAttributes.getRequest();
        AuthUser authUser=(AuthUser) request.getSession().getAttribute(Constants.SESSION_USER_KEY);
        if (authUser==null){
            throw new AuthException(1);
        }
    }
}
