package Data.Context.Interfaces;

import Interfaces.model.IRight;
import Interfaces.model.IUser;

import java.util.List;

public interface IRightContext {
    IRight read(int id);
    IRight read(String name);

    List<IRight> list(IUser user);
    List<IRight> list();
}
