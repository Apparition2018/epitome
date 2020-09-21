package l.utils;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class NumberUtils {

    // 格式化数字 #,###
    public static String formatToSeparate(float data) {
        DecimalFormat df = new DecimalFormat("#,###");
        return df.format(data);
    }

    // 保留多少位小数
    public static String keepDecimal(double data, int digit) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i <= digit; i++) {
            if (i == 1) {
                str.append(".0");
            } else {
                str.append("0");
            }
        }
        DecimalFormat df = new DecimalFormat(str.toString());
        df.setRoundingMode(RoundingMode.HALF_UP);
        return df.format(data);
    }

}
