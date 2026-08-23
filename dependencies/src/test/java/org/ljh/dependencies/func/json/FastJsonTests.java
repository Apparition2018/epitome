package org.ljh.dependencies.func.json;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://segmentfault.com/a/1190000011212806">FastJson 的简单使用</a>
 *
 * @author ljh
 * @since 2019/8/8 19:39
 */
public class FastJsonTests implements JsonDemo {
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
        jsonObject = JSONObject.parseObject(JsonDemo.COMPLEX_JSON);
        courseJsonObject = jsonObject.getJSONObject("course");
        studentsJsonArray = jsonObject.getJSONArray("students");
        System.out.println(jsonObject);
        // JSONObject → 复杂对象JSON
        jsonStr = JSONObject.toJSONString(jsonObject);
        jsonStr = jsonObject.toJSONString();
        System.out.println(jsonStr);

        // 复杂对象JSON → JavaBean
        teacher = JSONObject.parseObject(JsonDemo.COMPLEX_JSON, Teacher.class);
        teacher = JSONObject.parseObject(JsonDemo.COMPLEX_JSON, new TypeReference<>() {
        });
        System.out.println(teacher);
        // JavaBean → 复杂对象JSON
        jsonStr = JSONObject.toJSONString(teacher);
        System.out.println(jsonStr);

        // JSONObject → JavaBean
        teacher = JSONObject.parseObject(jsonObject.toJSONString(), Teacher.class);
        teacher = JSONObject.parseObject(jsonObject.toJSONString(), new TypeReference<>() {
        });
        System.out.println(teacher);

        // JavaBean → JSONObject
        jsonObject = (JSONObject) JSONObject.toJSON(teacher);
        jsonObject = JSONObject.parseObject(JSONObject.toJSONString(teacher));
        System.out.println(jsonObject);
    }

    /** 数组对象JSON ⇆ JSONObject ⇆ List<JavaBean> */
    @Test
    public void testJSONArray() {
        // 数组对象JSON → JSONArray
        jsonArray = JSONArray.parseArray(JsonDemo.ARRAY_JSON);
        System.out.println(jsonArray);
        // JSONArray → 数组对象JSON
        jsonStr = JSONArray.toJSONString(jsonArray);
        jsonStr = jsonArray.toJSONString();
        System.out.println(jsonStr);

        // 数组对象JSON → List<JavaBean>
        students = JSONArray.parseObject(JsonDemo.ARRAY_JSON, new TypeReference<ArrayList<Student>>() {
        });
        System.out.println(students);
        // List<JavaBean> → 数组对象JSON
        jsonStr = JSONArray.toJSONString(students);
        System.out.println(jsonStr);

        // JSONArray → List<JavaBean>
        students = JSONArray.parseArray(jsonArray.toJSONString(), Student.class);
        students = JSONArray.parseObject(jsonArray.toJSONString(), new TypeReference<>() {
        });
        System.out.println(students);
        // List<JavaBean> → JSONArray
        jsonArray = (JSONArray) JSONArray.toJSON(students);
        jsonArray = JSONArray.parseArray(JSONArray.toJSONString(students));
        System.out.println(jsonArray);
    }
}
