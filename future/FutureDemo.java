package JucLearning.future;

import java.util.concurrent.*;

/**
 * @author: LuoZhiKun
 * @date: 2022/02/10 周四
 */
public class FutureDemo {
    public static  void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("main线程开始");
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(1, 4, 1, TimeUnit.SECONDS, new ArrayBlockingQueue<>(2), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        try {
            FutureTask<Integer> futureTask = new FutureTask<>(() -> {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"ok!");
                return 200;
            });
            try{
                threadPool.execute(futureTask);
//                System.out.println(futureTask.get()); //这里的get会阻塞线程，如果没有返回值的话就是异步了，不然就是同步
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                threadPool.shutdown();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("main线程结束");
    }
}
