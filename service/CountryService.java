package com.wolf.demo.service;


import com.wolf.demo.exception.ConflictException;
import com.wolf.demo.exception.NotFoundException;
import com.wolf.demo.model.Country;
import com.wolf.demo.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CountryService implements ICountry {

    private CountryRepository countryRepository;
    
    private CountryMapper countryMapper;
   

   @Override
    public void add(CountryDto countryDto) {
        Category category= countryMapper.DtoToCategory(countryDto);
        Category RepetitiveCategory=countryRepository.findFirstByName(countryDto.getName());
        if (RepetitiveCategory != null){
            throw new ConflictException("already exists");
        }
        categoryRepository.save(country);

    }

    @Override
    public CategoryDto get(Long id) {
        Optional<Country> country=countryRepository.findById(id);
        if (!category.isPresent()){
            throw new NotFoundException("not found");
        }
            CountryDto countryDto = countryMapper.CountryToDto(country);
        return  countryDto;

    }

    @Override
    public void delete(Long id) {
        Optional<Category> optionalCategory=countryRepository.findById(id);
        Category category=optionalCategory.get();
        categoryRepository.delete(category);

    }

    @Override
    public Category update(Long id, CountryDto countryDto) {
        Country country = categoryMapper.DtoToCountry(countryDto);
        Optional<Country> optionalCountry=countryRepository.findById(id);
        Country targetCountry=optionalCountry.get();
        targetCounrty.setName(country.getName());
        Country updatedCountry=categoryRepository.save(targetCountry);
        return updatedCountry;
    }

    @Override
    public List<Country> getAll() {
        
        return (List<Country>) countryRepository.findAll();
    }


}
