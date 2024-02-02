package ru.labs.coffer.dto.search;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.metamodel.SingularAttribute;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import ru.labs.coffer.dto.PageInfo;
import ru.labs.coffer.entity.AbstractEntity;
import ru.labs.coffer.entity.AbstractEntity_;

import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;

import static ru.labs.coffer.util.SpecificationUtils.in;
import static ru.labs.coffer.util.SpecificationUtils.is;

@Data
@NoArgsConstructor
public class BaseSearchDto implements Serializable {

    private Long id;
    private Collection<Long> ids;
    private PageInfo pageInfo;


    @JsonIgnore
    public <T extends AbstractEntity> Specification<T> getBaseSpecification() {
        return is((SingularAttribute<T, Long>) AbstractEntity_.id, this.id)
                .and(in(AbstractEntity_.ID, this.ids));
    }

    public Pageable getPageable() {
        return Optional.ofNullable(pageInfo).map(PageInfo::toPageable).orElse(Pageable.ofSize(20));
    }

}
