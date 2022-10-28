package JucLearning.lock;

import java.util.concurrent.TimeUnit;

/**
 * @author: LuoZhiKun
 * @date: 2022/02/14 周一
 */
//死锁
public class DeadLock {

    private static String lockA = "lockA";
    private static String lockB = "lockB";

    public static void main(String[] args) {
        new Thread(new MyThread(lockA,lockB),"A").start();
        new Thread(new MyThread(lockB,lockA),"B").start();
    }
}

class MyThread implements Runnable{

    private String lockA;
    private String lockB;

    public MyThread(String lockA,String lockB){
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName()+"lock:"+lockA+"=>get"+lockB);
            try{
                TimeUnit.SECONDS.sleep(2);
            }catch (Exception e){
                e.printStackTrace();
            }
            synchronized (lockB){
                System.out.println(Thread.currentThread().getName()+"lock:"+lockB+"=>get"+lockA);
            }
        }
    }
}