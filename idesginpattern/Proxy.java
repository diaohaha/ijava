package idesginpattern;

/**
 * 代理模式
 * 为其他对象提供一种代理以控制对这个对象的访问
 * 
 * 代理模式加强控制； 适配器增强功能
 * 
 * 使用场景: 1、远程代理。 2、虚拟代理。 3、Copy-on-Write 代理。 4、保护（Protect or Access）代理。
 * 5、Cache代理。 6、防火墙（Firewall）代理。 7、同步化（Synchronization）代理。 8、智能引用（Smart
 * Reference）代理。
 */

public class Proxy {
    public static void main(String[] args) {
        Image image = new ProxyImage("xxx");
        // 图像将从磁盘加载
        image.display();
        // 图像不需要从磁盘加载
        image.display();
    }
}

interface Image {
    void display();
}

class RealImage implements Image {

    private String fileName;

    public RealImage(String fileName) {
        this.fileName = fileName;
        loadFromDisk(fileName);
    }

    @Override
    public void display() {
        System.out.println("Displaying " + fileName);
    }

    private void loadFromDisk(String fileName) {
        System.out.println("Loading " + fileName);
    }
}

class ProxyImage implements Image {

    // 这看着很像单例模式呢
    private RealImage realImage;
    private String fileName;

    public ProxyImage(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void display() {
        // 这里会有一些权限记录的代码
        if (realImage == null) {
            realImage = new RealImage(fileName);
        }
        realImage.display();
    }
}