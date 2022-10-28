package JucLearning.future;

/**
 * @author: LuoZhiKun
 * @date: 2022/02/10 周四
 */

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 异步调用 CompletableFuture 类似前端的：Ajax
 * //异步执行
 * //成功回调
 * //失败回调
 */
public class CompleteFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
       //发起一个请求,没有返回值的runAsync
//        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(()->{
//            try {
//                TimeUnit.SECONDS.sleep(2);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(Thread.currentThread().getName()+"runAsync=>Void");
//        });
//        System.out.println(111);
//        completableFuture.get(); //获取阻塞执行结果
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName()+"runAsync=>Integer");
            int i = 10/0;
            return 1024;
        });
        completableFuture.whenComplete((t,u)->{ //成功回调
            System.out.println("t=>"+t); //正常的返回结果
            System.out.println("u=>"+u); //错误信息 java.util.concurrent.CompletionException
        }).exceptionally((e)->{
            System.out.println(e.getMessage()); //出错误回调
            return 233;
        }).get();
    }
}
