package cinemaApp.interceptors;

import java.io.Serializable;
import javax.enterprise.context.Dependent;
import javax.interceptor.*;
import java.lang.reflect.Parameter;

@LoggedInvocation
@Interceptor
public class MethodLogger implements Serializable {
    @AroundInvoke
    public Object logInfo(InvocationContext context) throws Exception {

        Object obj = context.proceed();

        System.out.println("Method called: " + context.getMethod().getName());
        Parameter[] parameters = context.getMethod().getParameters();
        if (parameters.length == 0) {
            System.out.println("Method was called without parameters");
        } else {
            System.out.println("Method was called with parameters");
        }
        return obj;
    }
}
