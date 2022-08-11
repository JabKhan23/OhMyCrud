package com.qa.tasklist.services;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.tasklist.persistence.dto.TaskEntriesDTO;
import com.qa.tasklist.persistence.domain.TaskEntriesDomain;
import com.qa.tasklist.persistence.repos.TaskEntriesRepo;

@SpringBootTest
public class TaskEntriesServiceUnitTest {

	@MockBean
	private ModelMapper mockedMapper;

	@MockBean
	private TaskEntriesRepo mockedRepo;

	@Autowired
	TaskEntriesService service;

	// CRUD TESTS

	@Test
	public void readAll() {
		// resources
		Date dateTest = new Date(2022 - 8 - 11);
		Date dateTest2 = new Date(2022 - 8 - 12);
		List<TaskEntriesDomain> testList = new ArrayList<>();
		List<TaskEntriesDTO> testDTO = new ArrayList<>();
		TaskEntriesDomain testEntry1 = new TaskEntriesDomain(1L, "task 1", dateTest, true, null);
		TaskEntriesDomain testEntry2 = new TaskEntriesDomain(2L, "task 2", dateTest2, true, null);
		testList.add(testEntry1);
		testList.add(testEntry2);
		TaskEntriesDTO testDTO1 = new TaskEntriesDTO(1L, "task 1", dateTest, true);
		TaskEntriesDTO testDTO2 = new TaskEntriesDTO(2L, "task 2", dateTest2, true);
		testDTO.add(testDTO1);
		testDTO.add(testDTO2);

		// rules
		Mockito.when(this.mockedMapper.map(testEntry1, TaskEntriesDTO.class)).thenReturn(testDTO1);
		Mockito.when(this.mockedMapper.map(testEntry2, TaskEntriesDTO.class)).thenReturn(testDTO2);
		Mockito.when(this.mockedRepo.findAll()).thenReturn(testList);

		// actions
		List<TaskEntriesDTO> result = this.service.readAll();

		// assertions
		Assertions.assertThat(result).isNotNull();
		Assertions.assertThat(result).isEqualTo(testDTO);
		Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(testDTO);

		Mockito.verify(this.mockedRepo, Mockito.times(1)).findAll();
		Mockito.verify(this.mockedMapper, Mockito.times(1)).map(testEntry1, TaskEntriesDTO.class);
		Mockito.verify(this.mockedMapper, Mockito.times(1)).map(testEntry2, TaskEntriesDTO.class);

	}

	@Test
	public void readOne() {
		Date dateTest = new Date(2022 - 8 - 11);
		TaskEntriesDomain test_entry = new TaskEntriesDomain(1L, "task 1", dateTest, true, null);
		TaskEntriesDTO testDTO = this.mockedMapper.map(test_entry, TaskEntriesDTO.class);
		Mockito.when(this.mockedRepo.findById(test_entry.getId())).thenReturn(Optional.of(test_entry));
		TaskEntriesDTO result = this.service.readOne(1L);
		Assertions.assertThat(result).isEqualTo(testDTO);

		Mockito.verify(this.mockedRepo, Mockito.times(1)).findById(1L);
	}

	@Test
	public void create() {
		// resources
		Date dateTest = new Date(2022 - 8 - 11);
		TaskEntriesDomain test_entry = new TaskEntriesDomain(1L, "task 1", dateTest, true, null);
		TaskEntriesDTO testDTO = new TaskEntriesDTO(1L, "task 1", dateTest, true);
		// rules
		Mockito.when(this.mockedRepo.save(Mockito.any(TaskEntriesDomain.class))).thenReturn(test_entry);
		Mockito.when(this.mockedMapper.map(test_entry, TaskEntriesDTO.class)).thenReturn(testDTO);
		// actions
		TaskEntriesDTO result = this.service.create(test_entry);
		// assertions
		Assertions.assertThat(result).isNotNull();
		Assertions.assertThat(result).isEqualTo(testDTO);
		Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(testDTO);

		Mockito.verify(this.mockedMapper, Mockito.times(1)).map(test_entry, TaskEntriesDTO.class);
		Mockito.verify(this.mockedRepo, Mockito.times(1)).save(Mockito.any(TaskEntriesDomain.class));
	}

	@Test
	public void update() {
		// resources
		Date dateTest = new Date(2022 - 8 - 11);
		TaskEntriesDomain test_entry = new TaskEntriesDomain(1L, "task 1", dateTest, true, null);
		TaskEntriesDomain updated_entry = new TaskEntriesDomain(test_entry.getDescription(), test_entry.getDueDate(),
				test_entry.isCompleted(), test_entry.getMyList());
		updated_entry.setId(1L);
		TaskEntriesDTO testDTO = this.mockedMapper.map(updated_entry, TaskEntriesDTO.class);
		// rules
		Mockito.when(this.mockedRepo.findById(1L))
				.thenReturn(Optional.of(new TaskEntriesDomain(test_entry.getDescription(), test_entry.getDueDate(),
						test_entry.isCompleted(), test_entry.getMyList())));
		Mockito.when(this.mockedMapper.map(updated_entry, TaskEntriesDTO.class)).thenReturn(testDTO);
		Mockito.when(this.mockedRepo.save(updated_entry)).thenReturn(updated_entry);
		// actions
		TaskEntriesDTO result = this.service.update(1L, test_entry);
		// assertions
		Assertions.assertThat(result).isEqualTo(testDTO);

		Mockito.verify(this.mockedMapper, Mockito.times(1)).map(updated_entry, TaskEntriesDTO.class);
		Mockito.verify(this.mockedRepo, Mockito.times(1)).save(Mockito.any(TaskEntriesDomain.class));
	}

	@Test
	public void delete() {
		Date dateTest = new Date(2022 - 8 - 12);
		TaskEntriesDomain test_entry = new TaskEntriesDomain(1L, "task 1", dateTest, true, null);
		TaskEntriesDTO testDTO = new TaskEntriesDTO(1L, "task 1", dateTest, true);

		Mockito.when(this.mockedRepo.existsById(test_entry.getId())).thenReturn(true);

		Assertions.assertThat(this.service.delete(testDTO.getId())).isEqualTo(!true);

		Mockito.verify(this.mockedRepo, Mockito.times(1)).deleteById(test_entry.getId());
		Mockito.verify(this.mockedRepo, Mockito.times(1)).existsById(test_entry.getId());
	}
}
