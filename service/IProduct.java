package com.wolf.demo.service;



import com.querydsl.core.types.Predicate;
import com.wolf.demo.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;


public interface IProduct {
    public void add(Long categoryId,Product product );
    public List<Product> getWithCategory(Long categoryId);
    public Product addProduct(Product product, MultipartFile file);
    public void delete(Long id);
    public Product get(Long id);
    public Product update(Long id,Product product);
    public List<Product> getAll();
    public Page<Product> getAllWithPagination(int page, int size);

    @Transactional
    Page<Product> filter(int page, int size,String name,Integer price );
    @Transactional
    Page<Product> simplifiedFilter(int page,int size,Predicate predicate);


}
