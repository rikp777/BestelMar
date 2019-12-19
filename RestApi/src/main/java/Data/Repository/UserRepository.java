package Data.Repository;

import Data.Context.SQLContext.UserContextSQL;
import Data.Context.Interfaces.IUserContext;
import Data.Repository.Interfaces.IUserRepository;
import Interfaces.model.IUser;

import java.util.List;

public class UserRepository implements IUserRepository {
    private IUserContext _userContext;
    public UserRepository(){
        this._userContext = new UserContextSQL();
    }


    public boolean add(IUser entity) {
        return _userContext.create(entity);
    }
    public boolean edit(IUser entity) {
        return _userContext.update(entity);
    }
    public boolean remove(IUser entity) {
        return _userContext.delete(entity);
    }



    public IUser getBy(int id) {
        return _userContext.read(id);
    }
    public IUser getBy(String email) {
        return _userContext.read(email);
    }
    public IUser getBy(IUser entity) {
        return _userContext.read(entity);
    }



    public List<IUser> getAll() {
        return _userContext.list();
    }

    public boolean checkAuth(String email, String password) {
        return _userContext.auth(email, password);
    }
}
