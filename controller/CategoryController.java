package com.wolf.demo.controller;


import com.wolf.demo.dto.CategoryDto;

import com.wolf.demo.mapper.CategoryMapper;
import com.wolf.demo.model.Category;
import com.wolf.demo.service.ICategory;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/category")

public class CategoryController {
    @Autowired
    ICategory CategoryService;
    @Autowired
    CategoryMapper categoryMapper;

    @PostMapping("/")
    public ResponseEntity<Void> add(@RequestBody CategoryDto categoryDto) {
        CategoryService.add(category);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping("/{page}/{size}")
    public ResponseEntity<Page<CategoryDto>> getAllWithPagination(@PathVariable(value = "page") int page,
                                                               @PathVariable(value = "size") int size){
        Page<CategoryDto> categoryDtoPage =CategoryService.getAllWithPagination(page, size);
        return ResponseEntity.ok(categoryDtoPage);
    }
    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getAll() {
        List<CategoryDto> categories=CategoryService.getAll();
        return  ResponseEntity.ok(categoryDtos);
    }
    @ApiOperation(value = "Add or insert user")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Added successfully"),
            @ApiResponse(code = 401, message = "You are not authorized"),
            @ApiResponse(code = 409, message = "It is duplicate"),
            @ApiResponse(code = 500, message = "Server error")
    })

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getOne(@PathVariable(value = "id") Long id){
        CategoryDto categoryDto = CategoryService.get(id);
        return ResponseEntity.ok(categoryDto);
    }
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> update(@PathVariable(value ="id") Long id,@RequestBody CategoryDto categoryDto){
        Category targetCategory = CategoryService.update(id,categoryDto);
        return ResponseEntity.ok(targetCategory);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable(value = "id") Long id){
        CategoryService.delete(id);
        return ResponseEntity.ok(id);

    }



}
