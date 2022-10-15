package idesginpattern;

/**
 * 建造者模式
 * 定义：负责对象一步一步构造
 * 使用场景：
 * 1.生成的对象复杂 2.内部属性相互依赖
 * 
 * es的QueryBuilder
 * golang的 RequestBuilder
 * builder.RequestBuilder().WithUuid("uuid").WithToken("token").WithMsg("构建者模式").Create()
 */

public class Builder {
    public static void main(String[] args) {
        Computer computer = new Computer.Builder("因特尔", "三星")
                .setDisplay("三星24寸")
                .setKeyboard("罗技")
                .setUsbCount(2)
                .build();
    }

}

class Computer {
    private final String cpu;// 必须
    private final String ram;// 必须
    private final int usbCount;// 可选
    private final String keyboard;// 可选
    private final String display;// 可选

    // 构造函数接收build 来支持链式写法
    private Computer(Builder builder) {
        this.cpu = builder.cpu;
        this.ram = builder.ram;
        this.usbCount = builder.usbCount;
        this.keyboard = builder.keyboard;
        this.display = builder.display;
    }

    public static class Builder {
        private String cpu;// 必须
        private String ram;// 必须
        private int usbCount;// 可选
        private String keyboard;// 可选
        private String display;// 可选

        public Builder(String cup, String ram) {
            this.cpu = cup;
            this.ram = ram;
        }

        public Builder setUsbCount(int usbCount) {
            this.usbCount = usbCount;
            return this;
        }

        public Builder setKeyboard(String keyboard) {
            this.keyboard = keyboard;
            return this;
        }

        public Builder setDisplay(String display) {
            this.display = display;
            return this;
        }

        public Computer build() {
            return new Computer(this);
        }
    }
    // 省略getter方法
}
