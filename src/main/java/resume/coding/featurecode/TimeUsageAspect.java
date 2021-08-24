package resume.coding.featurecode;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface Aspect {
    void before();
    void after();
    // getProxy
    static <U, T extends U> U getProxy(Class<U> iClass, Class<T> tClass, String[] aspects) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // 接口方法调用之前调用before
        // 接口方法调用之后调用after
        Object[] aspectObj = new Object[aspects.length];
        Method[] beforeMethods = new Method[aspects.length];
        Method[] afterMethods = new Method[aspects.length];
        for (int i = 0; i < aspectObj.length; i++) {
            var klazz = Class.forName(aspects[i]);
            aspectObj[i] = klazz.getDeclaredConstructor().newInstance();
            beforeMethods[i] = klazz.getMethod("before");
            afterMethods[i] = klazz.getMethod("after");
        }
        var proxy = Proxy.newProxyInstance(tClass.getClassLoader(),
                new Class[]{iClass},
                (proxy1, method, args) -> {
                    for (int i = 0; i < beforeMethods.length; i++) {
                        beforeMethods[i].invoke(aspectObj[i]);
                    }
                    var result = method.invoke(tClass.getDeclaredConstructor().newInstance(), args);
                    for (int i = 0; i < afterMethods.length; i++) {
                        afterMethods[i].invoke(aspectObj[i]);
                    }
                    return result;
                });
        return (U) proxy;
    }
}

//IOrder order = Aspect.getProxy(Order.class, "coding.proxy.TimeUsageAspect");
//order.pay();
//order.show();
public class TimeUsageAspect implements Aspect {

    long start;

    @Override
    public void before() {
        start = System.currentTimeMillis();
    }

    @Override
    public void after() {
        var usage = System.currentTimeMillis() - start;
        System.out.format("time usage : %dms\n", usage);
    }


}