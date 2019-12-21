package Data.Context.SQLContext;

import Data.Context.Interfaces.IUserContext;
import Data.DTO.UserDto;
import Data.Helpers.SQLConnector;
import Interfaces.model.IUser;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserContextSQL extends SQLConnector implements IUserContext {
    private final static Logger LOGGER = Logger.getLogger(UserContextSQL.class.getName());

    public boolean create(IUser entity) {
        String query = "INSERT INTO users (first_name, last_name, email, password)" +
                " VALUES (?, ?, ?, ?) ";
        try {
            this.open();
            PreparedStatement stmt = this.getPreparedStatement(query);
            stmt.setString(1, entity.getFirstName());
            stmt.setString(2, entity.getLastName());
            stmt.setString(3, entity.getEmail());
            stmt.setString(4, "rik");
            LOGGER.log(Level.INFO, "User Created: " + entity.getEmail());
            return this.executeUpdate(stmt) != 0;
        } catch (Exception e){
            LOGGER.log(Level.WARNING, e.getMessage());
        } finally {
            this.close();
        }
        return false;
    }
    public boolean update(IUser entity) {
        System.out.println("Hit");
        String query = "UPDATE users SET email = ?, first_name = ?, last_name = ? WHERE id = ?";
        try {
            this.open();
            PreparedStatement stmt = this.getPreparedStatement(query);
            stmt.setString(1, entity.getEmail());
            stmt.setString(2, entity.getFirstName());
            stmt.setString(3, entity.getLastName());
            stmt.setInt(4, entity.getId());

            LOGGER.log(Level.INFO, "User Updated: " + entity.getEmail());
            return this.executeUpdate(stmt) != 0;
        }catch (Exception e){
            LOGGER.log(Level.WARNING, e.getMessage());
        }finally {
            this.close();
        }
        return false;
    }
    public boolean delete(IUser entity) {
        System.out.println("del");
        String query = "UPDATE users SET blocked = ? WHERE id = ?";
        try {
            this.open();
            PreparedStatement stmt = this.getPreparedStatement(query);
            if(entity.getBlocked()){
                entity.setBlocked(false);
            }else{
                entity.setBlocked(true);
            }
            stmt.setBoolean(1, entity.getBlocked());
            stmt.setInt(2, entity.getId());

            LOGGER.log(Level.INFO, "User Blocked: " + entity.getEmail());
            return this.executeUpdate(stmt) != 0;
        }catch (Exception e){
            LOGGER.log(Level.WARNING, e.getMessage());
        }finally {
            this.close();
        }
        return false;
    }
//    public boolean delete(IUser entity) {
//        String query = "UPDATE users SET blocked = ? WHERE id = ?";
//        try {
//            this.open();
//            PreparedStatement stmt = this.getPreparedStatement(query);
////            if(entity.getBlocked()){
////                entity.setBlocked(false);
////            }else{
////                entity.setBlocked(true);
////            }
//            stmt.setBoolean(1, true);
//            stmt.setInt(2, entity.getId());
//
////            LOGGER.log(Level.INFO, "User Blocked: " + entity.getEmail() + " Blocked =" + entity.getBlocked());
//            return this.executeUpdate(stmt) != 0;
//        } catch (Exception e){
//            e.printStackTrace();
//            LOGGER.log(Level.WARNING, e.getMessage());
//        }finally {
//            this.close();
//        }
//        return false;
//    }



    public IUser read(String email) {
        UserDto userDto = null;
        String query = "SELECT * FROM users WHERE email = ? LIMIT 1";
        try{
            this.open();
            PreparedStatement stmt = this.getPreparedStatement(query);
            stmt.setString(1, email);

            ResultSet resultSet = this.executeQuery(stmt);
            if(resultSet.next()){
                userDto = new UserDto(
                        resultSet.getInt("id"),
                        resultSet.getString("email"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getBoolean("blocked")
                );
            }
        }catch (Exception e){
            LOGGER.log(Level.WARNING, e.getMessage());
        }finally {
            this.close();
        }
        return userDto;
    }
    public IUser read(int id) {
        UserDto userDto = null;
        String query = "SELECT * FROM users WHERE id = ? LIMIT 1";
        try{
            this.open();
            PreparedStatement stmt = this.getPreparedStatement(query);
            stmt.setInt(1, id);

            ResultSet resultSet = this.executeQuery(stmt);
            if(resultSet.next()){
                userDto = new UserDto(
                        resultSet.getInt("id"),
                        resultSet.getString("email"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getBoolean("blocked")
                );
            }
        }catch (Exception e){
            LOGGER.log(Level.WARNING, e.getMessage());
        }finally {
            this.close();
        }
        return userDto;
    }
    public IUser read(IUser entity) {
        if(entity.getEmail() != null){
            return this.read(entity.getEmail());
        }else if(entity.getId() != 0){
            return this.read(entity.getId());
        }
        return null;
    }



    public List<IUser> list() {
        List<IUser> users = new ArrayList<IUser>();
        String query = "SELECT * FROM users";

        try{
            this.open();
            PreparedStatement stmt = this.getPreparedStatement(query);
            ResultSet resultSet = this.executeQuery(stmt);

            while (resultSet.next()){
                UserDto userDto = new UserDto(
                        resultSet.getInt("id"),
                        resultSet.getString("email"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getBoolean("blocked")
                );
                users.add(userDto);
            }

        } catch (Exception e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        }finally {
            this.close();
        }
        return users;
    }
    public boolean auth(String email, String password) {
        UserDto userDto = null;
        String query = "SELECT * FROM users WHERE email = ? and password = ? LIMIT 1";
        try{
            this.open();
            PreparedStatement stmt = this.getPreparedStatement(query);
            stmt.setString(1, email);
            stmt.setString(2, password);

            ResultSet resultSet = this.executeQuery(stmt);
            if(resultSet.next()){
                return true;
            }
        }catch (Exception e){
            LOGGER.log(Level.WARNING, e.getMessage());
        }finally {
            this.close();
        }
        return false;
    }
}
