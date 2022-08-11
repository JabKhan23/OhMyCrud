package com.qa.tasklist.persistence.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.tasklist.persistence.domain.TaskListsDomain;

@Repository
public interface TaskListsRepo extends JpaRepository<TaskListsDomain, Long> {

}
