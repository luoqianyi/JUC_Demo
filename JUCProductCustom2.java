package JucLearning;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: LuoZhiKun
 * @date: 2022/02/07 周一
 */
//A->B,B->C,C->D,D->A
public class JUCProductCustom2 {
    public static void main(String[] args) {
        Data3 data = new Data3();
        new Thread(()->{ for (int i = 0; i < 10; i++) data.printA(); },"A").start();
        new Thread(()->{ for (int i = 0; i < 10; i++) data.printB(); },"B").start();
        new Thread(()->{ for (int i = 0; i < 10; i++) data.printC(); },"C").start();
        new Thread(()->{ for (int i = 0; i < 10; i++) data.printD(); },"D").start();
    }
}
//资源类Lock
class Data3{
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();
    private Condition condition4 = lock.newCondition();

    private int number = 1; //1A 2B 3C

    public void printA(){
        lock.lock();
        try{
            //业务，判断->执行->通知
            while(number != 1){
                //等待
                condition1.await();
            }
            System.out.println(Thread.currentThread().getName()+"=>"+number);
            //唤醒，唤醒指定的人，B
            number = 2;
            condition2.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void printB(){
        lock.lock();
        try{
            //业务，判断->执行->通知
            while(number != 2){
                //等待
                condition2.await();
            }
            System.out.println(Thread.currentThread().getName()+"=>"+number);
            //唤醒，唤醒指定的人，B
            number = 3;
            condition3.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void printC(){
        lock.lock();
        try{
            //业务，判断->执行->通知
            while(number != 3){
                //等待
                condition3.await();
            }
            System.out.println(Thread.currentThread().getName()+"=>"+number);
            //唤醒，唤醒指定的人，B
            number = 4;
            condition4.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void printD(){
        lock.lock();
        try{
            //业务，判断->执行->通知
            while(number != 4){
                //等待
                condition4.await();
            }
            System.out.println(Thread.currentThread().getName()+"=>"+number);
            //唤醒，唤醒指定的人，B
            number = 1;
            condition1.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}