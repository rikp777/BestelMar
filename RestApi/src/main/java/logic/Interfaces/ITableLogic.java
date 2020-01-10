package logic.Interfaces;

import Interfaces.model.ITable;

public interface ITableLogic extends ILogic<ITable>{
    ITable getBy(String name);
}
