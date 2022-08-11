package com.qa.tasklist.rest;

import java.sql.Date;
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

import com.qa.tasklist.persistence.dto.TaskEntriesDTO;
import com.qa.tasklist.services.TaskEntriesService;
import com.qa.tasklist.persistence.domain.TaskEntriesDomain;

@SpringBootTest
public class TaskEntriesControllerUnitTest {

	@MockBean
	private TaskEntriesService service;

	@Autowired
	TaskEntriesController controller;

	@MockBean
	private ModelMapper mapper;

	private TaskEntriesDTO mapToDTO(TaskEntriesDomain model) {
		return this.mapper.map(model, TaskEntriesDTO.class);
	}

	@Test
	public void readAll() {
		// resources
		List<TaskEntriesDomain> testList = new ArrayList<>();
		List<TaskEntriesDTO> testDTOList = new ArrayList<>();
		Date dateTest = Date.valueOf("2022-08-11");
		TaskEntriesDomain testEntry = new TaskEntriesDomain(1L, "Task 1", dateTest, true, null);
		TaskEntriesDTO testDTO = mapToDTO(testEntry);
		Date dateTest2 = Date.valueOf("2022-08-12");
		TaskEntriesDomain testEntry2 = new TaskEntriesDomain(2L, "Task 2", dateTest2, true, null);
		TaskEntriesDTO testDTO2 = mapToDTO(testEntry2);
		testList.add(testEntry);
		testList.add(testEntry2);
		testDTOList.add(testDTO);
		testDTOList.add(testDTO2);
		// rules
		Mockito.when(service.readAll()).thenReturn(testDTOList);
		// results
		ResponseEntity<List<TaskEntriesDTO>> result = this.controller.readAll();
		ResponseEntity<List<TaskEntriesDTO>> result2 = new ResponseEntity<List<TaskEntriesDTO>>(testDTOList,
				HttpStatus.OK);
		// assertions
		Assertions.assertThat(result).isEqualTo(result2);

		Mockito.verify(this.service, Mockito.times(1)).readAll();
	}

	@Test
	public void readOne() {
		// resources
		Date dateTest = Date.valueOf("2022-08-11");
		TaskEntriesDomain testEntry = new TaskEntriesDomain(1L, "Task 1", dateTest, true, null);
		TaskEntriesDTO testDTO = mapToDTO(testEntry);
		// rules
		Mockito.when(this.service.readOne(1L)).thenReturn(testDTO);
		// results
		ResponseEntity<TaskEntriesDTO> result = this.controller.readOne(1L);
		ResponseEntity<TaskEntriesDTO> result2 = new ResponseEntity<TaskEntriesDTO>(testDTO, HttpStatus.OK);
		// assertions
		Assertions.assertThat(result).isEqualTo(result2);

		Mockito.verify(this.service, Mockito.times(1)).readOne(1L);
	}

	@Test
	public void create() {
		// resources
		Date dateTest = Date.valueOf("2022-08-11");
		TaskEntriesDomain testEntry = new TaskEntriesDomain(1L, "Task 1", dateTest, true, null);
		TaskEntriesDTO testDTO = mapToDTO(testEntry);
		// rules
		Mockito.when(this.service.create(testEntry)).thenReturn(testDTO);
		// results
		ResponseEntity<TaskEntriesDTO> result = this.controller.create(testEntry);
		ResponseEntity<TaskEntriesDTO> result2 = new ResponseEntity<TaskEntriesDTO>(testDTO, HttpStatus.CREATED);
		// assertions
		Assertions.assertThat(result2).isEqualTo(result);

		Mockito.verify(this.service, Mockito.times(1)).create(testEntry);
	}

	@Test
	public void update() {
		// resources
		Date dateTest = Date.valueOf("2022-08-11");
		TaskEntriesDomain testEntry = new TaskEntriesDomain(1L, "Task 1", dateTest, true, null);
		TaskEntriesDomain entryUpdated = new TaskEntriesDomain(testEntry.getDescription(), testEntry.getDueDate(),
				testEntry.isCompleted(), testEntry.getMyList());
		entryUpdated.setId(1L);
		TaskEntriesDTO testDTO = mapToDTO(entryUpdated);
		// rules
		Mockito.when(this.service.update(1L, entryUpdated)).thenReturn(testDTO);
		// results
		ResponseEntity<TaskEntriesDTO> result = this.controller.update(1L, entryUpdated);
		ResponseEntity<TaskEntriesDTO> result2 = new ResponseEntity<TaskEntriesDTO>(testDTO, HttpStatus.ACCEPTED);
		// assertions
		Assertions.assertThat(result).isEqualTo(result2);

		Mockito.verify(this.service, Mockito.times(1)).update(1L, entryUpdated);
	}

	@Test
	public void delete() {
		// resources
		ResponseEntity<TaskEntriesDTO> expectedResult = new ResponseEntity<TaskEntriesDTO>(HttpStatus.NO_CONTENT);
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
