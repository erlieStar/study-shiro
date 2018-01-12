package com.makenv.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//只能注解到方法上
@Target(ElementType.METHOD)
//在运行时起作用
@Retention(RetentionPolicy.RUNTIME)
public @interface SysLog {
    String value() default "";
}
