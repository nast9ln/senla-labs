package org.example.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.example.enums.RoleEnum;

@Data
@Builder
@AllArgsConstructor
@Entity
@Table(name = "role")
public class Role extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @Enumerated(EnumType.STRING)
    private RoleEnum name;

    public Role() {
    }

    public Role(RoleEnum name) {
        this.name = name;
    }

}
