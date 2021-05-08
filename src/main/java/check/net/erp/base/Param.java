package check.net.erp.base;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Param {

    @AliasFor("name")
    String value() default "";

    @AliasFor("value")
    String name() default "";

    boolean required() default false;

    String defaultValue() default "";

}
