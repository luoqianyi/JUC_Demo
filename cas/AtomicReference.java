package JucLearning.cas;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author: LuoZhiKun
 * @date: 2022/02/14 周一
 */
//原子引用解决ABA问题
public class AtomicReference {
    public static void main(String[] args) {
        //AtomicStampedReference 注意，如果泛型是一个包装类，注意对象的引用问题
        //正常在业务操作，这里面比较的都是一个对象
        AtomicStampedReference<Integer> integerAtomicStampedReference = new AtomicStampedReference<>(1, 1);

//        new Thread(()->{
//            int stamp = integerAtomicStampedReference.getStamp();
//            System.out.println("a1=>"+stamp);
//            try {
//                TimeUnit.SECONDS.sleep(2);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("a2 info=>"+integerAtomicStampedReference.compareAndSet(1,2,stamp,stamp+1));
//            System.out.println("a2=>"+integerAtomicStampedReference.getStamp());
//
//            System.out.println("a3 info=>"+integerAtomicStampedReference.compareAndSet(2,1,integerAtomicStampedReference.getStamp(),integerAtomicStampedReference.getStamp()+1));
//            System.out.println("a3=>"+integerAtomicStampedReference.getStamp());
//
//        },"A").start();
//
//        new Thread(()->{
//            int stamp = integerAtomicStampedReference.getStamp();
//            System.out.println("b1=>"+stamp);
//            try {
//                TimeUnit.SECONDS.sleep(2);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("b2 info=>"+integerAtomicStampedReference.compareAndSet(1,6,stamp,stamp+1));
//            System.out.println("b2=>"+integerAtomicStampedReference.getStamp());
//        },"B").start();

        System.out.println(integerAtomicStampedReference.compareAndSet(1, 2,1,2)); //true
        System.out.println(integerAtomicStampedReference.getStamp());

        System.out.println(integerAtomicStampedReference.compareAndSet(2, 1,2,3)); //true
        System.out.println(integerAtomicStampedReference.getStamp());

        //这个是期望的线程
        System.out.println(integerAtomicStampedReference.compareAndSet(1, 6,1,2)); //true
        System.out.println(integerAtomicStampedReference.getStamp());
    }
}
