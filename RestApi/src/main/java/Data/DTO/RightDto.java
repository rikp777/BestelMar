package Data.DTO;

import Interfaces.model.IRight;

public class RightDto implements IRight {
    private int id;
    private String name;
    private String description;

    public RightDto(){

    }
    public RightDto(int id, String name, String description){

    }

    public void setId(int id){
        this.id = id;
    }
    public int getId() {
        return this.id;
    }

    public void setName(String name){
        this.name = name;
    }
    public String getName() {
        return this.name;
    }

    public void setDescription(String description){
        this.description = description;
    }
    public String getDescription() {
        return null;
    }
}
