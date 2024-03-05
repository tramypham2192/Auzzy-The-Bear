package com.auzzythebear.dto;

public class ProductDto {
  private int id;
  private String name;
  private int price;
  private String description;
  //    private String imageUrl;

  public ProductDto(int id, String name, int price) {
    this.id = id;
    this.name = name;
    this.price = price;
  }

  public ProductDto() {}
  ;

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public int getPrice() {
    return price;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }
}
