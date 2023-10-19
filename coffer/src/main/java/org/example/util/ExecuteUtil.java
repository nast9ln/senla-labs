package org.example.util;

import lombok.RequiredArgsConstructor;
import org.example.config.ParametersHolder;

@RequiredArgsConstructor
public class ExecuteUtil {
    private ParametersHolder parametersHolder;
    public  String execute() {
        return parametersHolder.getSomeText();
    }

}
