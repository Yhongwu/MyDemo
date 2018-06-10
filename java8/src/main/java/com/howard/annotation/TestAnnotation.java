package com.howard.annotation;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * 重复注解与类型注解
 * Created by Howard Yao on 2018/6/5.
 */
public class TestAnnotation {

    @MyAnnotation("java")
    @MyAnnotation("web")
    public void test() {

    }
    @MyAnnotation("java")
    @MyAnnotation("web")
    public void test1(@MyAnnotation String str) {

    }

    @Test
    public void test2() throws NoSuchMethodException {
        Class<TestAnnotation> aClass = TestAnnotation.class;
        Method method = aClass.getMethod("test");
        System.out.println(method);
        MyAnnotation[] annotationsByType = method.getAnnotationsByType(MyAnnotation.class);
        Annotation[] declaredAnnotations = method.getDeclaredAnnotations();
        System.out.println(declaredAnnotations.length);
        System.out.println(annotationsByType.length);
        for (MyAnnotation myAnnotation : annotationsByType) {
            System.out.println(myAnnotation.value());
        }
    }
}
