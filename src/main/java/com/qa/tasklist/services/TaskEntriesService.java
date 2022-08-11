package com.qa.tasklist.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.qa.tasklist.persistence.domain.TaskEntriesDomain;
import com.qa.tasklist.persistence.dto.TaskEntriesDTO;
import com.qa.tasklist.persistence.repos.TaskEntriesRepo;


@Service
public class TaskEntriesService {


	// we call on the repo to extract data to and from the database

	private TaskEntriesRepo repo;
	private ModelMapper mapper;

	@Autowired
	public TaskEntriesService(TaskEntriesRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}

	private TaskEntriesDTO mapToDTO(TaskEntriesDomain model) {
		return this.mapper.map(model, TaskEntriesDTO.class);
	}

	// GET - read all
	public List<TaskEntriesDTO> readAll() {
		List<TaskEntriesDomain> dbList = this.repo.findAll();
		List<TaskEntriesDTO> resultList = dbList.stream().map(this::mapToDTO).collect(Collectors.toList());
		return resultList;
	}

	// GET - read by id
	public TaskEntriesDTO readOne(Long id) {
		return mapToDTO(this.repo.findById(id).orElseThrow());
	}

	// POST - create
	public TaskEntriesDTO create(TaskEntriesDomain model) {
		return this.mapToDTO(this.repo.save(model));
	}

	// PUT - update
	public TaskEntriesDTO update(Long id, TaskEntriesDomain newDetails) {
		this.repo.findById(id).orElseThrow();
		newDetails.setId(id);
		TaskEntriesDTO result = this.mapToDTO(this.repo.save(newDetails));
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
