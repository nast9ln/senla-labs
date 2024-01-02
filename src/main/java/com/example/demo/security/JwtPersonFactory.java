//package com.example.demo.security;
//
//import com.example.demo.entity.Person;
//import com.example.demo.entity.Role;
//import lombok.NoArgsConstructor;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//
//import java.util.HashSet;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//@NoArgsConstructor
//public final class JwtPersonFactory {
//
//    public static JwtPerson create(Person person){
//        return new JwtPerson(
//                person.getId(),
//                person.getLogin(),
//                person.getFirstName(),
//                person.getLastName(),
//                person.getPassword(),
//                person.getEmail(),
//                person.isDeleted(),
//                setToGrantedAuthorities(new HashSet<>(person.getRoles()))
//        );
//    }
//
//    private static Set<GrantedAuthority> setToGrantedAuthorities(Set<Role> roles){
//        return roles.stream()
//                .map(role -> new SimpleGrantedAuthority(role.getName().name())
//                ).collect(Collectors.toSet());
//    }
//}
