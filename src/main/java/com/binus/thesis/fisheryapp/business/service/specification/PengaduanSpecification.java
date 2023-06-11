package com.binus.thesis.fisheryapp.business.service.specification;

import com.binus.thesis.fisheryapp.base.component.BaseSpecification;
import com.binus.thesis.fisheryapp.base.dto.BaseParameter;
import com.binus.thesis.fisheryapp.base.dto.BetweenParameter;
import com.binus.thesis.fisheryapp.business.model.Pengaduan;
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
public class PengaduanSpecification extends BaseSpecification {

    public Specification<Pengaduan> predicate(BaseParameter<Pengaduan> parameter){
        Map<String, BetweenParameter> paramBetween = parameter.getBetween();
        Map<String, String> paramSort = parameter.getSort();
        Map<String, Object> paramFilter = parameter.getFilter();
        Map<String, String> paramCriteria = parameter.getCriteria();
        String criteriaType = parameter.getCriteriaType();
        return ((root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (paramFilter != null && paramFilter.size() > 0) {
                predicates.addAll(generateFilterPengaduan(paramFilter, builder, root));
            }
            if (paramBetween != null && paramBetween.size() > 0) {
                predicates.addAll(generateBetweenDate(paramBetween, builder, root));
            }

            List<Predicate> criterias = new ArrayList<>();
            Predicate predicateCriteria;

            if (paramCriteria != null && paramCriteria.size() > 0) {
                for (Map.Entry<String, String> entry : paramCriteria.entrySet()) {
                    String searchLike = String.format("%%%s%%", entry.getValue().toLowerCase());
                    Predicate predicate = null;
                    if (entry.getKey().equalsIgnoreCase("namaNelayan")) {
                        predicate = builder.or(
                                builder.like(builder.lower(root.get("nelayan").get("namaLengkap")), searchLike)
                        );
                    } else {
                        predicate = builder.or(
                                builder.like(builder.lower(root.get(entry.getKey())), searchLike)
                        );
                    }

                    criterias.add(predicate);
                }

                if (criteriaType.equalsIgnoreCase("OR")) {
                    predicateCriteria = builder.or(criterias.toArray(new Predicate[0]));
                } else {
                    predicateCriteria = builder.and(criterias.toArray(new Predicate[0]));
                }

                predicates.add(predicateCriteria);
            }

            ((CriteriaQuery) query).where(builder.and(predicates.toArray(new Predicate[0])));

            if(paramSort != null && !paramSort.isEmpty()) {
                ((CriteriaQuery) query).orderBy(generateSort(getSortList(paramSort), builder, root));
            }

            return query.getRestriction();
        });
    }

    public List<Predicate> generateFilterPengaduan(Map<String, Object> filter, CriteriaBuilder builder, Root root){
        List<Predicate> predicates = new ArrayList<>();

        for (Map.Entry<String, Object> entry : filter.entrySet()) {
            List<String> filterStatus = (List<String>) entry.getValue();
            if (entry.getKey().equals("idUserNelayan")) {
                predicates.add(root.get("nelayan").get("idUser").in(filterStatus));
            } else if (entry.getKey().equals("namaNelayan")) {
                predicates.add(root.get("nelayan").get("nameLengkap").in(filterStatus));
            } else {
                predicates.add(root.get(entry.getKey()).in(filterStatus));
            }
        }

        return predicates;
    }

    private List<String> getSortList(Map<String, String> sort){
        List<String> sortList = new ArrayList<>();
        for (Map.Entry<String, String> entry : sort.entrySet()) {
            if (entry.getKey().equals("namaNelayan")) {
                sortList.add("nelayan");
                sortList.add("namaLengkap");
                sortList.add(entry.getValue());
                continue;
            }

            sortList.add(entry.getKey());
            sortList.add(entry.getValue());
        }
        return sortList;
    }
}
