package JucLearning.single;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author: LuoZhiKun
 * @date: 2022/02/14 周一
 */
public enum EnumSingleton {
    INSTANCE;

    public EnumSingleton getInstance(){
        return INSTANCE;
    }
}

class Test{
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        EnumSingleton enumSingleton = EnumSingleton.INSTANCE;
        Constructor<EnumSingleton> declaredConstructor = EnumSingleton.class.getDeclaredConstructor(String.class,Integer.class);
        declaredConstructor.setAccessible(true);
        EnumSingleton enumSingleton1 = declaredConstructor.newInstance();
        System.out.println(enumSingleton);
        System.out.println(enumSingleton1);
    }
}
