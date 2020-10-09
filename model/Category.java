package com.wolf.demo.model;


import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "category")
public class Category {

    public Category(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Category_id",unique = true,updatable = false)
    private Long id;

    @Column(name = "Category_name",nullable = false)
    private String name;

    @Column(name = "Category_date" ,nullable = false,updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Column(name = "Category_update" ,nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;


    @OneToMany(mappedBy = "category" )
    private List<Product> products;

  /*  public List<Product> getProducts() { return products; }

    public void setProducts(List<Product> products) { this.products = products; }
    public Category makeDomain(CategoryDto categoryDto){
        if(categoryDto.getId()!=null) {
            this.id = categoryDto.getId(); }
        this.name=categoryDto.getName();

        return this;
    }*/



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

    public Date getCreateDate() { return createDate; }

    public void setCreateDate(Date createDate) { this.createDate = createDate; }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) { this.lastUpdate = lastUpdate; }

    @PrePersist
    public void setCreateDate() {
        this.createDate = new Date();
    }
    @PreUpdate
    public void setLastUpdate() {
        this.lastUpdate = new Date();
    }





}
