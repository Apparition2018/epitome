package knowledge.api.swing;

import javax.swing.filechooser.FileSystemView;

import static l.demo.Demo.ae;

/**
 * FileSystemView
 *
 * @author ljh
 * @since 2022/4/5 0:42
 */
public class FileSystemViewDemo {

    public static void main(String[] args) {
        FileSystemView fileSystemView = FileSystemView.getFileSystemView();
        ae(fileSystemView.getHomeDirectory().getPath(), "C:\\Users\\Administrator\\Desktop");
    }
}
