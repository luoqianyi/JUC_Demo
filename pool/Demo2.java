package JucLearning.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author: LuoZhiKun
 * @date: 2022/02/08 周二
 */
public class Demo2 {
    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors()); //得到CPU最大核心线程数为8
        //自定义线程类
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(2,
                5,
                3,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
//                new ThreadPoolExecutor.AbortPolicy() //默认的拒绝策略就是这个:不处理后续线程，抛出异常
//                new ThreadPoolExecutor.CallerRunsPolicy() //哪来的回哪
//                new ThreadPoolExecutor.DiscardPolicy() //队列满了，丢弃新的任务，不抛异常
                new ThreadPoolExecutor.DiscardOldestPolicy()
        );
        try{
            IntStream.range(1,10).forEach( //5+3 最多同时有8个，但是出现第9个了，拒绝策略抛出异常
                    i ->poolExecutor.execute(()->{
                        System.out.println(Thread.currentThread().getName()+" ok");
                    })
            );
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            poolExecutor.shutdown();
        }
    }
}
