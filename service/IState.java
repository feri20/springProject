package com.wolf.demo.service;


import com.querydsl.core.types.Predicate;
import com.wolf.demo.model.State;

import javax.transaction.Transactional;
import java.util.List;

public interface IState {

    public void add(Long countryId,State state);
    public State get(Long id);
    public void delete(Long id);
    public State update(Long id,State state);
    public List<State> getAll();

    @Transactional
   List<State> Filter(Predicate predicate);



}
