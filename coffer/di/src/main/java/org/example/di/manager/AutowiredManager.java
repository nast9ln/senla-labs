package org.example.di.manager;

import org.example.di.annotation.Autowired;
import org.example.di.context.ApplicationContext;
import org.example.di.exception.BeanNotFoundException;

import java.lang.reflect.*;

public class AutowiredManager {
    public void populateProperties() throws IllegalAccessException, InvocationTargetException, BeanNotFoundException, InstantiationException, NoSuchMethodException {
        fieldPopulateProperties();
        methodPopulateProperties();
        constructorPopulateProperties();
    }

    private void constructorPopulateProperties() throws IllegalAccessException, InvocationTargetException, InstantiationException {
        for (Object object : ApplicationContext.values()) {
            Constructor<?>[] constructors = object.getClass().getDeclaredConstructors();
            for (Constructor<?> constructor : constructors) {
                if (constructor.isAnnotationPresent(Autowired.class)) {
                    Object[] arguments = getArguments(constructor);
                    Object newInstance = constructor.newInstance(arguments);
                    ApplicationContext.getContext().put(object.getClass().getName(), newInstance);
                }
            }
        }
    }

    private void methodPopulateProperties() throws IllegalAccessException, InvocationTargetException {
        for (Object object : ApplicationContext.values()) {
            for (Method method : object.getClass().getMethods()) {
                if (method.isAnnotationPresent(Autowired.class)) {
                    Object[] arguments = getArguments(method);
                    method.invoke(object, arguments);
                }
            }
        }
    }

    private Object[] getArguments(Executable method) {
        method.setAccessible(true);
        Class<?>[] parameterTypes = method.getParameterTypes();
        Object[] arguments = new Object[parameterTypes.length];
        for (int i = 0; i < parameterTypes.length; i++) {
            for (Object dependency : ApplicationContext.values()) {
                if (parameterTypes[i].isAssignableFrom(dependency.getClass())) {
                    arguments[i] = dependency;
                    break;
                }
            }
        }
        return arguments;
    }

    private void fieldPopulateProperties() throws IllegalAccessException, BeanNotFoundException, InvocationTargetException, NoSuchMethodException {
        for (Object object : ApplicationContext.values()) {
            for (Field field : object.getClass().getDeclaredFields()) {
                if (field.isAnnotationPresent(Autowired.class)) {
                    field.setAccessible(true);
                    Class<?> fieldClass = field.getType();
                    Object dependency = findDependencyByInterface(fieldClass);
                    field.set(object, dependency);
                    getSetterName(object, field, dependency);
                }
            }
        }
    }


    private Object findDependencyByInterface(Class<?> interfaceClass) throws BeanNotFoundException {
        for (Object dependency : ApplicationContext.values()) {
            if (interfaceClass.isAssignableFrom(dependency.getClass())) {
                return dependency;
            }
        }
        throw new BeanNotFoundException();
    }


    private void getSetterName(Object object, Field field, Object dependency) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        String setterName = "set" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
        System.out.println("Setter name = " + setterName);
        Method setter = object.getClass().getMethod(setterName, dependency.getClass());
        setter.invoke(object, dependency);
    }
}