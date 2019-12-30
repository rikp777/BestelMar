package Data.Context.MemoryContext;

import Data.Context.Interfaces.IUserContext;
import Data.DTO.TableDto;
import Data.DTO.UserDto;
import Interfaces.model.ITable;
import Interfaces.model.IUser;

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
        user.setId(entity.getId());
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

        UserDto old;

        for(UserDto u : users){
            if(u.getId() == entity.getId()){
                old = u;
                users.set(users.indexOf(old), user);
            }
        }
        return users.contains(user);
    }

    public boolean delete(IUser entity){
        UserDto old;
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
        UserDto user = null;
        for(UserDto u : users){
            if(u.getId() == id){
                user = u;
            }
        }
        return user;
    }

    public IUser read(IUser entity) {
        UserDto user = null;
        for(UserDto u : users){
            if(u.getId() == entity.getId()){
                user = u;
            }
        }
        return user;
    }
    public IUser read(String email) {
        UserDto user = null;
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
        for(UserDto u : users){
            if(u.getEmail().equals(email) && u.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }

}