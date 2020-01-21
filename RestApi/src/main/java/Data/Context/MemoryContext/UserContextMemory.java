package Data.Context.MemoryContext;

import Data.Context.Interfaces.IUserContext;
import Data.DTO.TableDto;
import Data.DTO.UserDto;
import Interfaces.model.IArticleOrder;
import Interfaces.model.ITable;
import Interfaces.model.IUser;
import models.User;

import java.util.ArrayList;
import java.util.List;

public class UserContextMemory implements IUserContext {
    private static List<UserDto> users;

    public UserContextMemory(){
        users = new ArrayList<>();
    }

    public boolean create(IUser entity) {
        for(UserDto u : users){
            if(u.getEmail().equals(entity.getEmail())){
                return false;
            }
        }
        UserDto user = new UserDto();
        user.setId(generateId(entity));
        user.setEmail(entity.getEmail());
        user.setFirstName(entity.getFirstName());
        user.setLastName(entity.getLastName());
        user.setBlocked(entity.getBlocked());
        user.setPassword(entity.getPassword());

        users.add(user);
        return users.contains(user);
    }


    public boolean update(IUser entity){
        UserDto user = new UserDto();
        user.setId(entity.getId());
        user.setEmail(entity.getEmail());
        user.setFirstName(entity.getFirstName());
        user.setLastName(entity.getLastName());
        user.setBlocked(entity.getBlocked());

        IUser old;

        for(IUser u : users){
            if(u.getId() == entity.getId()){
                old = u;
                users.set(users.indexOf(old), user);
            }
        }
        return users.contains(entity);
    }

    public boolean delete(IUser entity){
        IUser old;
        for(UserDto u : users){
            if(u.getId() == entity.getId()){
                old = u;
                users.remove(old);
                return !users.contains(old);
            }
        }
        return false;
    }

    public IUser read(int id) {
        IUser user = null;
        for(UserDto u : users){
            if(u.getId() == id){
                user = u;
            }
        }
        return user;
    }

    public IUser read(IUser entity) {
        IUser user = null;
        for(UserDto u : users){
            if(u.getId() == entity.getId()){
                user = u;
            }
        }
        return user;
    }
    public IUser read(String email) {
        IUser user = null;
        for(UserDto u : users){
            if(u.getEmail() == email){
                user = u;
            }
        }
        return user;
    }


    public List<IUser> list() {
        return new ArrayList<>(users);
    }

    public boolean auth(String email, String password) {
        for(IUser u : users){
            if(u.getEmail().equals(email) && u.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }

    public int generateId(IUser entity){
        int id;
        if(entity.getId() == 0){
            if(users.size() == 0){
                id = 1;
            }else{
                id = list().get(list().size() -1).getId() + 1;
            }
        }else{
            id = entity.getId();
        }
        return id;
    }

}
