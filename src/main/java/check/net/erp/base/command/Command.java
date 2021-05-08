package check.net.erp.base.command;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Service
@Scope("prototype")
public @interface Command {
}
