package com.binus.thesis.fisheryapp.service.specification;

import com.binus.thesis.fisheryapp.base.component.BaseSpecification;
import com.binus.thesis.fisheryapp.base.dto.BaseParameter;
import com.binus.thesis.fisheryapp.model.Ikan;
import com.binus.thesis.fisheryapp.model.Sosialisasi;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class SosialisasiSpecification extends BaseSpecification {

    public Specification<Sosialisasi> predicate(BaseParameter<Sosialisasi> parameter){
        Map<String, String> paramCriteria = parameter.getCriteria();
        Map<String, String> paramSort = parameter.getSort();
        Map<String, String> paramFilter = parameter.getFilter();
        return ((root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            Predicate predicate;

            if (paramCriteria != null && !paramCriteria.isEmpty()){
                Map.Entry<String, String> entry = paramCriteria.entrySet().iterator().next();
                String criteria = entry.getValue();
                String searchLike = String.format("%%%s%%", criteria.toLowerCase());
                predicate = builder.or(
                        builder.like(builder.lower(root.get("judul")), searchLike),
                        builder.like(builder.lower(root.get("konten")), searchLike)
                );
                predicates.add(predicate);
            }

            predicates.addAll(generateFilter(paramFilter, builder, root));

            ((CriteriaQuery) query).where(builder.and(predicates.toArray(new Predicate[0])));

            if(paramSort != null && !paramCriteria.isEmpty()) {
                ((CriteriaQuery) query).orderBy(generateSort(paramSort, builder, root));
            }

            return query.getRestriction();
        });
    }

    public List<Predicate> generateFilter(Map<String, String> filter, CriteriaBuilder builder, Root root){
        List<Predicate> predicates = new ArrayList<>();
        if (filter != null && !filter.isEmpty()) {
            for (Map.Entry<String, String> entry : filter.entrySet()) {
                predicates.add(builder.like(builder.lower(root.get(entry.getKey())), entry.getValue()));
            }
        }

        return predicates;
    }
}
