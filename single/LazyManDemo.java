package JucLearning.single;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author: LuoZhiKun
 * @date: 2022/02/11 周五
 */
//懒汉式单例模式
public class LazyManDemo {
    private LazyManDemo(){
        synchronized (LazyManDemo.class){
            if(lazyMan!=null){
                throw new RuntimeException("不要试图使用反射破坏异常！");
            }
        }
        System.out.println(Thread.currentThread().getName()+"ok");
    }

    private static LazyManDemo lazyMan;

    //双重检测锁模式单例
    public static LazyManDemo getInstance(){
        if(lazyMan==null){
            synchronized (LazyManDemo.class){
                if(lazyMan==null){
                    lazyMan = new LazyManDemo(); //不是一个原子性操作
                    /**
                     * 1. 分配内存空间
                     * 2. 执行构造方法，初始化对象
                     * 3、把这个对象指向这个空间
                     *
                     */
                }
            }
        }
        return lazyMan;
    }

    //多线程并发
//    public static void main(String[] args) {
//        for (int i = 0; i < 10; i++) {
//            new Thread(()->{
//                LazyManDemo.getInstance();
//            }).start();
//        }
//    }

    //反射！
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        LazyManDemo instance = LazyManDemo.getInstance();
        Constructor<LazyManDemo> declaredConstructor = LazyManDemo.class.getDeclaredConstructor(null);
        declaredConstructor.setAccessible(true);
        LazyManDemo instance1 = declaredConstructor.newInstance();
        System.out.println(instance);
        System.out.println(instance1);

    }

}
