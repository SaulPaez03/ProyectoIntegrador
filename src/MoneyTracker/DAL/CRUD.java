package MoneyTracker.DAL;

import java.util.Collection;

public interface CRUD<T> {
    public  Collection<T> getAll();
    public T getById(Integer id);
    public int update(T object);
    public boolean insert(T object);
    public int delete(Integer id);

}
