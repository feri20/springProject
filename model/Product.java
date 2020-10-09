package com.wolf.demo.model;



import com.wolf.demo.dto.ProductDto;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @Column(name = "Product_ID",unique = true, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Product_name")
    private String name;

    @Column(name = "price")
    private int price;

    @Column(name = "comments")
    private String comments;

    @Column(name = "image_name" , nullable = true)
    private String imageName;

    @Column(name = "image_type" , nullable = true)
    private String imageType;

    @Lob
    @Column(name = "image" , nullable = true)
    private byte[] image;

    @Column(name = "last_update",nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;

    @Column(name = "creat_date",nullable = false,updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @PrePersist
    public void setCreatDate(){
        this.createDate= new Date();
    }

    @PreUpdate
    public void setLastUpdate(){
        this.lastUpdate= new Date();
    }



    @ManyToOne()
    @JoinColumn(name = "category_id" ,updatable = false)
    private Category category;

  /* public Product makeDomain(ProductDto productDto){
        if(productDto.getId()!=null){
            this.id = productDto.getId();
        }
        this.name = productDto.getName();
        this.price = productDto.getPrice();
        this.comments = productDto.getComments();
       this.image = productDto.getImage();
        this.imageName = productDto.getImageName();
        this.imageType = productDto.getImageType();

        return this;
    }*/



    public Category getCategory() { return category; }

    public void setCategory(Category category) { this.category = category; }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public Date getCreateDate() { return createDate; }

    public String getImageName() { return imageName; }

    public void setImageName(String imageName) { this.imageName = imageName; }

    public String getImageType() { return imageType; }

    public void setImageType(String imageType) { this.imageType = imageType; }

    public byte[] getImage() { return image; }

    public void setImage(byte[] image) { this.image = image; }

    public void setLastUpdate(Date lastUpdate) { this.lastUpdate = lastUpdate; }

    public void setCreateDate(Date createDate) { this.createDate = createDate; }
}
