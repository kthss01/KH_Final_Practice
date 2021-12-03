package com.kh.spring.member.model.service;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import com.kh.spring.member.model.dao.MemberDao;
import com.kh.spring.member.model.service.MemberServiceTest.ContextConfig;
import com.kh.spring.member.model.vo.Member;


/*
 * Member Service에 있는 MemberDao 테스트
 */

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { MemberDao.class, ContextConfig.class })
class MemberServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(MemberServiceTest.class);

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
	SqlSessionTemplate sqlSession;

	@Autowired
	MemberDao dao;

	// 회원 로그인
	@Test
	public void 회원_로그인() {
		// given
		String userId = "user01";
		// 암호화된 값 Service에서 암호화함
		String userPwd = "$2a$10$eXJ4TW3er3jdLz8tIi5wFePiAy/ldh/lIfplkXXqDlK1yzOTWYI2u";
		
		Member m = new Member();
		m.setUserId(userId);
		m.setUserPwd(userPwd);
		
		// when
		Member loginUser = dao.loginMember(sqlSession, m);
		// debug 찍기
		logger.debug(loginUser.toString());
		
		// then
		// message, 기대값, 실제값
		assertEquals("로그인한 회원아이디는 같아야한다.", m.getUserId(), loginUser.getUserId());
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
		
		// when
		dao.insertMember(sqlSession, m);
		
		// then
		// 회원가입한 멤버 로그인
		Member loginUser = dao.loginMember(sqlSession, m);
		logger.debug(loginUser.toString());
		
		// 더 좋은 방법 있는데 일단 이 방법으로 진행
		// message, 기대값, 실제값
		assertEquals("userId 같아야 한다.", m.getUserId(), loginUser.getUserId());
		assertEquals("userPwd 같아야 한다.", m.getUserPwd(), loginUser.getUserPwd());
		assertEquals("userName 같아야 한다.", m.getUserName(), loginUser.getUserName());
		assertEquals("email 같아야 한다.", m.getEmail(), loginUser.getEmail());
		assertEquals("gender 같아야 한다.", m.getGender(), loginUser.getGender());
		assertEquals("age 같아야 한다.", m.getAge(), loginUser.getAge());
		assertEquals("phone 같아야 한다.", m.getPhone(), loginUser.getPhone());
		assertEquals("address 같아야 한다.", m.getAddress(), loginUser.getAddress());
	}

	// 회원 수정
	@Test
	@Transactional
	public void 회원_수정() {
		// given
		String userId = "user02";
		// 암호화된 값 Service에서 암호화함
		String userPwd = "$2a$10$eXJ4TW3er3jdLz8tIi5wFePiAy/ldh/lIfplkXXqDlK1yzOTWYI2u";
		Member m = new Member();
		m.setUserId(userId);
		m.setUserPwd(userPwd);
		
		Member loginUser = dao.loginMember(sqlSession, m);
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
		dao.updateMember(sqlSession, loginUser);
		
		// then
		// 다시 로그인
		loginUser = dao.loginMember(sqlSession, m);
		logger.debug("수정 후 로그인 유저:" + loginUser.toString());
		
		// message, 기대값, 실제값
		assertEquals("userId 같아야 한다.", userId, loginUser.getUserId());
		assertEquals("userPwd 같아야 한다.", userPwd, loginUser.getUserPwd());
		assertEquals("userName 같아야 한다.", userName, loginUser.getUserName());
		assertEquals("email 같아야 한다.", email, loginUser.getEmail());
		assertEquals("gender 같아야 한다.", gender, loginUser.getGender());
		assertEquals("age 같아야 한다.", age, loginUser.getAge());
		assertEquals("phone 같아야 한다.", phone, loginUser.getPhone());
		assertEquals("address 같아야 한다.", address, loginUser.getAddress());
	}
	
	// 회원 삭제
	@Test
	@Transactional
	public void 회원_삭제() {
		// given
		String userId = "user02";
		// 암호화된 값 Service에서 암호화함
		String userPwd = "$2a$10$eXJ4TW3er3jdLz8tIi5wFePiAy/ldh/lIfplkXXqDlK1yzOTWYI2u";
		Member m = new Member();
		m.setUserId(userId);
		m.setUserPwd(userPwd);
		
		// when
		dao.deleteMember(sqlSession, userId);
		
		// then
		Member loginUser = dao.loginMember(sqlSession, m);
		if (loginUser != null) {
			logger.debug(loginUser.toString());
		}
		assertEquals("삭제된 유저로 로그인시 null이 반환되어야한다.", null, loginUser);
	}
	
	// 비밀번호 변경
	@Test
	@Transactional
	public void 비밀번호_변경() {
		// given
		String userId = "user02";
		// 암호화된 값 Service에서 암호화함
		String userPwd = "$2a$10$eXJ4TW3er3jdLz8tIi5wFePiAy/ldh/lIfplkXXqDlK1yzOTWYI2u";
		Member m = new Member();
		m.setUserId(userId);
		m.setUserPwd(userPwd);
		
		Member loginUser = dao.loginMember(sqlSession, m);
		logger.debug("비밀번호 변경 전 로그인 유저:" + loginUser.toString());
		
		String userPwd2 = "changePwd";
		loginUser.setUserPwd(userPwd2);
		
		// when
		dao.updatePwd(sqlSession, loginUser);
		
		// then
		loginUser = dao.loginMember(sqlSession, m);
		logger.debug("비밀번호 변경 후 로그인 유저:" + loginUser.toString());
		
		assertNotEquals("재 로그인시 비밀번호는 전과 달라야한다..", userPwd, loginUser.getUserPwd());
	}
}
