package JucLearning.util_class;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ThreadLocalDemo {
    static class DataUtils{
        public static final ThreadLocal<DateFormat> df = new ThreadLocal<DateFormat>(){
            @Override
            protected DateFormat initialValue() {
                return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            }
        };
    }
    public static void main(String[] args) {
        System.out.println(DataUtils.df.get().format(new Date()));
    }
}
