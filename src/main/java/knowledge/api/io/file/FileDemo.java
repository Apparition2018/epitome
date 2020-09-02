package knowledge.api.io.file;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

/**
 * File
 * <p>
 * boolean	canRead()                   测试应用程序是否可以读取此抽象路径名表示的文件
 * boolean	canWrite(                   测试应用程序是否可以修改此抽象路径名表示的文件
 * boolean	setReadOnly()               标记此抽象路径名指定的文件或目录，从而只能对其进行读操作
 * <p>
 * boolean	exists()                    测试此抽象路径名表示的文件或目录是否存在
 * long	    length()                    返回由此抽象路径名表示的文件的长度
 * String	getName()                   返回由此抽象路径名表示的文件或目录的名称
 * <p>
 * boolean	isDirectory()               测试此抽象路径名表示的文件是否是一个目录
 * boolean	isFile()                    测试此抽象路径名表示的文件是否是一个标准文件
 * boolean	isHidden()                  测试此抽象路径名指定的文件是否是一个隐藏文件
 * <p>
 * long	    lastModified()              返回此抽象路径名表示的文件最后一次被修改的时间
 * boolean	setLastModified(long time)  设置此抽象路径名指定的文件或目录的最后一次修改时间
 */
public class FileDemo {

    private final static String dirPath = "src/main/java/knowledge/api/io/file/";

    /**
     * File(File parent, String child)
     * 根据 parent 抽象路径名和 child 路径名字符串创建一个新 File 实例。
     * <p>
     * File(String pathname)
     * 通过将给定路径名字符串转换为抽象路径名来创建一个新 File 实例。
     * <p>
     * File(String parent, String child)
     * 根据 parent 路径名字符串和 child 路径名字符串创建一个新 File 实例。
     * <p>
     * File(URI uri)
     * 通过将给定的 file: URI 转换为一个抽象路径名来创建一个新的 File 实例
     */
    @Test
    public void File() {
    }

    /**
     * int	compareTo(File pathname)
     * 按字母顺序比较两个抽象路径名
     */
    @Test
    public void compareTo() {
        File file1 = new File("C:/File/demo.txt");
        File file2 = new File("C:/File/demo");
        if (file1.compareTo(file2) == 0) {
            p("文件路径一致！");
        } else {
            p("文件路径不一致！");
        }
    }

    /**
     * boolean	createNewFile()
     * 当且仅当不存在具有此抽象路径名指定名称的文件时，不可分地创建一个新的空文件
     */
    @Test
    public void createNewFile() throws IOException {
        File file = new File(dirPath + "File.txt");
        if (!file.exists()) {
            boolean b = file.createNewFile();
            if (b) {
                p("文件创建完毕！");
            } else {
                p("文件创建失败！");
            }
        } else {
            p("文件已存在！");
        }
    }

    /**
     * static File	createTempFile(String prefix, String suffix[, File directory])
     * 在指定目录中创建一个新的空文件，使用给定的前缀和后缀字符串生成其名称
     */
    @Test
    public void createTempFile() throws IOException, InterruptedException {
        File temp = File.createTempFile("tmp", ".txt", new File("C:/"));
        Thread.sleep(5000);
        temp.deleteOnExit();
    }

    /**
     * void	deleteOnExit()
     * 在虚拟机终止时，请求删除此抽象路径名表示的文件或目录
     */
    @Test
    public void deleteOnExit() throws IOException, InterruptedException {
        createTempFile();
    }

    /**
     * boolean	mkdir()
     * 创建此抽象路径名指定的目录
     * <p>
     * boolean	mkdirs()
     * 创建此抽象路径名指定的目录，包括所有必需但不存在的父目录
     */
    @Test
    public void mkdir() {
        File dir = new File(dirPath + "a");
        if (!dir.exists()) {
            boolean b = dir.mkdir();
            if (b) {
                p("目录创建完毕！");
            } else {
                p("目录创建失败！");
            }
        } else {
            p("目录已存在！");
        }

        dir = new File(dirPath + "a/b/c");
        if (!dir.exists()) {
            boolean b = dir.mkdirs();
            if (b) {
                p("目录创建完毕！");
            } else {
                p("目录创建失败！");
            }
        } else {
            p("目录已存在！");
        }
    }

    /**
     * boolean	delete()
     * 删除此抽象路径名表示的文件或目录
     */
    public static void main(String[] args) {
        File file = new File(dirPath + "a");
        delete(file);
    }

