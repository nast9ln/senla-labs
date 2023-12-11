package org.example.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "advertisement")
public class Advertisement extends SoftDeletableEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

    @Column(name = "category_id")
    private Long categoryId;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "top_param_id", referencedColumnName = "id")
    private TopParam topParamId;

    @Column(name = "created_data")
    private Instant createdDate;

    @Column
    private int cost;
    @Column
    private String city;
    @Column
    private String header;
    @Column
    private String description;
    @Column
    private String status;
    @Column(name = "main_image_id")
    private Long mainImageId;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "advertisement", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "advertisement", cascade = CascadeType.ALL)
    private List<Image> images;

    @PrePersist
    public void prePersist() {
        this.createdDate = Instant.now();
    }


}
