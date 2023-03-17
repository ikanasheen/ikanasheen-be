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
        Map<String, Object> paramFilter = parameter.getFilter();
        return ((root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            Predicate predicate;

            if (paramCriteria != null && !paramCriteria.isEmpty()){
                Map.Entry<String, String> entry = paramCriteria.entrySet().iterator().next();
                String criteria = entry.getValue();
                String searchLike = String.format("%%%s%%", criteria.toLowerCase());
                predicate = builder.or(
                        builder.like(builder.lower(root.get("idSosialisasi")), searchLike),
                        builder.like(builder.lower(root.get("judul")), searchLike),
                        builder.like(builder.lower(root.get("konten")), searchLike),
                        builder.like(builder.lower(root.get("jenisKonten")), searchLike),
                        builder.like(builder.lower(root.get("penulis")), searchLike),
                        builder.like(builder.lower(root.get("status")), searchLike),
                        builder.like(builder.lower(root.get("tanggalDibuat")), searchLike),
                        builder.like(builder.lower(root.get("tanggalDiubah")), searchLike)
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
