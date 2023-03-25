package com.myTask.myTask;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class NodeControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testJoinNodes() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/nodes/connect")
						.contentType(MediaType.APPLICATION_FORM_URLENCODED)
						.param("node1", "2")
						.param("node2", "3"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testIsNodesConnected() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/nodes/isConnected")
						.contentType(MediaType.APPLICATION_FORM_URLENCODED)
						.param("node1", "2")
						.param("node2", "3"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string("true"));
	}
}
