package Data.Context.SQLContext;

import Data.Context.Interfaces.IArticleContext;
import Data.Context.Interfaces.IUserContext;
import Data.DTO.ArticleDto;
import Data.Helpers.SQLConnector;
import Interfaces.model.IArticle;
import Interfaces.model.IUser;
import models.Article;
import models.Article;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ArticleContextSQL extends SQLConnector implements IArticleContext {
    private final static Logger LOGGER = Logger.getLogger(UserContextSQL.class.getName());


    public boolean create(IArticle entity) {
        String query = "INSERT INTO articles (name, description, price)" +
                "VALUES (?, ?, ?) ";
        try {
            this.open();
            PreparedStatement stmt = this.getPreparedStatement(query);
            stmt.setString(1, entity.getName());
            stmt.setString(2, entity.getDescription());
            stmt.setDouble(3, entity.getPrice());
            LOGGER.log(Level.INFO, "Article Created: " + entity.getName());
            return this.executeUpdate(stmt) != 0;
        } catch (Exception e){
            LOGGER.log(Level.WARNING, e.getMessage());
        } finally {
            this.close();
        }
        return false;
    }
    public boolean update(IArticle entity) {
        String query = "UPDATE articles SET name = ?, description = ?, price = ? WHERE id = ?";
        try {
            this.open();
            PreparedStatement stmt = this.getPreparedStatement(query);
            stmt.setString(1, entity.getName());
            stmt.setString(2, entity.getDescription());
            stmt.setDouble(3, entity.getPrice());

            stmt.setInt(4, entity.getId());

            LOGGER.log(Level.INFO, "User Updated: " + entity.getName());
            return this.executeUpdate(stmt) != 0;
        }catch (Exception e){
            LOGGER.log(Level.WARNING, e.getMessage());
        }finally {
            this.close();
        }
        return false;
    }
    public boolean delete(IArticle entity) {
        String query = "DELETE FROM articles WHERE id = ?";
        try {
            this.open();
            PreparedStatement stmt = this.getPreparedStatement(query);
            stmt.setInt(1, entity.getId());

            LOGGER.log(Level.INFO, "User Deleted: " + entity.getName());
            return this.executeUpdate(stmt) != 0;
        } catch (Exception e){
            LOGGER.log(Level.WARNING, e.getMessage());
        }finally {
            this.close();
        }
        return false;
    }



    public IArticle read(int id) {
        ArticleDto articleDto = null;
        String query = "SELECT * FROM articles WHERE id = ? LIMIT 1";
        try{
            this.open();
            PreparedStatement stmt = this.getPreparedStatement(query);
            stmt.setInt(1, id);

            ResultSet resultSet = this.executeQuery(stmt);
            if(resultSet.next()){
                articleDto = new ArticleDto(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getDouble("price")
                );
            }
        }catch (Exception e){
            LOGGER.log(Level.WARNING, e.getMessage());
        }finally {
            this.close();
        }
        return articleDto;
    }
    public IArticle read(String name) {
        ArticleDto articleDto = null;
        String query = "SELECT * FROM articles WHERE name = ? LIMIT 1";
        try{
            this.open();
            PreparedStatement stmt = this.getPreparedStatement(query);
            stmt.setString(1, name);

            ResultSet resultSet = this.executeQuery(stmt);
            if(resultSet.next()){
                articleDto = new ArticleDto(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getDouble("price")
                );
            }
        }catch (Exception e){
            LOGGER.log(Level.WARNING, e.getMessage());
        }finally {
            this.close();
        }
        return articleDto;
    }
    public IArticle read(IArticle entity) {
        if(entity.getName() != null){
            return this.read(entity.getName());
        }
        if(entity.getId() != 0){
            return this.read(entity.getId());
        }
        return null;
    }



    public List<IArticle> list() {
        List<IArticle> articles = new ArrayList<>();
        String query = "SELECT * FROM articles";

        try{
            this.open();
            PreparedStatement stmt = this.getPreparedStatement(query);
            ResultSet resultSet = this.executeQuery(stmt);

            while (resultSet.next()){
                ArticleDto articleDto = new ArticleDto(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getDouble("price")
                );
                articles.add(articleDto);
            }

        } catch (Exception e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        }finally {
            this.close();
        }
        return articles;
    }
}
