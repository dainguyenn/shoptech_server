package com.dn.shoptech;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ShoptechApplicationTests {

	Calculator underTest = new Calculator();

	@Test
	void isShouldAddNumber() {
		int  numberOne = 1;
		int  numberTwo = 2;

		int result = underTest.add(numberOne,numberTwo);

		assertThat(result).isEqualTo(3);

	}

	class Calculator{
		int add(int a, int b){
			return a+b;
		}
	}
}
