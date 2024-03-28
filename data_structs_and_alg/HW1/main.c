// Your name: Kevin Chen
// Date: 9/25/23
//
// Add your program here!

#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int main() {
	int numGuesses[5]; //Array to store no. of guesses per game
	int totalGuesses = 0;

	//Seed for random no. generator
	srand(time(0));

	//Play guessing game five times
	for (int game = 0; game <5; game++) {
		int correctNum = rand() % 10 + 1; //Generate no. randomly between one to ten
		int guess;
		numGuesses[game] = 0; //Initialize guesses for this game to 0

		printf("=====================================\n"); //Prompt user for input
		printf("CPU Says: Pick a number between 1-10\n");
		printf("=====================================\n");

		while (1) {
			printf("Make a guess:");
			scanf("%d", &guess);
			numGuesses[game]++; //Increment counting guesses

			if (guess < correctNum) {
				printf("No guess higher!\n");
			} else if (guess > correctNum) {
				printf("No guess lower!\n");
			} else {
				printf("You got it!\n");
				printf("Game %d took you %d tries!\n", game, numGuesses[game]); //Print number of guesses after every game to check final result numbers
				break; //If user guesses correctly, exit loop
			}
		}
	}

	printf("====================================================\n");
	printf("Here are the results of your guessing abilities:\n");
	printf("====================================================\n");
	for (int i = 0; i < 5; i++) {
		printf("Game %d took you %d guesses\n", i, numGuesses[i]);
	}

	return 0; //Exit after 5 games

}
