package com.qa.todolist.persistence.domain;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
public class TaskEntriesDomain {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String description;

	@NotNull
	@Basic
	@Temporal(TemporalType.DATE)
	private java.util.Date dueDate;

//	private String dueDate2;

	private boolean completed;

	@ManyToOne
	private TaskListsDomain myList;

	// empty constructor
	public TaskEntriesDomain() {
		super();
	}

	// constructor with all fields
	public TaskEntriesDomain(Long id, String description, Date dueDate, boolean completed, TaskListsDomain myList) {
		super();
		this.id = id;
		this.description = description;
		this.dueDate = dueDate;
		this.completed = completed;
		this.myList = myList;
	}

	// constructor without id (for testing purposes)
	public TaskEntriesDomain(String description, Date dueDate, boolean completed, TaskListsDomain myList) {
		super();
		this.description = description;
		this.dueDate = dueDate;
		this.completed = completed;
		this.myList = myList;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public java.util.Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(java.util.Date dueDate) {
		this.dueDate = dueDate;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public TaskListsDomain getMyList() {
		return myList;
	}

	public void setMyList(TaskListsDomain myList) {
		this.myList = myList;
	}

}