    public static void delete(File file) {
        if (file.isDirectory()) {
            File[] subs = file.listFiles();
            if (subs != null) {
                for (File sub : subs) {
                    delete(sub);
                }
            }
        }
        boolean b = file.delete();
        if (b) {
            p("删除 " + file.getName() + " 成功！");
        }
    }

    /**
     * String	getParent()
     * 返回此抽象路径名父目录的路径名字符串；如果此路径名没有指定父目录，则返回 null
     * <p>
     * File	getParentFile()
     * 返回此抽象路径名父目录的抽象路径名；如果此路径名没有指定父目录，则返回 null
     */
    @Test
    public void getParent() {
        File dir = new File(dirPath);

        p(dir.getParent());       // src\main\java\knowledge\io
        p(dir.getParentFile());   // src\main\java\knowledge\io
    }

    /**
     * String	getPath()           将此抽象路径名转换为一个路径名字符串
     * String	getAbsolutePath()   返回此抽象路径名的绝对路径名字符串
     * String	getCanonicalPath()  返回此抽象路径名的规范路径名字符串
     */
    @Test
    public void getPath() throws IOException {
        final String DIR = System.getProperty("user.dir");

        File f1 = new File(DIR + "/src/main/java/knowledge/api/io/file/File.txt");
        p("相对路径：" + f1.getPath());          // 相对路径：C:\Users\234607\git\epitome\src\main\java\knowledge\api\io\file\File.txt
        p("绝对路径：" + f1.getAbsolutePath());  // 绝对路径：C:\Users\234607\git\epitome\src\main\java\knowledge\api\io\file\File.txt
        p("抽象路径：" + f1.getCanonicalPath()); // 抽象路径：C:\Users\234607\git\epitome\src\main\java\knowledge\api\io\file\File.txt

        File f2 = new File("./src/main/java/knowledge/api/io/file/File.txt");
        p("相对路径：" + f2.getPath());          // 相对路径：.\src\main\java\knowledge\api\io\file\File.txt
        p("绝对路径：" + f2.getAbsolutePath());  // 绝对路径：C:\Users\234607\git\epitome\.\src\main\java\knowledge\api\io\file\File.txt
        p("抽象路径：" + f2.getCanonicalPath()); // 抽象路径：C:\Users\234607\git\epitome\src\main\java\knowledge\api\io\file\File.txt
    }

    /**
     * String[]	list()
     * 返回一个字符串数组，这些字符串指定此抽象路径名表示的目录中的文件和目录
     * <p>
     * String[]	list(FilenameFilter filter)
     * 返回一个字符串数组，这些字符串指定此抽象路径名表示的目录中满足指定过滤器的文件和目录
     * <p>
     * File[]	listFiles()
     * 返回一个抽象路径名数组，这些路径名表示此抽象路径名表示的目录中的文件和目录
     * <p>
     * File[]	listFiles(FileFilter filter)
     * 返回抽象路径名数组，这些路径名表示此抽象路径名表示的目录中满足指定过滤器的文件和目录
     * <p>
     * File[]	listFiles(FilenameFilter filter)
     * 返回抽象路径名数组，这些路径名表示此抽象路径名表示的目录中满足指定过滤器的文件和目录
     */
    @Test
    public void list() {
        String dirName = "/file";
        File dir = new File("src/main/java/knowledge/api/io" + dirName);
        if (dir.isDirectory()) {
            File[] subs = dir.listFiles();
            if (subs != null) {
                for (File sub : subs) {
                    if (sub.isFile()) {
                        p(sub + " 是一个文件");
                    } else {
                        p(sub + " 是一个目录");
                    }
                }
            }
        } else {
            p(dirName + " 不是一个目录");
        }
    }

    /**
     * static File[]	listRoots()
     * 列出可用的文件系统根
     */
    @Test
    public void listRoots() {
        File[] roots = File.listRoots();
        for (File root : roots) {
            p(root);
            // C:\
            // T:\
            // U:\
            // X:\
            // Y:\
            // Z:\
        }
    }

    /**
     * Path	toPath()
     * File → Path
     */
    @Test
    public void toPath() {
        Path path = new File(dirPath).toPath();
        p(path.getFileName());
    }

    /**
     * boolean	renameTo(File dest)
     * 重新命名此抽象路径名表示的文件
     */
    @Test
    public void renameTo() {
        File oldName = new File(dirPath + "File.txt");
        File newName = new File(dirPath + "File");

        if (!oldName.renameTo(newName)) {
            newName.renameTo(oldName);
        }

    }

    private static <T> void p(T obj) {
        if (obj == null) return;
        System.out.println(obj);
    }

}
