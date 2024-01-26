package ru.labs.coffer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.*;

import static org.apache.commons.lang3.ObjectUtils.isEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class PageInfo implements Serializable {
    @Schema(description = "Индекс страницы начиная с 0",
            example = "0")
    private Integer page;
    @Schema(description = "Количество записей в странице",
            example = "20")
    private Integer size;
    private List<SortInstruction> sort;

    public Sort buildSortOptions() {
        if (isEmpty(sort)) Sort.unsorted();
        List<Sort.Order> orders = new ArrayList<>(sort.size());
        sort.forEach(sortInstruction -> orders.add(Sort.Direction.ASC.equals(
                sortInstruction.getDirection()) ?
                Sort.Order.asc(sortInstruction.getField()) :
                Sort.Order.desc(sortInstruction.getField())
        ));
        return Sort.by(orders);
    }

    public Pageable toPageable() {
        return PageRequest.of(Optional.ofNullable(this.page).orElse(0),
                Optional.ofNullable(this.size).orElse(20),
                CollectionUtils.isEmpty(this.sort) ? Sort.unsorted() : buildSortOptions());
    }

}
