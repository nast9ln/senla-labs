package org.example.main.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.main.util.ExecuteUtil;

import java.util.Date;

@Data
@Slf4j
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    private Long id;
    private String gender;
    private String firstName;
    private String lastName;
    private Date birthday;
    private String city;
    private String phone;
    private String email;
    private String password;
    private byte[] avatar;
    private boolean isDeleted;

    public String execute() {
        return ExecuteUtil.execute();
    }
}
