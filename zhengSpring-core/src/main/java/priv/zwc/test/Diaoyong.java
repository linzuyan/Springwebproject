package priv.zwc.test;

import java.lang.reflect.Proxy;

/**
 * Created by Administrator on 2016/4/14.
 */
public class Diaoyong {
    public static void  main(String args[]){
        XiangQinInterface xiangQinInterface=new XiangQinInterfaceImp();
        //XiangQinInterface xiangQinInterface=null;
        XiangQinInterface proxy=(XiangQinInterface) ReadyInvocationHandler.NewInstance(xiangQinInterface);
        proxy.xiangqin();
    }
}
