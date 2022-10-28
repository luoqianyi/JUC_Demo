package JucLearning.lock8;

/**
 * @author: LuoZhiKun
 * @date: 2022/02/07 周一
 */

import java.util.concurrent.TimeUnit;

/**
 * 8锁，就是关于锁的8个问题
 * 1.标准情况下，两个线程先打印sendSms还是call? 先sendSms，然后call
 * 2.sendSms延迟4秒，两个线程先打印sendSms还是call？ 先sendSms,然后call
 */
public class Test1 {
    public static void main(String[] args) throws InterruptedException {
        Phone phone = new Phone();
        //锁的存在
        new Thread(()->{
            try {
                phone.sendSms();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"A").start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(()->{
            phone.call();
        },"B").start();
    }
}

class Phone{
    //synchronized 锁的对象是方法的调用者
    //两个方法用的是同一个锁，谁先拿到谁先执行！
    public synchronized void sendSms() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        System.out.println("sendSms");
    }
    public synchronized void call(){
        System.out.println("call");
    }
}
