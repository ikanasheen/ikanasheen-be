package com.binus.thesis.fisheryapp.service.specification;

import com.binus.thesis.fisheryapp.base.component.BaseSpecification;
import com.binus.thesis.fisheryapp.base.dto.BaseParameter;
import com.binus.thesis.fisheryapp.model.Ikan;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class IkanSpecification extends BaseSpecification {

    public Specification<Ikan> predicate(BaseParameter<Ikan> parameter){
        Map<String, String> paramCriteria = parameter.getCriteria();
        Map<String, String> paramSort = parameter.getSort();
        Map<String, Object> paramFilter = parameter.getFilter();
        return ((root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            Predicate predicate;

            if (paramCriteria != null && !paramCriteria.isEmpty()){
                Map.Entry<String, String> entry = paramCriteria.entrySet().iterator().next();
                String criteria = entry.getValue();
                String searchLike = String.format("%%%s%%", criteria.toLowerCase());
                predicate = builder.or(
                        builder.like(builder.lower(root.get("idIkan")), searchLike),
                        builder.like(builder.lower(root.get("namaIkan")), searchLike),
                        builder.like(builder.lower(root.get("deskripsi")), searchLike),
                        builder.like(builder.lower(root.get("ukuran")), searchLike)
                );
                predicates.add(predicate);
            }
            predicates.addAll(generateFilter(paramFilter, builder, root));

            ((CriteriaQuery) query).where(builder.and(predicates.toArray(new Predicate[0])));

            if(paramSort != null && !paramSort.isEmpty()) {
                ((CriteriaQuery) query).orderBy(generateSort(getSortList(paramSort), builder, root));
            }

            return query.getRestriction();
        });
    }

    private List<String> getSortList(Map<String, String> sort){
        List<String> sortList = new ArrayList<>();
        for (Map.Entry<String, String> entry : sort.entrySet()) {
            sortList.add(entry.getKey());
            sortList.add(entry.getValue());
        }
        return sortList;
    }
}
