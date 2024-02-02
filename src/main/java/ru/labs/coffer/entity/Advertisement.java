package ru.labs.coffer.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Where;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Entity
@SuperBuilder
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Table(name = "advertisement")
@Where(clause = "is_deleted = false")
public class Advertisement extends SoftDeletableEntity {

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "top_param_id", referencedColumnName = "id")
    private TopParam topParam;

    @Column(name = "created_date")
    private Instant createdDate;

    @Column
    private Integer cost;
    @Column
    private String city;
    @Column
    private String header;
    @Column
    private String description;
    @Column
    private String status;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "main_image_id", referencedColumnName = "id")
    private Image mainImage;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "advertisement", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "advertisement", cascade = CascadeType.ALL)
    private List<Image> images;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "advertisement", cascade = CascadeType.ALL)
    private List<Message> messages;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @PrePersist
    public void prePersist() {
        this.createdDate = Instant.now();
    }
}