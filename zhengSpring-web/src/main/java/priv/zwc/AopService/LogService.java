package priv.zwc.AopService;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import priv.zwc.Annotation.Log;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Created by admin on 2016/6/2.
 */
@Aspect
@Component
public class LogService {

    /**
     * 定义切点
     */
    @Pointcut("@annotation(priv.zwc.Annotation.Log)")
    public void methodPointcut(){

    }

    @Before("methodPointcut()")
    public void before(JoinPoint joinPoint){
        System.out.println("------------方法执行前执行------------------");
    }

    @AfterReturning("methodPointcut()")
    public void afterReturning(){
        System.out.println("-------------------方法执行后执行-----------------------");
    }

    @AfterThrowing("methodPointcut()")
    public void afterThrowing(){
        System.out.println("--------------------方法抛异常执行-------------------------");
    }

    @After("methodPointcut()")
    public void after(JoinPoint joinPoint) throws NoSuchMethodException {
        String s= joinPoint.getSignature().getName();
        System.out.println("---------"+s+"------------");


        Annotation[] annotations=  joinPoint.getTarget().getClass().getAnnotations();
        //Annotation[] annotations=  method.getAnnotations();
        for (Annotation a:annotations){
            System.out.println("-----方法的注解"+a+"-----");
        }
        System.out.println("----------------方法最后执行-------------------");
    }

    @Around(value = "methodPointcut()&&@annotation(log)")
    public Object around(ProceedingJoinPoint point,Log log) throws Throwable {
        System.out.println("-----------------"+log.name()+"----------------------");
        System.out.println("-------------------方法环绕start------------------------");
        System.out.println("-------------------方法环绕end------------------------");
        return point.proceed();
    }
}
