package idesginpattern;

/**
 * 单例模式
 * 定义：一个类全局只有一个实例; 频繁使用的类节约系统资源
 * 使用场景：
 * 1.生产唯一序列号
 * 2.计数器
 * 3.创建对象消耗资源过多
 */

public class Singleton {

}

class SingleObject {
    // 构造函数为private 类就不会实例化
    private SingleObject() {

    }

    // 类变量
    private static SingleObject ins = new SingleObject();

    public SingleObject getInstance() {
        return ins;
    }
}