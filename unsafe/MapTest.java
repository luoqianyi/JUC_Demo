package JucLearning.unsafe;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.IntStream;

/**
 * @author: LuoZhiKun
 * @date: 2022/02/07 周一
 */
public class MapTest {
    public static void main(String[] args) {
        Map<String,Integer> map = new ConcurrentHashMap<>();
        IntStream.range(1,10).forEach(
                i->new Thread(()->{
                    map.put(String.valueOf(i),i);
                    System.out.println(map);
                },String.valueOf(i)).start()
        );
    }
}
