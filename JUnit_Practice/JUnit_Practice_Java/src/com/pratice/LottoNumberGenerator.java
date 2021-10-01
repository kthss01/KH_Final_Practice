package com.pratice;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * 로또 생성기 코드
 * 1000원을 주면 1개의 로또를 생성해주는 클래스 
 */
public class LottoNumberGenerator {

	public List<Integer> generate(final int money) {
		if (!isValidMoney(money)) {
			throw new RuntimeException("올바른 금액이 아닙니다.");
		}
		
		return generate();
	}

	private List<Integer> generate() {
		return new Random()
				.ints(1, 45 + 1)
				.distinct()
				.limit(6)
				.boxed()
				.collect(Collectors.toList());
	}

	private boolean isValidMoney(int money) {
		return money == 1000;
	}
	
}
