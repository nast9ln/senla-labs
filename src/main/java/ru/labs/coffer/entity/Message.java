package ru.labs.coffer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@Getter
@Setter
@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "message")
public class Message extends AbstractEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sender_id")
    private Person sender;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "recipient_id")
    private Person recipient;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "advertisement_id")
    private Advertisement advertisement;

    @Column(name = "text")
    private String text;

    @Column(name = "created_date")
    private Instant createdDate;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @PrePersist
    public void prePersist() {
        this.createdDate = Instant.now();
    }
}
