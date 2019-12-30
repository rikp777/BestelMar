package logic.Interfaces;

import Interfaces.model.IUser;

public interface IUserLogic extends ILogic<IUser> {
    IUser getBy(String email);
    boolean login(String email, String password);
}
