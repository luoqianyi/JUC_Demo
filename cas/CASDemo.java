package JucLearning.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: LuoZhiKun
 * @date: 2022/02/14 周一
 */
public class CASDemo {

    //CAS compareAndSet:比较和交换
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(2019);

        //对于我们平时的sql:乐观锁！

        //期望、更新
        //public final boolean compareAndSet(int expect,int update)
        //如果我期望的值达到了，那么就更新，否则就不更新，CAS是CPU的并发原语

        //这个是捣乱的线程
        System.out.println(atomicInteger.compareAndSet(2019, 2022)); //true
        System.out.println(atomicInteger.get());

        System.out.println(atomicInteger.compareAndSet(2022, 2019)); //true
        System.out.println(atomicInteger.get());

        //这个是期望的线程
        System.out.println(atomicInteger.compareAndSet(2019, 6666)); //true
        System.out.println(atomicInteger.get());

    }
}
