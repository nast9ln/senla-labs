package org.example.di.manager;

import org.example.di.annotation.Value;
import org.example.di.context.ApplicationContext;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.Enumeration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ValueManager {
    private Map<String, String> properties = new ConcurrentHashMap<>();
    private final static String  propertyFilePath = "/application.properties";
    public ValueManager(String basePackage) throws IOException {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        basePackage = basePackage.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(basePackage);
        if (resources.hasMoreElements()){
            URL resourceUrl = resources.nextElement();
             basePackage = resourceUrl.getFile();
        }
        String path = (basePackage.substring(0,basePackage.indexOf("target"))) + "src/main/resources" + propertyFilePath;

        readProperties(path);

    }

    private void readProperties(String path) {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("=");
                if (parts.length == 2) {
                    String key = parts[0].trim();
                    String value = parts[1].trim();
                    properties.put(key, value);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void processValueAnnotation() {
        for (Object object : ApplicationContext.values()) {
            for (Field field : object.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Value.class)) {
                field.setAccessible(true);
                Value valueAnnotation = field.getAnnotation(Value.class);
                String value = valueAnnotation.value();
                String resolvedValue = resolveValue(value);
                setValue(object, field, resolvedValue);
            }
        }
    }
    }
    private String resolveValue(String value) {
        return properties.get(value);
    }

    private void setValue(Object bean, Field field, String value) {
        try {
            field.set(bean, convertValue(field.getType(), value));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    private Object convertValue(Class<?> type, String value) {
        if (type == String.class) {
            return value;
        } else if (type == int.class || type == Integer.class) {
            return Integer.parseInt(value);
        } else if (type == boolean.class || type == Boolean.class) {
            return Boolean.parseBoolean(value);
        } else if (type == double.class || type == Double.class) {
            return Double.parseDouble(value);
        } else return new RuntimeException();
    }
}
