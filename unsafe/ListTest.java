package JucLearning.unsafe;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author: LuoZhiKun
 * @date: 2022/02/07 周一
 */
//java.util.ConcurrentModificationException 并发修改异常！
public class ListTest {
    public static void main(String[] args) {
        //并发下ArrayList 不安全的
        /**
         * 解决方案：
         *  1.List<String> list = new Vector<>(); //早就有了，还是使用了synchronized锁
         *  2.List<String> list = Collections.synchronizedList(new ArrayList<>());
         *  3.List<String> list = new CopyOnWriteArrayList<>();
         */
        //CopyOnWrite 写入时复制 COW 计算机程序设计领域的一种优化策略
        //多个线程调用的时候，List，读取的时候，固定的，写入（覆盖）
        //在写入的时候避免覆盖，造成数据问题！
        //读写分离
        //CopyOnWriteArrayList比Vector牛逼在哪？ 看看它的add底层方法就知道它用的是Lock锁，而不是Synchronized锁，更高效
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 1; i <= 10; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(list);
            },i+"").start();
        }
    }
}
