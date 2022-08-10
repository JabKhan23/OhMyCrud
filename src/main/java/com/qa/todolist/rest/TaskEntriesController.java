package com.qa.todolist.rest;

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

import com.qa.todolist.persistence.domain.TaskEntriesDomain;
import com.qa.todolist.persistence.dto.TaskEntriesDTO;
import com.qa.todolist.services.TaskEntriesService;


@CrossOrigin
@RestController
@RequestMapping("/entries")
public class TaskEntriesController {

	private TaskEntriesService service;

	@Autowired
	public TaskEntriesController(TaskEntriesService service) {
		super();
		this.service = service;
	}

	// GET - read all
	@GetMapping("/readAll")
	public ResponseEntity<List<TaskEntriesDTO>> readAll() {
		return new ResponseEntity<List<TaskEntriesDTO>>(this.service.readAll(), HttpStatus.OK);
	}

	// GET - read by id
	@GetMapping("/read/{id}")
	public ResponseEntity<TaskEntriesDTO> readOne(@PathVariable("id") Long id) {
		return ResponseEntity.ok(this.service.readOne(id));
	}

	// POST
	@PostMapping("/create")
	public ResponseEntity<TaskEntriesDTO> create(@RequestBody TaskEntriesDomain entry) {
		return new ResponseEntity<TaskEntriesDTO>(this.service.create(entry), HttpStatus.CREATED);
	}

	// PUT
	@PutMapping("/update/{id}")
	public ResponseEntity<TaskEntriesDTO> update(@PathVariable Long id, @RequestBody TaskEntriesDomain entry) {
		return new ResponseEntity<TaskEntriesDTO>(this.service.update(id, entry), HttpStatus.ACCEPTED);
	}

	// DELETE
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable Long id) {
		return this.service.delete(id) ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
