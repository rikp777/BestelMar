package Data.Context.Interfaces;

import Data.Context.Interfaces.ICrudContext;
import Data.DTO.UserDto;
import Interfaces.model.IUser;

public interface IUserContext extends ICrudContext<IUser> {
    boolean auth(String email, String password);
    IUser read(String email);
}
