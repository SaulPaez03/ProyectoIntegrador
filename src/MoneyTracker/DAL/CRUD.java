package MoneyTracker.DAL;

import java.util.Collection;

public interface CRUD<T> {
    Collection<T> getAll();

    T getById(Integer id);

    int update(T object);

    boolean insert(T object);

    int delete(Integer id);

}
