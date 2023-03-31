package com.binus.thesis.fisheryapp.business.service.specification;

import com.binus.thesis.fisheryapp.base.component.BaseSpecification;
import com.binus.thesis.fisheryapp.base.dto.BaseParameter;
import com.binus.thesis.fisheryapp.base.dto.BetweenParameter;
import com.binus.thesis.fisheryapp.business.model.Transaksi;
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
public class TransaksiSpecification extends BaseSpecification {

    public Specification<Transaksi> predicate(BaseParameter<Transaksi> parameter){
        Map<String, BetweenParameter> paramBetween = parameter.getBetween();
        Map<String, String> paramSort = parameter.getSort();
        Map<String, Object> paramFilter = parameter.getFilter();
        return ((root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (paramFilter != null && paramFilter.size() > 0) {
                predicates.addAll(generateFilterTransaksi(paramFilter, builder, root));
            }
            if (paramBetween != null && paramBetween.size() > 0) {
                predicates.addAll(generateBetweenDate(paramBetween, builder, root));
            }
            ((CriteriaQuery) query).where(builder.and(predicates.toArray(new Predicate[0])));

            if(paramSort != null && paramSort.size() > 0) {
                ((CriteriaQuery) query).orderBy(generateSort(getSortList(paramSort), builder, root));
            }

            return query.getRestriction();
        });
    }

    public List<Predicate> generateFilterTransaksi(Map<String, Object> filter, CriteriaBuilder builder, Root root){
        List<Predicate> predicates = new ArrayList<>();

        for (Map.Entry<String, Object> entry : filter.entrySet()) {
            if (entry.getKey().equals("idUserPembeli")) {
                List<String> filterStatus = (List<String>) entry.getValue();
                predicates.add(root.get("pembeli").get("idUser").in(filterStatus));
            } else if (entry.getKey().equals("idUserNelayan")) {
                List<String> filterStatus = (List<String>) entry.getValue();
                predicates.add(root.get("nelayan").get("idUser").in(filterStatus));
            } else if (entry.getKey().equals("namaPembeli")) {
                List<String> filterStatus = (List<String>) entry.getValue();
                predicates.add(root.get("pembeli").get("namaLengkap").in(filterStatus));
            } else if (entry.getKey().equals("namaNelayan")) {
                List<String> filterStatus = (List<String>) entry.getValue();
                predicates.add(root.get("nelayan").get("namaLengkap").in(filterStatus));
            }else if (entry.getKey().equals("status")) {
                List<String> filterStatus = (List<String>) entry.getValue();
                predicates.add(root.get("status").in(filterStatus));
            } else if (entry.getKey().equals("namaIkan")) {
                List<String> filterStatus = (List<String>) entry.getValue();
                predicates.add(root.get("ikan").get("namaIkan").in(filterStatus));
            } else if (entry.getKey().equals("ukuran")) {
                List<String> filterStatus = (List<String>) entry.getValue();
                predicates.add(root.get("ikan").get("ukuran").in(filterStatus));
            } else {
                List<String> filterStatus = (List<String>) entry.getValue();
                predicates.add(root.get(entry.getKey()).in(filterStatus));
            }
        }

        return predicates;
    }

    private List<String> getSortList(Map<String, String> sort){
        List<String> sortList = new ArrayList<>();
        for (Map.Entry<String, String> entry : sort.entrySet()) {
            if (entry.getKey().equals("namaPembeli")) {
                sortList.add("pembeli");
                sortList.add("namaLengkap");
                sortList.add(entry.getValue());
                continue;
            }

            if (entry.getKey().equals("namaNelayan")) {
                sortList.add("nelayan");
                sortList.add("namaLengkap");
                sortList.add(entry.getValue());
                continue;
            }

            if (entry.getKey().equals("namaIkan")) {
                sortList.add("ikan");
                sortList.add("namaIkan");
                sortList.add(entry.getValue());
                continue;
            }

            if (entry.getKey().equals("ukuran")) {
                sortList.add("ikan");
                sortList.add("ukuran");
                sortList.add(entry.getValue());
                continue;
            }

            if (entry.getKey().equals("alamatPembeli")) {
                sortList.add("alamat");
                sortList.add(entry.getValue());
                continue;
            }

            sortList.add(entry.getKey());
            sortList.add(entry.getValue());
        }
        return sortList;
    }
}
