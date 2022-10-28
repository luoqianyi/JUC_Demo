package JucLearning.lock8;

/**
 * @author: LuoZhiKun
 * @date: 2022/02/07 周一
 */

import java.util.concurrent.TimeUnit;

/**
 *  5.将之前的两个同步方法改成两个静态同步方法，只有一个对象，那么先谁？ 先sendSms,然后call
 *  6.上题要求变一下，如果是两个对象呢？ 仍然是先sendSms,然后call
 */
public class Test3 {
    public static void main(String[] args) throws InterruptedException {
        Phone3 phone = new Phone3();
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
class Phone3{
    //synchronized 锁的对象是方法的调用者
    //static 类一加载的时候就有了，所以锁的是Class
    //两个方法用的是同一个锁，谁先拿到谁先执行！
    public static synchronized void sendSms() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        System.out.println("sendSms");
    }
    public static synchronized void call(){
        System.out.println("call");
    }
}
