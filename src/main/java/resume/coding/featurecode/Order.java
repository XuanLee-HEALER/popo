package resume.coding.featurecode;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface IOrder {

    void pay() throws InterruptedException;

    void show();
}

@WithAspect({TimeUsageAspect.class, TimeUsageAspect.class})
public class Order implements IOrder{

    private Order() {}

    static IOrder getOrder() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Order o = new Order();
        Class<Order> clazz = Order.class;
        WithAspect aspects = clazz.getDeclaredAnnotation(WithAspect.class);
        if (aspects != null) {
            Class<Aspect>[] aspectsClass = aspects.value();
            Object[] aspectObj = new Object[aspectsClass.length];
            Method[] beforeMethods = new Method[aspectsClass.length];
            Method[] afterMethods = new Method[aspectsClass.length];
            for (int i = 0; i < aspectObj.length; i++) {
                aspectObj[i] = aspectsClass[i].getDeclaredConstructor().newInstance();
                beforeMethods[i] = aspectsClass[i].getMethod("before");
                afterMethods[i] = aspectsClass[i].getMethod("after");
            }
            var proxy = Proxy.newProxyInstance(clazz.getClassLoader(),
                    new Class[]{IOrder.class},
                    (proxy1, method, args) -> {
                        for (int i = 0; i < beforeMethods.length; i++) {
                            beforeMethods[i].invoke(aspectObj[i]);
                        }
                        var result = method.invoke(clazz.getDeclaredConstructor().newInstance(), args);
                        for (int i = 0; i < afterMethods.length; i++) {
                            afterMethods[i].invoke(aspectObj[i]);
                        }
                        return result;
                    });
            return (IOrder) proxy;
        }
        return o;
    }

    int state = 0;
    @Override
    public void pay() throws InterruptedException {
        Thread.sleep(50); // 模拟需要耗费时间
        this.state = 1;
    }

    @Override
    public void show() {
        System.out.println("order status:" + this.state);
    }


}
