package JucLearning.single;

/**
 * @author: LuoZhiKun
 * @date: 2022/02/11 周五
 */
//静态内部类
public class HolderDemo {

    private HolderDemo(){

    }

    public static HolderDemo getInstance(){
        return InnerClass.HOLDER;
    }

    public static class InnerClass{
        private static final HolderDemo HOLDER = new HolderDemo();
    }
}
