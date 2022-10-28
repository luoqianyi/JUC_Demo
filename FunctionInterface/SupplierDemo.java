package JucLearning.FunctionInterface;

/**
 * @author: LuoZhiKun
 * @date: 2022/02/09 周三
 */

import java.util.function.Supplier;

/**
 *  Supplier 供给型接口
 */
public class SupplierDemo {
    public static void main(String[] args) {
//        Supplier<String> supplier = new Supplier() {
//            @Override
//            public String get() {
//                return "Hello World";
//            }
//        };
        Supplier<String> supplier = ()->"Hello World";
        System.out.println(supplier.get());

    }
}
