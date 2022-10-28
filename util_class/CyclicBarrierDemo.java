package JucLearning.util_class;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.stream.IntStream;

/**
 * @author: LuoZhiKun
 * @date: 2022/02/08 周二
 */
//计数器，集齐7颗龙珠召唤神龙
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        //召唤龙珠的线程
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,()->{
            System.out.println("召唤神龙成功！");
        });
        IntStream.range(1,8).forEach(
               i->new Thread(()->{
                   System.out.println(Thread.currentThread().getName()+"收集第"+i+"龙珠");
                   try {
                       cyclicBarrier.await();
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   } catch (BrokenBarrierException e) {
                       e.printStackTrace();
                   }
               }).start()
        );
    }
}
