package other.设计模式.structural.bridge.demo2;

/**
 * 某图像预览程序要求能够查看 BMP、JPEG 和GIF 三种格式的文件，而且能够在 Windows 和 Linux 两种操作系统上运行。
 * 程序需具有较好的扩展性以支持新的文件格式和操作系统。
 * 为满足上述需求并减少所需生成的子类数目，现采用桥接模式进行设计，得到如图6-1 所示的类图。
 */
public class BridgeDemo {

    public static void main(String[] args) {
        Image image = new GIFImage();
        Implementor imageImp = new LinuxImp();
        image.setImp(imageImp);
        image.parseFile("demo.gif");
    }

}

class Matrix { // 各种格式的文件最终都被转化为像素矩阵
    // 此处代码省略
}

abstract class Implementor {
    // 显示像素矩阵 m
    public void doPaint(Matrix m) {}
}

class WinImp extends Implementor {
    // 调用 Windows 系统的绘制函数绘制像素矩阵
    public void doPaint(Matrix m) {

    }
}

class LinuxImp extends Implementor {
    // 调用 Linux 系统的绘制函数绘制像素矩阵
    public void doPaint(Matrix m) {
    }
}

abstract class Image {
    protected Implementor imp;

    public void setImp(Implementor imp) {
        this.imp = imp;
    }

    public abstract void parseFile(String fileName);
}

class BMPImage extends Image {
    @Override
    public void parseFile(String fileName) {
        // 此处代码省略
    }
}

class GIFImage extends Image {
    @Override
    public void parseFile(String fileName) {
        Matrix m = new Matrix(); // 解析 BMP 文件并获得一个像素矩阵对象 （此处用 new Matrix() 模拟）
        imp.doPaint(m);
    }
}