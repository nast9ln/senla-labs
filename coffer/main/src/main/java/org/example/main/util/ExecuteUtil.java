package org.example.main.util;

import org.example.di.annotation.Autowired;
import org.example.di.annotation.Component;
import org.example.main.config.ParametersHolder;
import org.example.main.config.ParametersHolderImpl;

@Component
public class ExecuteUtil {

    private ParametersHolder parametersHolder;
    public  String execute() {
        return parametersHolder.getSomeText();
    }

    @Autowired
    public void setParametersHolder(ParametersHolder parametersHolder) {
        this.parametersHolder = parametersHolder;
    }
}
