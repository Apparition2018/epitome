package knowledge.design.pattern.gof.structural.bridge;

/**
 * 桥接模式
 *
 * @author ljh
 * @since 2020/11/23 19:38
 */
public class BridgeDemo2 {

    /**
     * 某图像预览程序要求能够查看 BMP、JPEG、GIF 三种格式的文件，而且能够在 Windows 和 Linux 两种操作系统上运行。程序需具有较好的扩展性以支持新的文件格式和操作系统。
     * <pre>
     * 1 抽象-图片
     * 2 实现-操作系统
     * </pre>
     */
    public static void main(String[] args) {
        Image image = new GIFImage();
        image.setOs(new Linux());
        image.parseFile("demo.gif");
    }

    /**
     * 各种格式的文件最终都被转化为像素矩阵
     */
    private static class Matrix {
        // 此处代码省略
    }

    /**
     * Implementor
     */
    private static abstract class OS {
        // 绘制像素矩阵
        private void doPaint(Matrix m) {
        }
    }

    /**
     * ConcreteImplementor
     */
    private static class Windows extends OS {
        public void doPaint(Matrix m) {
        }
    }

    /**
     * ConcreteImplementor
     */
    private static class Linux extends OS {
        public void doPaint(Matrix m) {
        }
    }

    /**
     * Abstraction
     */
    private static abstract class Image {
        protected OS os;

        private void setOs(OS os) {
            this.os = os;
        }

        protected abstract void parseFile(String fileName);
    }

    /**
     * RefinedAbstraction
     */
    private static class GIFImage extends Image {
        @Override
        public void parseFile(String fileName) {
            // 解析 GIF 文件绘制像素矩阵，此处用 new Matrix() 模拟
            Matrix m = new Matrix();
            os.doPaint(m);
        }
    }

    /**
     * RefinedAbstraction
     */
    private static class BMPImage extends Image {
        @Override
        public void parseFile(String fileName) {
            Matrix m = new Matrix();
            os.doPaint(m);
        }
    }
}
