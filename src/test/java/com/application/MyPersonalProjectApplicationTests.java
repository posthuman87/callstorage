package com.application;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * JUnit test for REST CallController
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MyPersonalProjectApplication.class)
@AutoConfigureMockMvc
public class MyPersonalProjectApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	private static final String GET_CALLS = "CallTest.addCall.json";

	private static final String ENCODING = "UTF-8";

	/**
	 * test addCall POST request
	 *
	 * @throws Exception
	 */
	@Test
	public void addCall() throws Exception {
		mockMvc.perform(post("/calls").contentType(MediaType.APPLICATION_JSON)
				.content(getResources(GET_CALLS))).andExpect(content().string(""))
				.andExpect(status().isOk());
	}

	/**
	 * Utility method for reading test data from file
	 *
	 * @param resourceName file name with test data
	 * @return string content of the file
	 * @throws IOException if file can't be read
	 */
	protected String getResources(String resourceName) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int length;
		try (InputStream is = getClass().getResourceAsStream(resourceName)) {
			while ((length = is.read(buffer)) != -1) {
				baos.write(buffer, 0, length);
			}
		}
		return baos.toString(ENCODING);
	}

}
