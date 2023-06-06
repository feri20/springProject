package com.wolf.demo.service;


import com.wolf.demo.dto.CategoryDto;
import com.wolf.demo.exception.ConflictException;
import com.wolf.demo.exception.NotFoundException;
import com.wolf.demo.mapper.CategoryMapper;
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
@AllArgsConstructor
public class CategoryService implements ICategory {


    private CategoryRepository categoryRepository;
    private CategoryMapper categoryMapper;

    @Override
    public void add(CategoryDto categoryDto) {
        Category category= categoryMapper.DtoToCategory(categoryDto);
        Category RepetitiveCategory=categoryRepository.findFirstByName(categoryDto.getName());
        if (RepetitiveCategory != null){
            throw new ConflictException("already exists");
        }
        categoryRepository.save(category);

    }

    @Override
    public CategoryDto get(Long id) {
        Optional<Category> category=categoryRepository.findById(id);
        if (!category.isPresent()){
            throw new NotFoundException("not found");
        }
            CategoryDto categoryDto = categoryMapper.CategoryToDto(category);
        return  categoryDto;

    }

    @Override
    public void delete(Long id) {
        Optional<Category> optionalCategory=categoryRepository.findById(id);
        Category category=optionalCategory.get();
        categoryRepository.delete(category);

    }

    @Override
    public Category update(Long id, CategoryDto categoryDto) {
        Category category = categoryMapper.DtoToCategory(categoryDto);
        Optional<Category> optionalCategory=categoryRepository.findById(id);
        Category targetCategory=optionalCategory.get();
        targetCategory.setName(category.getName());
        Category updatedCountry=categoryRepository.save(targetCategory);
        return updatedCountry;
    }

    @Override
    public List<CategoryDto> getAll() {
         List<Category> categories = (List<Category>) categoryRepository.findAll();
         List<CategoryDto> categoryDtos = new ArrayList<>();
        categories.forEach(category -> {
            CategoryDto categoryDto  =categoryMapper.CategoryToDto(category);
            categoryDtos.add(categoryDto);
        });
        return categoryDtos;

    }

    @Override
    public Page<CategoryDto> getAllWithPagination(int page, int pageSize) {
        Pageable pageable= PageRequest.of(page,pageSize, Sort.by(Sort.Order.desc("id")));
        Page<Category> CategoryPage=categoryRepository.findAll(pageable);
        return CategoryPage;
    }


}
