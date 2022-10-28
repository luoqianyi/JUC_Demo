package JucLearning.Volatile;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author: LuoZhiKun
 * @date: 2022/02/10 周四
 */
//可见性
public class VolatileTest {
    private static volatile int initValue = 0;
    private final static int LIMIT  = 5;
    public static void main(String[] args) {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(2, 2, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        poolExecutor.execute(()->{
            int value = initValue;
            while(value<LIMIT){
                if(value!=initValue){
                    System.out.println("获取更新后的值："+initValue);
                    value = initValue;
                }
            }
        });
        poolExecutor.execute(()->{
            int value = initValue;
            while (initValue < LIMIT) {
                System.out.println("将值更新为：" + ++value);
                initValue = value;
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        poolExecutor.shutdown();
    }
}
