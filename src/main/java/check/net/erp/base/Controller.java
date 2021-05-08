package check.net.erp.base;

import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RestController
@CrossOrigin
@Scope("prototype")
@RequestMapping
public @interface Controller {

    @AliasFor(annotation = RequestMapping.class)
    String value() default "";
}
