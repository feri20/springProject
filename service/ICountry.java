package com.wolf.demo.service;


import com.wolf.demo.model.Country;

import java.util.List;

public interface ICountry {
    public void add(Country country);
    public Country get(Long id);
    public void delete(Long id);
    public Country update(Long id,Country country);
    public List<Country> getAll();

}
