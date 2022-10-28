package JucLearning.util_class;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author: LuoZhiKun
 * @date: 2022/02/08 周二
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        //线程数量，停车位，限流里常用！
        Semaphore semaphore = new Semaphore(3);
        for (int i = 1; i <= 6; i++) {
            new Thread(()->{
                try {
                    semaphore.acquire();//得到
                    System.out.println(Thread.currentThread().getName()+"抢到车位");
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(Thread.currentThread().getName()+"离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release(); //释放
                }
            }).start();
        }
    }
}
