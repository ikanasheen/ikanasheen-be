package com.binus.thesis.fisheryapp.base.component;

import com.binus.thesis.fisheryapp.base.constant.GlobalConstant;
import com.binus.thesis.fisheryapp.base.dto.RequestSorting;
import com.google.common.base.CaseFormat;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
public class BaseSpecification {

    public Pageable pageGenerator(int page, int size) {
        return PageRequest.of(page, size);
    }
    public String generateSort(String sort) {
        if(sort != null) {
            String[] splSort = sort.split(",");
            return splSort[0];
        }
        return null;
    }

    public List<Order> generateSort(List<String> sortList, CriteriaBuilder builder, Root root) {
        List<Order> orderList = new ArrayList<>();
        String sortElement = "";
        for (int i = 0; i < sortList.size(); i++) {
            String[] arrSort = sortList.get(i).split(",");
            if (arrSort.length < 2 && sortList.size() == 3){
                if(sortList.get(2).toUpperCase(Locale.ROOT).equals("DESC")){
                    orderList.add(builder.desc(root.get(generateSort(sortList.get(0))).get(generateSort(sortList.get(1)))));
                } else if(sortList.get(2).toUpperCase(Locale.ROOT).equals("ASC")){
                    orderList.add(builder.asc(root.get(generateSort(sortList.get(0))).get(generateSort(sortList.get(1)))));
                }

                break;
            }

            if (arrSort.length > 2){
                if(arrSort[2].toUpperCase(Locale.ROOT).equals("DESC")){
                    orderList.add(builder.desc(root.get(generateSort(arrSort[0])).get(generateSort(arrSort[1]))));
                } else if(arrSort[2].toUpperCase(Locale.ROOT).equals("ASC")){
                    orderList.add(builder.asc(root.get(generateSort(arrSort[0])).get(generateSort(arrSort[1]))));
                }
            } else {
                for (int j = 0; j < arrSort.length; j++) {
                    if(arrSort[j].toUpperCase(Locale.ROOT).equals("DESC")){
                        orderList.add(builder.desc(root.get(sortElement)));
                    } else if(arrSort[j].toUpperCase(Locale.ROOT).equals("ASC")){
                        orderList.add(builder.asc(root.get(sortElement)));
                    } else {
                        sortElement = generateSort(arrSort[j]);
                    }
                }
            }
        }
        return orderList;
    }

    public List<Predicate> generateFilter(Map<String, String> filter, CriteriaBuilder builder, Root root){
        List<Predicate> predicates = new ArrayList<>();
        if (filter != null && !filter.isEmpty()) {
            for (Map.Entry<String, String> entry : filter.entrySet()) {
                predicates.add(builder.like(builder.lower(root.get(entry.getKey())), entry.getValue()));
            }
        }

        return predicates;
    }
}
