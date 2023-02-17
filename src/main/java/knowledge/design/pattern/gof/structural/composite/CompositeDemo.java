package knowledge.design.pattern.gof.structural.composite;

import lombok.AllArgsConstructor;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.IntStream;

/**
 * 组合模式：又称整体-部分模式 (Part-Whole)，将对象组合成树状层次结构的模式，对单个对象和组合对象的使用具有一致性
 * <p>使用场景：树结构、整体-部分
 * <p>使用实例：
 * <pre>
 * 1 {@link HashMap}
 * 2 {@link Container#add(Component)}
 * 3 WebMvcConfigurerComposite 和 @{@link WebMvcConfigurer}
 * </pre>
 * 角色：
 * <pre>
 * 抽象组件 Component：定义通用方法
 *    透明模式：声明访问和管理子类的接口，如 add(), remove()，getChild() 等
 *    安全模式：不声明访问和管理子类的接口，管理工作由 Composite 完成
 * 树叶组件 Leaf：实现 Component，没有子节点
 * 组合组件 Composite/Container：实现 Component，持有子节点集合的引用，循环调用子节点通用方法
 * </pre>
 * 优点：符合开闭原则
 * <p>扩展：使用 Builder 创建复杂 Composite
 * <p>参考：
 * <pre>
 * <a href="https://refactoringguru.cn/design-patterns/composite">Composite</a>
 * <a href="http://c.biancheng.net/view/1373.html">Java设计模式</a>
 * </pre>
 *
 * @author ljh
 * @since 2020/9/26 2:51
 */
public class CompositeDemo {

    /**
     * <a href="http://c.biancheng.net/view/8474.html">文件系统 (安全模式)</a>
     */
    public static void main(String[] args) {
        Folder folder1 = new Folder("LJH", 1);
        Folder folder2 = new Folder("Exe", 2);
        Folder folder3 = new Folder("Media", 2);
        Folder folder4 = new Folder("Image", 3);
        Folder folder5 = new Folder("Video", 3);

        File file1 = new ExeFile("Word.exe");
        File file2 = new ExeFile("Excel.exe");
        File file3 = new ImageFile("Naruto.jpg");
        File file4 = new ImageFile("Sasuke.jpg");
        File file5 = new VideoFile("Demon Slayer.mp4");

        folder1.add(folder2, folder3);
        folder3.add(folder4, folder5);
        folder2.add(file1, file2);
        folder4.add(file3, file4);
        folder5.add(file5);

        folder1.show();
    }

    /**
     * Component
     */
    private static abstract class File {
        protected abstract void show();
    }

    /**
     * Leaf
     */
    @AllArgsConstructor
    private static class ImageFile extends File {
        private final String name;

        @Override
        public void show() {
            System.out.println("  图片 [" + name + "]");
        }
    }

    /**
     * Leaf
     */
    @AllArgsConstructor
    private static class ExeFile extends File {
        private final String name;

        @Override
        public void show() {
            System.out.println("  应用程序 [" + name + "]");
        }
    }

    /**
     * Leaf
     */
    @AllArgsConstructor
    private static class VideoFile extends File {
        private final String name;

        @Override
        public void show() {
            System.out.println("  视频 [" + name + "]");
        }
    }

    /**
     * Composite/Container
     */
    @AllArgsConstructor
    private static class Folder extends File {
        private final List<File> filesList = new ArrayList<>();
        private final String name;
        private final Integer level;

        public void add(File... file) {
            filesList.addAll(List.of(file));
        }

        public void remove(File file) {
            filesList.remove(file);
        }

        public File getChild(int i) {
            return filesList.get(i);
        }

        @Override
        public void show() {
            System.out.println("+ 【" + name + "】");
            for (File file : filesList) {
                if (this.level != null) {
                    IntStream.rangeClosed(1, level).forEach(i -> System.out.println("  "));
                }
                file.show();
            }
        }
    }
}
