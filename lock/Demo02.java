package JucLearning.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: LuoZhiKun
 * @date: 2022/02/14 周一
 */
public class Demo02 {
    public static void main(String[] args) {
        Phone2 phone = new Phone2();
        new Thread(()->{
            phone.sms();
        },"A").start();

        new Thread(()->{
            phone.sms();
        },"B").start();
    }
}

class Phone2{
    Lock lock = new ReentrantLock();

    public void sms(){
        lock.lock(); //这有一把锁
        //锁必须配对，否则就会死锁
        try{
            System.out.println(Thread.currentThread().getName()+"sms");
            call(); //这里也有锁
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void call(){
        lock.lock(); //这也有一把锁
        try{
            System.out.println(Thread.currentThread().getName()+"call");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}