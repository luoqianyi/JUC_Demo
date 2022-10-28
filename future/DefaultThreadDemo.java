package JucLearning.future;

import java.util.concurrent.*;

/**
 * @author: LuoZhiKun
 * @date: 2022/02/10 周四
 */
public class DefaultThreadDemo {
    public static void main(String[] args) {
        System.out.println("main线程开始");
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(1,
                4,
                1,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(2),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );
        try{
            poolExecutor.execute(()->{
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"ok!");
            });
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            poolExecutor.shutdown();
        }
        System.out.println("main线程结束");
    }
}
