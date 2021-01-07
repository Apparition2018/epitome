package spring.demo;

/**
 * MultipartFile
 * <p>
 * byte[]	        getBytes()                      MultipartFile → byte[]
 * InputStream	    getInputStream()                MultipartFile → InputStream
 * <p>
 * String	        getContentType()                返回文件的内容类型
 * String	        getName()                       返回表单中 name="value" 中的 value 值
 * String	        getOriginalFilename()           返回客户端文件系统中的原始文件名
 * long	            getSize()                       返回文件的字节大小
 * <p>
 * boolean  	    isEmpty()                       返回上传的文件是否为空
 * <p>
 * void	            transferTo(File dest)           将接收到的文件传输到给定的目标文件
 * void	            transferTo(Path dest)           将接收到的文件传输到给定的目标文件
 *
 * @author ljh
 * created on 2020/9/7 1:28
 * @see springboot.controller.UploadController
 */
public class MultipartFileDemo {

}
