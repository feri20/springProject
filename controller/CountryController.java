package com.wolf.demo.controller;



import com.wolf.demo.dto.CountryDto;
import com.wolf.demo.mapper.CountryMapper;
import com.wolf.demo.model.Country;
import com.wolf.demo.service.CountryService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/country")
public class CountryController {

    @Autowired
    CountryService countryService;

    @GetMapping("/")
    public ResponseEntity<List<CountryDto>> getAll() {
        List<CountryDto> countries = countryService.getAll();
        return  ResponseEntity.ok(countryDtos);
    }

    @PostMapping("/")
    public ResponseEntity<Void> add(@RequestBody CountryDto countryDto) {
        Country country= countryMapper.DtoToCountry(countryDto);
        countryService.add(country);
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
    public ResponseEntity<Country> update(@PathVariable(value ="id") Long id,@RequestBody CountryDto countryDto){
        Country country = countryMapper.DtoToCountry(countryDto);
        Country targetCountry = countryService.update(id,country);
        return ResponseEntity.ok(targetCountry);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable(value = "id") Long id){
       countryService.delete(id);
        return ResponseEntity.ok(id);

    }

}
