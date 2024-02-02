package ru.labs.coffer.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ru.labs.coffer.enums.Gender;

import java.time.Instant;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "person")
public class Person extends SoftDeletableEntity {

    @Column(name = "login")
    private String login;

    @Column
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "birthday")
    private Instant birthday;

    @Column(name = "city")
    private String city;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "total_ratings")
    private Integer totalRatings;

    @Column(name = "password")
    private String password;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "person_role", joinColumns = @JoinColumn(name = "person_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "person", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<Advertisement> advertisements;

    public List<SimpleGrantedAuthority> getAuthorities() {
        return this.roles.stream().map(Role::getAuthorities).flatMap(List::stream).toList();
    }

}
