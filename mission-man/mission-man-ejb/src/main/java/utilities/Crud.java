package utilities;

import java.util.List;

public interface Crud<T> {
	void saveOrUpdateT(T t);

	void deleteT(T t);

	T findTById(String id);

	List<T> findAllT();
}
