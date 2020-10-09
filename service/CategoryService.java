package com.wolf.demo.service;


import com.wolf.demo.exception.ConflictException;
import com.wolf.demo.exception.NotFoundException;
import com.wolf.demo.model.Category;
import com.wolf.demo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ICategory {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public void add(Category category) {
        Category RepetitiveCategory=categoryRepository.findFirstByName(category.getName());
        if (RepetitiveCategory != null){
            throw new ConflictException("already exists");
        }
        categoryRepository.save(category);

    }

    @Override
    public Category get(Long id) {
        Optional<Category> categories=categoryRepository.findById(id);
        if (!categories.isPresent()){
            throw new NotFoundException("not found");
        }
        return  categories.get();

    }

    @Override
    public void delete(Long id) {
        Optional<Category> optionalCategory=categoryRepository.findById(id);
        Category category=optionalCategory.get();
        categoryRepository.delete(category);

    }

    @Override
    public Category update(Long id, Category category) {
        Optional<Category> optionalCategory=categoryRepository.findById(id);
        Category targetCategory=optionalCategory.get();
        targetCategory.setName(category.getName());
        Category updatedCountry=categoryRepository.save(targetCategory);
        return updatedCountry;
    }

    @Override
    public List<Category> getAll() {
        return  (List<Category>) categoryRepository.findAll();
    }

    @Override
    public Page<Category> getAllWithPagination(int page, int pageSize) {
        Pageable pageable= PageRequest.of(page,pageSize, Sort.by(Sort.Order.desc("id")));
        Page<Category> CategoryPage=categoryRepository.findAll(pageable);
        return CategoryPage;
    }


}
