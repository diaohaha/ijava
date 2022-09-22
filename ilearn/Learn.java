package ilearn;

import java.util.Arrays;
import java.util.stream.Stream;

public class Learn {
    public static void main(String[] args) {
        iLambda l = new iLambda();
        l.Test();

        iStream i = new iStream();
        i.Test();
    }
}

class iLambda {
    public void Test() {
        String[] array = { "a", "2" };
        System.out.println(String.join(",", array));
        // Arrays.sort(array, new Comparator<String>() {
        // public int compare(String s1, String s2) {
        // return s1.compareTo(s2);
        // }
        // });

        // super关键字 1.super(args) 调用父类构造函数 2. super.func() 调用父类方法

        // lambda 可以替换 Compartor<? super T>
        Arrays.sort(array, (s1, s2) -> {
            return s1.compareTo(s2);
        });
        System.out.println(String.join(",", array));

        // 直接使用方法签名
        array = new String[] { "Apple", "Orange", "Banana", "Lemon" };
        Arrays.sort(array, String::compareTo);
        System.out.println(String.join(", ", array));

    }
}

class iStream {
    // stream 类似 list 但list可能存在内存中，但是stream没有; 用来实现惰性计算

    // 函数式编程的另一个特点就是链式写法。

    /**
     * 转换操作：map()，filter()，sorted()，distinct()；
     * 合并操作：concat()，flatMap()；
     * 并行处理：parallel()；
     * 聚合操作：reduce()，collect()，count()，max()，min()，sum()，average()；
     */

    public void Test() {
        // Stream<Integer> nums = Stream.of(1, 2, 3, 4).map(iStream::idouble).sum();
        Integer s = Stream.of(1, 2, 3, 4).mapToInt(iStream::idouble).sum();
        System.out.print("sum is");
        System.out.println(s);
    }

    public static Integer idouble(Integer i) {
        return 2 * i;
    }
}