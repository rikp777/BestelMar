package Data.Repository.Interfaces;

import Interfaces.model.ITable;

public interface ITableRepository extends ICrudRepository<ITable>{
    ITable getBy(String name);
}
