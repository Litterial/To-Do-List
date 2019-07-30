package com.example.demo.models.Data;

import com.example.demo.models.ToDo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository // Spring knows that the interface is a repo and should manage it, allows to create a concrete class to implement
@Transactional // all methods should be wrapped by database transactions
public interface ToDoDAO extends CrudRepository<ToDo,Integer> {
}
