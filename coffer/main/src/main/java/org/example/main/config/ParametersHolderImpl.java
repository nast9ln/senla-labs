package org.example.main.config;

import lombok.Data;
import org.example.di.annotation.Component;
import org.example.di.annotation.Value;

@Component
@Data
public class ParametersHolderImpl implements ParametersHolder {

    @Value("text")
    private String SOME_TEXT;

    public ParametersHolderImpl() {
    }

    public ParametersHolderImpl(String SOME_TEXT) {
        this.SOME_TEXT = SOME_TEXT;
    }

    public String getSomeText() {
        return SOME_TEXT;
    }
}
