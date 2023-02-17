package knowledge.annotation;

import lombok.Getter;
import lombok.Setter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static l.demo.Demo.p;

/**
 * 需求：
 * <pre>
 * 1 有一张用户表，字段包括用户ID，用户名，昵称，年龄，性别，所在城市，邮箱，手机号
 * 2 方便地对每个字段或字段的组合条件进行检索，并打印出SQL
 * 3 使用方式要足够简单，见代码实例
 * </pre>
 *
 * @author ljh
 * @since 2020/9/18 18:01
 */
public class CustomAnnotationExercise {

    public static void main(String[] args) {
        // 查询id为10的用户
        Filter filter1 = new Filter();
        filter1.setId(10);

        // 模糊查询用户名为lucy的用户
        Filter filter2 = new Filter();
        filter2.setUserName("lucy");
        filter1.setAge(18);

        // 查询邮箱为其中任意一个用户
        Filter filter3 = new Filter();
        filter3.setEmail("liu@sina.com,zh@163.com,77777@qq.com");

        String sql1 = query(filter1);
        String sql2 = query(filter2);
        String sql3 = query(filter3);

        p(sql1);
        p(sql2);
        p(sql3);

        Filter2 filter4 = new Filter2();
        filter4.setAmount(10);
        filter4.setName("技术部");
        p(query(filter4));
    }

    public static String query(Object filter) {
        StringBuilder sb = new StringBuilder();
        // 1.获取到class
        Class<?> clazz = filter.getClass();
        // 2.获取到table的名字
        if (!clazz.isAnnotationPresent(Table.class)) {
            // 当某个方法的代码总行数超过 10 行时，return / throw 等中断逻辑的右大括号后均需要加一个空行（阿里编程规约）
            return null;
        }

        Table table = clazz.getAnnotation(Table.class);
        String tableName = table.value();
        sb.append("select * from ").append(tableName).append(" where 1 = 1");
        // 3.遍历所有的字段
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            // 4.处理每个字段对应的sql
            // 4.1拿到字段名
            boolean isExistF = field.isAnnotationPresent(Column.class);
            if (!isExistF) {
                continue;
            }
            Column column = field.getAnnotation(Column.class);
            String columnName = column.value();
            // 4.2拿到字段的值
            String fieldName = field.getName();
            String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            Object fieldValue = null;
            try {
                Method method = clazz.getMethod(getMethodName);
                fieldValue = method.invoke(filter);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
            // 4.3拼装sql
            if (null == fieldValue || (fieldValue instanceof Integer && (Integer) fieldValue == 0)) {
                continue;
            }
            sb.append(" and ").append(columnName);
            if (fieldValue instanceof String) {
                if (((String) fieldValue).contains(",")) {
                    String[] values = ((String) fieldValue).split(",");
                    sb.append(" in(");
                    for (String value : values) {
                        sb.append("'").append(value).append("'").append(",");
                    }
                    sb.deleteCharAt(sb.length() - 1);
                    sb.append(")");
                } else {
                    sb.append("='").append(fieldValue).append("'");
                }
            } else if (fieldValue instanceof Integer) {
                sb.append("=").append(fieldValue);
            }
        }
        return sb.toString();
    }


    @Table("user")
    @Getter
    @Setter
    private static class Filter {
        @Column("id")
        private int id;
        @Column("user_name")
        private String userName;
        @Column("nick_name")
        private String nickName;
        @Column("age")
        private int age;
        @Column("city")
        private String city;
        @Column("email")
        private String email;
        @Column("mobile")
        private String mobile;
    }

    @Table("department")
    @Getter
    @Setter
    private static class Filter2 {
        @Column("id")
        private int id;
        @Column("name")
        private String name;
        @Column("leader")
        private String leader;
        @Column("amount")
        private int amount;
    }


    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Table {
        String value();
    }

    @Target({ElementType.FIELD})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Column {
        String value();
    }
}
