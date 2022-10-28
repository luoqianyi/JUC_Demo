package JucLearning;

import java.util.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    //结果值
    public static AtomicInteger r_num = new AtomicInteger(0);
    //线程数
    public static int t_num = 0;
    //需要打印的数值
    public static int print_num = 0;
    //信号量为1，严格限制只有1个线程输出
    public static final Semaphore sem = new Semaphore(1);

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //t_num:创建的线程数
        t_num = sc.nextInt();
        //print_num:需要打印出来的数字
        print_num = sc.nextInt();
        for (int i = 0; i < t_num; i++) {
            new Thread(new Task(i),"Thread-"+i).start();
        }
    }

    static class Task implements Runnable {
        //线程绑定的序号
        private final int t_index;
        public Task(int t_index) {
            this.t_index = t_index;
        }
        public void run() {
            while (r_num.get() < print_num) {
                if (((r_num.get() % t_num) == t_index) && (r_num.get() < print_num)) {
                    try {
                        sem.acquire();
                        System.out.println(Thread.currentThread().getName() +
                                "-" + r_num);
                        r_num.getAndAdd(1);
                    } catch (Exception e) {
                        //
                    } finally {
                        sem.release();
                    }
                }
            }
        }
    }
}