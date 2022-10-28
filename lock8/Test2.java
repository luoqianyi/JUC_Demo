package JucLearning.lock8;

import java.util.concurrent.TimeUnit;

/**
 * @author: LuoZhiKun
 * @date: 2022/02/07 周一
 */

/**
 * 3.增加了一个普通方法，两个线程先打印sendSms还是hello? 先hello，然后sendSms
 * 4.两个对象，一个调用sendSms，一个调用call，是先谁？ 先call，然后sendSms
 */
public class Test2 {
    public static void main(String[] args) throws InterruptedException {
        Phone2 phone = new Phone2();
        Phone2 phone2 = new Phone2();
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
            phone2.call();
        },"B").start();
    }
}
class Phone2{
    //synchronized 锁的对象是方法的调用者
    //两个方法用的是同一个锁，谁先拿到谁先执行！
    public synchronized void sendSms() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        System.out.println("sendSms");
    }
    public synchronized void call(){
        System.out.println("call");
    }
    public void hello(){
        System.out.println("hello");
    }
}