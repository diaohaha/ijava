package icollection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

// 集合测试

/**
 * 1. List是一个接口，不可以被构造；Arrlist是一个类 实现了List的方法
 * 2. java里的==比较的是指针?
 * 3. 没有类似func def这张的函数关键字。 默认就当作是函数处理
 */

public class Ilist {
    public static void main(String[] args) {
        ListTest lt = new ListTest();
        // lt.simpleOper();
        lt.mapOper();
    }
}

class ListTest {
    public void simpleOper() {
        List<Integer> l = new ArrayList<Integer>();
        l.add(1);
        var a = l.get(0);
        l.remove(0);
        System.out.println(String.format("i get %d", a));
        boolean isContain = l.contains(1);
        System.out.println(String.format("is contain %s", isContain));

        String s = "ddd";
        s.equals("ddd");
    }

    public void mapOper() {
        Map<String, Integer> scoreMap = new HashMap<>();
        // scoreMap["bob"] = 100;
        scoreMap.put("bob", 100);
        Integer bobScore = scoreMap.get("bob");
        System.out.println(String.format("bob score is: %d", bobScore));

        scoreMap.put("alice", 98);

        // travel key
        for (String key : scoreMap.keySet()) {
            System.out.println(String.format("%s score is: %d", key, scoreMap.get(key)));
        }

        // travel key val
        for (Map.Entry<String, Integer> entry : scoreMap.entrySet()) {
            System.out.println(String.format("%s score is: %d", entry.getKey(), entry.getValue()));
        }

        // test get empty // get null
        System.out.println(scoreMap.get("james"));

        // test string ==
        String s1 = "string";
        String s2 = new String("string");
        System.out.println(s1 == s2);
        System.out.println(s1.equals(s2));

        // enumMap 可以节约点空间

        // treeMap 有序Map key O需要实现Campare接口 ()
        // 也就是参数是一个Compareable?
        Map<Person, Integer> ageMap = new TreeMap<>();
        Person bob = new Person("bob");
        Person alice = new Person("alice");
        ageMap.put(bob, 12);
        ageMap.put(alice, 13);
    }

    public void setOper() {
        Set<String> set = new HashSet<>();
        set.add("red");
        set.add("black");
        set.add("white");
        set.add("red");

        set.remove("black");
        set.contains("red");
        set.size();
    }

    public void iteratorTest() {
        // 迭代器 需要实现Iterator方法
    }
}

// java的泛型可真复杂 老老实实写一遍多好
class Person implements Comparable<Person> {
    public String name;

    // construct
    Person(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Person o) {
        return this.name.compareTo(o.name);
    }
}