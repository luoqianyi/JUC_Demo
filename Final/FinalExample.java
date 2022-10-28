package JucLearning.Final;

/**
 * 演示final底层原理
 */
public class FinalExample {
    int i; //普通变量
    //final int j; // final变量
    static FinalExample obj;


    public void FinalExample() {  //构造函数
        i = 1; //写普通域
      //  j = 2; //写final域
    }
    public static void writer(){
      //  obj = new FinalExample(j);
    }
    public static void reader(){
        FinalExample object = obj;  //读对象引用
        int a = object.i; //读普通域
     //   int b = object.j; //读final域
    }
}
