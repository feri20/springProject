package com.wolf.demo.controller;


import com.querydsl.core.types.Predicate;
import com.wolf.demo.dto.StateDto;
import com.wolf.demo.mapper.StateMapper;
import com.wolf.demo.model.State;
import com.wolf.demo.service.StateService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/state")
public class StateController {

    @Autowired
    StateService stateService;

    @GetMapping("/{id}")
        public ResponseEntity<StateDto> getOne(@PathVariable Long id) {
            StateDto stateDto = stateService.get(id);
            return ResponseEntity.ok(stateDto);
    }

    @GetMapping("/")
    public ResponseEntity<List<StateDto>> getAll() {
        List<StateDto> statesDto = stateService.getAll();
        return  ResponseEntity.ok(stateDtos);
    }

    @PostMapping("/{countryId}")
    public ResponseEntity<Void> create(@PathVariable(value = "countryId") Long countryId, @RequestBody StateDto stateDto) {
        stateService.add(countryId,stateDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "Add or insert user")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Added successfully"),
            @ApiResponse(code = 401, message = "You are not authorized"),
            @ApiResponse(code = 409, message = "It is duplicate"),
            @ApiResponse(code = 500, message = "Server error")
    })

    @PutMapping("/{id}")
    public ResponseEntity<StateDto> update(@PathVariable(value ="id") Long id,@RequestBody StateDto stateDto){
        StateDto targetState = stateService.update(id,stateDto);
        return ResponseEntity.ok(targetState);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable(value = "id") Long id){
        stateService.delete(id);
        return ResponseEntity.ok(id);

    }

    @Transactional
    @GetMapping("/filter")
    public ResponseEntity<List<StateDto>> filter(
            @QuerydslPredicate(root = State.class) Predicate predicate) {
        List<StateDto> states= stateService.Filter(predicate);
        return ResponseEntity.ok(stateDtoList);
    }
}
