package JucLearning.single;

/**
 * @author: LuoZhiKun
 * @date: 2022/02/11 周五
 */
//饿汉式
public class HungryDemo {

    private byte[] data1 = new byte[1024*1024];
    private byte[] data2 = new byte[1024*1024];
    private byte[] data3 = new byte[1024*1024];
    private byte[] data4 = new byte[1024*1024];


    private  HungryDemo(){

    }
    private final static HungryDemo HUNGRY = new HungryDemo();

    public static HungryDemo getInstance(){
        return HUNGRY;
    }
}
