package JucLearning.Synchronized;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: LuoZhiKun
 * @date: 2022/09/20 周二
 * @description:
 */
public class WaitAwait {
    public static void main(String[] args) throws InterruptedException {
        Object obj = new Object();
        ReentrantLock lock = new ReentrantLock();
        Condition waitCond = lock.newCondition();
        Thread t1 = new Thread(() -> {
            System.out.println("before-wait...1");
            //wait
            try {
                lock.lock();
//                synchronized (obj) {
//                    obj.wait();
//                }
                waitCond.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
               lock.unlock();
            }
            System.out.println("after-wait...1");
        });

        Thread t2 = new Thread(() ->{
            System.out.println("before-wait...2");
            try {
                lock.lock();
//                synchronized (obj) {
//                    obj.wait();
//                }
                waitCond.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
            System.out.println("after-wait...2");
        });

        t1.start();
        t2.start();

        Thread.sleep(1);
//        synchronized (obj){
//            obj.notifyAll();
//        }
        lock.lock();
        try{
            waitCond.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
