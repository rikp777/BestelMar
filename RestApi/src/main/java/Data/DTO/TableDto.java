package Data.DTO;

import Interfaces.model.ITable;

public class TableDto implements ITable {
    private int id;
    private String name;
    private String description;
    private Boolean disabled;

    public TableDto(){

    }
    public TableDto(String name, String description, Boolean disabled){
        this.name = name;
        this.description = description;
        this.disabled = disabled;
    }
    public TableDto(int id, String name, String description, Boolean disabled){
        this.id = id;
        this.name = name;
        this.description = description;
        this.disabled = disabled;
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

    public Boolean getDisabled(){
        return this.disabled;
    }
    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }
}
