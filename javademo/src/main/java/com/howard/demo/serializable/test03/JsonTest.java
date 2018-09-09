package com.howard.demo.serializable.test03;

import com.alibaba.fastjson.JSON;
import com.baidu.bjf.remoting.protobuf.Codec;
import com.baidu.bjf.remoting.protobuf.ProtobufProxy;
import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * 其它序列化方式（第三方封装）
 * 主流的序列化技术：
 * JSON/Hessian(2) /xml/protobuf/kryo/MsgPack/FST/thrift/protostuff/Avro
 *
 * 从序列化的速度和单个对象的大小进行对比
 * Created by Howard Yao on 2018/8/5.
 */
public class JsonTest {
    public static void main(String[] args) throws IOException {
        JacksonTest();
        FastjsonTest();
        JprotobufTest();
        HessianTest();
    }

    /**
     * jackson-mapper-asl
     * @throws IOException
     */
    private static void JacksonTest() throws IOException {
        Student student = initObject();
        ObjectMapper objectMapper = new ObjectMapper();

        byte[] writeBytes=null;
        Long start=System.currentTimeMillis();
        for (int i = 0 ; i < 10000; i ++ ) {
            writeBytes=objectMapper.writeValueAsBytes(student);
        }
        System.out.println("Json序列化："+(System.currentTimeMillis()-start)+"ms : " +
                "总大小->"+ writeBytes.length);

        Student stu = objectMapper.readValue(writeBytes,Student.class);
        System.out.println(stu);
    }

    /**
     * 阿里的Fastjson
     * @throws IOException
     */
    private static void FastjsonTest() throws IOException {
        Student student = initObject();
        String text=null;
        Long start=System.currentTimeMillis();
        for(int i=0;i<10000;i++){
            text= JSON.toJSONString(student);
        }
        System.out.println("fastjson序列化："+ (System.currentTimeMillis()-start) +"ms : " +
                "总大小->"+ text.getBytes().length);

        Student stu = JSON.parseObject(text,Student.class);
        System.out.println(stu);
    }

    /**
     * 百度基于protobuf封装的Jprotobuf
     * 需对序列化的类加上注解 见Student类
     * @throws IOException
     */
    private static void JprotobufTest() throws IOException {
        Student student = initObject();
        Codec<Student> personCodec = ProtobufProxy.create(Student.class, false);

        Long start = System.currentTimeMillis();
        byte[] bytes = null;
        for (int i = 0; i < 10000; i++) {
            bytes = personCodec.encode(student);
        }
        System.out.println("protobuf序列化：" + (System.currentTimeMillis() - start) + "ms : " +
                "总大小->" + bytes.length);

        Student stu = personCodec.decode(bytes);
        System.out.println(stu);

    }

    /**
     * Hessian序列化
     * @throws IOException
     */
    private static void HessianTest() throws IOException {
        Student student = initObject();
        ByteArrayOutputStream os=new ByteArrayOutputStream();
        HessianOutput ho=new HessianOutput(os);
        Long start=System.currentTimeMillis();
        for(int i = 0;i < 10000;i++ ){
            ho.writeObject(student);
            //单独一个大小 来对比
            if(i == 0){
                System.out.println(os.toByteArray().length);
            }
        }
        System.out.println("Hessian序列化："+(System.currentTimeMillis()-start)+"ms : " +
                "总大小->"+os.toByteArray().length);

        HessianInput hi=new HessianInput(new ByteArrayInputStream(os.toByteArray()));
        Student stu=(Student)hi.readObject();
        System.out.println(stu);
    }

    private static Student initObject() {
        Student student = new Student();
        student.setAge(23);
        student.setName("howard");
        return student;
    }
}
