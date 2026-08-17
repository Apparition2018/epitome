package knowledge.api.swing;

import javax.swing.filechooser.FileSystemView;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * FileSystemView
 *
 * @author ljh
 * @since 2022/4/5 0:42
 */
public class FileSystemViewDemo {

    public static void main(String[] args) {
        FileSystemView fileSystemView = FileSystemView.getFileSystemView();
        assertThat(fileSystemView.getHomeDirectory().getPath()).isEqualTo("C:\\Users\\Administrator\\Desktop");
    }
}
