package com.binus.thesis.fisheryapp.service.specification;

import com.binus.thesis.fisheryapp.base.component.BaseSpecification;
import com.binus.thesis.fisheryapp.base.dto.BaseParameter;
import com.binus.thesis.fisheryapp.model.Nelayan;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class NelayanSpecification extends BaseSpecification {

    public Specification<Nelayan> predicate(BaseParameter<Nelayan> parameter){
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
                        builder.like(builder.lower(root.get("namaLengkap")), searchLike),
                        builder.like(builder.lower(root.get("idUser")), searchLike),
                        builder.like(builder.lower(root.get("gender")), searchLike),
                        builder.like(builder.lower(root.get("tanggalLahir")), searchLike),
                        builder.like(builder.lower(root.get("alamat")), searchLike),
                        builder.like(builder.lower(root.get("kecamatan")), searchLike),
                        builder.like(builder.lower(root.get("kelurahanDesa")), searchLike),
                        builder.like(builder.lower(root.get("noTelepon")), searchLike),
                        builder.like(builder.lower(root.get("email")), searchLike)
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
}
