/*
 * 包声明
 * 包声明在源文件的第一行，每个源文件只有一个包声明，这个文件中的每个类型都应用于它
 * 如果一个源文件中没有使用包声明，那么其中的类，接口，枚举，注解等都放在一个无名的包(unnamed package)中
 */
package knowledge.面向对象;

/*
 * import
 * 导入包，能够使用包内的成员 (类，接口，枚举，注解）
 * import 语句位于 package 语句之后，所有类的定义之前
 */

import org.junit.Test;

/*
 * 包
 * 作用：
 *  1.把功能相似或相关的类或接口组织在同一个包中，方便类(class)、接口(interface)、枚举(enum)、注解(Annotation)的查找和使用
 *  2.如同文件夹一样，包也采用了树形目录的存储方式。
 *      同一个包中的类名字是不同的，不同的包中的类的名字是可以相同的，
 *      当同时调用两个不同包中相同类名的类时，应该加上包名加以区别。
 *      因此，包可以避免名字冲突。
 *  3.包也限定了访问权限，拥有包访问权限的类才能访问某个包中的类。
 */
public class Package {
    
    @Test
    public void testPackage() {
        
    }

}
