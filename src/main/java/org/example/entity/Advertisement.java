package org.example.entity;

import javax.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "advertisement")
public class Advertisement extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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


    @Column(name = "is_deleted")
    private boolean isDeleted;

    @PrePersist
    public void prePersist() {
        this.createdDate = Instant.now();
    }


}
