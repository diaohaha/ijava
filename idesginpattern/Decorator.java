package idesginpattern;

/**
 * 装饰器
 * 动态地给一个对象添加一些额外的职责
 * 
 * python里的装饰器提供了语法糖
 */

public class Decorator {

}

interface Component {
    public void sampleOpreation();
}

class ConcreteComponent implements Component {
    @Override
    public void sampleOpreation() {
        // TODO 完成相关的业务代码
    }
}

class IDecorator implements Component {
    private Component component;

    public IDecorator(Component component) {
        this.component = component;
    }

    @Override
    public void sampleOpreation() {
        // 委派给构件 动态的扩展了函数
        component.sampleOpreation();
    }

}

class ConcreteDecoratorA extends IDecorator {
    public ConcreteDecoratorA(Component component) {
        super(component);
    }

    @Override
    public void sampleOpreation() {
        super.sampleOpreation();
        // TODO 完成相关的业务代码
    }
}