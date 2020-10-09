package com.wolf.demo.mapper;


import com.wolf.demo.dto.CountryDto;
import com.wolf.demo.model.Country;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CountryMapper {

    public CountryMapper Instance = Mappers.getMapper(CountryMapper.class);


    CountryDto CountryToDto(Country country);


    Country DtoToCountry(CountryDto countryDto);
}
