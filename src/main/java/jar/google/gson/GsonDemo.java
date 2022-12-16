package jar.google.gson;

import com.google.common.collect.Maps;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import l.demo.Demo;
import l.demo.JsonDemo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

/**
 * <a href="https://www.jianshu.com/p/e740196225a4">Gson 使用指南</a>
 *
 * @author ljh
 * @since 2020/10/25 2:16
 */
public class GsonDemo extends Demo implements JsonDemo {

    Gson gson = new Gson();
    JsonObject jsonObject;
    JsonArray jsonArray;
    String jsonStr;
    Teacher teacher;
    List<Student> students;
    JsonObject courseJsonObject;
    JsonArray studentsJsonArray;

    /**
     * 复杂/简单对象JSON ⇆ JsonObject ⇆ JavaBean
     */
    @Test
    public void testJSONObject() {
        // 复杂对象JSON → JsonObject
        jsonObject = JsonParser.parseString(JSON_COMPLEX).getAsJsonObject();
        courseJsonObject = jsonObject.getAsJsonObject("course");
        studentsJsonArray = jsonObject.getAsJsonArray("students");
        p(jsonObject);
        // JsonObject → 复杂对象JSON
        jsonStr = gson.toJson(jsonObject);
        p(jsonStr + "\n");

        // 复杂对象JSON → JavaBean
        teacher = gson.fromJson(JSON_COMPLEX, Teacher.class);
        p(teacher);
        // JavaBean → 复杂对象JSON
        jsonStr = gson.toJson(teacher);
        p(jsonStr + "\n");

        // JsonObject → JavaBean
        teacher = gson.fromJson(jsonObject, Teacher.class);
        p(teacher);

        // JavaBean → JsonObject
        jsonObject = (JsonObject) gson.toJsonTree(teacher);
        p(jsonObject + "\n");
    }

    /**
     * 数组对象JSON ⇆ JSONObject ⇆ List<JavaBean>
     */
    @Test
    public void testJSONArray() {
        // 数组对象JSON → JSONArray
        jsonArray = JsonParser.parseString(JSON_ARRAY).getAsJsonArray();
        p(jsonArray);
        // JSONArray → 数组对象JSON
        jsonStr = gson.toJson(jsonArray);
        p(jsonStr + "\n");

        // 数组对象JSON → List<JavaBean>
        students = gson.fromJson(JSON_ARRAY, new TypeToken<List<Student>>() {
        }.getType());
        p(students);
        // List<JavaBean> → 数组对象JSON
        jsonStr = gson.toJson(students);
        p(jsonStr + "\n");

        // JSONArray → List<JavaBean>
        students = gson.fromJson(jsonArray, new TypeToken<List<Student>>() {
        }.getType());
        p(students);
        // List<JavaBean> → JSONArray
        jsonArray = (JsonArray) gson.toJsonTree(students);
        p(jsonArray + "\n");
    }

    /**
     * &#064;SerializedName 字段别名
     */
    @Test
    public void testSerializedName() {
        p(gson.fromJson(JSON_PLAIN, Student.class));
    }

    @Test
    public void testJsonObjectToMap() {
        jsonObject = JsonParser.parseString(JSON_COMPLEX).getAsJsonObject();
        p(jsonObject2Map(jsonObject));
    }

    /**
     * JsonObject → Map
     */
    private HashMap<String, Object> jsonObject2Map(JsonObject jo) {
        HashMap<String, Object> map = Maps.newHashMap();
        for (Entry<String, JsonElement> entry : jo.entrySet()) {
            if (entry.getValue().isJsonArray()) {
                JsonArray array = (JsonArray) entry.getValue();
                List<HashMap<String, Object>> list = new ArrayList<>();
                for (JsonElement obj : array) {
                    HashMap<String, Object> ch = jsonObject2Map(obj.getAsJsonObject());
                    list.add(ch);
                }
                map.put(entry.getKey(), list);
            } else {
                if (entry.getValue().isJsonObject()) {
                    map.put(entry.getKey(), jsonObject2Map(entry.getValue().getAsJsonObject().getAsJsonObject()));
                } else {
                    map.put(entry.getKey(), entry.getValue().getAsString());
                }
            }
        }
        return map;
    }
}
