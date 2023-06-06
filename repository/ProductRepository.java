package com.wolf.demo.repository;

import com.wolf.demo.model.Category;
import com.wolf.demo.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;


public interface ProductRepository extends JpaRepository<Product,Long>, PagingAndSortingRepository<Product,Long> , QuerydslPredicateExecutor<Product> {

    Optional<Product> findById(Long id);
    Optional<Product> findByName(String name);
    List<Product> findAllByCategory(Category category);
    List<Product> findAll();
    Page<Product> findAll(Pageable pageable);


}
