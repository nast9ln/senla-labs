package org.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.enums.Gender;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonDto {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("gender")
    private Gender gender;
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("birthday")
    private Instant birthday;
    @JsonProperty("city")
    private String city;
    @JsonProperty("phone")
    private String phone;
    @JsonProperty("email")
    private String email;
    @JsonProperty("password")
    private String password;
    @JsonProperty("isDeleted")
    private boolean isDeleted;
    @JsonProperty("roles")
    private List<RoleDto> roles = new ArrayList<>();
    @JsonProperty("advertisementDto")
    private List<AdvertisementDto> advertisementDto = new ArrayList<>();

}
