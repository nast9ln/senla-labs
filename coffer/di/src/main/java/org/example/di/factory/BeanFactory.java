package org.example.di.factory;

import org.example.di.annotation.Component;
import org.example.di.context.ApplicationContext;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Objects;


public class BeanFactory {

    private final String basePackage;

    public BeanFactory(String basePackage) {
        this.basePackage = basePackage;
    }

    public BeanFactory instantiate() {
        try {
            ClassLoader classLoader = ClassLoader.getSystemClassLoader();
            String path = basePackage.replace('.', '/');
            Enumeration<URL> resources = classLoader.getResources(path);

            while (resources.hasMoreElements()) {
                URL resource = resources.nextElement();
                File file = new File(resource.toURI());

                if (file.isDirectory()) {
                    scan(basePackage, file);
                }
            }
        } catch (IOException | URISyntaxException | ClassNotFoundException | InvocationTargetException |
                 IllegalAccessException | NoSuchMethodException | InstantiationException e) {
            e.printStackTrace();
        }
        return this;
    }

    private void scan(String basePackage, File file) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        for (File classFile : Objects.requireNonNull(file.listFiles())) {
            if (classFile.isDirectory()) {
                scan(basePackage + "." + classFile.getName(), classFile);
            } else if (classFile.getName().endsWith(".class")) {
                String fileName = classFile.getName();
                String className = fileName.substring(0, fileName.lastIndexOf("."));

                Class<?> classObject = Class.forName(basePackage + "." + className);
                if (classObject.isAnnotationPresent(Component.class)) {
                    System.out.println("Component: " + classObject);
                    Object instance = classObject.newInstance();
                    String beanName = className.substring(0, 1).toLowerCase() + className.substring(1);
                    ApplicationContext.getContext().put(beanName, instance);
                }
            }
        }
    }
}