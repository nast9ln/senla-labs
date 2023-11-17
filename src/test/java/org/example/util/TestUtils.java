package org.example.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestUtils {

    public static LocalDateTime getLocalDateTime() {
        String dateString = "2023-11-16 17:32:51";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(dateString, formatter);
    }

}
