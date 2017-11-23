package priv.zwc.test;

/**
 * Created by admin on 2016/6/1.
 */
public class Product {
    public static void main(String[] args){
        P p=new P();
        Pthread pthread=new Pthread(p);
        Gthread gthread=new Gthread(p);
    }
}

class P{
    int n;
    boolean isset=false;
    synchronized void put(int i){
        if (isset){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        n=i;
        System.out.println("put:"+n);
        isset=true;
        notify();
    }

    synchronized int get(){
        if (!isset){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("get:"+n);
        isset=false;
        notify();
        return n;
    }
}

class Pthread implements Runnable{
    Thread t;
    P p;
    Pthread(P _p){
        p=_p;
        t=new Thread(this,"Put");
        t.start();
    }
    public void run() {
        int i=5;
        while (i>0){
            p.put(i);
            i--;
        }
    }
}

class Gthread implements Runnable{
    Thread t;
    P p;
    Gthread(P _p){
        p=_p;
        t=new Thread(this,"Get");
        t.start();
    }
    public void run() {
        int i=5;
        while (true){
            p.get();
        }
    }
}
