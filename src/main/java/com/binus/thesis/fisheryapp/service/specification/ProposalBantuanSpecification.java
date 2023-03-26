package com.binus.thesis.fisheryapp.service.specification;

import com.binus.thesis.fisheryapp.base.component.BaseSpecification;
import com.binus.thesis.fisheryapp.base.dto.BaseParameter;
import com.binus.thesis.fisheryapp.model.ProposalBantuan;
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
public class ProposalBantuanSpecification extends BaseSpecification {

    public Specification<ProposalBantuan> predicate(BaseParameter<ProposalBantuan> parameter){
        Map<String, String> paramSort = parameter.getSort();
        Map<String, Object> paramFilter = parameter.getFilter();
        return ((root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (paramFilter != null && paramFilter.size() > 0) {
                predicates.addAll(generateFilterProposal(paramFilter, builder, root));
            }
            ((CriteriaQuery) query).where(builder.and(predicates.toArray(new Predicate[0])));

            if(paramSort != null && !paramSort.isEmpty()) {
                ((CriteriaQuery) query).orderBy(generateSort(getSortList(paramSort), builder, root));
            }

            return query.getRestriction();
        });
    }

    public List<Predicate> generateFilterProposal(Map<String, Object> filter, CriteriaBuilder builder, Root root){
        List<Predicate> predicates = new ArrayList<>();

        for (Map.Entry<String, Object> entry : filter.entrySet()) {
            if (entry.getKey().equals("idUserNelayan")) {
                List<String> filterStatus = (List<String>) entry.getValue();
                predicates.add(root.get("nelayan").get("idUser").in(filterStatus));
            } else if (entry.getKey().equals("idNelayan")) {
                List<String> filterStatus = (List<String>) entry.getValue();
                predicates.add(root.get("nelayan").get("idNelayan").in(filterStatus));
            } else if (entry.getKey().equals("namaNelayan")) {
                List<String> filterStatus = (List<String>) entry.getValue();
                predicates.add(root.get("nelayan").get("namaLengkap").in(filterStatus));
            } else if (entry.getKey().equals("status")) {
                List<String> filterStatus = (List<String>) entry.getValue();
                predicates.add(root.get("status").in(filterStatus));
            } else if (entry.getKey().equals("namaBantuan")) {
                List<String> filterStatus = (List<String>) entry.getValue();
                predicates.add(root.get("bantuan").get("namaBantuan").in(filterStatus));
            } else if (entry.getKey().equals("jenisBantuan")) {
                List<String> filterStatus = (List<String>) entry.getValue();
                predicates.add(root.get("bantuan").get("jenisBantuan").in(filterStatus));
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
            if (entry.getKey().equals("idNelayan")) {
                sortList.add("nelayan");
                sortList.add("idNelayan");
                sortList.add(entry.getValue());
                continue;
            }

            if (entry.getKey().equals("namaNelayan")) {
                sortList.add("nelayan");
                sortList.add("namaLengkap");
                sortList.add(entry.getValue());
                continue;
            }

            if (entry.getKey().equals("namaBantuan")) {
                sortList.add("bantuan");
                sortList.add("namaBantuan");
                sortList.add(entry.getValue());
                continue;
            }

            if (entry.getKey().equals("jenisBantuan")) {
                sortList.add("bantuan");
                sortList.add("jenisBantuan");
                sortList.add(entry.getValue());
                continue;
            }

            sortList.add(entry.getKey());
            sortList.add(entry.getValue());
        }
        return sortList;
    }
}
