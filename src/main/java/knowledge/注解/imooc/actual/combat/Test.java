package knowledge.注解.imooc.actual.combat;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 需求：
 * 1.有一张用户表，字段包括用户ID，用户名，昵称，年龄，性别，所在城市，邮箱，手机号
 * 2.方便地对每个字段或字段的组合条件进行检索，并打印出SQL
 * 3.使用方式要足够简单，见代码实例
 */
public class Test {
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

        System.out.println(sql1);
        System.out.println(sql2);
        System.out.println(sql3);

        Filter2 filter4 = new Filter2();
        filter4.setAmount(10);
        filter4.setName("技术部");
        System.out.println(query(filter4));
    }

    public static String query(Object filter) {
        StringBuilder sb = new StringBuilder();
        // 1.获取到class
        Class clazz = filter.getClass();
        // 2.获取到table的名字
        boolean isExist = clazz.isAnnotationPresent(Table.class);
        if (!isExist) {
            return null;
        }
        Table table = (Table) clazz.getAnnotation(Table.class);
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
                @SuppressWarnings("unchecked")
                Method method = clazz.getMethod(getMethodName);
                fieldValue = method.invoke(filter);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
            // 4.3拼装sql
            if (fieldValue == null || (fieldValue instanceof Integer && (Integer) fieldValue == 0)) {
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

}