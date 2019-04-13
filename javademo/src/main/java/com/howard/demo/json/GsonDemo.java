package com.howard.demo.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.howard.demo.json.bean.Student;
import org.junit.Test;

import java.io.IOException;
import java.util.*;

/**
 * Gson使用
 * Created by Howard Yao on 2019/3/23.
 */
public class GsonDemo {
    /**
     * 属性注解@SerializedName指定序列化后的key
     */
    @Test
    public void testToJsonString() {
        Gson gson = new Gson();

        // list
        List<String> list = new ArrayList<>();
        list.add("apple");
        list.add("orange");
        String listStr = gson.toJson(list);
        System.out.println(listStr);

        // map
        Map<String, String> map = new HashMap<>();
        map.put("name", "howard");
        map.put("age", "25");
        String mapStr = gson.toJson(map);
        System.out.println(mapStr);

        Student stu1 = new Student(20, "tom", new Date(), 1);
        Student stu2 = new Student(25, "tony", new Date(), 2);
        Student stu3 = new Student(25, "tony", null, null);
        String stuJson1 = gson.toJson(stu1);
        System.out.println(stuJson1);
        String stuJson2 = gson.toJson(stu3);
        System.out.println(stuJson2);
        String stuJson3 = gson.toJson(Arrays.asList(stu1, stu2));
        System.out.println(stuJson3);
    }

    @Test
    public void testToJson() throws IOException {
        Gson gson = new Gson();
        Student stu1 = new Student(20, "tom", new Date(), 1);
        Student stu2 = new Student(25, "tony", new Date(), 2);
        String jsonStr = gson.toJson(stu1);
        String jsonListStr = gson.toJson(Arrays.asList(stu1, stu2));

        // json字符串转java对象1 比较麻烦 且对date类型支持不好
        //JsonReader reader = new JsonReader(new StringReader(jsonStr));
        //Student stuJson1 = new Student();
        // io异常
        /*reader.beginObject();
        while (reader.hasNext()) {
            String s = reader.nextName();
            switch (s) {
                case "name":
                    stuJson1.setName(reader.nextString());
                    break;
                case "age":
                    stuJson1.setAge(reader.nextInt());
                    break;
                case "clazz":
                    stuJson1.setClazz(reader.nextInt());
                    break;
                default:
                    break;
            }
        }*/
        // 这些方式貌似对特殊类型难以处理
        /*reader.endObject();
        System.out.println(stuJson1);*/

        // json字符串转java对象2
        Student stuJson2 = gson.fromJson(jsonStr, Student.class);
        System.out.println(stuJson2);

        // json字符串转list对象
        List<Student> studentList = gson.fromJson(jsonListStr, new TypeToken<List<Student>>() {}.getType());
        studentList.forEach(System.out::println);

        Student[] studentArr = gson.fromJson(jsonListStr, Student[].class);
        for (Student s : studentArr) {
            System.out.println(s);
        }

    }

    @Test
    public void testGsonBuilder() {
        Gson gson = new GsonBuilder()
                //序列化null
                .serializeNulls()
                // 设置日期时间格式，另有2个重载方法
                // 在序列化和反序化时均生效
                .setDateFormat("yyyy-MM-dd")
                .create();
        Student stu1 = new Student(20, "tom", new Date(), 1);
        Student stu2 = new Student(25, "tony", null, null);
        String stuJson1 = gson.toJson(stu1);
        System.out.println(stuJson1);
        String stuJson2 = gson.toJson(stu2);
        System.out.println(stuJson2);

        /*Gson gson = new GsonBuilder()
                //序列化null
                .serializeNulls()
                // 设置日期时间格式，另有2个重载方法
                // 在序列化和反序化时均生效
                .setDateFormat("yyyy-MM-dd")
                // 禁此序列化内部类
                .disableInnerClassSerialization()
                //生成不可执行的Json（多了 )]}' 这4个字符）
                .generateNonExecutableJson()
                //禁止转义html标签
                .disableHtmlEscaping()
                //格式化输出
                .setPrettyPrinting()
                .create();*/
    }
}
