package test.member.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.kh.member.controller.LoginServlet;
import com.kh.member.model.vo.Member;

class LoginServletTest {

	private LoginServlet servlet;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession session;
	private StringWriter response_writer;
	private Map<String, String> parameters;
	private Map<String, Object> attributes;
	
	@BeforeEach
	public void setUp() throws IOException {
		parameters = new HashMap<>();
		attributes = new HashMap<>();
		servlet = new LoginServlet();
		request = mock(HttpServletRequest.class);
		response = mock(HttpServletResponse.class);
		session = mock(HttpSession.class);
		response_writer = new StringWriter();
		
		when(request.getParameter(anyString())).thenAnswer(new Answer<String>() {
			@Override
			public String answer(InvocationOnMock invocation) throws Throwable {
				return parameters.get((String) invocation.getArguments()[0]);
			}
		});
		when(response.getWriter()).thenReturn(new PrintWriter(response_writer));
		when(request.getSession()).thenReturn(session);
		
//		when(request.setAttribute(anyString(), any()));
		
		doAnswer(invocation -> {
			String arg0 = invocation.getArgument(0);
			Object arg1 = invocation.getArgument(1);
			
			attributes.put(arg0, arg1);
			
			return null;
		}).when(request).setAttribute(anyString(), any());
		
		when(request.getAttribute(anyString())).thenAnswer(new Answer<Object>() {

			@Override
			public Object answer(InvocationOnMock invocation) throws Throwable {
				return attributes.get((String) invocation.getArguments()[0]);
			}
			
		});
		
//		request.setAttribute("test", "1234");
		
		doAnswer(invocation -> {
			String arg0 = invocation.getArgument(0);
			Object arg1 = invocation.getArgument(1);
			
			attributes.put(arg0, arg1);
			
			return null;
		}).when(session).setAttribute(anyString(), any());
		
		when(session.getAttribute(anyString())).thenAnswer(new Answer<Object>() {

			@Override
			public Object answer(InvocationOnMock invocation) throws Throwable {
				return attributes.get((String) invocation.getArguments()[0]);
			}
			
		});		
	}
	
	@Test
	public void loginTest() throws Exception {
//		System.out.println(request.getAttribute("test"));
//		System.out.println("test");
		parameters.put("userId", "user01");
		parameters.put("userPwd", "pass01");
		servlet.doGet(request, response);
//		System.out.println("test2");
		System.out.println(request.getSession());
		Member member = (Member)request.getSession().getAttribute("loginUser");
		System.out.println(member);
		String userId = member.getUserId();
		assertThat(userId).isEqualTo("user01");
	}

}
