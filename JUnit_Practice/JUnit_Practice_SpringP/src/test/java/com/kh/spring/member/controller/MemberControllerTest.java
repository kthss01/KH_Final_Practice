package com.kh.spring.member.controller;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import com.kh.spring.example.ControllerExampleTest.ContextConfig;
import com.kh.spring.member.model.dao.MemberDao;
import com.kh.spring.member.model.service.MemberService;
import com.kh.spring.member.model.service.MemberServiceImpl;
import com.kh.spring.member.model.vo.Member;

/*
 * Member Controller에 있는 Service 테스트
 * 
 */

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { MemberServiceImpl.class, MemberDao.class, ContextConfig.class, BCryptPasswordEncoder.class } )
class MemberControllerTest {

	private static final Logger logger = LoggerFactory.getLogger(MemberControllerTest.class);

	// Session DI 하기 위한 Configuration 클래스위에 annotation 형태가 아니고 xml로 되어있어서 따로 추가
	@Configuration
	@ImportResource(locations = { "file:src/main/resources/root-context.xml" })
	public static class ContextConfig {

		@Inject
		private SqlSessionFactory sqlFactory;

		@Bean
		public SqlSession sqlSession() {
			return sqlFactory.openSession();
		}

	}

	@Autowired
	MemberService service;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	// 회원 로그인
	@Test
	public void 회원_로그인() {
		// given 
		String userId = "user02";
		String userPwd = "1234";
		Member m = new Member();
		m.setUserId(userId);
		m.setUserPwd(userPwd);
		
		// when
		Member loginUser = service.loginMember(bCryptPasswordEncoder, m);
		
		// then
		// message, 기대값, 실제값
		logger.debug(loginUser.toString());
		assertNotEquals("로그인한 유저의 비밀번호는 암호화되어야한다.", userPwd, loginUser.getUserPwd());
	}
	
	// 회원 생성
	@Test
	@Transactional
	public void 회원_생성() {
		// given 
		String userId = "test01";
		String userPwd = "1234"; // 그냥 1234로 테스트 실제론 암호화함
		String userName = "테스터";
		String email = "test@test.co.kr";
		String gender = "M";
		String age = "31";
		String phone = "010-1234-5678";
		String address = "테스트주소";
		
		Member m = new Member();
		m.setUserId(userId);
		m.setUserPwd(userPwd);
		m.setUserName(userName);
		m.setEmail(email);
		m.setGender(gender);
		m.setAge(age);
		m.setPhone(phone);
		m.setAddress(address);
		
		logger.debug("암호화 전 비밀번호: " + m.getUserPwd());
		String encPwd = bCryptPasswordEncoder.encode(m.getUserPwd());
		logger.debug("암호화 후 비밀번호: " + encPwd);
		
		assertNotEquals("암호화한 비밀번호는 달라야한다", encPwd, m.getUserPwd());
		
		m.setUserPwd(encPwd);
		
		// when
		service.insertMember(m);
		
		// then
		m.setUserPwd(userPwd); // 다시 암호화전 비밀번호로 설정
		Member loginUser = service.loginMember(bCryptPasswordEncoder, m);
		logger.debug(loginUser.toString());
		
		// message, 기대값, 실제값
		assertEquals("userId 같아야 한다.", userId, loginUser.getUserId());
		assertEquals("userPwd는 암호화된 비밀번호와 같아야 한다.", encPwd, loginUser.getUserPwd());
		assertEquals("userName 같아야 한다.", userName, loginUser.getUserName());
		assertEquals("email 같아야 한다.", email, loginUser.getEmail());
		assertEquals("gender 같아야 한다.", gender, loginUser.getGender());
		assertEquals("age 같아야 한다.", age, loginUser.getAge());
		assertEquals("phone 같아야 한다.", phone, loginUser.getPhone());
		assertEquals("address 같아야 한다.", address, loginUser.getAddress());
	}
	
	// 회원 수정
	@Test
	@Transactional
	public void 회원_수정() throws Exception {
		// given
		String userId = "user02";
		String userPwd = "1234";
		Member m = new Member();
		m.setUserId(userId);
		m.setUserPwd(userPwd);
		
		Member loginUser = service.loginMember(bCryptPasswordEncoder, m);
		logger.debug("수정 전 로그인 유저:" + loginUser.toString());
		
		String userName = loginUser.getUserName();
		String email = "emailUpdateTest@test.test";
		String age = "99";
		String phone = "111-2222-3333";
		String address = "수정된 주소";
		String gender = "F";
		
		loginUser.setEmail(email);
		loginUser.setAge(age);
		loginUser.setPhone(phone);
		loginUser.setAddress(address);
		loginUser.setGender(gender);
		
		// when
		// Exception 처리 test 참고
		// 예외 발생하는지 체크하는 assert 였음 안나면 에러나는거
//		assertThatThrownBy(() -> service.updateMember(loginUser))
//		.isInstanceOf(Exception.class)
//		.hasMessageContaining("회원수정 실패");
		
//		assertThrows(Exception.class, 
//				() -> service.updateMember(loginUser),
//				"회원수정 실패");
		
		service.updateMember(loginUser);
		
		// then
		Member loginUser2 = service.loginMember(bCryptPasswordEncoder, m);
		logger.debug("수정 후 로그인 유저:" + loginUser2.toString());
		
		// message, 기대값, 실제값
		assertEquals("userId 같아야 한다.", userId, loginUser2.getUserId());
		assertEquals("userPwd 같아야 한다.", loginUser.getUserPwd(), loginUser2.getUserPwd());
		assertEquals("userName 같아야 한다.", userName, loginUser2.getUserName());
		assertEquals("email 같아야 한다.", email, loginUser2.getEmail());
		assertEquals("gender 같아야 한다.", gender, loginUser2.getGender());
		assertEquals("age 같아야 한다.", age, loginUser2.getAge());
		assertEquals("phone 같아야 한다.", phone, loginUser2.getPhone());
		assertEquals("address 같아야 한다.", address, loginUser2.getAddress());
	}
	
	// 회원 삭제
	@Test
	@Transactional
	public void 회원_삭제() {
		// given 
		String userId = "user02";
		String userPwd = "1234";
		Member m = new Member();
		m.setUserId(userId);
		m.setUserPwd(userPwd);
		
		// when
		service.deleteMember(userId);
		
		// then
		// 예외가 발생하는지 테스트하는거 
		// 예외가 발생 안하면 에러가남
		assertThatThrownBy(
		() -> service.loginMember(bCryptPasswordEncoder, m))
		.isInstanceOf(Exception.class)
		.hasMessageContaining("아이디가 없음");
	}
	
	// 비밀번호 변경
	@Test
	@Transactional
	public void 비밀번호_변경() {
		// given 
		String userId = "user02";
		// 암호화된 값 Service에서 암호화함
		String userPwd = "1234";
		Member m = new Member();
		m.setUserId(userId);
		m.setUserPwd(userPwd);
		
		Member loginUser = service.loginMember(bCryptPasswordEncoder, m);
		logger.debug("비밀번호 변경 전 로그인 유저:" + loginUser.toString());
		
		String userPwd2 = "changePwd";
		
		// when
		service.updatePwd(bCryptPasswordEncoder, loginUser, userPwd, userPwd2);
		
		// then
		assertThatThrownBy(
			() -> service.loginMember(bCryptPasswordEncoder, m))
			.isInstanceOf(Exception.class)
//			.hasMessageContaining("아이디가 없음"); // 이걸로 하면 비밀번호 불일치 exception 떴다고 비교해줌
			.hasMessageContaining("비밀번호 불일치");
	}
}
