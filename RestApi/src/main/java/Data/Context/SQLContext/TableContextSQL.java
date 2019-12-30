package Data.Context.SQLContext;

import Data.Context.Interfaces.ITableContext;
import Data.DTO.TableDto;
import Data.Context.SQLContext.Helpers.SQLConnector;
import Interfaces.model.ITable;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TableContextSQL extends SQLConnector implements ITableContext {
    private final static Logger LOGGER = Logger.getLogger(UserContextSQL.class.getName());

    public boolean create(ITable entity) {
        String query = "INSERT INTO tables (name, description)" +
                "VALUES (?, ?) ";
        try {
            this.open();
            PreparedStatement stmt = this.getPreparedStatement(query);
            stmt.setString(1, entity.getName());
            stmt.setString(2, entity.getDescription());
            LOGGER.log(Level.INFO, "Table Created: " + entity.getName());
            return this.executeUpdate(stmt) != 0;
        } catch (Exception e){
            LOGGER.log(Level.WARNING, e.getMessage());
        } finally {
            this.close();
        }
        return false;
    }
    public boolean update(ITable entity) {
        String query = "UPDATE tables SET name = ?, description = ?, disabled = ? WHERE id = ?";
        try {
            this.open();
            PreparedStatement stmt = this.getPreparedStatement(query);
            stmt.setString(1, entity.getName());
            stmt.setString(2, entity.getDescription());
            stmt.setBoolean(3, entity.getDisabled());

            stmt.setInt(4, entity.getId());

            LOGGER.log(Level.INFO, "table Updated: " + entity.getName());
            return this.executeUpdate(stmt) != 0;
        }catch (Exception e){
            LOGGER.log(Level.WARNING, e.getMessage());
        }finally {
            this.close();
        }
        return false;
    }
    public boolean delete(ITable entity) {
        System.out.println("del");
        String query = "UPDATE tables SET disabled = ? WHERE id = ?";
        try {
            this.open();
            PreparedStatement stmt = this.getPreparedStatement(query);
            if(entity.getDisabled()){
                entity.setDisabled(false);
            }else{
                entity.setDisabled(true);
            }
            stmt.setBoolean(1, entity.getDisabled());
            stmt.setInt(2, entity.getId());

            LOGGER.log(Level.INFO, "Table Disabled: " + entity.getName());
            return this.executeUpdate(stmt) != 0;
        }catch (Exception e){
            LOGGER.log(Level.WARNING, e.getMessage());
        }finally {
            this.close();
        }
        return false;
    }



    public ITable read(int id) {
        TableDto tableDto = null;
        String query = "SELECT * FROM tables WHERE id = ? LIMIT 1";
        try{
            this.open();
            PreparedStatement stmt = this.getPreparedStatement(query);
            stmt.setInt(1, id);

            ResultSet resultSet = this.executeQuery(stmt);
            if(resultSet.next()){
                tableDto = new TableDto(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getBoolean("disabled")
                );
            }
        }catch (Exception e){
            LOGGER.log(Level.WARNING, e.getMessage());
        }finally {
            this.close();
        }
        return tableDto;
    }
    public ITable read(String name) {
        TableDto tableDto = null;
        String query = "SELECT * FROM tables WHERE name = ? LIMIT 1";
        try{
            this.open();
            PreparedStatement stmt = this.getPreparedStatement(query);
            stmt.setString(1, name);

            ResultSet resultSet = this.executeQuery(stmt);
            if(resultSet.next()){
                tableDto = new TableDto(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getBoolean("disabled")
                );
            }
        }catch (Exception e){
            LOGGER.log(Level.WARNING, e.getMessage());
        }finally {
            this.close();
        }
        return tableDto;
    }
    public ITable read(ITable entity) {
        if(entity.getName() != null){
            return this.read(entity.getName());
        }
        if(entity.getId() != 0){
            return this.read(entity.getId());
        }
        return null;
    }



    public List<ITable> list() {
        List<ITable> tables = new ArrayList<>();
        String query = "SELECT * FROM tables";

        try{
            this.open();
            PreparedStatement stmt = this.getPreparedStatement(query);
            ResultSet resultSet = this.executeQuery(stmt);

            while (resultSet.next()){
                TableDto tableDto = new TableDto(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getBoolean("disabled")
                );
                tables.add(tableDto);
            }

        } catch (Exception e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        }finally {
            this.close();
        }
        return tables;
    }
}
