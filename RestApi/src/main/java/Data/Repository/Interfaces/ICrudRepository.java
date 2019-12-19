package Data.Repository.Interfaces;

import java.util.List;

public interface ICrudRepository<T> {
    boolean add(T entity);
    boolean edit(T entity);
    boolean remove(T entity);

    T getBy(int id);
    T getBy(T entity);

    List<T> getAll();
}
