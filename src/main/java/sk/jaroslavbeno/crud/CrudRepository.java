package sk.jaroslavbeno.crud;

public interface CrudRepository<T> {
    void create(T entita);
    T read(Class<T> entityClass, Object id);
    T update(T entita);
    void delete(T entita);
}
