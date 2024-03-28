// Modify this file
// compile with: gcc linkedlist.c -o linkedlist
// TODO: Kevin Chen
// TODO: 10/4/2023
// Lab02 Fall 2023

#include <stdio.h>
#include <stdlib.h> // contains the functions free/malloc

// TODO: Create your node_t type here

typedef struct node
{
	int years;
	int wins;
	struct node *next;
} node_t;

// TODO: Write your functions here
// There should be 1.) create_list 2.) print_list 3.) free_list
// You may create as many helper functions as you like.

struct LinkedList
{
	node_t *head;
};

node_t *create_node(int years, int wins)
{
	node_t *n = NULL;

	n = (node_t *)malloc(sizeof(node_t));

	n->years = years;
	n->wins = wins;
	n->next = NULL;

	return n;
}

void insert(struct LinkedList *l, int years, int wins)
{
	node_t *n = create_node(years, wins);

	if (l->head == NULL)
	{
		l->head = n;
		l->head->next = NULL;
	}
	else
	{
		node_t *temp = l->head;
		while (temp->next != NULL)
		{
			temp = temp->next;
		}

		temp->next = n;
		temp = NULL;
		free(temp);
	}
}

node_t *create_list()
{
	struct LinkedList* l = (struct LinkedList*)malloc(sizeof(struct LinkedList));

    l->head = NULL;

	insert(l, 2018, 108);
	insert(l, 2017, 93);
	insert(l, 2016, 93);
	insert(l, 2015, 78);
	insert(l, 2014, 71);
	
	return l->head;
}

void print_list(const struct LinkedList* l) {
    const node_t* current = l->head;
    while (current != NULL) {
        printf("Year: %d, Wins: %d\n", current->years, current->wins);
        current = current->next;
    }
}

void free_list(struct LinkedList* l) {
    node_t* current = l->head;
    while (current != NULL) {
        node_t* temp = current;
        current = current->next;
        free(temp);
    }
    l->head = NULL;
}

int main()
{
	// TODO: Implement me!
	struct LinkedList list;
    list.head = create_list();

    printf("Printing the list:\n");
    print_list(&list);

    free_list(&list); // Free the memory allocated for the list

	return 0;
}
