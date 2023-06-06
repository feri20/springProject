package com.wolf.demo.repository;

import com.wolf.demo.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;


public interface CategoryRepository extends JpaRepository<Category,Long>, PagingAndSortingRepository<Category,Long>, QuerydslPredicateExecutor<Category> {
    Optional<Category> findById(Long id);
    Page<Category> findAll(Pageable pageable);
    Category findFirstByName(String name);
}
