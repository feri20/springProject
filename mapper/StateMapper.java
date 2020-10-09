package com.wolf.demo.mapper;


import com.wolf.demo.dto.StateDto;
import com.wolf.demo.model.State;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface StateMapper {

    public StateMapper Instance = Mappers.getMapper(StateMapper.class);
    StateDto StateToDto(State state);
    State DtoToState(StateDto StateDto);
    ArrayList<StateDto> StateToDto(List<State> list);
}
