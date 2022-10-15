package idesginpattern;

/**
 * 工厂模式
 * 1.简单工厂
 * 2.工厂模式 Define an interface for creating an object, but let subclass decide
 * which class to instantiate.Factory Method lets a class defer instantiation to
 * subclass.
 * 3.抽象工厂模式 Provide an interface for creating families of related or dependent
 * objects without specifying their concrete classes.
 * 
 * 创建型模式通常用于要创建的实例很复杂的时候, 给的示例很难说明问题
 * 
 */

public class Factory {

}

// 简单工厂只是一种编码习惯
// 只是讲类规整了一下，貌似没啥用

interface Shape {
    void draw();
}

class CircleShape implements Shape {
    public CircleShape() {
        System.out.println("CircleShape: created");
    }

    @Override
    public void draw() {
        System.out.println("draw: CircleShape");
    }

}

class RectShape implements Shape {
    public RectShape() {
        System.out.println("RectShape: created");
    }

    @Override
    public void draw() {
        System.out.println("draw: RectShape");
    }
}

class ShapeFactory {
    public static Shape getShape(String type) {
        Shape shape = null;
        if (type.equalsIgnoreCase("circle")) {
            shape = new CircleShape();
        } else if (type.equalsIgnoreCase("rect")) {
            shape = new RectShape();
        }
        return shape;
    }
}

/**
 * 工厂模式 适用场景
 * 1. 对象的创建过程/实例化准备工作很复杂，需要初始化很多参数、查询数据库等。
 * 2. 类本身有好多子类，这些类的创建过程在业务中容易发生改变，或者对类的调用容易发生改变。
 * 类的创建在代码中有很多地方存在，如果变了需要在各处修改, 统一到工厂类中只改一个地方。
 */

/**
 * 抽象工厂模式
 * 
 * 工厂模式用来生成一个产品实例，那么抽象工厂就是生成一类产品族的实例
 */