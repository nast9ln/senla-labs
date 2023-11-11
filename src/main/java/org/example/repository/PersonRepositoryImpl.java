package org.example.repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.example.AbstractDaoImpl;
import org.example.GenericDao;
import org.example.entity.Person;
import org.example.entity.Person_;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PersonRepositoryImpl extends AbstractDaoImpl<Person, Long> implements PersonRepository {
    public PersonRepositoryImpl() {
        super(Person.class);
    }

    @Override
    public List<Person> findAllByPersonName(String firstName, String lastName) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Person> query = criteriaBuilder.createQuery(Person.class);
        Root<Person> personRoot = query.from(Person.class);

        Predicate firstNamePredicate = criteriaBuilder.equal(personRoot.get(Person_.firstName), firstName);
        Predicate lastNamePredicate = criteriaBuilder.equal(personRoot.get(Person_.lastName), lastName);

        Predicate finalPredicate = criteriaBuilder.and(firstNamePredicate, lastNamePredicate);
        query.where(finalPredicate);

        return entityManager.createQuery(query).getResultList();
    }



//    @PersistenceContext
//    private final EntityManager em;

}
