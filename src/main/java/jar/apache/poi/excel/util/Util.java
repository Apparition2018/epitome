package jar.apache.poi.excel.util;

import jar.apache.poi.excel.common.Common;

/**
 * @author Hongten
 * @since 2014-5-21
 */
public class Util {

    /**
     * get postfix of the path
     */
    public static String getSuffix(String path) {
        if (path == null || Common.EMPTY.equals(path.trim())) {
            return Common.EMPTY;
        }
        if (path.contains(Common.POINT)) {
            return path.substring(path.lastIndexOf(Common.POINT) + 1);
        }
        return Common.EMPTY;
    }
}
