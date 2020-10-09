package com.wolf.demo.mapper;


import com.wolf.demo.dto.CategoryDto;
import com.wolf.demo.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    public CategoryMapper Instance = Mappers.getMapper(CategoryMapper.class);

    @Mapping(source = "lastUpdate",target = "lastUpdate",qualifiedByName = "updateToLong")
    @Mapping(source = "createDate",target = "createDate",qualifiedByName = "creatToLong")
    CategoryDto CategoryToDto(Category category);


    @Mapping(target = "lastUpdate",ignore = true)
    @Mapping(target = "createDate",ignore = true)
    Category DtoToCategory(CategoryDto categoryDto);





     @Named("updateToLong")
    default Long updateToLong(Date lastUpdate){
         if(lastUpdate!=null){
         return lastUpdate.getTime();}
         return null;
     }
     @Named("creatToLong")
    default Long creatToLong(Date createDate){
         return createDate.getTime();
     }
}
