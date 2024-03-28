# Build an executable using the following:
#
# clang barebones.s -o barebones  # clang is another compiler like gcc
#
.text
_barebones:

.data
	
.globl main

main:
				# (1) What are we setting up here?
				# Ans: Function prologue, setting up the stack frame.
	pushq %rbp		#
	movq  %rsp, %rbp	#

				# (2) What is going on here
				# Ans: syscall number for sys_write (1)
	movq $1, %rax		# syscall number for sys_write
	movq $1, %rdi		# file descriptor (1 for stdout)
	leaq .hello.str,%rsi	# address of the string to be printed


			# (3) What is syscall? We did not talk about this
			# in class.
			# Ans: Syscall is a request to the operating system to perform a certain task.
			#
	syscall			# Which syscall is being run?
				# Ans: Syscall for sys_write

				# (4) What would another option be instead of 
				# using a syscall to achieve this?
				# Ans: We could use a library to do printf, similar to how it's done in the C language.

	movq	$60, %rax	# (5) We are again setting up another syscall
	movq	$0, %rdi	# What command is it?
				# Ans:	Syscall for sys_exit (60)
	syscall

	popq %rbp		# (Note we do not really need
			 	# this command here after the syscall)

.hello.str:
	.string "Hello World!\n"
	.size	.hello.str,13		# (6) Why is there a 13 here?
					# Ans: Size of string is 13 bytes
