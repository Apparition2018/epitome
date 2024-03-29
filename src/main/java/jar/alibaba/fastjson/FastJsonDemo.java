package jar.alibaba.fastjson;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import l.demo.JsonDemo;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static l.demo.Demo.p;

/**
 * <a href="https://segmentfault.com/a/1190000011212806">FastJson 的简单使用</a>
 *
 * @author ljh
 * @since 2019/8/8 19:39
 */
public class FastJsonDemo implements JsonDemo {
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
        jsonObject = (JSONObject) JSONObject.toJSON(teacher);
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
        students = JSONArray.parseObject(ARRAY_JSON, new TypeReference<ArrayList<Student>>() {
        });
        p(students);
        // List<JavaBean> → 数组对象JSON
        jsonStr = JSONArray.toJSONString(students);
        p(jsonStr + StringUtils.CR);

        // JSONArray → List<JavaBean>
        students = JSONArray.parseArray(jsonArray.toJSONString(), Student.class);
        students = JSONArray.parseObject(jsonArray.toJSONString(), new TypeReference<>() {
        });
        p(students);
        // List<JavaBean> → JSONArray
        jsonArray = (JSONArray) JSONArray.toJSON(students);
        jsonArray = JSONArray.parseArray(JSONArray.toJSONString(students));
        p(jsonArray);
    }
}
