# Lab 3 -- Counting Assembly

This is the starting readme for this assignment.  Please edit the following 
information by removing the "*edit me*" and replace it with appropriate 
information your assignment. If it is asking you a question, please provide 
a response.

- Name: Kevin Chen

- Title: Lab03

- How many hours did it take you to complete this assignment? 10-12. I'm really glad we had extra time for this lab, since I kept running into issues running estimator.c on my local windows machine then adapting it for the Linux server, and I had somehow gotten the wrong barebones.s file. I've sorted everything out though. 

- Did you collaborate with any other students/TAs/Professors? If so, tell 
  us who and in what capacity. 
  - I consulted Nick for help with the lab, and I was a bit confused by the assignment instructions and emailed Professor Valcourt for clarification.
  
- Did you use any external resources? (Cite them below)
  - w3schools.com
  - stackoverflow.com
  - TA & Professor
  
- Use your tool to measure the  number of instructions that are require to 
  execute your implementation of the guessing game program that you 
  implemented in Homework 1 using two different levels of optimization:

  - When your program is compiled using the -O0 flag:

    ```
    PS D:\Dropbox\Work\CS5008\Khoury Github\CS5008_kevinchen\Lab03> ./estimator_O0 barebones.s

    Add: 1
    Mov: 5
    Lea: 1
    Push: 1
    Pop: 1
    Total Instructions: 9
    Total Cycles: 11
    ```

  - When your program is compiled using the -O3 flag:

    ```
    PS D:\Dropbox\Work\CS5008\Khoury Github\CS5008_kevinchen\Lab03> ./estimator_O3 barebones.s

    Add: 1
    Mov: 5
    Lea: 1
    Push: 1
    Pop: 1
    Total Instructions: 9
    Total Cycles: 11
    ```

- (Optional) What was your favorite part of the assignment? 
  - *edit me*
  
- (Optional) How would you improve the assignment? 
  - *edit me*

## Logistics

For this assignment (and every assignment/lab), you must login into the 
servers through `your_khoury_name@login.khoury.northeastern.edu` to complete 
and test your work. The reason is the examples I will provide below are 
compiled strictly for our machine's architecture, and this is a consistent 
architecture where your submission will be graded.

### Important notes

* Your code **must compile and run** on the Khoury machines to earn credit. 
  Make sure you test your programs on these machines, as this is where we 
  grade your assignments.
* You must commit any additional files (if any) into your repository so we 
  can test your code.
  * Points will be lost if you forget!
* Do not forget to push your changes in your private repo for grading before 
  the assignment deadline!**

