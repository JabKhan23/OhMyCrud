package com.qa.tasklist.rest;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.tasklist.persistence.domain.TaskListsDomain;
import com.qa.tasklist.persistence.dto.TaskEntriesDTO;
import com.qa.tasklist.persistence.dto.TaskListsDTO;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:schema-test.sql",
		"classpath:data-test.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles(profiles = "test")
public class TaskListsControllerIntegrationTest {

	@Autowired
	private MockMvc mock;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private ObjectMapper jsonifier;

	private TaskListsDTO mapToDTO(TaskListsDomain model) {
		return this.mapper.map(model, TaskListsDTO.class);
	}

	private final int ID = 2;

	private final List<TaskEntriesDTO> entryListTest1 = new ArrayList<>();
	private final List<TaskEntriesDTO> entryListTest2 = new ArrayList<>();

	Date dateTest1  = Date.valueOf("2022-08-10");
	Date dateTest2 = Date.valueOf("2022-08-11");
	Date dateTest3 = Date.valueOf("2022-08-12");

	private final TaskEntriesDTO entryTest1 = new TaskEntriesDTO(1L, "create back end and cry", dateTest1 , true);
	private final TaskEntriesDTO entryTest2 = new TaskEntriesDTO(2L, "testing for back end and cry", dateTest2, false);
	private final TaskEntriesDTO entryTest3 = new TaskEntriesDTO(3L, "finish the project and cry", dateTest3, true);

	@Test
	public void readAll() throws Exception {
		// resources
		List<TaskListsDTO> expectedResult = new ArrayList<>();
		entryListTest1.add(entryTest3);
		entryListTest2.add(entryTest1);
		entryListTest2.add(entryTest2);
		TaskListsDTO listTest1 = new TaskListsDTO(1L, "General Tasks", entryListTest1);
		TaskListsDTO listTest2 = new TaskListsDTO(2L, "Test tasks", entryListTest2);
		expectedResult.add(listTest1);
		expectedResult.add(listTest2);
		// request
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.GET,
				"http://localhost:8080/lists/readAll");
		// expectations
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(jsonifier.writeValueAsString(expectedResult));
		// action
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
	}

	@Test
	public void readOne() throws Exception {
		// resources
		entryListTest2.add(entryTest1);
		entryListTest2.add(entryTest2);
		TaskListsDTO expectedResult = new TaskListsDTO(2L, "Test tasks", entryListTest2);
		// request
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.GET,
				"http://localhost:8080/lists/read/" + ID);
		// expectations
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(jsonifier.writeValueAsString(expectedResult));
		// action
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
	}

	@Test
	public void create() throws Exception {
		// resources
		TaskListsDomain contentBody = new TaskListsDomain("Hit List", null);
		TaskListsDTO expectedResult = mapToDTO(contentBody);
		expectedResult.setId(3L);
		// request
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.request(HttpMethod.POST, "http://localhost:8080/lists/create").contentType(MediaType.APPLICATION_JSON)
				.content(jsonifier.writeValueAsString(contentBody)).accept(MediaType.APPLICATION_JSON);
		// expectations
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isCreated();
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(jsonifier.writeValueAsString(expectedResult));
		// action
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
	}

	@Test
	public void update() throws Exception {
		// resources
		TaskListsDomain contentBody = new TaskListsDomain(1L, "List 1", null);
		TaskListsDomain updatedBody = new TaskListsDomain(contentBody.getTitle(), contentBody.getToDoList());
		updatedBody.setId(1L);
		TaskListsDTO expectedResult = mapToDTO(updatedBody);
		// request
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.request(HttpMethod.PUT, "http://localhost:8080/lists/update/" + 1)
				.contentType(MediaType.APPLICATION_JSON).content(jsonifier.writeValueAsString(contentBody))
				.accept(MediaType.APPLICATION_JSON);
		// expectations
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isAccepted();
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(jsonifier.writeValueAsString(expectedResult));
		// action
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
	}

	@Test
	public void delete() throws Exception {
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.DELETE,
				"http://localhost:8080/lists/delete/" + 2);
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isNoContent();
		this.mock.perform(mockRequest).andExpect(matchStatus);
	}

	// delete if it doesn't exist
	@Test
	public void delete2() throws Exception {
		// resources
		Long nonExistantID = 50L;
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.DELETE,
				"http://localhost:8080/lists/delete/" + nonExistantID);
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isInternalServerError();
		this.mock.perform(mockRequest).andExpect(matchStatus);
	}
}
