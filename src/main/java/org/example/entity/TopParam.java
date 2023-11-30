package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "top_param")
public class TopParam extends AbstractEntity {

    @Column(name = "time_top_start")
    private LocalDateTime timeTopStart;
    @Column(name = "time_in_top")
    private int timeInTop;
    @Column(name = "is_top")
    private boolean isTop;
}
