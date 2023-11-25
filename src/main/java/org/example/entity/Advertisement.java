package org.example.entity;

import javax.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

    @Column(name = "category_id")
    private Long categoryId;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "top_param_id", referencedColumnName = "id")
    private TopParam topParamId;

    @Column(name = "created_data")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdDate;

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
    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "advertisement")
    private List<Comment> comments = new ArrayList<>();

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "advertisement")
    private List<Image> image = new ArrayList<>();


    @Column(name = "is_deleted")
    private boolean isDeleted;

}
