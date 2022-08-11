package com.qa.tasklist.persistence.domain;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TaskListsDomainUnitTests {

	private TaskListsDomain listTest1 = new TaskListsDomain(1L, "List 1", null);
	private TaskListsDomain listTest2 = new TaskListsDomain(2L, "List 2", null);
	private TaskListsDomain listTest3 = new TaskListsDomain(2L, "List 2", null);

	private final List<TaskEntriesDomain> entryList = new ArrayList<>();
	private final Date dateTest1 = Date.valueOf("2022-08-12");
	private final TaskEntriesDomain entryTest1 = new TaskEntriesDomain(1L, "create back end and cry", dateTest1, true, null);

	@Test
	public void settersTest() {
		Assertions.assertThat(listTest1).isNotNull();
		listTest1.setId(1L);
		listTest1.setTitle("List 1");
		listTest1.setToDoList(null);

		Assertions.assertThat(listTest1.getId()).isNotNull();
		Assertions.assertThat(listTest1.getTitle()).isNotNull();
		Assertions.assertThat(listTest1.getToDoList()).isNull();
	}

	@Test
	public void equalsWithNull() {
		Assertions.assertThat(listTest1).isNotNull();
	}

	@Test
	public void equalsWithDifferentObjects() {
		Assertions.assertThat(listTest1).isNotEqualTo(listTest2);
	}

	@Test
	public void createList() {
		Assertions.assertThat(listTest1.getId()).isEqualTo(1L);
		Assertions.assertThat(listTest1.getTitle()).isEqualTo("List 1");
		Assertions.assertThat(listTest1.getToDoList()).isEqualTo(null);
	}

	@Test
	public void checkEquality() {
		Assertions.assertThat(listTest1).isEqualTo(listTest1);
	}

	@Test
	public void checkEqualityBetweenDifferent() {
		Assertions.assertThat(listTest2.getTitle()).isEqualTo(listTest3.getTitle());
		Assertions.assertThat(listTest2.getId()).isEqualTo(listTest3.getId());
	}

	@Test
	public void checkNonEquality() {
		Assertions.assertThat(listTest1.getId()).isNotEqualTo(listTest2.getId());
		Assertions.assertThat(listTest1.getTitle()).isNotEqualTo(listTest2.getTitle());
	}

	@Test
	public void oneListTitleNull() {
		listTest2.setTitle(null);
		Assertions.assertThat(listTest2.getTitle()).isNotEqualTo(listTest3.getTitle());
	}

	@Test
	public void listTitlesNotEqual() {
		Assertions.assertThat(listTest1.getTitle()).isNotEqualTo(listTest2.getTitle());
	}

	@Test
	public void listIdsNotEqual() {
		Assertions.assertThat(listTest1.getId()).isNotEqualTo(listTest2.getId());
	}

	@Test
	public void nullId() {
		listTest1.setId(null);
		Assertions.assertThat(listTest1.getId()).isNull();
	}

	@Test
	public void nullId2() {
		listTest2.setId(null);
		Assertions.assertThat(listTest1.getId()).isNotEqualTo(listTest3.getId());
	}

	@Test
	public void toDoListNotNull() {
		entryList.add(entryTest1);
		listTest2.setToDoList(entryList);
		Assertions.assertThat(listTest2.getToDoList()).isNotNull();
		Assertions.assertThat(listTest2.getToDoList()).isNotEqualTo(listTest3.getToDoList());
	}

	@Test
	public void constructorWithoutId() {
		TaskListsDomain listTest4 = new TaskListsDomain("List 4", null);
		Assertions.assertThat(listTest4.getId()).isNull();
		Assertions.assertThat(listTest4.getTitle()).isNotNull();
		Assertions.assertThat(listTest4.getTitle()).isEqualTo("List 4");
		Assertions.assertThat(listTest4.getToDoList()).isNull();
	}

}
