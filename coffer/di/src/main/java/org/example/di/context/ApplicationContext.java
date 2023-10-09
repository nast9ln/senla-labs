package org.example.di.context;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class ApplicationContext {

    private static final Map<String, Object> context = new ConcurrentHashMap<>();

    public static <T> Optional<T> getBean(Class<T> beanClass) {
        String className = beanClass.getSimpleName();
        className = className.substring(0, 1).toLowerCase() + className.substring(1);
        return Optional.of(beanClass.cast(context.get(className)));
    }

    public static Map<String, Object> getContext() {
        return context;
    }

    public static Collection<Object> values() {
        return context.values();
    }

}
