package org.example.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.example.enums.CategoryType;

import javax.persistence.*;

@Getter
@Setter
@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "category")
public class Category extends AbstractEntity {

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private CategoryType type;

}
