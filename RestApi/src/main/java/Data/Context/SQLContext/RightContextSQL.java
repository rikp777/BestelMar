package Data.Context.SQLContext;

import Data.Context.Interfaces.IRightContext;
import Data.DTO.RightDto;
import Data.Context.SQLContext.Helpers.SQLConnector;
import Interfaces.model.IRight;
import Interfaces.model.IUser;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RightContextSQL extends SQLConnector implements IRightContext {
    private final static Logger LOGGER = Logger.getLogger(RightContextSQL.class.getName());

    private List<IRight> _rights;

    public RightContextSQL(){
        instantiateContextSQL();
    }

    private void instantiateContextSQL(){
        List<IRight> rights = new ArrayList<>();
        String query = "SELECT * FROM rights";
        try{
            this.open();
            PreparedStatement stmt = this.getPreparedStatement(query);
            ResultSet resultSet = this.executeQuery(stmt);

            while(resultSet.next()){
                RightDto rightDto = new RightDto();
                rightDto.setId(resultSet.getInt("id"));
                rightDto.setName(resultSet.getString("name"));
                rightDto.setDescription(resultSet.getString("description"));

                rights.add(rightDto);
            }
        }catch (Exception e){
            LOGGER.log(Level.WARNING, e.getMessage());
        }finally {
            this.close();
        }
        _rights = rights;
    }

    public IRight read(int id){
        IRight right = _rights.stream().filter(r -> r.getId() == id).findFirst().orElse(null);
        return right;
    }
    public IRight read(String name){
        IRight right = _rights.stream().filter(r -> r.getName() == name).findFirst().orElse(null);
        return right;
    }
    public List<IRight> list(IUser user){
        List<IRight> rights = new ArrayList<>();
        String query = "SELECT * FROM right_user JOIN rights on rights.id = right_user.right_id WHERE user_id = ?";
        try{
            this.open();
            PreparedStatement stmt = this.getPreparedStatement(query);
            stmt.setInt(1, user.getId());

            ResultSet resultSet = this.executeQuery(stmt);

            while(resultSet.next()){
                RightDto rightDto = new RightDto();
                rightDto.setId(resultSet.getInt("id"));
                rightDto.setName(resultSet.getString("name"));
                rightDto.setDescription(resultSet.getString("description"));

                rights.add(rightDto);
            }
        }catch (Exception e){
            LOGGER.log(Level.WARNING, e.getMessage());
        }finally {
            this.close();
        }
        return new ArrayList<>(rights);
    }

    public List<IRight> list(){
        return new ArrayList<>(_rights);
    }
}
