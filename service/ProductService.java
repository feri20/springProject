package com.wolf.demo.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.wolf.demo.model.Category;
import com.wolf.demo.model.Product;
import com.wolf.demo.model.QProduct;
import com.wolf.demo.repository.ProductRepository;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


@Service
public class ProductService implements IProduct {

    @Autowired
    ICategory CategoryService;

    @Autowired
    ProductRepository productRepository;


    @Override
    public void add(Long categoryId, Product product) {
        Category category = CategoryService.get(categoryId);
        product.setCategory(category);
        productRepository.save(product);
    }

    @Override
    public List<Product> getWithCategory(Long categoryId) {
        Category category =CategoryService.get(categoryId);
        return productRepository.findAllByCategory(category);
    }



    @Override
    public Product addProduct(Product product, MultipartFile file) {
      try { product.setImageName(file.getOriginalFilename());
      product.setImageType(file.getContentType());
      product.setImage(IOUtils.toByteArray(file.getInputStream()));
       }
       catch (IOException e){
           e.printStackTrace();
       }
      productRepository.save(product);
      return product;
    }

    @Override
    public void delete(Long id) {
        Optional<Product> targetProduct = productRepository.findById(id);
        Product delProduct = targetProduct.get();
        productRepository.delete(delProduct);

    }

    @Override
    public Product get(Long id) {
        Optional<Product> targetProduct = productRepository.findById(id);
        Product Product = targetProduct.get();
        return Product;
    }

    @Override
    public Product update(Long id, Product product) {
        Optional<Product> targetProduct = productRepository.findById(id);
        Product updProduct =targetProduct.get();
        updProduct.setName(product.getName());
        updProduct.setCategory(product.getCategory());
        updProduct.setPrice(product.getPrice());
        updProduct.setComments(product.getComments());
        Product updatedProduct = productRepository.save(updProduct);
        return updatedProduct;
    }

    @Override
    public List<Product> getAll() {
        return (List<Product>) productRepository.findAll();
    }


    @Override
    public Page<Product> getAllWithPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.desc("id")));
        Page<Product> products =productRepository.findAll(pageable);
        return products;
    }

   @Override
    public Page<Product> filter(int page, int size, String name, Integer price) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QProduct qProduct = QProduct.product;
        if(name != null&& !name.isEmpty()){
            booleanBuilder.and(qProduct.name.contains(name));
        }
        if(price != null && price!=0){
            booleanBuilder.and(qProduct.price.goe(price));
        }
        Page<Product> productPage = productRepository.findAll(booleanBuilder.getValue(),
               PageRequest.of(page,size,Sort.by(Sort.Direction.ASC,"id")));

        return productPage;
    }

    @Override
    public Page<Product> simplifiedFilter(int page, int size, Predicate predicate) {
        Page<Product> productPage2 = productRepository.findAll(predicate,PageRequest.of(page, size,
                Sort.by(Sort.Direction.ASC,"id")));
        return productPage2;
    }

    }


