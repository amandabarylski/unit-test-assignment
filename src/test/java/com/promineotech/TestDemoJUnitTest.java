package com.promineotech;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class TestDemoJUnitTest {
	
	private TestDemo testDemo;

	@BeforeEach
	void setUp() throws Exception {
		testDemo = new TestDemo();
	}
	
	//I put my Arguments Streams above the tests to avoid splitting the tests up.
	static Stream<Arguments> argumentsForAddPositive() {
		return Stream.of(
				Arguments.of(2, 4, 6, false),
				Arguments.of(5, 7, 12, false),
				Arguments.of(0, 2, 2, true),
				Arguments.of(5, -5, 0, true)
				);
	}
	
	//As I didn't forbid the use of negatives, I had to check a couple of examples of them as well.
	//I had to adjust some of the expected parameters as I could not put null for the divide by 0 attempts,
	//and forgot exactly how dividing with negatives worked, so it took a bit of trial and error.
	static Stream<Arguments> argumentsForFindRemainder() {
		return Stream.of(
				Arguments.of(9, 2, 1, false),
				Arguments.of(12, 4, 0, false),
				Arguments.of(7, -2, 1, false),
				Arguments.of(4, 0, 0, true),
				Arguments.of(-10, 4, -2, false),
				Arguments.of(8, 0, 0, true)
				);
	}

	@ParameterizedTest
	@MethodSource("com.promineotech.TestDemoJUnitTest#argumentsForAddPositive")
	void assertThatTwoPositiveNumbersAreAddedCorrectly(int a, int b, int expected, boolean expectException) {
		if(!expectException) {
			assertThat(testDemo.addPositive(a, b)).isEqualTo(expected);
		} else {
			assertThatThrownBy(() -> testDemo.addPositive(a, b));
		}
	}
	
	@Test
	void assertThatPairsOfPositiveNumbersAreAddedCorrectly() {
		assertThat(testDemo.addPositive(5, 4)).isEqualTo(9);
		assertThat(testDemo.addPositive(10, 3)).isEqualTo(13);
		assertThat(testDemo.addPositive(73, 27)).isEqualTo(100);
		assertThat(testDemo.addPositive(7, 9)).isEqualTo(16);
	}
	
	//I opted to use a ParametizedTest so I could check that my thrown error was working.
	//It's structured the same way as the first example test was, so I mainly just followed along.
	@ParameterizedTest
	@MethodSource("com.promineotech.TestDemoJUnitTest#argumentsForFindRemainder")
	void assertThatRemainderOfTwoNumbersIsReturnedCorrectly(int x, int y, int expected, boolean expectException) {
		if(!expectException) {
			assertThat(testDemo.findRemainder(x, y)).isEqualTo(expected);
		} else {
			assertThatThrownBy(() -> testDemo.findRemainder(x, y));
		}
	}
	
	@Test
	void assertThatNumberSquaredIsCorrect() {
		TestDemo mockDemo = spy(testDemo);
		
		doReturn(5).when(mockDemo).getRandomInt();
		
		int fiveSquared = mockDemo.randomNumberSquared();
		
		assertThat(fiveSquared).isEqualTo(25);
	}

}
