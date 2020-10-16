package jar.google.gson;

import com.google.gson.*;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import l.demo.Demo;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

/**
 * Gson使用指南：https://www.jianshu.com/p/e740196225a4
 */
public class GsonDemo extends Demo {

    @Test
    public void test() {
        /* Gson的基本用法 */
        Gson gson = new Gson();
        String jsonStr = "100";
        // 反序列化，将Json字符串转换成对象
        int i = gson.fromJson(jsonStr, int.class);
        // 序列化，将对象转换成Json字符串
        jsonStr = gson.toJson(i);


        /* 属性重命名 @SerializedName 注解的使用 */
        User user = new User("怪盗基德", 24, "kidou@163.com");
        String jsonUser = gson.toJson(user);
        p(jsonUser); // {"name":"怪盗基德","age":24,"emailAddress":"ikidou@163.com"}
        String jsonUser2 = "{\"name\":\"怪盗基德\",\"age\":24,\"email\":\"kidou@163.com\"}";
        user = gson.fromJson(jsonUser2, User.class);
        p(user.emailAddress); // kidou@163.com
        p("--------------------");


        /* 将Json字符串转换成JsonObject对象 */
        JsonObject jo = new JsonParser().parse(jsonUser).getAsJsonObject();
        p(jo); // {"name":"怪盗基德","age":24,"emailAddress":"kidou@163.com"}
        for (Entry<String, JsonElement> entry : jo.entrySet()) {
            p(entry.getKey() + ":" + entry.getValue());
        }
        p("--------------------");

        /* Gson中使用泛型 */
        String jsonArray = "[\"Android\",\"Java\",\"PHP\"]";
        // 解析成数组
        String[] strings = gson.fromJson(jsonArray, String[].class);
        p("数组:" + Arrays.toString(strings));
        // 解析成List，Gson为我们提供了TypeToken来实现对泛型的支持
        List<String> stringList = gson.fromJson(jsonArray, new TypeToken<List<String>>() {
        }.getType());
        p("List:");
        for (String s : stringList) {
            p(s);
        }
        p("--------------------");


        /* 将JsonArray类型的Json字符串解析成对象 */
        JsonArray Jarray = new JsonParser().parse(jsonArray).getAsJsonArray();
        p(Jarray); // ["Android","Java","PHP"]


        /* 获取JsonObject中指定key值对应的JsonArray对象 */
        String json = "{\"pids\":[\"1\",\"2\",\"3\"]}";
        JsonObject jo2 = new JsonParser().parse(json).getAsJsonObject();
        p(jo2.getAsJsonArray("pids").get(1).getAsString()); // 2; toString()返回"2",getAsString()返回2
        p("--------------------");


        /* JsonObject对象转化成HashMap */
        HashMap<String, Object> map = new HashMap<>();
        jsonObject2Map(jo, map);
        for (Entry<String, Object> entry : map.entrySet()) {
            p(entry.getKey() + ":" + entry.getValue());
        }

    }

    // JsonObject对象转化成HashMap
    private static void jsonObject2Map(JsonObject jo, HashMap<String, Object> map) {
        for (Entry<String, JsonElement> entry : jo.entrySet()) {
            if (entry.getValue().isJsonArray()) {
                JsonArray array = (JsonArray) entry.getValue();
                List<HashMap<String, Object>> list = new ArrayList<>();
                for (JsonElement obj : array) {
                    HashMap<String, Object> ch = new HashMap<>();
                    jsonObject2Map(obj.getAsJsonObject(), ch);
                    list.add(ch);
                }
                map.put(entry.getKey(), list);
            } else {
                if (entry.getValue().isJsonObject()) {
                    HashMap<String, Object> ch = new HashMap<>();
                    map.put(entry.getKey(), ch);
                    jsonObject2Map(entry.getValue().getAsJsonObject(), ch);
                } else {
                    map.put(entry.getKey(), entry.getValue().getAsString());
                }
            }
        }
    }

    @AllArgsConstructor
    private static class User {
        public String name;
        public int age;
        @SerializedName(value = "emailAddress", alternate = {"email", "email_address"})
        // 当上面的三个属性(email_address、email、emailAddress)都中出现任意一个时均可以得到正确的结果。
                String emailAddress;
    }
}