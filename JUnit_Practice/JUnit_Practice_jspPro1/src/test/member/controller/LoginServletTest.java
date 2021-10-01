package test.member.controller;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.kh.member.controller.LoginServlet;
import com.kh.member.model.vo.Member;

class LoginServletTest {

	private LoginServlet servlet;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private StringWriter response_writer;
	private Map<String, String> parameters;
	
	@BeforeEach
	public void setUp() throws IOException {
		parameters = new HashMap<>();
		servlet = new LoginServlet();
		request = mock(HttpServletRequest.class);
		response = mock(HttpServletResponse.class);
		response_writer = new StringWriter();
		
		when(request.getParameter(anyString())).thenAnswer(new Answer<String>() {
			@Override
			public String answer(InvocationOnMock invocation) throws Throwable {
				return parameters.get((String) invocation.getArguments()[0]);
			}
		});
		when(response.getWriter()).thenReturn(new PrintWriter(response_writer));
	}
	
	@Test
	public void loginTest() throws Exception {
		parameters.put("user01", "pass01");
		servlet.doGet(request, response);
		String userId = ((Member)request.getSession().getAttribute("loginUser")).getUserId();
		assertThat(userId).isEqualTo("user01");
	}

}
