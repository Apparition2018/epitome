package org.ljh.dependencies.func.json;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.TypeReference;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * FastJson2
 *
 * @author ljh
 * @since 2023/10/26 9:13
 */
public class FastJson2Tests implements JsonDemo {
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
        System.out.println(jsonObject);
        // JSONObject → 复杂对象JSON
        jsonStr = JSONObject.toJSONString(jsonObject);
        jsonStr = jsonObject.toJSONString();
        System.out.println(jsonStr);

        // 复杂对象JSON → JavaBean
        teacher = JSONObject.parseObject(COMPLEX_JSON, Teacher.class);
        teacher = JSONObject.parseObject(COMPLEX_JSON, new TypeReference<>() {
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
        jsonObject = (JSONObject) JSON.toJSON(teacher);
        jsonObject = JSONObject.parseObject(JSONObject.toJSONString(teacher));
        System.out.println(jsonObject);
    }

    /** 数组对象JSON ⇆ JSONObject ⇆ List<JavaBean> */
    @Test
    public void testJSONArray() {
        // 数组对象JSON → JSONArray
        jsonArray = JSONArray.parseArray(ARRAY_JSON);
        System.out.println(jsonArray);
        // JSONArray → 数组对象JSON
        jsonStr = JSONArray.toJSONString(jsonArray);
        jsonStr = jsonArray.toJSONString();
        System.out.println(jsonStr);

        // 数组对象JSON → List<JavaBean>
        students = JSONArray.parseArray(ARRAY_JSON, Student.class);
        System.out.println(students);
        // List<JavaBean> → 数组对象JSON
        jsonStr = JSONArray.toJSONString(students);
        System.out.println(jsonStr);

        // JSONArray → List<JavaBean>
        students = JSONArray.parseArray(jsonArray.toJSONString(), Student.class);
        students = jsonArray.toList(Student.class);
        System.out.println(students);
        // List<JavaBean> → JSONArray
        jsonArray = JSONArray.of(students);
        jsonArray = JSONArray.parseArray(JSONArray.toJSONString(students));
        System.out.println(jsonArray);
    }
}
