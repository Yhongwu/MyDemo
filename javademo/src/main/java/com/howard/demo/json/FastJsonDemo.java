package com.howard.demo.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.*;
import com.howard.demo.json.bean.Student;
import com.howard.demo.json.bean.User;
import org.junit.Test;

import java.util.*;

/**
 * FastJson
 * Created by Howard Yao on 2019/3/23.
 */
public class FastJsonDemo {


    @Test
    public void testToJsonString() {
        // list
        List<String> list = new ArrayList<>();
        list.add("apple");
        list.add("orange");
        String listStr = JSON.toJSONString(list);
        System.out.println(listStr);

        // map
        Map<String, String> map = new HashMap<>();
        map.put("name", "howard");
        map.put("age", "25");
        String mapStr = JSON.toJSONString(map);
        System.out.println(mapStr);

        Student stu1 = new Student(20, "tom", new Date(), 1);
        Student stu2 = new Student(25, "tony", new Date(), 2);
        Student stu3 = new Student(25, "tony", null, null);

        // 序列化对象
        String objJsonStr = JSON.toJSONString(stu1);
        // 同时指定格式化时间
        String objJsonStrDate = JSON.toJSONStringWithDateFormat(stu1, "yyyy-MM-dd");
        System.out.println(objJsonStr);
        System.out.println(objJsonStrDate);

        // null值默认不序列化
        String objJsonStr2 = JSON.toJSONString(stu3);
        System.out.println(objJsonStr2);
        // 指定序列化null
        String objJsonStr3MapNull = JSON.toJSONString(stu3, SerializerFeature.WriteMapNullValue);
        System.out.println(objJsonStr3MapNull);
        // 序列化对象list
        String listJsonStr = JSON.toJSONString(Arrays.asList(stu1, stu2));
        System.out.println(listJsonStr);

        String objJsonStr3NullNumber = JSON.toJSONString(stu3, SerializerFeature.WriteNullNumberAsZero);
        System.out.println(objJsonStr3NullNumber);

        // 序列化为数组
        String objJsonStr4 = JSON.toJSONString(stu1, SerializerFeature.BeanToArray);
        System.out.println(objJsonStr4);
        String listJsonStr2 = JSON.toJSONString(Arrays.asList(stu1, stu2), SerializerFeature.BeanToArray);
        System.out.println(listJsonStr2);

        // String objJsonStr3NullNumber = JSON.toJSONString(stu3, SerializerFeature.QuoteFieldNames);
        // System.out.println(objJsonStr3NullNumber);
    }

    @Test
    public void testToJson() {
        Student stu1 = new Student(20, "tom", new Date(), 1);
        Student stu2 = new Student(25, "tony", new Date(), 2);
        String jsonStr = JSON.toJSONString(stu1);
        String jsonListStr = JSON.toJSONString(Arrays.asList(stu1, stu2));

        // json字符串转java对象1
        JSONObject jsonObject = JSON.parseObject(jsonStr);
        int age = jsonObject.getInteger("age");
        String name = jsonObject.getString("name");
        Date date = jsonObject.getDate("birth");
        Integer clazz = jsonObject.getInteger("clazz");
        Student stuJson1 = new Student(age, name, date, clazz);
        System.out.println(stuJson1);

        // json字符串转java对象2
        Student stuJson2 = JSON.parseObject(jsonStr, new TypeReference<Student>() {});
        System.out.println(stuJson2);

        // json字符串转java对象3
        Student stuJson3 = JSON.parseObject(jsonStr, Student.class);
        System.out.println(stuJson3);

        // json字符串转list对象
        List<Student> studentList = JSON.parseArray(jsonListStr, Student.class);
        // studentList.stream().forEach(string -> System.out.println(string));
        studentList.forEach(System.out::println);


    }

    @Test
    public void testCreateJsonObj() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "tom");
        jsonObject.put("age", 20);
        System.out.println(jsonObject.toJSONString());
        System.out.println(JSONObject.toJSONString(jsonObject));

        JSONArray jsonArray = new JSONArray();
        jsonArray.add("apple");
        jsonArray.add("orange");
        System.out.println(JSON.toJSONString(jsonArray));
        System.out.println(JSONObject.toJSONString(jsonArray));
        System.out.println(JSONArray.toJSONString(jsonArray));

    }

    /**
     * JSONField
     */
    @Test
    public void testJsonField() {
        // ordinal: 序列化顺序 name：指定json的命名 serialize：不被序列化 format：格式化
        // 此外 没有getter的属性也不会被序列化 没有指定序列化名称的情况下，以getter方法名截取后的属性名为序列化后的名字
        User user = new User(20, "tom", "n_tom", "r_tom", "192.168.1.118", new Date());
        String userStr = JSON.toJSONString(user);
        System.out.println(userStr);

        // 反过来 ip的转换与setter无关??
        User u = JSON.parseObject(userStr, User.class);
        System.out.println(u);
    }

    /**
     * ContextValueFilter
     */
    @Test
    public void testContextValueFilter() {
       ContextValueFilter contextValueFilter = new ContextValueFilter() {
            @Override
            public Object process(BeanContext context, Object object, String name, Object value) {
                if (Objects.equals("age", name)) {
                    return "no support";
                } else if (Objects.equals("name", name)) {
                    return ((String)value).toUpperCase();
                } else {
                    return null;
                }
            }
        };
        Student stu1 = new Student(20, "tom", new Date(), 1);
        Student stu2 = new Student(25, "tony", new Date(), 2);

        // 或 SerializeConfig.getGlobalInstance().addFilter(Student.class, contextValueFilter);
        String jsonString = JSON.toJSONString(Arrays.asList(stu1, stu2), contextValueFilter);
        System.out.println(jsonString);
    }

    /**
     * NameFilter
     */
    @Test
    public void testNameFilter() {
        NameFilter nameFilter = new NameFilter() {
            @Override
            public String process(Object object, String name, Object value) {
                return name.toUpperCase();
            }
        };
        SerializeConfig.getGlobalInstance().addFilter(Student.class, nameFilter);
        Student stu1 = new Student(20, "tom", new Date(), 1);
        String jsonString = JSON.toJSONString(stu1);
        System.out.println(jsonString);
    }
}
