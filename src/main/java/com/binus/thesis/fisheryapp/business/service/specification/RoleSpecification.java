package com.binus.thesis.fisheryapp.business.service.specification;

import com.binus.thesis.fisheryapp.base.component.BaseSpecification;
import com.binus.thesis.fisheryapp.base.dto.BaseParameter;
import com.binus.thesis.fisheryapp.base.dto.BetweenParameter;
import com.binus.thesis.fisheryapp.business.model.Role;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class RoleSpecification extends BaseSpecification {

    public Specification<Role> predicate(BaseParameter<Role> parameter){
        Map<String, BetweenParameter> paramBetween = parameter.getBetween();
        Map<String, String> paramSort = parameter.getSort();
        Map<String, Object> paramFilter = parameter.getFilter();
        return ((root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (paramFilter != null && paramFilter.size() > 0) {
                predicates.addAll(generateFilter(paramFilter, builder, root));
            }
            if (paramBetween != null && paramBetween.size() > 0) {
                predicates.addAll(generateBetweenDate(paramBetween, builder, root));
            }

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
