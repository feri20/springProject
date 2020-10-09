package com.wolf.demo.dto;



public class CategoryDto {

    private Long id;
    private String name;
    private Long createDate;
    private Long lastUpdate;

  /*  public CategoryDto makeDto(Category category){
        if(category.getId()!=null){
            this.id=category.getId();
        }

        this.name=category.getName();
        this.createDate=category.getCreatDate().getTime();
        if(category.getLastUpdate()!=null){
            this.lastUpdate=category.getLastUpdate().getTime();
        }
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

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    public Long getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Long lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

}
