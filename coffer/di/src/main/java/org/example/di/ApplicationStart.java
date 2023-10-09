package org.example.di;

import org.example.di.exception.BeanNotFoundException;
import org.example.di.factory.BeanFactory;
import org.example.di.manager.AutowiredManager;
import org.example.di.manager.ValueManager;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class ApplicationStart {

    public static void run (String path) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, IOException, BeanNotFoundException, InstantiationException {
        new BeanFactory(path).instantiate();
        new AutowiredManager().populateProperties();
        new ValueManager(path).processValueAnnotation();
    }
//    public ApplicationStart(String path) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, IOException, BeanNotFoundException, InstantiationException {
//        new BeanFactory(path).instantiate();
//        new AutowiredManager().populateProperties();
//        new ValueManager(path).processValueAnnotation();
//    }
}
