package l.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

    // 判断字符串是否为整数字符串
    public static boolean isNumber(String str) {
        Pattern p = Pattern.compile("^[-+]?[\\d]+$");
        return p.matcher(str).matches();
    }

    // 判断字符串是否为字母字符串
    public static boolean isLetter(String str) {
        Pattern p = Pattern.compile("^[a-zA-Z]+$");
        return p.matcher(str).matches();
    }

    // 判断字符串是否为汉字字符串
    public static boolean isChinese(String str) {
        Pattern p = Pattern.compile("[\\u4e00-\\u9fa5]+");
        return p.matcher(str).matches();
    }

    // 匹配分组
    /**
     * @param str       被匹配字符串
     * @param regex     匹配正则        如："[\\u4e00-\\u9fa5]+|[a-zA-Z0-9]+"
     * @return          Matcher m = return matcherGruop(str, regex);
     *                  while(m.find()) {
     *                      System.out.println(m.group());
     *                  }
     */
    public static Matcher matcherGroup(String str, String regex) {
        Pattern p = Pattern.compile(regex);
        return p.matcher(str);
    }

}
