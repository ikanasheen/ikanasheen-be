package com.binus.thesis.fisheryapp.service.specification;

import com.binus.thesis.fisheryapp.base.component.BaseSpecification;
import com.binus.thesis.fisheryapp.base.dto.BaseParameter;
import com.binus.thesis.fisheryapp.model.Transaksi;
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
        Map<String, String> paramCriteria = parameter.getCriteria();
        Map<String, String> paramSort = parameter.getSort();
        Map<String, Object> paramFilter = parameter.getFilter();
        return ((root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            Predicate predicate;

            if (paramCriteria != null && paramCriteria.size() > 0){
                Map.Entry<String, String> entry = paramCriteria.entrySet().iterator().next();
                String criteria = entry.getValue();
                String searchLike = String.format("%%%s%%", criteria.toLowerCase());
                predicate = builder.or(
                        builder.like(builder.lower(root.get("idTransaksi")), searchLike),
                        builder.like(builder.lower(root.get("idPembeli")), searchLike),
                        builder.like(builder.lower(root.get("idNelayan")), searchLike),
                        builder.like(builder.lower(root.get("pembeli").get("namaLengkap")), searchLike),
                        builder.like(builder.lower(root.get("tanggalDibutuhkan")), searchLike),
                        builder.like(builder.lower(root.get("jumlah")), searchLike),
                        builder.like(builder.lower(root.get("hargaAwal")), searchLike),
                        builder.like(builder.lower(root.get("hargaNego")), searchLike),
                        builder.like(builder.lower(root.get("hargaAkhir")), searchLike),
                        builder.like(builder.lower(root.get("ikan").get("namaIkan")), searchLike),
                        builder.like(builder.lower(root.get("ikan").get("ukuran")), searchLike),
                        builder.like(builder.lower(root.get("alamat")), searchLike),
                        builder.like(builder.lower(root.get("catatan")), searchLike)
                );
                predicates.add(predicate);
            }

            if (paramFilter != null && paramFilter.size() > 0) {
                predicates.addAll(generateFilterTransaksi(paramFilter, builder, root));
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
                    predicates.add(builder.like(builder.lower(root.get("pembeli").get("idUser")), String.valueOf(entry.getValue())));
                } else if (entry.getKey().equals("idUserNelayan")) {
                    predicates.add(builder.like(builder.lower(root.get("nelayan").get("idUser")), String.valueOf(entry.getValue())));
                } else if (entry.getKey().equals("namaPembeli")) {
                    predicates.add(builder.like(builder.lower(root.get("pembeli").get("namaLengkap")), String.valueOf(entry.getValue())));
                } else if (entry.getKey().equals("namaNelayan")) {
                    predicates.add(builder.like(builder.lower(root.get("nelayan").get("namaLengkap")), String.valueOf(entry.getValue())));
                }else if (entry.getKey().equals("status")) {
                    List<String> filterStatus = (List<String>) entry.getValue();
                    predicates.add(root.get("status").in(filterStatus));
                } else if (entry.getKey().equals("namaIkan")) {
                    predicates.add(builder.like(builder.lower(root.get("ikan").get("namaIkan")), String.valueOf(entry.getValue())));
                } else if (entry.getKey().equals("ukuran")) {
                    predicates.add(builder.like(builder.lower(root.get("ikan").get("ukuran")), String.valueOf(entry.getValue())));
                } else {
                    predicates.add(root.get(entry.getKey()).in(String.valueOf(entry.getValue())));
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
