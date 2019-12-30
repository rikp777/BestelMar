package Data.Repository;

import Data.Context.Interfaces.ITableContext;
import Data.Context.SQLContext.TableContextSQL;
import Data.Repository.Interfaces.ITableRepository;
import Interfaces.model.ITable;

import java.util.List;

public class TableRepository implements ITableRepository {
    private ITableContext _articleContext;
    public TableRepository(ITableContext tableContext){
        this._articleContext = tableContext;
    }


    public boolean add(ITable entity) {
        return _articleContext.create(entity);
    }
    public boolean edit(ITable entity) {
        return _articleContext.update(entity);
    }
    public boolean remove(ITable entity) {
        return _articleContext.delete(entity);
    }



    public ITable getBy(int id) {
        return _articleContext.read(id);
    }
    public ITable getBy(ITable entity) {
        return _articleContext.read(entity);
    }
    public ITable getBy(String name) {
        return _articleContext.read(name);
    }


    public List<ITable> getAll() {
        return _articleContext.list();
    }
}
