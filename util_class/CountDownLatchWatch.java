package JucLearning.util_class;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 实现一个容器，提供两个方法，add，size 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到5个时，线程2给出提示并结束?
 */
public class CountDownLatchWatch {
    public static void main(String[] args) {
        MyCollection<Integer> myCollection = new MyCollection<>();
        CountDownLatch latch = new CountDownLatch(1);
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+" start");
            if (myCollection.size()!=5){
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName()+" end");
        }).start();
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+" start");
            for (int i = 1; i <= 10; i++) {
                myCollection.add(i);
                if(myCollection.size()==5){
                    latch.countDown();
                }
                System.out.println("add "+i);
            }
            System.out.println(Thread.currentThread().getName()+" end");
        }).start();

    }

    static class MyCollection<T>{
        volatile List<T> list=new ArrayList<>();
        public void add(T i){
            list.add(i);
        }
        public int size(){
            return list.size();
        }
    }
}


