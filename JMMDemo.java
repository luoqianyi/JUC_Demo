package JucLearning;

import java.util.concurrent.TimeUnit;

/**
 * @author: LuoZhiKun
 * @date: 2022/02/10 周四
 */
public class JMMDemo {
//    private volatile static int num = 0;  //加入volatile使得num可见
    private static int num = 0;
    public static void main(String[] args) { //main主线程
        new Thread(()->{ //线程1 对主内存的变化不知道
            while(num == 0){

            }
        }).start();

        try{
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        num = 1; //main线程修改了值，但是线程1不知道，所以程序一直不结束
        System.out.println(num);
    }
}
