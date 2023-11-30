package org.example.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Data
@SuperBuilder
@MappedSuperclass
@NoArgsConstructor
public abstract class SoftDeletableEntity extends AbstractEntity {

    @Column(name = "is_deleted")
    private boolean isDeleted;

}
