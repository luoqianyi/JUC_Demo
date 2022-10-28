package JucLearning.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author: LuoZhiKun
 * @date: 2022/02/14 周一
 */

//自旋锁
public class SpinlockDemo {
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void myLock(){
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName()+"==>mylock");
        //自旋锁
        while (atomicReference.compareAndSet(null,thread)){

        }
    }

    public void myUnLock(){
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName()+"==>myunlock");
        atomicReference.compareAndSet(thread,null);
    }

    //底层使用的自旋锁CAS
    public static void main(String[] args) throws InterruptedException {
        SpinlockDemo spinlockDemo = new SpinlockDemo();

        new Thread(()->{
            spinlockDemo.myLock();
            try{
                TimeUnit.SECONDS.sleep(3);
            }catch (Exception e){

            }finally {
                spinlockDemo.myUnLock();
            }
        },"T1").start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(()->{
            spinlockDemo.myLock();
            try{
                TimeUnit.SECONDS.sleep(3);
            }catch (Exception e){

            }finally {
                spinlockDemo.myUnLock();
            }
        },"T2").start();
    }

}
