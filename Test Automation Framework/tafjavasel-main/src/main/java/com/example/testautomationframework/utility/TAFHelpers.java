package com.example.testautomationframework.utility;

public class TAFHelpers {

	// Private constructor to hide the implicit public one
	private TAFHelpers() {
		throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
	}

	public static void printMessage(String message) {
		System.out.println(message);
	}
	
}
