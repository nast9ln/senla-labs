package ru.labs.coffer.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@Getter
@Setter
@ToString
@SuperBuilder
@MappedSuperclass
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public abstract class SoftDeletableEntity extends AbstractEntity {

    @Column(name = "is_deleted")
    private Boolean isDeleted;
}


