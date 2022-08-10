package com.qa.todolist.persistence.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.todolist.persistence.domain.TaskEntriesDomain;

@Repository
public interface TaskEntriesRepo extends JpaRepository<TaskEntriesDomain, Long> {

	// CRUD -> h2 database

}
