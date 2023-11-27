package org.example.entity;


import javax.persistence.*;

@Table(name = "image")
@Entity
public class Image extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "advertisement_id")
    private Advertisement advertisement;
    @Column(name = "path")
    private byte[] path;
    @Column(name = "is_deleted")
    private boolean isDeleted;
}
