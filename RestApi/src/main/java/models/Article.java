package models;

import Interfaces.model.IArticle;

public class Article implements IArticle {
    private int id;
    private String name;
    private String description;
    private Double price;

    Article(){

    }
    public Article(int id, String name, String description, double price){
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName(){
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice(){
        return this.price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
}
