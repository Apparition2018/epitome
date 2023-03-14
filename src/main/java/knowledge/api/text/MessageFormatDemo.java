package knowledge.api.text;

import org.junit.jupiter.api.Test;

import java.text.ChoiceFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import static l.demo.Demo.p;

/**
 * <a href="https://tool.oschina.net/uploads/apidocs/jdk-zh/java/text/MessageFormat.html">...</a>
 * <p>提供了以与语言无关方式生成连接消息的方式。使用此方法构造向终端用户显示的消息。
 * <p>值映射到 Format 实例：
 * <pre>
 * 格式类型         格式样式                            创建的子格式
 * none             none                null
 * number           none                NumberFormat.getInstance(getLocale())
 * number           integer             NumberFormat.getIntegerInstance(getLocale())
 * number           currency            NumberFormat.getCurrencyInstance(getLocale())
 * number           percent             NumberFormat.getPercentInstance(getLocale())
 * number       SubFormatPattern        new DecimalFormat(subFormatPattern, DecimalFormatSymbols.getInstance(getLocale()))
 * date	            none                DateFormat.getDateInstance(DateFormat.DEFAULT, getLocale())
 * date             short               DateFormat.getDateInstance(DateFormat.SHORT, getLocale())
 * date             medium              DateFormat.getDateInstance(DateFormat.DEFAULT, getLocale())
 * date             long                DateFormat.getDateInstance(DateFormat.LONG, getLocale())
 * date             full                DateFormat.getDateInstance(DateFormat.FULL, getLocale())
 * date         SubFormatPattern        new SimpleDateFormat(subFormatPattern, getLocale())
 * time             none                DateFormat.getTimeInstance(DateFormat.DEFAULT, getLocale())
 * time             short               DateFormat.getTimeInstance(DateFormat.SHORT, getLocale())
 * time             medium              DateFormat.getTimeInstance(DateFormat.DEFAULT, getLocale())
 * time             long                DateFormat.getTimeInstance(DateFormat.LONG, getLocale())
 * time             full                DateFormat.getTimeInstance(DateFormat.FULL, getLocale())
 * time         SubFormatPattern        new SimpleDateFormat(subFormatPattern, getLocale())
 * choice       SubFormatPattern        new ChoiceFormat(subFormatPattern)
 * </pre>
 *
 * @author ljh
 * @since 2020/9/3 14:54
 */
public class MessageFormatDemo {

    public static final MessageFormat FORMAT;

    static {
        FORMAT = new MessageFormat("The disk \"{0}\" contains {1} file(s).");
    }

    @Test
    public void testMessageFormat() throws ParseException {
        // String	    format(Object obj)              格式化一个对象以生成一个字符串，继承自 Format
        p(FORMAT.format(new Object[]{"MyDisk", 1273})); // The disk "MyDisk" contains 1,273 file(s).

        // Object[]	    parse(String source)            从给定字符串的开始位置解析文本，以生成一个对象数组
        p(FORMAT.parse("The disk \"YourDisk\" contains 0 file(s).")); // [YourDisk, 0]

        // String	    toPattern()                     返回表示消息格式当前状态的模式
        p(FORMAT.toPattern()); // The disk "{0}" contains {1} file(s).
    }

    /**
     * 和 ChoiceFormat 联合使用
     */
    @Test
    public void testChoiceFormat() {
        // void	        applyPattern(String pattern)    设置此消息格式所使用的模式
        FORMAT.applyPattern("The disk \"{0}\" contains {1}.");
        double[] limits = {0, 1, 2};
        String[] formats = {"no files", "one file", "{1,number} files"};
        ChoiceFormat format = new ChoiceFormat(limits, formats);
        FORMAT.setFormatByArgumentIndex(1, format);

        List<Integer> list = List.of(0, 2, 1273);
        for (Integer i : list) {
            p(FORMAT.format(new Object[]{"MyDisk", i}));
            // The disk "MyDisk" contains no files.
            // The disk "MyDisk" contains 2 files.
            // The disk "MyDisk" contains 1,273 files.
        }
    }

    @Test
    public void tesFormat() {
        // static String	format(String pattern, Object... arguments)
        // 创建具有给定模式的 MessageFormat，并用它来格式化给定的参数
        String result = MessageFormat.format(
                "At {0,time} on {0,date}, there was {1} on planet {2,number,integer}.",
                new Date(), "a disturbance in the Force", 7);
        p(result); // At 15:00:45 on 2020-9-3, there was a disturbance in the Force on planet 7.
    }
}
