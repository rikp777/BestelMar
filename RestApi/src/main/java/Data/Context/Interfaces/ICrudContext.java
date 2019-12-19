package Data.Context.Interfaces;

import java.util.List;

public interface ICrudContext<T> {
    boolean create(T entity);
    boolean update(T entity);
    boolean delete(T entity);

    T read(int id);
    T read(T entity);

    List<T> list();

}
