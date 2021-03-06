package jar.hutool;

import cn.hutool.core.lang.TypeReference;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import l.demo.JsonDemo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static l.demo.Demo.p;

/**
 * JSONUtil
 * https://hutool.cn/docs/#/json/JSONUtil
 * https://apidoc.gitee.com/dromara/hutool/cn/hutool/json/JSONUtil.html
 * https://apidoc.gitee.com/dromara/hutool/cn/hutool/json/JSON.html
 * https://apidoc.gitee.com/dromara/hutool/cn/hutool/json/JSONObject.html
 * https://apidoc.gitee.com/dromara/hutool/cn/hutool/json/JSONArray.html
 *
 * @author ljh
 * created on 2020/11/20 8:43
 */
public class JSONUtilDemo implements JsonDemo {
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
        jsonObject = JSONUtil.parseObj(JSON_COMPLEX);
        courseJsonObject = jsonObject.getJSONObject("course");
        studentsJsonArray = jsonObject.getJSONArray("students");
        p(jsonObject);
        // JSONObject → 复杂对象JSON
        jsonStr = JSONUtil.toJsonStr(jsonObject);
        jsonStr = jsonObject.toJSONString(0);
        p(jsonStr + "\n");

        // 复杂对象JSON → JavaBean
        teacher = JSONUtil.toBean(JSON_COMPLEX, Teacher.class);
        teacher = JSONUtil.toBean(JSON_COMPLEX, new TypeReference<Teacher>() {
        }, false);
        p(teacher);
        // JavaBean → 复杂对象JSON
        jsonStr = JSONUtil.toJsonStr(teacher);
        p(jsonStr + "\n");

        // JSONObject → JavaBean
        teacher = JSONUtil.toBean(jsonObject, Teacher.class);
        teacher = JSONUtil.toBean(jsonObject, new TypeReference<Teacher>() {
        }, false);
        p(teacher);

        // JavaBean → JSONObject
        jsonObject = JSONUtil.parseObj(teacher);
        p(jsonObject + "\n");
    }

    /**
     * 数组对象JSON ⇆ JSONObject ⇆ List<JavaBean>
     */
    @Test
    public void testJSONArray() {
        // 数组对象JSON → JSONArray
        jsonArray = JSONUtil.parseArray(JSON_ARRAY);
        p(jsonArray);
        // JSONArray → 数组对象JSON
        jsonStr = JSONUtil.toJsonStr(jsonArray);
        jsonStr = jsonArray.toJSONString(0);
        p(jsonStr + "\n");

        // 数组对象JSON → List<JavaBean>
        students = JSONUtil.toBean(JSON_ARRAY, new TypeReference<ArrayList<Student>>() {
        }, false);
        p(students);
        // List<JavaBean> → 数组对象JSON
        jsonStr = JSONUtil.toJsonStr(students);
        p(jsonStr + "\n");

        // JSONArray → List<JavaBean>
        students = JSONUtil.toList(jsonArray, Student.class);
        p(students);
        // List<JavaBean> → JSONArray
        jsonArray = JSONUtil.parseArray(students);
        p(jsonArray + "\n");
    }
}
