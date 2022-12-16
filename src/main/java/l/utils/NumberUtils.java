package l.utils;

import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * @author ljh
 * @since 2020/9/7 1:28
 */
public class NumberUtils {

    // 格式化数字 #,###
    public static String formatToSeparate(float data) {
        DecimalFormat df = new DecimalFormat("#,###");
        return df.format(data);
    }

    // 保留多少位小数
    public static String keepDecimal(double data, int digit) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= digit; i++) {
            if (i == 1) {
                sb.append(".0");
            } else {
                sb.append("0");
            }
        }
        DecimalFormat df = new DecimalFormat(sb.toString());
        df.setRoundingMode(RoundingMode.HALF_UP);
        return df.format(data);
    }

}
