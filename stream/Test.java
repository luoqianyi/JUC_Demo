package JucLearning.stream;

/**
 * @author: LuoZhiKun
 * @date: 2022/02/09 周三
 */

import java.util.Comparator;
import java.util.stream.Stream;

/**
 *  面试题：一分钟内完成此题，只能用一行代码实现！
 *  现在有5个用户！按条件筛选：
 *  1.ID必须为偶数
 *  2.年龄必须大于23岁
 *  3.用户名转为大写字母
 *  4.用户名字母倒序排列
 *  5.只能输出一个用户！
 */
public class Test {
    public static void main(String[] args) {
        User u1  = new User(1,"a",21);
        User u2  = new User(2,"b",22);
        User u3  = new User(3,"c",23);
        User u4  = new User(4,"d",24);
        User u5  = new User(5,"e",26);

        Stream.of(u1,u2,u3,u4,u5).filter(u->u.getId()%2==0).filter(u->u.getAge()>23).map(u->u.getName().toUpperCase()).sorted(Comparator.reverseOrder()).forEach(System.out::println);
    }
}
