package jar.alibaba.fastjson;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.TypeReference;
import l.demo.JsonDemo;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static l.demo.Demo.p;

/**
 * FastJson2
 *
 * @author ljh
 * @since 2023/10/26 9:13
 */
public class FastJson2Demo implements JsonDemo {
    JSONObject jsonObject;
    JSONArray jsonArray;
    String jsonStr;
    Teacher teacher;
    List<Student> students;
    JSONObject courseJsonObject;
    JSONArray studentsJsonArray;

    /** 复杂/简单对象JSON ⇆ JSONObject ⇆ JavaBean */
    @Test
    public void testJSONObject() {
        // 复杂对象JSON → JSONObject
        jsonObject = JSONObject.parseObject(COMPLEX_JSON);
        courseJsonObject = jsonObject.getJSONObject("course");
        studentsJsonArray = jsonObject.getJSONArray("students");
        p(jsonObject);
        // JSONObject → 复杂对象JSON
        jsonStr = JSONObject.toJSONString(jsonObject);
        jsonStr = jsonObject.toJSONString();
        p(jsonStr + StringUtils.CR);

        // 复杂对象JSON → JavaBean
        teacher = JSONObject.parseObject(COMPLEX_JSON, Teacher.class);
        teacher = JSONObject.parseObject(COMPLEX_JSON, new TypeReference<>() {
        });
        p(teacher);
        // JavaBean → 复杂对象JSON
        jsonStr = JSONObject.toJSONString(teacher);
        p(jsonStr + StringUtils.CR);

        // JSONObject → JavaBean
        teacher = JSONObject.parseObject(jsonObject.toJSONString(), Teacher.class);
        teacher = JSONObject.parseObject(jsonObject.toJSONString(), new TypeReference<>() {
        });
        p(teacher);

        // JavaBean → JSONObject
        jsonObject = (JSONObject) JSON.toJSON(teacher);
        jsonObject = JSONObject.parseObject(JSONObject.toJSONString(teacher));
        p(jsonObject);
    }

    /** 数组对象JSON ⇆ JSONObject ⇆ List<JavaBean> */
    @Test
    public void testJSONArray() {
        // 数组对象JSON → JSONArray
        jsonArray = JSONArray.parseArray(ARRAY_JSON);
        p(jsonArray);
        // JSONArray → 数组对象JSON
        jsonStr = JSONArray.toJSONString(jsonArray);
        jsonStr = jsonArray.toJSONString();
        p(jsonStr + StringUtils.CR);

        // 数组对象JSON → List<JavaBean>
        students = JSONArray.parseArray(ARRAY_JSON, Student.class);
        p(students);
        // List<JavaBean> → 数组对象JSON
        jsonStr = JSONArray.toJSONString(students);
        p(jsonStr + StringUtils.CR);

        // JSONArray → List<JavaBean>
        students = JSONArray.parseArray(jsonArray.toJSONString(), Student.class);
        students = jsonArray.toList(Student.class);
        p(students);
        // List<JavaBean> → JSONArray
        jsonArray = JSONArray.of(students);
        jsonArray = JSONArray.parseArray(JSONArray.toJSONString(students));
        p(jsonArray);
    }
}
