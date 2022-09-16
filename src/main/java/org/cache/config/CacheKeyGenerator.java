package org.cache.config;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component("myCacheKeyGenerator")
public class CacheKeyGenerator implements KeyGenerator {

    @Override
    public Object generate(Object target, Method method, Object... params) {
        StringBuilder sb = new StringBuilder();
        sb.append(target.getClass().getSimpleName()).append("-");
        sb.append(method.getName());

        for (Object param : params) {
            sb.append("-").append(param.getClass().getSimpleName()).append(":").append(param);
        }
        return sb.toString();
    }

}
