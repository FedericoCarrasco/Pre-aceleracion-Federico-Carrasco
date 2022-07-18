package com.alkemy.disney.repository.specification;

import com.alkemy.disney.dto.MovieFiltersDTO;
import com.alkemy.disney.entity.MovieEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class MovieSpecifications {

    public Specification<MovieEntity> getByFilters(MovieFiltersDTO filtersDTO){
        return ((root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasLength(filtersDTO.getName())){
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("title")),
                                "%" + filtersDTO.getName() +"%"
                        )
                );
            }

            if (filtersDTO.getGenre() != null) {
                predicates.add(criteriaBuilder.equal(root.get("genreId"), filtersDTO.getGenre()));
            }

            String orderByField = "releaseDate";
            query.orderBy(
                    filtersDTO.isASC() ?
                            criteriaBuilder.asc(root.get(orderByField)) :
                            criteriaBuilder.desc(root.get(orderByField))
            );

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }

}
