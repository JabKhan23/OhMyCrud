package com.qa.tasklist.persistence.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class TaskListsDomain {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String title;

	@OneToMany(mappedBy = "myList", fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<TaskEntriesDomain> toDoList;

	public TaskListsDomain() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TaskListsDomain(Long id, String title, List<TaskEntriesDomain> toDoList) {
		super();
		this.id = id;
		this.title = title;
		this.toDoList = toDoList;
	}

	// constructor without id for testing purposes
	public TaskListsDomain(String title, List<TaskEntriesDomain> toDoList) {
		super();
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

	public List<TaskEntriesDomain> getToDoList() {
		return toDoList;
	}

	public void setToDoList(List<TaskEntriesDomain> toDoList) {
		this.toDoList = toDoList;
	}

}
