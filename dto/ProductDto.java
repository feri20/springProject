package com.wolf.demo.dto;


public class ProductDto {

    private Long id;
    private String name;
    private String comments;
    private Long createDate;
    private Long lastUpdate;
    private int price;
   /* private byte[] image;*/
    private String imageType;
    private String imageName;


  /*  public ProductDto makeDto(Product product){
        if(product.getId()!=null){
            this.id=product.getId();
        }
        this.name = product.getName();
        this.comments =product.getComments();
        this.price = product.getPrice();
        this.createDate = product.getCreateDate().getTime();
        if(lastUpdate!=null){
        this.lastUpdate = product.getLastUpdate().getTime();}
        this.image = product.getImage();
        this.imageName = product.getImageName();
        this.imageType = product.getImageType();
        return  this;
    }*/


    public Long getId() { return id; }

    public void setId(Long productId) { this.id = productId; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getComments() { return comments; }

    public void setComments(String comments) { this.comments = comments; }

  public Long getCreateDate() { return createDate; }

    public void setCreateDate(Long createDate) { this.createDate = createDate; }

    public Long getLastUpdate() { return lastUpdate; }

    public void setLastUpdate(Long lastUpdate) { this.lastUpdate = lastUpdate; }

    public int getPrice() { return price; }

    public void setPrice(int price) { this.price = price; }

   /* public byte[] getImage() { return image; }

    public void setImage(byte[] image) { this.image = image; }*/

    public String getImageType() { return imageType; }

    public void setImageType(String imageType) { this.imageType = imageType; }

    public String getImageName() { return imageName; }

    public void setImageName(String imageName) { this.imageName = imageName; }
}
