package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "message")
public class Message extends AbstractEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id")
    private Person senderId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "advertisement_id")
    private Advertisement advertisement;
    @Column(name = "text")
    private String text;
    @Column(name = "created_data")
    private LocalDateTime createdData;
}
