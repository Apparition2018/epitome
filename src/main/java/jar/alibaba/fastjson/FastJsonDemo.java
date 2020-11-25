package jar.alibaba.fastjson;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import l.demo.JsonDemo;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static l.demo.Demo.p;

/**
 * FastJson 的简单使用：https://segmentfault.com/a/1190000011212806
 *
 * @author ljh
 * created on 2019/8/8 19:39
 */
public class FastJsonDemo implements JsonDemo {
    JSONObject jsonObject;
    JSONArray jsonArray;
    String jsonStr;
    Teacher teacher;
    List<Student> students;
    JSONObject courseJsonObject;
    JSONArray studentsJsonArray;

    /**
     * 复杂/简单对象JSON ⇆ JSONObject ⇆ JavaBean
     */
    @Test
    public void testJSONObject() {
        // 复杂对象JSON → JSONObject
        jsonObject = JSONObject.parseObject(JSON_COMPLEX);
        courseJsonObject = jsonObject.getJSONObject("course");
        studentsJsonArray = jsonObject.getJSONArray("students");
        p(jsonObject);
        // JSONObject → 复杂对象JSON
        jsonStr = JSONObject.toJSONString(jsonObject);
        jsonStr = jsonObject.toJSONString();
        p(jsonStr + "\n");

        // 复杂对象JSON → JavaBean
        teacher = JSONObject.parseObject(JSON_COMPLEX, Teacher.class);
        teacher = JSONObject.parseObject(JSON_COMPLEX, new TypeReference<Teacher>() {
        });
        p(teacher);
        // JavaBean → 复杂对象JSON
        jsonStr = JSONObject.toJSONString(teacher);
        p(jsonStr + "\n");

        // JSONObject → JavaBean
        teacher = JSONObject.parseObject(jsonObject.toJSONString(), Teacher.class);
        teacher = JSONObject.parseObject(jsonObject.toJSONString(), new TypeReference<Teacher>() {
        });
        p(teacher);

        // JavaBean → JSONObject
        jsonObject = (JSONObject) JSONObject.toJSON(teacher);
        jsonObject = JSONObject.parseObject(JSONObject.toJSONString(teacher));
        p(jsonObject);
    }

    /**
     * 数组对象JSON ⇆ JSONObject ⇆ List<JavaBean>
     */
    @Test
    public void testJSONArray() {
        // 数组对象JSON → JSONArray
        jsonArray = JSONArray.parseArray(JSON_ARRAY);
        p(jsonArray);
        // JSONArray → 数组对象JSON
        jsonStr = JSONArray.toJSONString(jsonArray);
        jsonStr = jsonArray.toJSONString();
        p(jsonStr + "\n");

        // 数组对象JSON → List<JavaBean>
        students = JSONArray.parseObject(JSON_ARRAY, new TypeReference<ArrayList<Student>>() {
        });
        p(students);
        // List<JavaBean> → 数组对象JSON
        jsonStr = JSONArray.toJSONString(students);
        p(jsonStr + "\n");

        // JSONArray → List<JavaBean>
        students = JSONArray.parseArray(jsonArray.toJSONString(), Student.class);
        students = JSONArray.parseObject(jsonArray.toJSONString(), new TypeReference<List<Student>>() {
        });
        p(students);
        // List<JavaBean> → JSONArray
        jsonArray = (JSONArray) JSONArray.toJSON(students);
        jsonArray = JSONArray.parseArray(JSONArray.toJSONString(students));
        p(jsonArray);
    }
}



