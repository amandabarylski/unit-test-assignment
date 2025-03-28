package com.promineotech;

import java.util.Random;

public class TestDemo {

	public int addPositive(int a, int b) {
		if (a > 0 && b > 0) {
			return a + b;
		} else {
			throw new IllegalArgumentException("Both parameters must be positive!");
		}
	}
	
	//I decided to make a method to find the remainder of one integer divided by another.
	//I threw an ArithmeticException with a custom message to prevent dividing by zero.
	public int findRemainder(int x, int y) {
		if (y != 0) {
			return x % y;
		} else {
			throw new ArithmeticException("You can't divide by zero!");
		}
	}
	
	public int randomNumberSquared() {
		int randomNumber = getRandomInt();
		
		return randomNumber * randomNumber;
	}
	
	int getRandomInt() {
		Random random = new Random();
		
		return random.nextInt(10) + 1;
	}
	
}
