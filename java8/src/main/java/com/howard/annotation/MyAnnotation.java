package com.howard.annotation;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.LOCAL_VARIABLE;

/**
 * 自定义注解
 * Created by Howard Yao on 2018/6/5.
 */
@Repeatable(MyAnnotations.class) //Repeatable支持重复注解
@Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE,TYPE_PARAMETER}) //TYPE_PARAMETER支持类型注解
@Retention(RetentionPolicy.SOURCE)
public @interface MyAnnotation {

    String value() default "howard";
}