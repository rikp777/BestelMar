package Data.Repository.Interfaces;

import Interfaces.model.IRight;

import java.util.List;

public interface IRightRepository {
    IRight getBy(int id);
    IRight getBy(String name);

    List<IRight> getAll();
}
