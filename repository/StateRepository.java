package com.wolf.demo.repository;


import com.wolf.demo.model.State;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface StateRepository extends  CrudRepository<State,Long> , QuerydslPredicateExecutor<State> {
    State findFirstByName(String name);
    Optional<State> findById(Long id);
    List<State> findByCountry_Code(String CountryCode);

}
