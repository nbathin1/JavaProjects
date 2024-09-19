package com.application.Remainderjava;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {
	
	void save(T t);
	void update(T t);
	void delete(T t);
	Optional <T> find(T t);
	List<T> getAll(int t);

}
