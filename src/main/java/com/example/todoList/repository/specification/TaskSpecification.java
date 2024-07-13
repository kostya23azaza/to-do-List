package com.example.todoList.repository.specification;

import com.example.todoList.entity.Task;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class TaskSpecification {

//  public static Specification<Task> getFilter(String search, String formId, Boolean enabled) {
//    Specification<Task> where = where(null);
//    if (search != null) {
//        where = where.and(stringFieldsLike(search));
//    }
////    if (formId != null) {
////      where = where.and(formIdLike(formId));
////    }
////    if (enabled != null) {
////      where = where.and(enabledLike(enabled));
////    }
//    return where;
//  }
//
//      private static Specification<Task> stringFieldsLike(String search) {
//    return (root, query, cb) -> {
//      log.debug("search = "+ search);
//      final List<Predicate> predicates = new ArrayList<>();
//      predicates.add(cb.like(cb.upper(
//        root.get(Task_.NAME)), "%" + search.toUpperCase() + "%"));
//      predicates.add(cb.like(cb.upper(
//        root.get(Task_.DESCRIPTION)), "%" + search.toUpperCase() + "%"));
//      Predicate[] predicatesArray = new Predicate[predicates.size()];
//      return cb.or(predicates.toArray(predicatesArray));
//    };
//  }
}
