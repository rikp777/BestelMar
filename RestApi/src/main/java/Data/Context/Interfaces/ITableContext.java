package Data.Context.Interfaces;

import Interfaces.model.ITable;

public interface ITableContext extends ICrudContext<ITable>{
    ITable read(String name);
}
