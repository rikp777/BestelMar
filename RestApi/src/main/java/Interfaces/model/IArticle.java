package Interfaces.model;

public interface IArticle {
    int getId();
    void setId(int id);

    String getName();
    void setName(String name);

    String getDescription();
    void setDescription(String description);

    Double getPrice();
    void setPrice(double price);
}
