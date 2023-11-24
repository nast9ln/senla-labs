package org.example.entity;

import javax.persistence.*;

import java.time.LocalDateTime;

@Table(name = "comment")
@Entity
public class Comment extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private Person person;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "advertisement_id")
    private Advertisement advertisement;
    @Column(name = "text")
    private String text;
    @Column(name = "created_data")
    private LocalDateTime createdData;
    @Column(name = "is_deleted")
    private boolean isDeleted;
}
