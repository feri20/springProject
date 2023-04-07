package com.wolf.demo.service;


import com.wolf.demo.dto.CategoryDto;
import com.wolf.demo.model.Category;
import org.springframework.data.domain.Page;
import java.util.List;


public interface ICategory {
    public void add(CategoryDto categoryDto);
    public Category get(Long id);
    public void delete(Long id);

    public Category update(Long id, CategoryDto categoryDto);
    public List<CategoryDto> getAll();
    Page<CategoryDto> getAllWithPagination(int page,int pageSize);

}
