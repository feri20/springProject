package com.wolf.demo.repository;


import com.wolf.demo.model.Country;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CountryRepository extends JpaRepository<Country,Long>  CrudRepository<Country,Long> {

    Country findFirstByName(String name);
    Optional<Country> findById(Long id);
}
