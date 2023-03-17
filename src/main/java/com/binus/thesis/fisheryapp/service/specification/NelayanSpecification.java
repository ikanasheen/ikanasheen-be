package com.binus.thesis.fisheryapp.service.specification;

import com.binus.thesis.fisheryapp.base.component.BaseSpecification;
import com.binus.thesis.fisheryapp.base.dto.BaseParameter;
import com.binus.thesis.fisheryapp.model.Nelayan;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Component
public class NelayanSpecification extends BaseSpecification {

    public Specification<Nelayan> predicate(BaseParameter<Nelayan> parameter){
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
                        builder.like(builder.lower(root.get("idNelayan")), searchLike),
                        builder.like(builder.lower(root.get("namaLengkap")), searchLike),
                        builder.like(builder.lower(root.get("idUser")), searchLike),
                        builder.like(builder.lower(root.get("gender")), searchLike),
                        builder.like(builder.lower(root.get("alamat")), searchLike),
                        builder.like(builder.lower(root.get("kecamatan")), searchLike),
                        builder.like(builder.lower(root.get("kelurahanDesa")), searchLike),
                        builder.like(builder.lower(root.get("tanggalLahir")), searchLike),
                        builder.like(builder.lower(root.get("noTelepon")), searchLike),
                        builder.like(builder.lower(root.get("email")), searchLike),
                        builder.like(builder.lower(root.get("user").get("status")), searchLike)
                );
                predicates.add(predicate);
            }

            predicates.addAll(generateFilterNelayan(paramFilter, builder, root));

            ((CriteriaQuery) query).where(builder.and(predicates.toArray(new Predicate[0])));

            if(paramSort != null && !paramSort.isEmpty()) {
                ((CriteriaQuery) query).orderBy(generateSort(getSortList(paramSort), builder, root));
            }

            return query.getRestriction();
        });
    }

    public List<Predicate> generateFilterNelayan(Map<String, Object> filter, CriteriaBuilder builder, Root root){
        List<Predicate> predicates = new ArrayList<>();
        if (filter != null && !filter.isEmpty()) {
            for (Map.Entry<String, Object> entry : filter.entrySet()) {
                if (entry.getKey().equals("status")) {
                    predicates.add(builder.like(builder.lower(root.get("user").get(entry.getKey())), String.valueOf(entry.getValue())));
                    continue;
                }
                predicates.add(builder.like(builder.lower(root.get(entry.getKey())), String.valueOf(entry.getValue())));
            }
        }

        return predicates;
    }

    private List<String> getSortList(Map<String, String> sort){
        List<String> sortList = new ArrayList<>();
        for (Map.Entry<String, String> entry : sort.entrySet()) {
            if (entry.getKey().equals("status")) {
                sortList.add("user");
                sortList.add(entry.getKey());
                sortList.add(entry.getValue());
                continue;
            }
            sortList.add(entry.getKey());
            sortList.add(entry.getValue());
        }
        return sortList;
    }
}
