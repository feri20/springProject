package com.wolf.demo.controller;


import com.querydsl.core.types.Predicate;
import com.wolf.demo.dto.ProductDto;
import com.wolf.demo.mapper.ProductMapper;
import com.wolf.demo.model.Product;
import com.wolf.demo.service.IProduct;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


     @RestController
     @CrossOrigin("http://localhost:4200")
     @RequestMapping("/api/product")
     public class ProductController {

     @Autowired
     IProduct productService;
     @Autowired()
     ProductMapper productMapper;


         @ApiOperation(value = "Add or insert user")
         @ApiResponses(value = {
                 @ApiResponse(code = 201, message = "Added successfully"),
                 @ApiResponse(code = 401, message = "You are not authorized"),
                 @ApiResponse(code = 409, message = "It is duplicate"),
                 @ApiResponse(code = 500, message = "Server error")
         })


   @GetMapping("/{id}")
     public ResponseEntity<ProductDto> getOne(@PathVariable Long id) {
        Product product = productService.get(id);
        ProductDto productDto =productMapper.ProductToDto(product);
        return ResponseEntity.ok(productDto);
    }

    @PostMapping( "/addProduct")
      public ResponseEntity<Void> addProduct(@Validated Product product, @RequestPart ("product_image")MultipartFile file){
             productService.addProduct(product,file);
             return ResponseEntity.status(HttpStatus.CREATED).build();

         }

    @GetMapping("/gawp/{page}/{size}")
     public ResponseEntity<Page<ProductDto>> getAllWithPagination(@PathVariable int page,@PathVariable int size){
         Page<Product> productPage=productService.getAllWithPagination(page, size);
         Pageable pageable = PageRequest.of(page, size);

         List<ProductDto> productDtoList = productMapper.ProductToDto(productPage.getContent());
         Page<ProductDto> productDtoPage= PageableExecutionUtils.getPage(productDtoList,pageable,productPage::getTotalElements);
         return ResponseEntity.ok(productDtoPage);
     }


    @GetMapping("/getProductsOfCategory/{categoryId}")
    public ResponseEntity<List<ProductDto>> getWithCategory(@PathVariable Long categoryId){
         List<Product> products = productService.getWithCategory(categoryId);
         List<ProductDto> productDtos = new ArrayList<>();
         products.forEach(product -> {
             ProductDto productDto = productMapper.ProductToDto(product);
             productDtos.add(productDto);
         });
        return  ResponseEntity.ok(productDtos);

    }

    @GetMapping("/")
    public ResponseEntity<List<ProductDto>> getAll(){
        List<Product> products = productService.getAll();
        List<ProductDto> productDtos = new ArrayList<>();
        products.forEach(product -> {
            ProductDto productDto =productMapper.ProductToDto(product);
            productDtos.add(productDto);

        });

        return  ResponseEntity.ok(productDtos);
    }


    @PostMapping("/{categoryId}")
    public ResponseEntity<Void> create(@PathVariable(value = "categoryId") Long categoryId, @RequestBody ProductDto productDto) {
        Product product = productMapper.DtoToProduct(productDto);
        productService.add(categoryId, product);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }



    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable(value = "id")Long id,@RequestBody ProductDto productDto){
        Product product = productMapper.DtoToProduct(productDto);
        Product targetProduct = productService.update(id,product);
        return ResponseEntity.ok(targetProduct);
    }


    @DeleteMapping("/{id}")
    public String delete(@PathVariable(value = "id") Long id ){
        productService.delete(id);
        return "deleted";

    }
    
         @GetMapping("/filter")
         public ResponseEntity<Page<ProductDto>>products (@RequestParam (name = "page",defaultValue = "0")int page,
          @RequestParam(name = "size",defaultValue = "500")int size,
          @RequestParam(name = "price",required = false) Integer price,
          @RequestParam(name = "name",required = false)String name){
           Page<Product> productPage =productService.filter(page, size, name, price);
           Pageable pageable = PageRequest.of(page, size);
           List<ProductDto> productDtoList =productMapper.ProductToDto(productPage.getContent());
           Page<ProductDto> productDtoPage =
             PageableExecutionUtils.getPage(productDtoList,pageable,productPage::getTotalElements);

           return ResponseEntity.ok(productDtoPage);

         }

       
         @GetMapping("/simplified")
         public ResponseEntity<Page<ProductDto>> filterProducts(
         @QuerydslPredicate(root = Product.class) Predicate predicate,
         @RequestParam(name = "page",defaultValue = "0")int page,
         @RequestParam(name = "size",defaultValue = "500")int size){
             Page<Product> productPage= productService.simplifiedFilter(page, size, predicate);
             Pageable pageable = PageRequest.of(page, size);
             List<ProductDto> productDtoList = productMapper.ProductToDto(productPage.getContent());
             Page<ProductDto> productDtoPage = PageableExecutionUtils.getPage(productDtoList,
                                                     pageable,productPage::getTotalElements);
             return ResponseEntity.ok(productDtoPage);
         }
       /*   @GetMapping("/getProductImage/{id}")
         public ResponseEntity<ByteArrayResource> getProductImage(@PathVariable(value = "id")Long id){
             Product product = productService.get(id);
             return ResponseEntity.ok().contentType(MediaType.parseMediaType(product.getImageType()))
             .header(HttpHeaders.CONTENT_DISPOSITION,"attachment;filename=\""
             + product.getImageName()+"\"").body(new ByteArrayResource(product.getImage()));

         }*/

}




