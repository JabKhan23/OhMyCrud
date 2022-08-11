package com.qa.tasklist.services;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.tasklist.persistence.dto.TaskListsDTO;
import com.qa.tasklist.persistence.domain.TaskListsDomain;
import com.qa.tasklist.persistence.repos.TaskListsRepo;

@SpringBootTest
public class TaskListsServiceUnitTest {

	@MockBean
	private ModelMapper mockedMapper;

	@MockBean
	private TaskListsRepo mockedRepo;

	@Autowired
	TaskListsService service;
	
	@Test
	public void readAll() {
		Long id = 1L; 
		TaskListsDomain testList = new TaskListsDomain(1L, "List 1", null);
		testList.setId(id);
		List<TaskListsDomain> lists = this.mockedRepo.findAll(); 
		TaskListsDTO testDTO = this.mockedMapper.map(lists, TaskListsDTO.class);
		Mockito.when(this.mockedRepo.findAll()).thenReturn(lists);
		Mockito.when(this.mockedMapper.map(lists, TaskListsDTO.class)).thenReturn(testDTO);
		Assertions.assertThat(lists).isNotNull();
		Assertions.assertThat(this.service.readAll()).isEqualTo(lists);
		Mockito.verify(this.mockedRepo, Mockito.times(2)).findAll();
	}

	@Test
	public void readOne() {
		TaskListsDomain testList = new TaskListsDomain(1L, "List 1", null);
		TaskListsDTO testDTO = this.mockedMapper.map(testList, TaskListsDTO.class);
		Mockito.when(this.mockedRepo.findById(testList.getId())).thenReturn(Optional.of(testList));
		TaskListsDTO result = this.service.readOne(1L);
		Assertions.assertThat(result).isEqualTo(testDTO);
		Mockito.verify(this.mockedRepo, Mockito.times(1)).findById(1L);
	}

	@Test
	public void create() {
		// resources
		TaskListsDomain testList = new TaskListsDomain(1L, "List 1", null);
		TaskListsDTO testDTO = new TaskListsDTO(1L, "List 1", null);
		// rules
		Mockito.when(this.mockedRepo.save(Mockito.any(TaskListsDomain.class))).thenReturn(testList);
		Mockito.when(this.mockedMapper.map(testList, TaskListsDTO.class)).thenReturn(testDTO);
		// actions
		TaskListsDTO result = this.service.create(testList);
		// assertions
		Assertions.assertThat(result).isNotNull();
		Assertions.assertThat(result).isEqualTo(testDTO);
		
		Mockito.verify(this.mockedMapper, Mockito.times(1)).map(testList, TaskListsDTO.class);
		Mockito.verify(this.mockedRepo, Mockito.times(1)).save(Mockito.any(TaskListsDomain.class));
	}

	@Test
	public void update() {
		// resources
		TaskListsDomain testList = new TaskListsDomain(1L, "List 1", null);
		TaskListsDomain listUpdated = new TaskListsDomain(testList.getTitle(), testList.getToDoList());
		listUpdated.setId(1L);
		TaskListsDTO testDTO = this.mockedMapper.map(listUpdated, TaskListsDTO.class);
		// rules
		Mockito.when(this.mockedRepo.findById(1L))
				.thenReturn(Optional.of(new TaskListsDomain(testList.getTitle(), testList.getToDoList())));
		Mockito.when(this.mockedMapper.map(listUpdated, TaskListsDTO.class)).thenReturn(testDTO);
		Mockito.when(this.mockedRepo.save(listUpdated)).thenReturn(listUpdated);
		// actions
		TaskListsDTO result = this.service.update(1L, testList);
		// assertions
		Assertions.assertThat(result).isEqualTo(testDTO);

		Mockito.verify(this.mockedMapper, Mockito.times(1)).map(listUpdated, TaskListsDTO.class);
		Mockito.verify(this.mockedRepo, Mockito.times(1)).save(Mockito.any(TaskListsDomain.class));
	}

	@Test
	public void delete() {
		Long id = 1L;
		Mockito.when(this.mockedRepo.existsById(id)).thenReturn(false);
		Assertions.assertThat(this.service.delete(id)).isTrue();
		Mockito.verify(this.mockedRepo, Mockito.times(1)).existsById(id);
	}

}
