package pl.spring.demo.dao;

import java.util.List;

public interface DaoAbstract<T> {
	
	 T save(T book);
	 
	 List<T> findAll();
}
