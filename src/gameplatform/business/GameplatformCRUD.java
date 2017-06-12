package gameplatform.business;

import java.util.List;

import javax.persistence.Query;

public interface GameplatformCRUD {

	<T> void save(T obj);
	<T> void update(T obj);
	<T> void saveOrUpdate(T obj);
	<T> void delete(T obj);
	<T> List<T> executeQuery(String query);

}
