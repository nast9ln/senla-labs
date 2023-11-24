package org.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Builder
@Data
@Table(name = "top_param")
@AllArgsConstructor
@NoArgsConstructor
public class TopParam extends AbstractEntity {
    @Id
    private Long id;
    @Column(name = "time_top_start")
    private LocalDateTime timeTopStart;
    @Column(name = "time_in_top")
    private int timeInTop;
    @Column(name = "is_top")
    private boolean isTop;
}
