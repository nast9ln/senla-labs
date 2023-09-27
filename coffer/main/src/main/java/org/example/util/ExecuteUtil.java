package org.example.util;

import lombok.RequiredArgsConstructor;
import org.example.config.ParametersHolder;

@RequiredArgsConstructor
public class ExecuteUtil {


    public static String execute(){
        return ParametersHolder.SOME_TEXT;
    }

}
