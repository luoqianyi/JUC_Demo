package JucLearning.Synchronized;

import lombok.var;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
public class ProducerCustomerModel {
    final static int MAX = 10;

    /** 生产者队列 */
    LinkedList<Integer> queue = new LinkedList<>();

    ReentrantLock lock = new ReentrantLock();
    Condition full = lock.newCondition();
    Condition emtpy = lock.newCondition();


    /** 模拟生产者实际生产的过程 */
    int readData() throws InterruptedException {
        Thread.sleep((long) (Math.random() * 1000));
        return (int)Math.floor(Math.random());
    }



    /** 生产者 */
    public void readDb() throws InterruptedException {
        lock.lock();
        if (queue.size() == MAX) {
            full.await();
            return;
        }
        // 1s
        var data = readData();
        if(queue.size() == 1) {
            emtpy.signalAll();
        }
        queue.add(data);
        lock.unlock();
    }

    /** 消费者 */
    public void calculate() throws InterruptedException {

        lock.lock();
        if (queue.size() == 0) {
            emtpy.await();
            return;
        }
        var data = queue.remove();
        System.out.println("queue-size:" + queue.size());
        if(queue.size() == MAX - 1) {
            full.signalAll();
        }

        data *= 100;
        lock.unlock();
    }


    public static void main(String[] argv) {
        var p = new ProducerCustomerModel();
        // 开100个线程不断去生产
        for(int i = 0; i < 100; i++) {
            new Thread(() -> {
                while (true) {
                    try {
                        p.readDb();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        // 创建一个消费者不断去消费
        new Thread(() -> {
            while(true) {
                try {
                    p.calculate();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
