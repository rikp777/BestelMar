package RestApi.VOModels;

import Interfaces.model.IOrder;
import Interfaces.model.IRole;
import Interfaces.model.IUser;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;


public class VOUser {
    private int id;
    private String email;
    private String firstName;
    private String lastName;
    private boolean blocked;
    private String ordersLink;
    private String rolesLink;

    public VOUser(){
    }

    public VOUser(int id, String email, String firstName, String lastName, boolean blocked, String ordersLink, String rolesLink){
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.blocked = blocked;
        this.ordersLink = ordersLink;
        this.rolesLink = rolesLink;
    }

    public int getId(){
        return this.id;
    }
    public void setId(int id){
        this.id = id;
    }

    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return this.firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean getBlocked() {
        return this.blocked;
    }
    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public String getOrdersLink(){
        return this.ordersLink;
    }
    public void setOrderLink(String ordersLink){
        this.ordersLink = ordersLink;
    }

    public String getRolesLink(){
        return this.rolesLink;
    }
    public void setRolesLink(String rolesLink){
        this.rolesLink = rolesLink;
    }
}
