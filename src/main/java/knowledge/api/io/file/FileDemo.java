package knowledge.api.io.file;

import l.demo.Demo;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * File
 * https://jdk6.net/io/File.html
 * <p>
 * boolean	    setExecutable(boolean executable[, boolean ownerOnly])  设置此抽象路径名的所有者或所有用户的执行权限
 * boolean	    setReadable(boolean readable[, boolean ownerOnly])      设置此抽象路径名的所有者或所有用户的读权限
 * boolean	    setWritable(boolean writable[, boolean ownerOnly])      设置此抽象路径名的所有者或所有用户的写权限
 * boolean	    setReadOnly()               标记此抽象路径名指定的文件或目录，从而只能对其进行读操作
 * boolean	    setLastModified(long time)  设置此抽象路径名指定的文件或目录的最后一次修改时间
 * boolean	    canExecute()                测试应用程序是否可以执行此抽象路径名表示的文件
 * boolean	    canRead()                   测试应用程序是否可以读取此抽象路径名表示的文件
 * boolean	    canWrite(                   测试应用程序是否可以修改此抽象路径名表示的文
 * boolean	    exists()                    测试此抽象路径名表示的文件或目录是否存在件
 * boolean	    isHidden()                  测试此抽象路径名指定的文件是否是一个隐藏文件
 * boolean	    isFile()                    测试此抽象路径名表示的文件是否是一个标准文件
 * boolean	    isDirectory()               测试此抽象路径名表示的文件是否是一个目录
 * boolean	    isAbsolute()                测试此抽象路径名是否为绝对路径名
 * int	        compareTo(File pathname)    按字母顺序比较两个抽象路径名
 * long	        lastModified()              返回此抽象路径名表示的文件最后一次被修改的时间
 * long	        length()                    返回由此抽象路径名表示的文件的长度
 * long	        getTotalSpace()             返回此抽象路径名指定的分区大小
 * long	        getFreeSpace()              返回此抽象路径名指定的分区中未分配的字节数
 * long	        getUsableSpace()            返回此抽象路径名指定的分区上可用于此虚拟机的字节数
 * String	    getName()                   返回由此抽象路径名表示的文件或目录的名称
 * Path         toPath()                    返回从此抽象路径构造的java.nio.file.Path对象
 * URI	        toURI()                     构造一个表示此抽象路径名的 file: URI
 */
public class FileDemo extends Demo {

    private static final File FILE;

    static {
        // File(File parent, String child)      根据 parent 抽象路径名和 child 路径名字符串创建一个新 File 实例
        // File(String pathname)                通过将给定路径名字符串转换为抽象路径名来创建一个新 File 实例
        // File(String parent, String child)    根据 parent 路径名字符串和 child 路径名字符串创建一个新 File 实例
        // File(URI uri)                        通过将给定的 file: URI 转换为一个抽象路径名来创建一个新的 File 实例
        FILE = new File(DEMO_PATH + "a/b/c/demo");
    }

    @Test
    public void testFile() throws IOException, InterruptedException {
        // static String	pathSeparator           与系统有关的路径分隔符，为了方便，它被表示为一个字符串
        p(File.separator);      // \
        // static String	separator               与系统有关的默认名称分隔符，为了方便，它被表示为一个字符串
        p(File.pathSeparator);  // ;

        create(FILE);
        // boolean	        renameTo(File dest)     重新命名此抽象路径名表示的文件
        // https://blog.csdn.net/u010648555/article/details/78356040
        boolean renameFlag = FILE.renameTo(new File(DEMO_PATH + "a/b/demo"));
        if (renameFlag) {
            p("文件移动成功！");
        } 
        delete(new File(DEMO_PATH + "a"));

        // static File	    createTempFile(String prefix, String suffix[, File directory])
        // 在指定目录中创建一个新的空文件，使用给定的前缀和后缀字符串生成其名称
        File tmpFile = File.createTempFile("tmp", ".txt", new File(DEMO_PATH));
        // void	            deleteOnExit()          在虚拟机终止时，请求删除此抽象路径名表示的文件或目录
        tmpFile.deleteOnExit();
    }

    /**
     * 创建文件
     */
    public void create(File file) throws IOException {
        if (!file.exists()) {
            // String	    getName()               返回由此抽象路径名表示的文件或目录的名称
            // String       getParent()             返回此抽象路径名父目录的路径名字符串；如果此路径名没有指定父目录，则返回 null
            // File	        getParentFile()         返回此抽象路径名父目录的抽象路径名；如果此路径名没有指定父目录，则返回 null
            String parentPath = file.getParent();
            if (!new File(parentPath).exists()) {
                // boolean	mkdir()                 创建此抽象路径名指定的目录
                // boolean	mkdirs()                创建此抽象路径名指定的目录，包括所有必需但不存在的父目录
                boolean mkBoolean = new File(parentPath).mkdirs();
                p(mkBoolean ? "目录创建完毕！" : "目录创建失败！");
            }
            // boolean	    createNewFile()         当且仅当不存在具有此抽象路径名指定名称的文件时，不可分地创建一个新的空文件
            boolean createBoolean = file.createNewFile();
            p(createBoolean ? "文件创建完毕！" : "文件创建失败！");
        } else {
            p("文件已存在！");
        }
    }

    /**
     * boolean	            delete()                删除此抽象路径名表示的文件或目录
     */
    public boolean delete(File file) {
        if (file.isDirectory()) {
            for (File sub : Objects.requireNonNull(file.listFiles())) {
                delete(sub);
            }
        }
        return file.delete();
    }

    /**
     * String	            getPath()               将此抽象路径名转换为一个路径名字符串
     * String	            getAbsolutePath()       返回此抽象路径名的绝对路径名字符串
     * String	            getCanonicalPath()      返回此抽象路径名的规范路径名字符串
     */
    @Test
    public void getPath() throws IOException {
        p(USER_DIR);                            // D:\L\git\epitome

        File f0 = new File(DEMO_PATH + "Demo.txt");
        p("相对路径：" + f0.getPath());          // 相对路径：src\main\resources\demo\Demo.txt
        p("绝对路径：" + f0.getAbsolutePath());  // 绝对路径：D:\L\git\epitome\src\main\resources\demo\Demo.txt
        p("抽象路径：" + f0.getCanonicalPath()); // 抽象路径：D:\L\git\epitome\src\main\resources\demo\Demo.txt

        File f1 = new File(USER_DIR + File.separator + DEMO_PATH + "Demo.txt");
        p("相对路径：" + f1.getPath());          // 相对路径：D:\L\git\epitome\src\main\resources\demo\Demo.txt
        p("绝对路径：" + f1.getAbsolutePath());  // 绝对路径：D:\L\git\epitome\src\main\resources\demo\Demo.txt
        p("抽象路径：" + f1.getCanonicalPath()); // 抽象路径：D:\L\git\epitome\src\main\resources\demo\Demo.txt

        File f2 = new File("." + File.separator + DEMO_PATH + "Demo.txt");
        p("相对路径：" + f2.getPath());          // 相对路径：.\src\main\resources\demo\Demo.txt
        p("绝对路径：" + f2.getAbsolutePath());  // 绝对路径：D:\L\git\epitome\.\src\main\resources\demo\Demo.txt
        p("抽象路径：" + f2.getCanonicalPath()); // 抽象路径：D:\L\git\epitome\src\main\resources\demo\Demo.txt
    }

    /**
     * String[]	            list([FilenameFilter filter])
     * 返回一个字符串数组，这些字符串指定此抽象路径名表示的目录中满足指定过滤器的文件和目录
     * File[]	            listFiles([FileFilter filter/FilenameFilter filter])
     * 返回抽象路径名数组，这些路径名表示此抽象路径名表示的目录中满足指定过滤器的文件和目录
     */
    @Test
    public void list() {
        p(USER_DIR); // D:\L\git\epitome
        File[] files = new File(USER_DIR).listFiles();
        p(files);   // [D:\L\git\epitome\.git, D:\L\git\epitome\.gitignore, D:\L\git\epitome\.idea, D:\L\git\epitome\epitome.iml, 
        // D:\L\git\epitome\logs, D:\L\git\epitome\plugins, D:\L\git\epitome\pom.xml, D:\L\git\epitome\src, D:\L\git\epitome\target]
    }

    /**
     * static File[]	    listRoots()             列出可用的文件系统根
     */
    @Test
    public void listRoots() {
        File[] roots = File.listRoots();
        for (File root : roots) {
            p(root);
            // C:\
            // D:\
            // E:\
            // H:\
        }
    }

}
