package resume.coding.featurecode;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

public class AspectTest {

    @Test
    public void testOrderAspect() throws InterruptedException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        IOrder order = Aspect.getProxy(IOrder.class, Order.class, new String[]{"resume.coding.featurecode.TimeUsageAspect","resume.coding.featurecode.TimeUsageAspect"});
        order.pay();
        order.show();
    }

    @Test
    public void testOrderAspect1() throws InterruptedException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        IOrder order = Order.getOrder();
        order.pay();
        order.show();
    }
}
