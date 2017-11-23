package priv.zwc.test;

/**
 * Created by admin on 2016/6/1.
 */
public class TestThread {
    public static void main(String[] args){
        MyThread myThread1= new MyThread("one");
        try {
            myThread1.thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        MyThread myThread2= new MyThread("tow");
        try {
            myThread2.thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        MyThread myThread3= new MyThread("three");
        try {
            myThread3.thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class MyThread implements Runnable{
    String name;
    Thread thread;
    MyThread(String _name){
        name=_name;
        thread=new Thread(this,name);
        thread.start();
    }
    public void run() {
        int i=5;
        while (i>0){
            System.out.println(name+":"+i);
            i--;
        }
    }
}
