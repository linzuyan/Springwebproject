package priv.zwc.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by Administrator on 2016/4/14.
 */
public class ReadyInvocationHandler implements InvocationHandler {
    private Object zhangsan=null;
    public ReadyInvocationHandler(Object realSubject){
        zhangsan=realSubject;
    }
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result=null;
        try {
            if (zhangsan!=null){
                System.out.println("相亲前的准备");
                method.invoke(zhangsan,args);
            }
            else {
                System.out.println("没有人相亲");
                method.invoke(zhangsan,args);
            }
        }catch (Exception ex){
        }
        return  result;
    }

    public static Object NewInstance(Object object){
        XiangQinInterface proxy=(XiangQinInterface) Proxy.newProxyInstance(object.getClass().getClassLoader(),
                object.getClass().getInterfaces(),new ReadyInvocationHandler(object));
        return proxy;
    }
}
