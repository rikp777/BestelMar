package Data.Context.MemoryContext;

import Data.Context.Interfaces.ITableContext;
import Data.DTO.ArticleDto;
import Data.DTO.TableDto;
import Interfaces.model.IArticle;
import Interfaces.model.ITable;

import java.util.ArrayList;
import java.util.List;

public class TableContextMemory implements ITableContext {
    private static List<TableDto> tables;

    public TableContextMemory(){
        tables = new ArrayList<>();
    }


    public boolean create(TableDto entity) {
        for(TableDto t : tables){
            if(t.getName().equals(entity.getName())){
                return false;
            }
        }

        TableDto table = new TableDto(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getDisabled()
        );

        tables.add(table);
        return tables.contains(table);
    }
    public boolean update(TableDto entity){
        TableDto table = new TableDto(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getDisabled()
        );

        TableDto old;

        for(TableDto t : tables){
            if(t.getId() == entity.getId()){
                old = t;
                tables.set(tables.indexOf(old), table);
            }
        }
        return tables.contains(table);
    }
    public boolean delete(TableDto entity){
        TableDto old;
        for(TableDto t : tables){
            if(t.getId() == entity.getId()){
                old = t;
                tables.remove(old);
                return !tables.contains(old);
            }
        }
        return false;
    }


    public TableDto read(int id) {
        TableDto table = null;
        for(TableDto t : tables){
            if(t.getId() == id){
                table = t;
            }
        }
        return table;
    }
    public TableDto read(String name) {
        TableDto table = null;
        for(TableDto t : tables){
            if(t.getName() == name){
                table = t;
            }
        }
        return table;
    }
    public TableDto read(TableDto entity) {
        TableDto table = null;
        for(TableDto a : tables){
            if(a.getId() == entity.getId()){
                table = a;
            }
        }
        return table;
    }

    public List<TableDto> list() {
        return new ArrayList<>(tables);
    }
}
