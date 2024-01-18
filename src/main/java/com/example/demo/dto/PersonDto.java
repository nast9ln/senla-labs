package com.example.demo.dto;

import com.example.demo.enums.Gender;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonDto {
    @JsonProperty("id")
    private Long id;

    @NotNull
    @NotBlank
    @JsonProperty("login")
    private String login;

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
    private Set<RoleDto> roles;

}