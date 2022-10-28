package JucLearning;

/**
 * @author: LuoZhiKun
 * @date: 2022/02/07 周一
 */
public class ProductCustom {
    public static void main(String[] args) {
        Data data = new Data();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.desc();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.desc();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"D").start();
    }
}
//判断等待 业务 通知
class Data{
    private int num = 0;

    //+1
    public synchronized void incr() throws InterruptedException {
        while (num!=0){
            //等待
            this.wait();
        }
        //业务
        num++;
        System.out.println(Thread.currentThread().getName()+"=>"+num);
        //通知其它线程，我业务完成+1了
        this.notifyAll();

    }

    //-1
    public synchronized void desc() throws InterruptedException {
        while (num==0){
            //等待
            this.wait();
        }
        //业务
        num--;
        System.out.println(Thread.currentThread().getName()+"=>"+num);
        //通知其它线程，我业务完成-1了
        this.notifyAll();
    }
}
