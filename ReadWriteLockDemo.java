package JucLearning;

/**
 * @author: LuoZhiKun
 * @date: 2022/02/08 周二
 */

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.IntStream;

/**
 * 独占锁（写锁）：一次只能被一个线程占有
 * 共享锁（读锁）：多个线程可以同时占有
 * ReadWriteLock
 * 读 - 读 ： 可以共存！
 * 读 - 写 ： 不能共存！
 * 写 - 写 ： 不能共存！
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyNoLockCache myNoLockCache = new MyNoLockCache();
        MyLockCache myLockCache = new MyLockCache();
        MyReadWriteLockCache myReadWriteLockCache = new MyReadWriteLockCache();
        IntStream.range(1,4).forEach(
                i -> new Thread(()->{
//                    myNoLockCache.put(i,i);
//                    myLockCache.put(i,i);
                    myReadWriteLockCache.put(i,i);
                },String.valueOf(i)).start()
        );
        IntStream.range(1,4).forEach(
                i -> new Thread(()->{
//                    myNoLockCache.get(i);
//                    myLockCache.get(i);
                    myReadWriteLockCache.get(i);
                },String.valueOf(i)).start()
        );
    }
}
//自定义缓存
class MyNoLockCache{
    private Map<Integer,Integer> map = new HashMap<>();
    //写
    public void put(Integer key,Integer value){
        System.out.println(Thread.currentThread().getName()+"写入"+key);
        map.put(key,value);
        System.out.println(Thread.currentThread().getName()+"写入完成!");
    }
    //读
    public void get(Integer key){
        System.out.println(Thread.currentThread().getName()+"读出"+key);
        map.get(key);
        System.out.println(Thread.currentThread().getName()+"读出完成!");
    }
}
class MyLockCache{
    private Map<Integer,Integer> map = new HashMap<>();
    private Lock lock = new ReentrantLock();
    //写
    public void put(Integer key,Integer value){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"写入"+key);
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"写入完成!");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    //读
    public void get(Integer key){
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getName()+"读出"+key);
            map.get(key);
            System.out.println(Thread.currentThread().getName()+"读出完成!");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
class MyReadWriteLockCache{
    private Map<Integer,Integer> map = new HashMap<>();
    //读写锁是更细粒度的
    private ReadWriteLock lock = new ReentrantReadWriteLock();
    //写,写入的时候，只希望同时只有一个线程写
    public void put(Integer key,Integer value){
        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"写入"+key);
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"写入完成!");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.writeLock().unlock();
        }
    }
    //读，所有人都可以读！
    public void get(Integer key){
        lock.readLock().lock();
        try{
            System.out.println(Thread.currentThread().getName()+"读出"+key);
            map.get(key);
            System.out.println(Thread.currentThread().getName()+"读出完成!");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.readLock().unlock();
        }
    }
}