package jar.apache.commons.io;

import org.apache.commons.io.FilenameUtils;

import static l.demo.Demo.p;

/**
 * <a href="http://commons.apache.org/proper/commons-io/apidocs/org/apache/commons/io/FilenameUtils.html">FilenameUtils</a>
 *
 * @author ljh
 * @since 2019/8/8 19:39
 */
public class FilenameUtilsDemo {

    public static void main(String[] args) {
        // prefix + path + base + ext

        // 获取名称，base + ext
        p(FilenameUtils.getName("a/b/c.txt"));      // c.txt
        p(FilenameUtils.getName("a/b/c"));          // c
        p(FilenameUtils.getName("a/b/c/"));         //

        // 获取基名，base
        p(FilenameUtils.getBaseName("a/b/c.txt"));  // c
        p(FilenameUtils.getBaseName("a/b/c"));      // c
        p(FilenameUtils.getBaseName("a/b/c/"));     //

        // 获取扩展名，ext
        p(FilenameUtils.getExtension("a/b/c.txt"));  // txt
        p(FilenameUtils.getExtension("a/b/c"));      //

        // 获取路径，path
        p(FilenameUtils.getPath("C:/a/b/c.txt"));                   // a/b/
        p(FilenameUtils.getPath("~/a/b/c"));                        // a/b/
        p(FilenameUtils.getPath("a/b/c/"));                         // a/b/c/
        // 获取路径，path，排除最后的目录分隔符
        p(FilenameUtils.getPathNoEndSeparator("C:/a/b/c.txt"));     // a/b

        // 获取完整路径，prefix + path
        p(FilenameUtils.getFullPath("C:/a/b/c.txt"));               // C:/a/b/
        p(FilenameUtils.getFullPath("~/a/b/c"));                    // ~/a/b/
        p(FilenameUtils.getFullPath("a/b/c/"));                     // a/b/c/
        // 获取完整路径，prefix + path，排除最后的目录分隔符
        p(FilenameUtils.getFullPathNoEndSeparator("C:/a/b/c.txt")); // C:/a/b

        // 获取前缀，prefix
        p(FilenameUtils.getPrefix("C:/a/b/c.txt"));         // C:/
        p(FilenameUtils.getPrefix("~/a/b/c"));              // ~/
        p(FilenameUtils.getPrefix("a/b/c/"));               //
        // 获取前缀长度
        p(FilenameUtils.getPrefixLength("C:/a/b/c.txt"));   // 3
    }
}
