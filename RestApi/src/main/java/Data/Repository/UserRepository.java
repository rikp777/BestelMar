package Data.Repository;

import Data.Context.SQLContext.UserContextSQL;
import Data.Context.Interfaces.IUserContext;
import Data.DTO.UserDto;
import Data.Repository.Interfaces.IUserRepository;
import Interfaces.model.IUser;

import java.util.ArrayList;
import java.util.List;

public class UserRepository implements IUserRepository {
    private IUserContext _userContext;
    public UserRepository(IUserContext userContext){
        this._userContext = userContext;
    }


    public boolean add(IUser entity) {
        return _userContext.create((UserDto) entity);
    }
    public boolean edit(IUser entity) {
        return _userContext.update((UserDto) entity);
    }
    public boolean remove(IUser entity) {
        return _userContext.delete((UserDto) entity);
    }



    public IUser getBy(int id) {
        return _userContext.read(id);
    }
    public IUser getBy(String email) {
        return _userContext.read(email);
    }
    public IUser getBy(IUser entity) {
        return _userContext.read((UserDto) entity);
    }



    public List<IUser> getAll() {
        return new ArrayList<>(_userContext.list());
    }

    public boolean checkAuth(String email, String password) {
        return _userContext.auth(email, password);
    }
}
