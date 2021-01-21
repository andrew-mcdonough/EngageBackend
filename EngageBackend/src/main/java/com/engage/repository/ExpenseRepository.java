package com.engage.repository;


import org.springframework.data.repository.CrudRepository;
import com.engage.repository.model.ExpenseModel;

public interface ExpenseRepository extends CrudRepository<ExpenseModel, Integer> {

}

