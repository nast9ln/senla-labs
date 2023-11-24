package org.example.entity;

import javax.persistence.*;
import lombok.*;
import org.example.enums.Gender;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "person")
public class Person extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column
    private LocalDate birthday;
    @Column
    private String city;
    @Column
    private String phone;
    @Column
    private String email;
    @Column
    private String password;
    @Column(name = "is_deleted")
    private boolean isDeleted;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "person_role", joinColumns = @JoinColumn(name = "person_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "person", fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Advertisement> advertisements = new HashSet<>();
}
