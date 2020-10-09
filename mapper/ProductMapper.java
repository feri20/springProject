package com.wolf.demo.mapper;



import com.wolf.demo.dto.ProductDto;
import com.wolf.demo.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Mapper(componentModel = "spring")
public interface ProductMapper {
   public ProductMapper instance = Mappers.getMapper(ProductMapper.class);


    @Mapping(source = "lastUpdate",target = "lastUpdate",qualifiedByName = "lastUpdateToLong")
    @Mapping(source = "createDate",target = "createDate",qualifiedByName = "createDateToLong")


    ProductDto ProductToDto(Product product);


    @Mapping(target = "lastUpdate",ignore = true)
    @Mapping(target = "createDate",ignore = true)

    Product DtoToProduct(ProductDto productDto);


    ArrayList<ProductDto> ProductToDto(List<Product> list);


    @Named("lastUpdateToLong")
    default Long updateConverter(Date lastUpdate){
      if(lastUpdate!=null){return lastUpdate.getTime();}
      return null;
    }
    @Named("createDateToLong")
    default Long createDateConverter(Date createDate){
        return createDate.getTime();
    }

}


