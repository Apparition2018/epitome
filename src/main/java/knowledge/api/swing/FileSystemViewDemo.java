package knowledge.api.swing;

import javax.swing.filechooser.FileSystemView;

/**
 * FileSystemViewDemo
 *
 * @author ljh
 * created on 2022/4/5 0:42
 */
public class FileSystemViewDemo {

    public static void main(String[] args) {
        FileSystemView fileSystemView = FileSystemView.getFileSystemView();
        System.out.println(fileSystemView.getHomeDirectory()); // C:\Users\Administrator\Desktop
    }
}
