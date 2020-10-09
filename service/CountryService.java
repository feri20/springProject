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
public class CountryService implements ICountry {


    @Autowired
    CountryRepository countryRepository;


    @Override
    public void add(Country country) {
        Country repetitive = countryRepository.findFirstByName(country.getName());
        if(repetitive!=null){
            throw new ConflictException("already exists");
        }
        countryRepository.save(country);
    }

    @Override
    public Country get(Long id) {
        Optional<Country> countries=countryRepository.findById(id);
        if (!countries.isPresent()){
            throw new NotFoundException("not found");
        }
        return  countries.get();
    }

    @Override
    public void delete(Long id) {
        Optional<Country> optionalCountry=countryRepository.findById(id);
        Country country=optionalCountry.get();
        countryRepository.delete(country);

    }

    @Override
    public Country update(Long id, Country country) {
        Optional<Country> optionalCountry=countryRepository.findById(id);
        Country targetCountry=optionalCountry.get();
        targetCountry.setName(country.getName());
        targetCountry.setCode(country.getCode());
        Country updatedCountry= countryRepository.save(targetCountry);
        return updatedCountry;
    }

    @Override
    public List<Country> getAll() {
        return (List<Country>) countryRepository.findAll();
    }


}
