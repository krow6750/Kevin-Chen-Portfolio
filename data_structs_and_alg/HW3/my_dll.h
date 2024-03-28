// =================== Support Code =================
// Doubly Linked List ( DLL ).
//
//
//
// - Implement each of the functions to create a working DLL.
// - Do not change any of the function declarations
//   - (i.e. dll_t* create_dll() should not have additional arguments)
// - You should not have any 'printf' statements in your DLL functions. 
//   - (You may consider using these printf statements to debug,
//     but they should be removed from your final version)
//   - (You may write helper functions to help you debug your code such as print_list etc)
// ==================================================
#ifndef MYDLL_H
#define MYDLL_H

#include <stdio.h>
#include <stdlib.h>

// Create a node data structure to store data within
// our DLL. In our case, we will stores 'integers'
typedef struct node {
	int data;
	struct node* next;
	struct node* previous;
} node_t;

// Create a DLL data structure
// Our DLL holds a pointer to the first node in our DLL called head,
// and a pointer to the last node in our DLL called tail.
typedef struct DLL {
	int count;		// count keeps track of how many items are in the DLL.
	node_t* head;		// head points to the first node in our DLL.
	node_t* tail;          //tail points to the last node in our DLL.
} dll_t;

// Creates a DLL
// Returns a pointer to a newly created DLL.
// The DLL should be initialized with data on the heap.
// (Think about what the means in terms of memory allocation)
// The DLLs fields should also be initialized to default values.
// Returns NULL if we could not allocate memory.
dll_t* create_dll() {
	// Modify the body of this function as needed.
	dll_t* myDLL = NULL;

	// TODO: Implement me!!

	myDLL = (dll_t*)malloc(sizeof(dll_t));

	if (!myDLL) {
		exit(1);
	}

	myDLL->count = 0;
	myDLL->head = NULL;
	myDLL->tail = NULL;

	return myDLL;
}

node_t* dll_get_node(dll_t* t, int pos)
{
	if (t == NULL) {
		return NULL;
	}

	if (pos < 0 || pos >= t->count) {
		return NULL;
	}

	// Traverse the list to find the node at the specified position
	node_t* current = t->head;
	for (int i = 0; i < pos; i++) {
		current = current->next;
	}

	// Return the item at the specified position
	return current;
}

// DLL Empty
// Check if the DLL is empty
// Returns -1 if the dll is NULL.
// Returns 1 if true (The DLL is completely empty)
// Returns 0 if false (the DLL has at least one element enqueued)
int dll_empty(dll_t* t) {
	// TODO: Implement me!!
	if (t == NULL)
		return -1;
	else
		return t->count == 0;
}

// push a new item to the front of the DLL ( before the first node in the list).
// Returns -1 if DLL is NULL.
// Returns 1 on success
// Returns 0 on failure ( i.e. we couldn't allocate memory for the new node)
// (i.e. the memory allocation for a new node failed).
int dll_push_front(dll_t* t, int item) {
	// TODO: Implement me!!
	if (t == NULL)
		return -1;
	else
	{
		node_t* n = (node_t*)malloc(sizeof(node_t));

		if (n == NULL)
			return 0;

		n->next = NULL;
		n->previous = NULL;
		n->data = item;

		if (t->head == NULL && t->tail == NULL)
		{
			t->head = n;
			t->tail = n;
		}
		else
		{
			n->next = t->head;
			t->head->previous = n;
			t->head = n;
		}

		t->count++;
		return 1;
	}
}

// push a new item to the end of the DLL (after the last node in the list).
// Returns -1 if DLL is NULL.
// Returns 1 on success
// Returns 0 on failure ( i.e. we couldn't allocate memory for the new node)
// (i.e. the memory allocation for a new node failed).
int dll_push_back(dll_t* t, int item) {
	// TODO: Implement me!!
	if (t == NULL)
		return -1;
	else
	{
		node_t* n = (node_t*)malloc(sizeof(node_t));

		if (n == NULL)
			return 0;

		n->next = NULL;
		n->previous = NULL;
		n->data = item;

		if (t->head == NULL && t->tail == NULL)
		{
			t->head = n;
			t->tail = n;
		}
		else
		{
			t->tail->next = n;
			n->previous = t->tail;
			t->tail = n;
		}

		t->count++;
		return 1;
	}
}

// Returns the first item in the DLL and also removes it from the list.
// Returns -1 if the DLL is NULL. 
// Returns 0 on failure, i.e. there is noting to pop from the list.
// Assume no negative numbers in the list or the number zero.
int dll_pop_front(dll_t* t) {
	// TODO: Implement me!!
	if (t == NULL)
		return -1; // Note: This line is a 'filler' so the code compiles.
	else
	{
		int num = 0;

		if (dll_empty(t))
			return num;

		num = t->head->data;
		t->head = t->head->next;
		t->head->previous = NULL;
		return num;
	}
}

