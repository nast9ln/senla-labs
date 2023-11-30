package org.example.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Getter
@Setter
@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "person_role")
public class PersonRole extends AbstractEntity {

    @Column(name = "person_id")
    private Long personId;

    @Column(name = "role_id")
    private Long roleId;

}
