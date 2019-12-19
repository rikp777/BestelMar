package Interfaces.model;

public interface ITable {
    int getId();
    void setId(int id);

    String getName();
    void setName(String name);

    String getDescription();
    void setDescription(String description);

    Boolean getDisabled();
    void setDisabled(Boolean disabled);
}
