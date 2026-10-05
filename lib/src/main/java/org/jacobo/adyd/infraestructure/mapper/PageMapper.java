package org.jacobo.adyd.infraestructure.mapper;

import lombok.val;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Objects;

public class PageMapper {

    public static PageRequest of(Integer page, Integer size, String sort) {
        if(Objects.nonNull(sort)) {
            val sortSplitted = sort.split(",");
            val direction = Sort.Direction.valueOf(sortSplitted[1].toUpperCase());
            val field = sortSplitted[0];
            return PageRequest.of(page, size, Sort.by(direction, field));
        }
        return PageRequest.of(page, size);
    }

}
