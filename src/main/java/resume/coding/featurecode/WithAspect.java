package resume.coding.featurecode;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//@Aspect(TimeUsageAspect)
//public class Order {
//    //...
//}
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface WithAspect {

    Class[] value();
}