package Data.Context.Interfaces;

import Data.DTO.TableDto;
import Interfaces.model.ITable;

public interface ITableContext extends ICrudContext<ITable>{
    ITable read(String name);
}
