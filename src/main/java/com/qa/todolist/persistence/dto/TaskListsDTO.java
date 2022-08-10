package com.qa.todolist.persistence.dto;

import java.util.List;

public class TaskListsDTO {

	private Long id;
	private String title;
	private List<TaskEntriesDTO> toDoList;

	public TaskListsDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TaskListsDTO(Long id, String title, List<TaskEntriesDTO> toDoList) {
		super();
		this.id = id;
		this.title = title;
		this.toDoList = toDoList;
	}
	 

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<TaskEntriesDTO> getToDoList() {
		return toDoList;
	}

	public void setToDoList(List<TaskEntriesDTO> toDoList) {
		this.toDoList = toDoList;
	}

}
