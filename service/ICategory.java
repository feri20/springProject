package com.wolf.demo.service;


import com.wolf.demo.model.Category;
import org.springframework.data.domain.Page;
import java.util.List;


public interface ICategory {
    public void add(Category category);
    public Category get(Long id);
    public void delete(Long id);

    public Category update(Long id,Category category);
    public List<Category> getAll();
    Page<Category> getAllWithPagination(int page,int pageSize);

}