// Returns the last item in the DLL, and also removes it from the list.
// Returns a -1 if the DLL is NULL. 
// Returns 0 on failure, i.e. there is noting to pop from the list.
// Assume no negative numbers in the list or the number zero.
int dll_pop_back(dll_t* t) {
	if (t == NULL) {
		return -1; // Return -1 if the list is NULL
	}

	if (t->count == 0) {
		return 0; // Return 0 if the list is empty (nothing to pop)
	}

	// Find the last node
	node_t* last = dll_get_node(t, t->count-1);

	if (t->count == 1) {
		// Special case: list has only one node
		t->head = NULL;
	}
	else {
		// Update the previous node's next pointer to skip the last node
		last->previous->next = NULL;
	}

	int poppedItem = last->data;
	free(last); // Free memory of the removed node

	t->count--; // Decrement the size of the list

	return poppedItem; // Return the popped item
}


// Inserts a new node before the node at the specified position.
// Returns -1 if the list is NULL
// Returns 1 on success
// Retruns 0 on failure:
//  * we couldn't allocate memory for the new node
//  * we tried to insert at a negative location.
//  * we tried to insert past the size of the list
//   (inserting at the size should be equivalent as calling push_back).

int dll_insert(dll_t* t, int pos, int item) {
	if (t == NULL) {
		return -1; // Return -1 if the list is NULL
	}

	if (pos < 0 || pos > t->count) {
		return 0; // Return 0 if pos is out of range
	}

	// Create a new node with the given item
	node_t* new_node = (node_t*)malloc(sizeof(node_t));

	if (new_node == NULL)
		return 0;

	new_node->next = NULL;
	new_node->previous = NULL;
	new_node->data = item;

	// If pos is 0, insert at the beginning (equivalent to push_front)
	if (pos == 0) {
		dll_push_front(t, item);
	}
	else if (pos == t->count) {
		// If pos is the size of the list, insert at the end (equivalent to push_back)
		dll_push_back(t, item);
	}
	else {
		// Insert in the middle
		node_t* current = t->head;
		for (int i = 0; i < pos - 1; i++) {
			current = current->next;
		}
		new_node->next = current->next;
		current->next = new_node;
		new_node->previous = current;
	}

	t->count++; // Increment the size of the list
	return 1; // Return 1 on success
}


// Returns the item at position pos starting at 0 ( 0 being the first item )
// Returns -1 if the list is NULL
//  (does not remove the item)
// Returns 0 on failure:
//  * we tried to get at a negative location.
//  * we tried to get past the size of the list
// Assume no negative numbers in the list or the number zero.
int dll_get(dll_t* t, int pos) {
	if (t == NULL) {
		return -1; // Return -1 if the list is NULL
	}

	if (pos < 0 || pos >= t->count) {
		return 0; // Return 0 if pos is out of range
	}

	// Traverse the list to find the node at the specified position
	node_t* current = t->head;
	for (int i = 0; i < pos; i++) {
		current = current->next;
	}

	// Return the item at the specified position
	return current->data;
}

// Removes the item at position pos starting at 0 ( 0 being the first item )
// Returns -1 if the list is NULL
// Returns 0 on failure:
//  * we tried to remove at a negative location.
//  * we tried to remove get past the size of the list
// Assume no negative numbers in the list or the number zero.
int dll_remove(dll_t* t, int pos) {
	if (t == NULL) {
		return -1; // Return -1 if the list is NULL
	}

	if (pos < 0 || pos >= t->count) {
		return 0; // Return 0 if pos is out of range
	}

	// Handle special cases for removing the first and last elements
	if (pos == 0) {
		node_t* removed = t->head;
		t->head = removed->next;
		if (t->head != NULL) {
			t->head->previous = NULL;
		}
		free(removed); // Free memory of the removed node
	}
	else if (pos == t->count - 1) {
		node_t* current = dll_get_node(t, t->count-1);
		node_t* removed = current->next;
		current->next = NULL;
		free(removed); // Free memory of the removed node
	}
	else {
		// Remove a node in the middle
		node_t* current = t->head;
		for (int i = 0; i < pos; i++) {
			current = current->next;
		}
		node_t* removed = current;
		current->previous->next = current->next;
		current->next->previous = current->previous;
		free(removed); // Free memory of the removed node
	}

	t->count--; // Decrement the size of the list
	return 1; // Return 1 on success
}


// DLL Size
// Returns -1 if the DLL is NULL.
// Queries the current size of a DLL
int dll_size(dll_t* t) {
	// TODO: Implement me!!

	return t->count; // Note: This line is a 'filler' so the code compiles.
}

// Free DLL
// Removes a DLL and all of its elements from memory.
// This should be called before the proram terminates.
void free_dll(dll_t* t) {
	
	while (!dll_empty(t))
		dll_pop_back(t);

}




 