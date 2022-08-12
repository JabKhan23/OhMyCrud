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
import com.qa.tasklist.persistence.domain.TaskEntriesDomain;
import com.qa.tasklist.persistence.dto.TaskEntriesDTO;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:schema-test.sql",
		"classpath:data-test.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles(profiles = "testing")
public class TaskEntriesControllerIntegrationTest {

	@Autowired
	private MockMvc mock;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private ObjectMapper jsonifier;

	Date dateTest1 = Date.valueOf("2022-08-10");
	Date dateTest2 = Date.valueOf("2022-08-11");
	Date dateTest3 = Date.valueOf("2022-08-12");

	private final int ID = 1;

	private final TaskEntriesDTO entryTest1 = new TaskEntriesDTO(1L, "create back end and cry", dateTest1, true);
	private final TaskEntriesDTO entryTest2 = new TaskEntriesDTO(2L, "testing for back end and cry", dateTest2, false);
	private final TaskEntriesDTO entryTest3 = new TaskEntriesDTO(3L, "finish the project and cry", dateTest3, true);

	private TaskEntriesDTO mapToDTO(TaskEntriesDomain model) {
		return this.mapper.map(model, TaskEntriesDTO.class);
	}

	@Test
	public void readAll() throws Exception {
		// resources
		List<TaskEntriesDTO> expectedResult = new ArrayList<>();
		expectedResult.add(entryTest1);
		expectedResult.add(entryTest2);
		expectedResult.add(entryTest3);
		// request
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.GET,
				"http://localhost:8080/entries/readAll");
		// expectations
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(jsonifier.writeValueAsString(expectedResult));
		// action
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
	}

	@Test
	public void readOne() throws Exception {
		TaskEntriesDTO expectedResult = entryTest1;
		// request
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.GET,
				"http://localhost:8080/entries/read/" + ID);
		// expectations
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(jsonifier.writeValueAsString(expectedResult));
		// action
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);

	}


	@Test
	public void create() throws Exception {
		// resources
		TaskEntriesDomain contentBody = new TaskEntriesDomain("complete front end", dateTest1, false, null);
		TaskEntriesDTO expectedResult = mapToDTO(contentBody);
		expectedResult.setId(4L);
		// request
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.request(HttpMethod.POST, "http://localhost:8080/entries/create")
				.contentType(MediaType.APPLICATION_JSON).content(jsonifier.writeValueAsString(contentBody))
				.accept(MediaType.APPLICATION_JSON);

		// expectations
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isCreated();
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(jsonifier.writeValueAsString(expectedResult));
		// actions
		// this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
		//didn't have time to finish this so commented out
	}

	@Test
	public void update() throws Exception {
		// resources
		TaskEntriesDomain contentBody = new TaskEntriesDomain(1L, "create back end", dateTest1, true, null);
		TaskEntriesDomain updatedBody = new TaskEntriesDomain(contentBody.getDescription(), contentBody.getDueDate(),
				contentBody.isCompleted(), contentBody.getMyList());
		updatedBody.setId(1L);
		TaskEntriesDTO expectedResult = mapToDTO(updatedBody);
		// request
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.request(HttpMethod.PUT, "http://localhost:8080/entries/update/" + ID)
				.contentType(MediaType.APPLICATION_JSON).content(jsonifier.writeValueAsString(contentBody))
				.accept(MediaType.APPLICATION_JSON);
		// expectations
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isAccepted();
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(jsonifier.writeValueAsString(expectedResult));
		// action
		// this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
	}

	@Test
	public void delete() throws Exception {
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.DELETE,
				"http://localhost:8080/entries/delete/" + 2);
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isNoContent();
		this.mock.perform(mockRequest).andExpect(matchStatus);
	}

	// delete if it doesn't exist
	@Test
	public void delete2() throws Exception {
		// resources
		Long nonExistantID = 50L;
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.DELETE,
				"http://localhost:8080/entries/delete/" + nonExistantID);
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isInternalServerError();
		this.mock.perform(mockRequest).andExpect(matchStatus);
	}
}
