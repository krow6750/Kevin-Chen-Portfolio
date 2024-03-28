// Kevin Chen
// 11/4/2023
//
// gcc -Wall factorial.c -o factorial
// ./factorial

#include <stdio.h>

int factorial(int n){
  int answer=1;
  while (n!=0){
    answer=answer*n;
    n=n-1;
  }
  return answer;
};

int factorial_rec(int n){
  if (n<=0){
    return 1;
  }
  else{
    return n*factorial_rec(n-1);
  }
}

int main(){

  // Both of these should print the same result!
  printf("factorial(10) = %d\n",factorial(10));
  printf("factorial_rec(10) = %d\n",factorial_rec(10));


  return 0;
}
