package ru.labs.coffer.util;

import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.metamodel.Attribute;
import jakarta.persistence.metamodel.SingularAttribute;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.jpa.domain.Specification;

import java.util.*;
import java.util.function.Function;

import static org.apache.commons.lang3.ObjectUtils.isEmpty;

@UtilityClass
public class SpecificationUtils {
    public static <T, V> Specification<T> is(String field, V value) {
        return nullValueCheck(value, (root, query, builder) -> builder.equal(createPath(field, root), value));
    }

    public static <T, V> Specification<T> isNot(String field, V value) {
        return nullValueCheck(value, (root, query, builder) -> builder.notEqual(createPath(field, root), value));
    }

    public static <T> Specification<T> like(String field, String value) {
        return nullValueCheck(value, (root, query, builder) -> builder.like(createPath(field, root), "%" + value + "%"));
    }

    public static <T> Specification<T> notLike(String field, String value) {
        return nullValueCheck(value, (root, query, builder) -> builder.notLike(createPath(field, root), "%" + value + "%"));
    }

    public static <T> Specification<T> insensitiveLike(String field, String value) {
        return nullValueCheck(value, (root, query, builder) -> builder.like(builder.lower(createPath(field, root)), "%" + (value != null ? value.toLowerCase() : "") + "%"));
    }

    public static <T, V> Specification<T> in(String field, Collection<V> value) {
        return nullValueCheck(value, (root, query, builder) -> createPath(field, root).in(value));
    }

    public static <T, V> Specification<T> notIn(String field, Collection<V> value) {
        return nullValueCheck(value, (root, query, builder) -> builder.not(createPath(field, root).in(value)));
    }

    public static <T, V extends Comparable<? super V>> Specification<T> between(String field, V firstValue, V secondValue) {
        if (ObjectUtils.allNotNull(firstValue, secondValue)) {
            return (root, query, builder) -> builder.between(createPath(field, root), firstValue, secondValue);

        } else if (ObjectUtils.allNotNull(firstValue)) {
            return (root, query, builder) -> builder.greaterThanOrEqualTo(createPath(field, root), firstValue);

        } else if (ObjectUtils.allNotNull(secondValue)) {
            return (root, query, builder) -> builder.lessThanOrEqualTo(createPath(field, root), secondValue);

        }

        return emptySpecification();
    }

    public static <T, V> Specification<T> is(SingularAttribute<T, V> field, V value) {
        return nullValueCheck(value, (root, query, builder) -> builder.equal(root.get(field), value));
    }

    public static <T, V> Specification<T> isNot(SingularAttribute<T, V> field, V value) {
        return nullValueCheck(value, (root, query, builder) -> builder.notEqual(root.get(field), value));
    }

    public static <T> Specification<T> like(SingularAttribute<T, String> field, String value) {
        return nullValueCheck(value, (root, query, builder) -> builder.like(root.get(field), "%" + value + "%"));
    }

    public static <T> Specification<T> notLike(SingularAttribute<T, String> field, String value) {
        return nullValueCheck(value, (root, query, builder) -> builder.notLike(root.get(field), "%" + value + "%"));
    }

    public static <T> Specification<T> insensitiveLike(SingularAttribute<T, String> field, String value) {
        return nullValueCheck(value, (root, query, builder) -> builder.like(builder.lower(root.get(field)), "%" + (value != null ? value.toLowerCase() : "") + "%"));
    }

    public static <T> Specification<T> insensitiveNotLike(SingularAttribute<T, String> field, String value) {
        return nullValueCheck(value, (root, query, builder) -> builder.notLike(builder.lower(root.get(field)), "%" + (value != null ? value.toLowerCase() : "") + "%"));
    }

    public static <T, V> Specification<T> in(SingularAttribute<T, V> field, Collection<V> value) {
        return nullValueCheck(value, (root, query, builder) -> root.get(field).in(value));
    }

    public static <T, V extends Comparable<? super V>> Specification<T> between(SingularAttribute<T, V> field, V firstValue, V secondValue) {
        if (ObjectUtils.allNotNull(firstValue, secondValue)) {
            return (root, query, builder) -> builder.between(root.get(field), firstValue, secondValue);
        } else if (ObjectUtils.allNotNull(firstValue)) {
            return (root, query, builder) -> builder.greaterThanOrEqualTo(root.get(field), firstValue);
        } else if (ObjectUtils.allNotNull(secondValue)) {
            return (root, query, builder) -> builder.lessThanOrEqualTo(root.get(field), secondValue);
        }

        return emptySpecification();
    }

    public static <T> Specification<T> emptySpecification() {
        return (root, query, builder) -> null;
    }

    public static <T, V> Path<V> createPath(String fieldPath, Root<T> root) {
        if (isEmpty(fieldPath)) {
            throw new RuntimeException("fieldPath null");
        }
        var fields = new ArrayDeque<>(List.of(fieldPath.split("[.]")));
        if (fields.size() == 1) {
            return root.get(fields.poll());
        }

        var lastField = fields.pollLast();
        var join = root.<Object, V>join(fields.pollFirst(), JoinType.INNER);
        for (String field : fields) {
            join = join.join(field, JoinType.INNER);
        }
        return join.get(lastField);
    }

    public static String createPath(Attribute<?, ?>... fields) {
        return String.join(".", mapList(List.of(fields), Attribute::getName));
    }

    private static <T, R> List<R> mapList(List<T> list, Function<T, R> func) {
        if (list == null) {
            return Collections.emptyList();
        }

        var result = new ArrayList<R>();
        for (T t : list) {
            result.add(func.apply(t));
        }
        return result;
    }

    private static <T, V> Specification<T> nullValueCheck(V value, Specification<T> specification) {
        if (isEmpty(value)) {
            return emptySpecification();
        }
        return specification;
    }

}