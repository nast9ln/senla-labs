package ru.labs.coffer.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@Getter
@Setter
@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "top_param")
@EqualsAndHashCode(callSuper = true)
public class TopParam extends AbstractEntity {

    @Column(name = "time_top_start")
    private Instant timeTopStart;
    @Column(name = "time_in_top")
    private Integer timeInTop;

}

