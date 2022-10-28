package JucLearning.FunctionInterface;

/**
 * @author: LuoZhiKun
 * @date: 2022/02/09 周三
 */

import java.util.function.Function;

/**
 * Function 函数型接口
 */
public class FunctionDemo {
    public static void main(String[] args) {
        //工具类：输出输入的值
//        Function<String, String> function = new Function<String,String>() {
//            @Override
//            public String apply(String str) {
//                return str;
//            }
//        };
        Function<String,String> function = (str)-> str;
        System.out.println(function.apply("你好啊！"));
    }
}
