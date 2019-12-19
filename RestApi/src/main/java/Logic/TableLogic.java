package Logic;

import Data.Repository.TableRepository;
import Interfaces.model.ITable;

import java.util.List;

public class TableLogic {
    private TableRepository _tableRepository;
    public TableLogic() {
        this._tableRepository = new TableRepository();
    }


    public boolean add(ITable entity) {
        return _tableRepository.add(entity);
    }
    public boolean edit(ITable entity) {
        return _tableRepository.edit(entity);
    }
    public boolean remove(ITable entity) {
        return _tableRepository.remove(entity);
    }



    public ITable getBy(int id) {
        return _tableRepository.getBy(id);
    }
    public ITable getBy(ITable entity) {
        return _tableRepository.getBy(entity);
    }
    public ITable getBy(String name) {
        return _tableRepository.getBy(name);
    }



    public List<ITable> getAll() {
        return _tableRepository.getAll();
    }
}
