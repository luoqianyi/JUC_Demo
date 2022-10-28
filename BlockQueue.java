package JucLearning;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author: LuoZhiKun
 * @date: 2022/02/08 周二
 */
public class BlockQueue {
    public static void main(String[] args) throws InterruptedException {
//        test1();
//        test2();
//        test3();
        test4();
    }

    /**
     * 抛出异常
     */
    public static void test1(){
        //队列的大小
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(arrayBlockingQueue.add("a"));
        System.out.println(arrayBlockingQueue.add("b"));
        System.out.println(arrayBlockingQueue.add("c"));
        //队列满的话再添加会抛出异常 java.lang.IllegalStateException: Queue full
        //System.out.println(arrayBlockingQueue.add("d"));

        System.out.println(arrayBlockingQueue.element()); //查看队首元素是谁

        System.out.println(arrayBlockingQueue.remove());
        System.out.println(arrayBlockingQueue.remove());
        System.out.println(arrayBlockingQueue.remove());
        //队列空的话再取出会抛出异常 java.util.NoSuchElementException
        //System.out.println(arrayBlockingQueue.remove());
    }

    /**
     * 不抛出异常，有返回值
     */
    public static void test2(){
        //队列的大小
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(arrayBlockingQueue.offer("a"));
        System.out.println(arrayBlockingQueue.offer("b"));
        System.out.println(arrayBlockingQueue.offer("c"));
        System.out.println(arrayBlockingQueue.offer("d")); //不抛出异常，返回false

        System.out.println(arrayBlockingQueue.peek());

        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll()); //不抛出异常，返回null
    }

    /**
     * 等待，阻塞（一直）
     */
    public static void test3() throws InterruptedException {
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue<>(3);
        //put方法没返回值
        arrayBlockingQueue.put("a");
        arrayBlockingQueue.put("b");
        arrayBlockingQueue.put("c");
//        arrayBlockingQueue.put("d"); //队列没有位置了，会一直阻塞

        System.out.println(arrayBlockingQueue.take());
        System.out.println(arrayBlockingQueue.take());
        System.out.println(arrayBlockingQueue.take());
        System.out.println(arrayBlockingQueue.take()); //没有这个元素，也会一直阻塞等待新的元素进去
    }

    /**
     * 等待，阻塞，超时退出
     */
    public static void test4() throws InterruptedException {
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue<>(3);
        arrayBlockingQueue.offer("a");
        arrayBlockingQueue.offer("b");
        arrayBlockingQueue.offer("c");
//        arrayBlockingQueue.offer("d",2, TimeUnit.SECONDS); //等待超过2秒就退出

        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());
        arrayBlockingQueue.poll(2,TimeUnit.SECONDS); //等待超过2秒就退出
    }
}
