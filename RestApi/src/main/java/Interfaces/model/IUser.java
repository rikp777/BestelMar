package Interfaces.model;


import java.util.List;

public interface IUser {
    int getId();
    void setId(int id);

    String getEmail();
    void setEmail(String email);

    String getFirstName();
    void setFirstName(String firstName);

    String getLastName();
    void setLastName(String lastName);

    boolean getBlocked();
    void setBlocked(boolean blocked);

    String getPassword();
    void setPassword(String password);

    List<IRole> getRoles();
    void setRoles(List<IRole> roles);

    List<IOrder> getOrders();
    void setOrders(List<IOrder> orders);

}
