package org.example.entity;


import javax.persistence.*;
import org.example.enums.CategoryType;

@Table(name = "category")
public class Category extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private CategoryType type;
}
