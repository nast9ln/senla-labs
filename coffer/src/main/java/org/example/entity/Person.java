package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.enums.Gender;
import org.example.util.ExecuteUtil;

import java.time.LocalDateTime;


@Data
@Slf4j
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    private Long id;
    private Gender gender;
    private String firstName;
    private String lastName;
    private LocalDateTime birthday;
    private String city;
    private String phone;
    private String email;
    private String password;
    private boolean isDeleted;

    public String execute(ExecuteUtil executeUtil) {
        return executeUtil.execute();
    }
}
