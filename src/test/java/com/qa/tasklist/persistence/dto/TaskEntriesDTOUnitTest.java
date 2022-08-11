package com.qa.tasklist.persistence.dto;

import java.sql.Date;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TaskEntriesDTOUnitTest {

	private java.util.Date dueDateTest = Date.valueOf("2022-08-11");
	private java.util.Date dueDateTest2 = Date.valueOf("2022-08-12");

	private TaskEntriesDTO entryTest1 = new TaskEntriesDTO(1L, "task1", dueDateTest, true);
	private TaskEntriesDTO entryTest2 = new TaskEntriesDTO(2L, "task2", dueDateTest2, true);
	private TaskEntriesDTO entryTest3 = new TaskEntriesDTO(2L, "task2", dueDateTest2, true);

	@Test
	public void settersTest() {
		Assertions.assertThat(entryTest1).isNotNull();
		entryTest1.setId(1L);
		entryTest1.setDescription("task1");
		entryTest1.setDueDate(dueDateTest);
		entryTest1.setCompleted(true);

		Assertions.assertThat(entryTest1.getId()).isNotNull();
		Assertions.assertThat(entryTest1.getDescription()).isNotNull();
		Assertions.assertThat(entryTest1.getDueDate()).isNotNull();
		Assertions.assertThat(entryTest1.isCompleted()).isTrue();
	}

	@Test
	public void gettersTest() {
		Assertions.assertThat(entryTest1.getId()).isNotNull();
		Assertions.assertThat(entryTest1.getDescription()).isNotNull();
		Assertions.assertThat(entryTest1.getDueDate()).isNotNull();
		Assertions.assertThat(entryTest1.isCompleted()).isTrue();
	}

	@Test
	public void createEntry() {
		Assertions.assertThat(entryTest1.getId()).isEqualTo(1L);
		Assertions.assertThat(entryTest1.getDescription()).isEqualTo("task1");
		Assertions.assertThat(entryTest1.getDueDate()).isEqualTo(dueDateTest);
		Assertions.assertThat(entryTest1.isCompleted()).isTrue();
	}

	@Test
	public void checkEquality() {
		Assertions.assertThat(entryTest2.getId()).isEqualTo(entryTest3.getId());
		Assertions.assertThat(entryTest2.getDescription()).isEqualTo(entryTest3.getDescription());
		Assertions.assertThat(entryTest2.getDueDate()).isEqualTo(entryTest3.getDueDate());
		Assertions.assertThat(entryTest2.isCompleted()).isEqualTo(entryTest3.isCompleted());
	}

	@Test
	public void checkNonEquality() {
		Assertions.assertThat(entryTest1.getId()).isNotEqualTo(entryTest2.getId());
		Assertions.assertThat(entryTest1.getDescription()).isNotEqualTo(entryTest2.getDescription());
		Assertions.assertThat(entryTest1.getDueDate()).isNotEqualTo(entryTest2.getDueDate());
		Assertions.assertThat(entryTest1.isCompleted()).isNotEqualTo(false);
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
	public void nullDueDate() {
		entryTest1.setDueDate(null);
		Assertions.assertThat(entryTest1.getDueDate()).isNull();
	}

	@Test
	public void nullDueDate2() {
		entryTest2.setDueDate(null);
		Assertions.assertThat(entryTest2.getDueDate()).isNotEqualTo(entryTest3.getDueDate());
	}

}
