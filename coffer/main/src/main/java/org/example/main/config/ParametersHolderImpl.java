package org.example.main.config;

import org.example.di.annotation.Component;
import org.example.di.annotation.Value;

@Component
public class ParametersHolderImpl implements ParametersHolder {

    @Value("text")
    public static String SOME_TEXT;

    public String getSomeText() {
        return SOME_TEXT;
    }
}
