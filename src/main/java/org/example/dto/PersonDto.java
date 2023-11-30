package org.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.enums.Gender;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonDto {
    @JsonProperty("id")
    private Long id;

    @NotNull
    @JsonProperty("gender")
    private Gender gender;

    @NotNull
    @NotBlank
    @JsonProperty("firstName")
    private String firstName;

    @NotNull
    @NotBlank
    @JsonProperty("lastName")
    private String lastName;

    @NotNull
    @NotBlank
    @JsonProperty("birthday")
    private Instant birthday;

    @NotNull
    @NotBlank
    @JsonProperty("city")
    private String city;

    @NotNull
    @NotBlank
    @JsonProperty("phone")
    private String phone;

    @NotNull
    @NotBlank
    @JsonProperty("email")
    private String email;

    @NotNull
    @NotBlank
    @JsonProperty("password")
    private String password;

    @NotNull
    @NotBlank
    @JsonProperty("isDeleted")
    private boolean isDeleted;

    @JsonProperty("roles")
    private Set<RoleDto> roles = new HashSet<>();

    @JsonProperty("advertisementDto")
    private List<AdvertisementDto> advertisementDto = new ArrayList<>();

}
