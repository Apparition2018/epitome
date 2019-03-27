package jar.apache.commons.lang3;

import org.apache.commons.lang3.LocaleUtils;
import org.junit.Test;

import java.util.List;
import java.util.Locale;
import java.util.Set;

/**
 * LocaleUtils
 *
 * http://commons.apache.org/proper/commons-lang/apidocs/org/apache/commons/lang3/LocaleUtils.html
 */
public class LocaleUtilsDemo {

    @Test
    public void test() {
        // static List<Locale>	availableLocaleList()
        // 获取不可更改的已安装区域设置列表
        List<Locale> localeList =  LocaleUtils.availableLocaleList();
        p(localeList);

        // static Set<Locale>	availableLocaleSet()
        // 获取不可更改的已安装区域设置集
        Set<Locale> localeSet = LocaleUtils.availableLocaleSet();
        p(localeSet);

        // static List<Locale>	countriesByLanguage(String languageCode)
        // 获取给定语言支持的国家列表
        localeList = LocaleUtils.countriesByLanguage("zh");
        p(localeList); // [zh_TW, zh_HK, zh_SG, zh_CN]

        // static List<Locale>	languagesByCountry(String countryCode)
        // 获取给定国家支持的语言列表
        localeList = LocaleUtils.languagesByCountry("CN");
        p(localeList); // [zh_CN]

        // static Locale	    toLocale(String str)
        // 将字符串转换为区域设置
        Locale locale = LocaleUtils.toLocale("zh_CN");

        // static boolean	    isAvailableLocale(Locale locale)
        // 检查指定的区域设置是否在可用区域设置列表中
        p(LocaleUtils.isAvailableLocale(locale)); // true
    }


    private static <T> void p(T obj) {
        if (obj == null) return;
        System.out.println(obj);
    }
}
