package com.example.demo.entity;

import com.example.demo.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "role")
@EqualsAndHashCode(callSuper = true)
public class Role extends AbstractEntity {

    @Column
    @Enumerated(EnumType.STRING)
    private RoleEnum name;
}