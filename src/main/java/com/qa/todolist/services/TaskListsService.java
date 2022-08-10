package com.qa.todolist.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.qa.todolist.persistence.domain.TaskListsDomain;
import com.qa.todolist.persistence.dto.TaskListsDTO;
import com.qa.todolist.persistence.repos.TaskListsRepo;

@Service
public class TaskListsService {

	private TaskListsRepo repo;
	private ModelMapper mapper;

	@Autowired
	public TaskListsService(TaskListsRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}

	private TaskListsDTO mapToDTO(TaskListsDomain model) {
		return this.mapper.map(model, TaskListsDTO.class);
	}

	// GET - read all
	public List<TaskListsDTO> readAll() {
		List<TaskListsDomain> todoList = this.repo.findAll();
		List<TaskListsDTO> todoDTOList = todoList.stream().map(this::mapToDTO).collect(Collectors.toList());
		return todoDTOList;
	}

	// GET - read by id
	public TaskListsDTO readOne(Long id) {
		return mapToDTO(this.repo.findById(id).orElseThrow());
	}

	// POST - create
	public TaskListsDTO create(TaskListsDomain model) {
		return this.mapToDTO(this.repo.save(model));
	}

	// PUT - update
	public TaskListsDTO update(Long id, TaskListsDomain newDetails) {
		this.repo.findById(id).orElseThrow();
		newDetails.setId(id);
		TaskListsDTO result = this.mapToDTO(this.repo.save(newDetails));
		return result;
	}

	// DELETE - delete
	public boolean delete(Long id) {
		try {
			this.repo.deleteById(id);
			boolean exists = this.repo.existsById(id);

			return !exists;

		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
			return false;
		}
	}

}
