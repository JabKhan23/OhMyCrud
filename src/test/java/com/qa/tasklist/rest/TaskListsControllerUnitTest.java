package com.qa.tasklist.rest;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.qa.tasklist.persistence.dto.TaskListsDTO;
import com.qa.tasklist.services.TaskListsService;
import com.qa.tasklist.persistence.domain.TaskListsDomain;

@SpringBootTest
public class TaskListsControllerUnitTest {

	@MockBean
	private TaskListsService service;

	@MockBean
	private ModelMapper mapper;

	@Autowired
	TaskListsController controller;

	private TaskListsDTO mapToDTO(TaskListsDomain model) {
		return this.mapper.map(model, TaskListsDTO.class);
	}

	private final long ID = 1L;

	@Test
	public void readAll() {
		// resources
		List<TaskListsDomain> testList = new ArrayList<>();
		List<TaskListsDTO> test_DTOList = new ArrayList<>();
		TaskListsDomain item1 = new TaskListsDomain(1L, "List 1", null);
		TaskListsDomain item2 = new TaskListsDomain(2L, "List 2", null);
		TaskListsDTO dto1 = mapToDTO(item1);
		TaskListsDTO dto2 = mapToDTO(item2);
		testList.add(item1);
		testList.add(item2);
		test_DTOList.add(dto1);
		test_DTOList.add(dto2);
		// rules
		Mockito.when(service.readAll()).thenReturn(test_DTOList);
		// results
		ResponseEntity<List<TaskListsDTO>> result = this.controller.readAll();
		ResponseEntity<List<TaskListsDTO>> result2 = new ResponseEntity<List<TaskListsDTO>>(test_DTOList,
				HttpStatus.OK);
		// assertions
		Assertions.assertThat(result).isEqualTo(result2);

		Mockito.verify(this.service, Mockito.times(1)).readAll();
	}

	@Test
	public void readOne() {
		// resources
		TaskListsDomain testList = new TaskListsDomain(1L, "List 1", null);
		TaskListsDTO testDTO = mapToDTO(testList);
		// rules
		Mockito.when(this.service.readOne(ID)).thenReturn(testDTO);
		// results
		ResponseEntity<TaskListsDTO> result = this.controller.readOne(ID);
		ResponseEntity<TaskListsDTO> result2 = new ResponseEntity<TaskListsDTO>(testDTO, HttpStatus.OK);
		// assertions
		Assertions.assertThat(result).isEqualTo(result2);

		Mockito.verify(this.service, Mockito.times(1)).readOne(ID);
	}

	@Test
	public void create() {
		// resources
		TaskListsDomain testList = new TaskListsDomain(1L, "List 1", null);
		TaskListsDTO testDTO = mapToDTO(testList);
		// rules
		Mockito.when(this.service.create(testList)).thenReturn(testDTO);
		// results
		ResponseEntity<TaskListsDTO> result = this.controller.create(testList);
		ResponseEntity<TaskListsDTO> result2 = new ResponseEntity<TaskListsDTO>(testDTO, HttpStatus.CREATED);
		// assertions
		Assertions.assertThat(result).isEqualTo(result2);

		Mockito.verify(this.service, Mockito.times(1)).create(testList);
	}

	@Test
	public void update() {
		// resources
		TaskListsDomain testList = new TaskListsDomain(1L, "List 1", null);
		TaskListsDomain listUpdated = new TaskListsDomain(testList.getTitle(), testList.getToDoList());
		listUpdated.setId(ID);
		TaskListsDTO testDTO = mapToDTO(listUpdated);
		// rules
		Mockito.when(this.service.update(ID, listUpdated)).thenReturn(testDTO);
		// results
		ResponseEntity<TaskListsDTO> result = this.controller.update(ID, listUpdated);
		ResponseEntity<TaskListsDTO> result2 = new ResponseEntity<TaskListsDTO>(testDTO, HttpStatus.ACCEPTED);
		// assertions
		Assertions.assertThat(result).isEqualTo(result2);

		Mockito.verify(this.service, Mockito.times(1)).update(ID, listUpdated);

	}

	@Test
	public void delete() {
		// resources
		ResponseEntity<TaskListsDTO> expectedResult = new ResponseEntity<TaskListsDTO>(HttpStatus.NO_CONTENT);
		// rules
		Mockito.when(this.service.delete(1L)).thenReturn(true);
		// results
		ResponseEntity<Boolean> result = this.controller.delete(1L);
		// assertions
		Assertions.assertThat(result).isNotNull();
		Assertions.assertThat(result).isEqualTo(expectedResult);

		Mockito.verify(this.service, Mockito.times(1)).delete(1L);
	}

}
