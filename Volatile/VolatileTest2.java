package JucLearning.Volatile;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: LuoZhiKun
 * @date: 2022/02/10 周四
 */
//不保证原子性
public class VolatileTest2 {
    private static volatile AtomicInteger num = new AtomicInteger(0); //不保证原子性

    public static void add(){
//        num++; //不是一个原子性操作
        num.getAndIncrement(); //AtomicInteger +1 方法，CAS
    }

    public static void main(String[] args) {
        //理论上num结果应该为2万
        for (int i = 1; i <= 20; i++) {
            new Thread(()->{
                for (int j = 0; j < 1000; j++) {
                    add();
                }
            }).start();
        }
        while (Thread.activeCount()>2){ //main、GC
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName()+ " "+ num);
    }
}
