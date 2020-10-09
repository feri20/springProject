package com.wolf.demo.service;


import com.querydsl.core.types.Predicate;
import com.wolf.demo.exception.NotFoundException;
import com.wolf.demo.model.Country;
import com.wolf.demo.model.State;
import com.wolf.demo.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StateService implements IState {

    @Autowired
    StateRepository stateRepository;
    @Autowired
    CountryService countryService;


    @Override
    public void add(Long countryId, State state) {
        Country country = countryService.get(countryId);
        state.setCountry(country);
        stateRepository.save(state);
    }

    @Override
    public State get(Long id) {
        Optional<State> states = stateRepository.findById(id);
        if (!states.isPresent()) {
            throw new NotFoundException("not found");
        }
        return states.get();
    }

    @Override
    public void delete(Long id) {
        Optional<State> optionalState = stateRepository.findById(id);
        State state = optionalState.get();
        stateRepository.delete(state);

    }

    @Override
    public State update(Long id, State state) {
        Optional<State> optionalState = stateRepository.findById(id);
        State targetState = optionalState.get();
        targetState.setName(state.getName());
        State updatedState = stateRepository.save(targetState);
        return updatedState;
    }

    @Override
    public List<State> getAll() {
        return (List<State>) stateRepository.findAll();
    }

    @Override
    public List<State> Filter( Predicate predicate) {
        return (List<State>)stateRepository.findAll(predicate);

    }


}

