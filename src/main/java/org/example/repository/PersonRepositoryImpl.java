package org.example.repository;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.example.AbstractDaoImpl;
import org.example.entity.Person;
import org.example.entity.Person_;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PersonRepositoryImpl extends AbstractDaoImpl<Person, Long> implements PersonRepository {
    public PersonRepositoryImpl() {
        super(Person.class);
    }

    @Override
    public List<Person> findByPersonName(String firstName, String lastName) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Person> query = criteriaBuilder.createQuery(Person.class);
        Root<Person> personRoot = query.from(Person.class);

        Predicate firstNamePredicate = criteriaBuilder.equal(personRoot.get(Person_.firstName), firstName);
        Predicate lastNamePredicate = criteriaBuilder.equal(personRoot.get(Person_.lastName), lastName);

        Predicate finalPredicate = criteriaBuilder.and(firstNamePredicate, lastNamePredicate);
        query.where(finalPredicate);

        return entityManager.createQuery(query).getResultList();
    }

    public Set<Person> findAllWithJPQL() {
        Query query = entityManager.createQuery("SELECT p FROM Person p LEFT JOIN FETCH p.roles LEFT JOIN p.advertisements");
        return (Set<Person>) query.getResultList().stream().collect(Collectors.toSet());
    }

    public Set<Person> findAllWithEntityGraph() {
        EntityGraph<Person> entityGraph = entityManager.createEntityGraph(Person.class);
        entityGraph.addAttributeNodes("roles");
        entityGraph.addAttributeNodes("advertisements");

        String jpql = "SELECT p FROM Person p";
        TypedQuery<Person> query = entityManager.createQuery(jpql, Person.class);
        query.setHint("javax.persistence.fetchgraph", entityGraph);

        return query.getResultList().stream().collect(Collectors.toSet());
    }

}
