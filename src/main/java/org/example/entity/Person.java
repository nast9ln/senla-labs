package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.enums.Gender;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


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
    private LocalDate birthday;
    private String city;
    private String phone;
    private String email;
    private String password;
    private boolean isDeleted;
    private List<Role> roles = new ArrayList<>();
    private List<Advertisement> advertisements = new ArrayList<>();
}
