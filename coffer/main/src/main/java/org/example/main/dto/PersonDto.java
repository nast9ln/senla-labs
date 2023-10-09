package org.example.main.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

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
