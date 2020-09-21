package jar.apache.commons.lang3;

import l.demo.Demo;
import org.apache.commons.lang3.EnumUtils;
import org.junit.Test;

/**
 * EnumUtils
 * http://commons.apache.org/proper/commons-lang/apidocs/org/apache/commons/lang3/EnumUtils.html
 */
public class EnumUtilsDemo extends Demo {

    @Test
    public void enum_() {
        // static <E extends Enum<E>> E	            getEnum(Class<E> enumClass, String enumName)
        // static <E extends Enum<E>> E	            getEnumIgnoreCase(Class<E> enumClass, String enumName)
        p(EnumUtils.getEnum(ImagesTypeEnum.class, "JPG"));  // JPG
        // static <E extends Enum<E>> List<E>	    getEnumList(Class<E> enumClass)
        p(EnumUtils.getEnumList(ImagesTypeEnum.class));     // [JPG, JPEG, PNG, BPM, GIF]
        // static <E extends Enum<E>> Map<String,E>	getEnumMap(Class<E> enumClass)
        p(EnumUtils.getEnumMap(ImagesTypeEnum.class));      // {JPG=JPG, JPEG=JPEG, PNG=PNG, BPM=BPM, GIF=GIF}

        // static <E extends Enum<E>> boolean	    isValidEnum(Class<E> enumClass, String enumName)
        // static <E extends Enum<E>> boolean	    isValidEnumIgnoreCase(Class<E> enumClass, String enumName)
        // 判断是否在枚举中
        p(EnumUtils.isValidEnum(ImagesTypeEnum.class, "JPG"));  // true
        p(EnumUtils.isValidEnum(ImagesTypeEnum.class, null));   // false

        // 位向量
        p(EnumUtils.generateBitVector(ImagesTypeEnum.class, ImagesTypeEnum.JPG));   // 1
        p(EnumUtils.generateBitVector(ImagesTypeEnum.class, ImagesTypeEnum.JPEG));  // 2
        p(EnumUtils.generateBitVector(ImagesTypeEnum.class, ImagesTypeEnum.PNG));   // 4
        p(EnumUtils.generateBitVector(ImagesTypeEnum.class, ImagesTypeEnum.BPM));   // 8
        p(EnumUtils.generateBitVector(ImagesTypeEnum.class, ImagesTypeEnum.GIF));   // 16
        p(EnumUtils.generateBitVector(ImagesTypeEnum.class, ImagesTypeEnum.JPG, ImagesTypeEnum.GIF));   // 17
        p(EnumUtils.processBitVector(ImagesTypeEnum.class, 7));   // [JPG, JPEG, PNG]
        p(EnumUtils.processBitVector(ImagesTypeEnum.class, 14));  // [JPEG, PNG, BPM]

    }

    private enum ImagesTypeEnum {
        JPG, JPEG, PNG, BPM, GIF
    }

}
