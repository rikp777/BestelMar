package Data.Repository;

import Data.Context.Interfaces.IOrderContext;
import Data.Context.Interfaces.IRightContext;
import Data.Context.SQLContext.OrderContextSQL;
import Data.Context.SQLContext.RightContextSQL;
import Data.Repository.Interfaces.IRightRepository;
import Interfaces.model.IRight;

import java.util.List;

public class RightRepository implements IRightRepository {
    private IRightContext _context;

    public RightRepository(){
        this._context = new RightContextSQL();
    }



    public IRight getBy(int id) {
        return _context.read(id);
    }
    public IRight getBy(String name) {
        return _context.read(name);
    }



    public List<IRight> getAll() {
        return _context.list();
    }
}
