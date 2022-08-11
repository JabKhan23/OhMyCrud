package com.qa.tasklist.persistence.dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TaskListsDTOUnitTest {

	// same as domain, except no constructor without id

	private TaskListsDTO listTest = new TaskListsDTO(1L, "List 1", null);
	private TaskListsDTO listTest2 = new TaskListsDTO(2L, "List 2", null);
	private TaskListsDTO listTest3 = new TaskListsDTO(2L, "List 2", null);

	private final List<TaskEntriesDTO> entryList = new ArrayList<>();
	private final Date dateTest1 = Date.valueOf("2022-08-12");
	private final TaskEntriesDTO entryTest1 = new TaskEntriesDTO(1L, "Create back end and cry", dateTest1, true);

	@Test
	public void settersTest() {
		Assertions.assertThat(listTest).isNotNull();
		listTest.setId(1L);
		listTest.setTitle("List 1");
		listTest.setToDoList(null);

		Assertions.assertThat(listTest.getId()).isNotNull();
		Assertions.assertThat(listTest.getTitle()).isNotNull();
		Assertions.assertThat(listTest.getToDoList()).isNull();
	}

	@Test
	public void equalsWithNull() {
		Assertions.assertThat(listTest).isNotNull();
	}

	@Test
	public void equalsWithDifferentObjects() {
		Assertions.assertThat(listTest).isNotEqualTo(listTest2);
	}

	@Test
	public void createList() {
		Assertions.assertThat(listTest.getId()).isEqualTo(1L);
		Assertions.assertThat(listTest.getTitle()).isEqualTo("List 1");
		Assertions.assertThat(listTest.getToDoList()).isEqualTo(null);
	}

	@Test
	public void checkEquality() {
		Assertions.assertThat(listTest).isEqualTo(listTest);
	}

	@Test
	public void checkEqualityBetweenDifferent() {
		Assertions.assertThat(listTest2.getTitle()).isEqualTo(listTest3.getTitle());
		Assertions.assertThat(listTest2.getId()).isEqualTo(listTest3.getId());
	}

	@Test
	public void checkNonEquality() {
		Assertions.assertThat(listTest.getId()).isNotEqualTo(listTest2.getId());
		Assertions.assertThat(listTest.getTitle()).isNotEqualTo(listTest2.getTitle());
	}

	@Test
	public void oneListTitleNull() {
		listTest2.setTitle(null);
		Assertions.assertThat(listTest2.getTitle()).isNotEqualTo(listTest3.getTitle());
	}

	@Test
	public void listTitlesNotEqual() {
		Assertions.assertThat(listTest.getTitle()).isNotEqualTo(listTest2.getTitle());
	}

	@Test
	public void listIdsNotEqual() {
		Assertions.assertThat(listTest.getId()).isNotEqualTo(listTest2.getId());
	}

	@Test
	public void nullId() {
		listTest.setId(null);
		Assertions.assertThat(listTest.getId()).isNull();
	}

	@Test
	public void nullId2() {
		listTest2.setId(null);
		Assertions.assertThat(listTest.getId()).isNotEqualTo(listTest3.getId());
	}

	@Test
	public void toDoListNotNull() {
		entryList.add(entryTest1);
		listTest2.setToDoList(entryList);
		Assertions.assertThat(listTest2.getToDoList()).isNotNull();
		Assertions.assertThat(listTest2.getToDoList()).isNotEqualTo(listTest3.getToDoList());
	}
	@Test
	public void constructorTest() {
		entryList.add(entryTest1);
		TaskListsDTO test_list = new TaskListsDTO(1L,"Test List", entryList);
		Assertions.assertThat(test_list.getId()).isEqualTo(1L);
		Assertions.assertThat(test_list.getTitle()).isEqualTo("Test List");
		Assertions.assertThat(test_list.getToDoList()).isEqualTo(entryList);
	}
	// testing at 91%
}
