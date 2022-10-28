package JucLearning.lock8;

import java.util.concurrent.TimeUnit;

/**
 * @author: LuoZhiKun
 * @date: 2022/02/07 周一
 */

/**
 * 7.sendSms方法是静态同步方法，call方法是普通同步方法，一个对象，先谁？ 先call，然后sendSms
 * 8.上述条件改一下，如果是两个对象呢，先谁？ 依然是先call,然后sendSms
 */
public class Test4 {
    public static void main(String[] args) throws InterruptedException {
        Phone4 phone = new Phone4();
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
class Phone4{
    //synchronized 锁的对象是方法的调用者
    //static 类一加载的时候就有了，所以锁的是Class
    //两个方法用的是同一个锁，谁先拿到谁先执行！
    public static synchronized void sendSms() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        System.out.println("sendSms");
    }
    //普通方法 ,这个锁的是phone4对象
    public synchronized void call(){
        System.out.println("call");
    }
}
