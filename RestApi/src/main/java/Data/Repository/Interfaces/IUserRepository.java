package Data.Repository.Interfaces;

import Interfaces.model.IUser;

public interface IUserRepository extends ICrudRepository<IUser>{

    boolean checkAuth(String email, String password);
    IUser getBy(String email);
}
