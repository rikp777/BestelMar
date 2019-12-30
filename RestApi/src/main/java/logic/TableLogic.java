package logic;

import Data.Repository.Interfaces.ITableRepository;
import Data.Repository.TableRepository;
import Interfaces.model.ITable;
import logic.Interfaces.ITableLogic;

import java.util.List;

public class TableLogic implements ITableLogic {
    private ITableRepository _tableRepository;
    public TableLogic(ITableRepository tableRepository) {
        this._tableRepository = tableRepository;
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
