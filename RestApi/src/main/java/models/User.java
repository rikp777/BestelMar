package models;

import Interfaces.model.IOrder;
import Interfaces.model.IRole;
import Interfaces.model.IUser;

import java.util.ArrayList;
import java.util.List;

public class User implements IUser {
    private int id;
    private String email;
    private String firstName;
    private String lastName;
    private boolean blocked;
    private String password;
    private List<IRole> roles;
    private List<IOrder> orders;

    public User(){

    }
    public User(int id, String email, String firstName, String lastName, boolean blocked){
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.blocked = blocked;
    }
    public void setOrders(List<IOrder> orders){
        this.orders = orders;
    }
    public List<IOrder> getOrders(){
        return this.orders;
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

    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password){
        this.password = password;
    }

    public List<IRole> getRoles() {
        return roles;
    }

    public void setRoles(List<IRole> roles) {
        this.roles = roles;
    }
}
