package org.example.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Table(name = "message")
@Entity
public class Message extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
