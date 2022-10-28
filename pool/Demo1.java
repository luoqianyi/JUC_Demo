package JucLearning.pool;

import java.util.concurrent.*;

/**
 * @author: LuoZhiKun
 * @date: 2022/02/08 周二
 */
//Executors 工具类、3大方法

public class Demo1 {
    public static void main(String[] args) {
//        ExecutorService threadPool = Executors.newSingleThreadExecutor();//单个线程
//        ExecutorService threadPool = Executors.newFixedThreadPool(5);//创建一个固定的线程池的大小
//        ExecutorService threadPool = Executors.newCachedThreadPool();//可伸缩的，遇强则强，遇弱则弱
        ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(5); //创建可指定时长调度任务的固定大小的线程池
        for (int i = 0; i < 5; i++) {
            // 使用线程池创建线程
//            threadPool.execute(()->{
//                System.out.println(Thread.currentThread().getName()+" ok!");
//            });
            //这个线程池延迟3秒后执行线程
//            threadPool.schedule(()->{
//                System.out.println(Thread.currentThread().getName()+" ok!");
//            },3, TimeUnit.SECONDS);
           // 延迟1秒执行，然后每5秒执行一次,默认池是用完的，所以不能在其后面使用shutdown方法
            threadPool.scheduleAtFixedRate(()-> System.out.println(Thread.currentThread().getName()+"ok！"),1,5,TimeUnit.SECONDS);
        }
        //线程池用完，程序结束，关闭线程池
        threadPool.shutdown();
    }
}
