package com.qa.tasklist.persistence.domain;

import java.sql.Date;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TaskEntriesDomainUnitTest {

	private java.util.Date dueDateTest = Date.valueOf("2022-08-12");
	private java.util.Date dueDateTest2 = Date.valueOf("2022-08-13");

	private TaskEntriesDomain entryTest1 = new TaskEntriesDomain(1L, "Task 1", dueDateTest, true, null);
	private TaskEntriesDomain entryTest2 = new TaskEntriesDomain(2L, "Task 2", dueDateTest2, true, null);
	private TaskEntriesDomain entryTest3 = new TaskEntriesDomain(2L, "Task 2", dueDateTest2, true, null);
	private TaskListsDomain taskListTest = new TaskListsDomain(1L, "List 1", null);

	@Test
	public void settersTest() {
		Assertions.assertThat(entryTest1).isNotNull();
		entryTest1.setId(1L);
		entryTest1.setDescription("Task 1");
		entryTest1.setDueDate(dueDateTest);
		entryTest1.setCompleted(true);
		entryTest1.setMyList(null);

		Assertions.assertThat(entryTest1.getId()).isNotNull();
		Assertions.assertThat(entryTest1.getDescription()).isNotNull();
		Assertions.assertThat(entryTest1.getDueDate()).isNotNull();
		Assertions.assertThat(entryTest1.isCompleted()).isTrue();
		Assertions.assertThat(entryTest1.getMyList()).isNull();

	}

	@Test
	public void gettersTest() {
		Assertions.assertThat(entryTest1.getId()).isNotNull();
		Assertions.assertThat(entryTest1.getDescription()).isNotNull();
		Assertions.assertThat(entryTest1.getDueDate()).isNotNull();
		Assertions.assertThat(entryTest1.isCompleted()).isTrue();
		Assertions.assertThat(entryTest1.getMyList()).isNull();
	}

	@Test
	public void createEntry() {
		Assertions.assertThat(entryTest1.getId()).isEqualTo(1L);
		Assertions.assertThat(entryTest1.getDescription()).isEqualTo("Task 1");
		Assertions.assertThat(entryTest1.getDueDate()).isEqualTo(dueDateTest);
		Assertions.assertThat(entryTest1.isCompleted()).isEqualTo(true);
		Assertions.assertThat(entryTest1.getMyList()).isEqualTo(null);
	}

	@Test
	public void checkEquality() {
		Assertions.assertThat(entryTest2.getId()).isEqualTo(entryTest3.getId());
		Assertions.assertThat(entryTest2.getDescription()).isEqualTo(entryTest3.getDescription());
		Assertions.assertThat(entryTest2.getDueDate()).isEqualTo(entryTest3.getDueDate());
		Assertions.assertThat(entryTest2.isCompleted()).isEqualTo(entryTest3.isCompleted());
		Assertions.assertThat(entryTest2.getMyList()).isEqualTo(entryTest3.getMyList());
	}

	@Test
	public void checkNonEquality() {
		Assertions.assertThat(entryTest1.getId()).isNotEqualTo(entryTest2.getId());
		Assertions.assertThat(entryTest1.getDescription()).isNotEqualTo(entryTest2.getDescription());
		Assertions.assertThat(entryTest1.getDueDate()).isNotEqualTo(entryTest2.getDueDate());
	}

	@Test
	public void nullId() {
		entryTest1.setId(null);
		Assertions.assertThat(entryTest1.getId()).isNull();
	}

	@Test
	public void nullDescription() {
		entryTest1.setDescription(null);
		Assertions.assertThat(entryTest1.getDescription()).isNull();
	}

	@Test
	public void nullDescription2() {
		entryTest2.setDescription(null);
		Assertions.assertThat(entryTest2.getDescription()).isNotEqualTo(entryTest3.getDescription());
	}

	@Test
	public void falseCompleted() {
		entryTest1.setCompleted(false);
		Assertions.assertThat(entryTest1.isCompleted()).isFalse();
	}

	@Test
	public void falseCompleted2() {
		entryTest1.setCompleted(false);
		Assertions.assertThat(entryTest1.isCompleted()).isNotEqualTo(entryTest2.isCompleted());
	}

	@Test
	public void trueCompleted() {
		Assertions.assertThat(entryTest1.isCompleted()).isTrue();
	}

	@Test
	public void myList() {
		entryTest1.setMyList(taskListTest);
		Assertions.assertThat(entryTest1.getMyList()).isNotNull();
	}

	@Test
	public void myList2() {
		entryTest2.setMyList(taskListTest);
		entryTest3.setMyList(taskListTest);
		Assertions.assertThat(entryTest2.getMyList()).isNotNull();
		Assertions.assertThat(entryTest3.getMyList()).isNotNull();
		Assertions.assertThat(entryTest2.getMyList()).isEqualTo(entryTest3.getMyList());
	}

	@Test
	public void constructorWithoutIdTest() {
		TaskEntriesDomain entry4 = new TaskEntriesDomain("Entry", dueDateTest, true, null);
		Assertions.assertThat(entry4.getId()).isNull();
		Assertions.assertThat(entry4.getDescription()).isEqualTo("Entry");
		Assertions.assertThat(entry4.isCompleted()).isTrue();
		Assertions.assertThat(entry4.getDueDate()).isEqualTo(dueDateTest);
	}
}
