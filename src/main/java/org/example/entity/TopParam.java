package org.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
