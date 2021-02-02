package cn.lusq.tools.javabase.reflex.demoOne;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * lusq
 * 2021/2/2 16:52
 */
@Target(value = {ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Test {

    String name() default "";
    String value() default "";


}
