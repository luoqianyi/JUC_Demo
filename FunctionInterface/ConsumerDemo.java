package JucLearning.FunctionInterface;

/**
 * @author: LuoZhiKun
 * @date: 2022/02/09 周三
 */

import java.util.function.Consumer;

/**
 * Consumer 消费型接口 ：只有输入，没有返回值
 */
public class ConsumerDemo {
    public static void main(String[] args) {
//        Consumer<String> consumer = new Consumer<String>() {
//            @Override
//            public void accept(String s) {
//                System.out.println(s);
//            }
//        };
//        Consumer<String> consumer = (str) -> System.out.println(str);
        Consumer<String> consumer = System.out::println;
        consumer.accept("你好");
    }
}
