package JucLearning.forkjoin;

/**
 * @author: LuoZhiKun
 * @date: 2022/02/09 周三
 */

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * 求和计算
 * 低级：for循环
 * 中级：forkjoin 通过ForkJoinPool来执行，计算任务forkjoinPool.execute(ForkJoinTask task),计算类继承ForkJoinTask
 * 高级：stream流
 */
public class ForkJoinDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoin forkJoin = new ForkJoin(1L,10_0000_0000L);
        long start = System.currentTimeMillis();
//        low();  //3371

//        forkJoinPool.execute(forkJoin);
//        System.out.println(forkJoin.get()); // 382

        high(); //217
        long end = System.currentTimeMillis();
        System.out.println("用时"+(end-start));
    }

    public static void low(){
        Long sum = 0L;
        for (long i = 1; i <= 10_0000_0000; i++) {
            sum += i;
        }
        System.out.println(sum);
    }

    public static void high(){
        long sum = LongStream.rangeClosed(0L, 10_0000_0000L).parallel().reduce(0, Long::sum);
        System.out.println(sum);
    }

}
class ForkJoin extends RecursiveTask<Long>{
    private Long start;
    private Long end;
    //临界值
    private Long tmp = 1000L;

    public ForkJoin(Long start,Long end){
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        if((end-start)<tmp){
            long sum = 0L;
            for (long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        }else {
            long mid = (start+end)/2; //中间值
            ForkJoin task1 = new ForkJoin(start,mid);
            task1.fork(); //拆分任务，把任务压入线程队列
            ForkJoin task2 = new ForkJoin(mid+1,end);
            task2.fork(); //拆分任务，把任务压入线程队列
            return task1.join()+task2.join();
        }
    }
}
