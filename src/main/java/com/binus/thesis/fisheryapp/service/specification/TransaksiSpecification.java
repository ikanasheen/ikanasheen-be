package com.binus.thesis.fisheryapp.service.specification;

import com.binus.thesis.fisheryapp.base.component.BaseSpecification;
import com.binus.thesis.fisheryapp.base.dto.BaseParameter;
import com.binus.thesis.fisheryapp.model.Transaksi;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class TransaksiSpecification extends BaseSpecification {

    public Specification<Transaksi> predicate(BaseParameter<Transaksi> parameter){
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
                        builder.like(builder.lower(root.get("idTransaksi")), searchLike),
                        builder.like(builder.lower(root.get("hargaAwal")), searchLike),
                        builder.like(builder.lower(root.get("hargaNego")), searchLike),
                        builder.like(builder.lower(root.get("hargaAkhir")), searchLike),
                        builder.like(builder.lower(root.get("alamat")), searchLike),
                        builder.like(builder.lower(root.get("status")), searchLike),
                        builder.like(builder.lower(root.get("ikan").get("namaIkan")), searchLike),
                        builder.like(builder.lower(root.get("ikan").get("ukuran")), searchLike),
                        builder.like(builder.lower(root.get("nelayan").get("namaLengkap")), searchLike),
                        builder.like(builder.lower(root.get("pembeli").get("namaLengkap")), searchLike)
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
