// Your name: Kevin Chen
// Date: 9/24/23
//
// Add your program here!
// 
//Include standard input/output header
#include<stdio.h>

// Declare functions
double power(double base, double n);

// Implement main, returns int value. 
int main() {
	//Loop from 1 to 10 to calculate powers of 2
	for (int i = 1; i <= 10; i++) {
		//Print 2 raised to the i, calling power function
		//%d specifies placeholder for integer value, %.2f specifies float, prints up to 2 decimal places
		printf("2 to the power of %d is %.2f\n", i, power(2, i));
	}
	//Return value of 0 from main indicates successful execution
	return 0;
}

//Function def for power
double power(double base, double n) {
	//Init result to 1.0, since any number to the power of 0 results in 1
	double result = 1.0;
	//Loop to multiply result by base 'n' times
	for (int i = 1; i <= n; i++) {
		result *= base;
	}
	//Return final calculated value
	return result;
}
