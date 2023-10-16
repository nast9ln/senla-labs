package org.example.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class PersonDto {
    private Long id;
    private String gender;
    private String firstName;
    private String lastName;
    private String city;
    private String phone;
    private String email;
    private byte[] avatar;


}
