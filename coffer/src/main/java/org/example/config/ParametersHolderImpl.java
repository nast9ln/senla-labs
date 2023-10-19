package org.example.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ParametersHolderImpl implements ParametersHolder {

    @Value("${t}")
    private String SOME_TEXT;

    public String getSomeText() {
        return SOME_TEXT;
    }

}
