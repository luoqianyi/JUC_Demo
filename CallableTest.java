package JucLearning;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author: LuoZhiKun
 * @date: 2022/02/08 周二
 */
public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyThread thread = new MyThread();
        FutureTask<Integer> integerFutureTask = new FutureTask<>(thread); //适配类
        new Thread(integerFutureTask,"A").start();
        new Thread(integerFutureTask,"B").start(); //结果会被缓存，效率高
        Integer integer = integerFutureTask.get(); //获取callable的返回结果,这个get方法可能会产生阻塞，把他放到最后
        //或者使用异步通信来处理
        System.out.println(integer);

    }
}

class MyThread implements Callable<Integer>{
    @Override
    public Integer call() throws Exception {
        System.out.println("ok");
        //如果这有个耗时的操作呢？
        return 0;
    }
}
