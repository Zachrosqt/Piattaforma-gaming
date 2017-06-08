package gameplatform.business;

public interface GameplatformCRUD {
	
	<T> void save(T obj);
	<T> void update(T obj);
	<T> void saveOrUpdate(T obj);
	<T> void delete(T obj);

}
