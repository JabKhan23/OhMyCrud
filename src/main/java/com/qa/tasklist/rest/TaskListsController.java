package com.qa.tasklist.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.tasklist.persistence.domain.TaskListsDomain;
import com.qa.tasklist.persistence.dto.TaskListsDTO;
import com.qa.tasklist.services.TaskListsService;

@CrossOrigin
@RestController
@RequestMapping("/lists")
public class TaskListsController {

	private TaskListsService service;

	@Autowired
	public TaskListsController(TaskListsService service) {
		super();
		this.service = service;
	}

	// GET - read all information
	@GetMapping("/readAll")
	public ResponseEntity<List<TaskListsDTO>> readAll() {
		return new ResponseEntity<List<TaskListsDTO>>(this.service.readAll(), HttpStatus.OK);
	}

	// GET - read by id
	@GetMapping("read/{id}")
	public ResponseEntity<TaskListsDTO> readOne(@PathVariable("id") Long id) {
		return new ResponseEntity<TaskListsDTO>(this.service.readOne(id), HttpStatus.OK);
	}

	// POST
	@PostMapping("/create")
	public ResponseEntity<TaskListsDTO> create(@RequestBody TaskListsDomain entry) {
		return new ResponseEntity<TaskListsDTO>(this.service.create(entry), HttpStatus.CREATED);
	}

	// PUT
	@PutMapping("/update/{id}")
	public ResponseEntity<TaskListsDTO> update(@PathVariable Long id, @RequestBody TaskListsDomain entry) {
		return new ResponseEntity<TaskListsDTO>(this.service.update(id, entry), HttpStatus.ACCEPTED);
	}

	// DELETE
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable Long id) {
		return this.service.delete(id) ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
