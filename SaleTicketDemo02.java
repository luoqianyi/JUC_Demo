package JucLearning;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: LuoZhiKun
 * @date: 2022/02/07 周一
 */
public class SaleTicketDemo02 {
    public static void main(String[] args) {
        //并发，多线程操作同一个资源类，把资源类丢入线程
        Ticket2 ticket = new Ticket2();
        new Thread(()->{
            for (int i = 1; i < 40; i++) {
                ticket.sale();
            }
        },"A").start();

        new Thread(()->{
            for (int i = 1; i < 40; i++) {
                ticket.sale();
            }
        },"B").start();

        new Thread(()->{
            for (int i = 1; i < 40; i++) {
                ticket.sale();
            }
        },"C").start();

    }
}
//lock
class Ticket2{

    private int num =50;
    Lock lock = new ReentrantLock();
    public void sale(){
        lock.lock(); //加锁
        try {
            //业务代码
            if(num>0){
                System.out.println(Thread.currentThread().getName()+"卖出了第"+(num--)+"票,剩余："+num);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock(); //解锁
        }
    }
}
