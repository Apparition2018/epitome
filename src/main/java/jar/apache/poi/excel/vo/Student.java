package jar.apache.poi.excel.vo;

import lombok.Data;

/**
 * Student
 *
 * @author Hongten
 * @since 2014-5-18
 */
@Data
public class Student {
    private Integer id;     // id
    private String no;      // 学号
    private String name;    // 姓名
    private String age;     // 年龄
    private float score;    // 成绩

}
