// Kevin Chen
// 11/4/2023
//
// Include our header file for our my_bst.c
#include "my_bst.h"

// Include any other libraries needed
#include <stdio.h>
#include <stdlib.h>
bstnode_t* createNewNode(int item) {
    bstnode_t* newNode = (bstnode_t*)malloc(sizeof(bstnode_t));
    if (newNode) {
        newNode->data = item;
        newNode->leftChild = NULL;
        newNode->rightChild = NULL;
    }
    return newNode;
}

int insertNode(bstnode_t** node, int item) {
    if (*node == NULL) {
        *node = createNewNode(item);
        return 1;
    } else if (item < (*node)->data) {
        return insertNode(&(*node)->leftChild, item);
    } else if (item > (*node)->data) {
        return insertNode(&(*node)->rightChild, item);
    }
    return 0;
}

void printAscending(bstnode_t* node) {
    if (node) {
        printAscending(node->leftChild);
        printf("%d ", node->data);
        printAscending(node->rightChild);
    }
}

void printDescending(bstnode_t* node) {
    if (node) {
        printDescending(node->rightChild);
        printf("%d ", node->data);
        printDescending(node->leftChild);
    }
}
// Creates a BST
// Returns a pointer to a newly created BST.
// The BST should be initialized with data on the heap.
// The BST fields should also be initialized to default values(i.e. size=0).
bst_t* bst_create(){
    bst_t* myBST = (bst_t*)malloc(sizeof(bst_t));
    if (myBST) {
        myBST->size = 0;
        myBST->root = NULL;
    }
    return myBST;
}

// BST Empty
// Check if the BST is empty
// Returns 1 if true (The BST is completely empty)
// Returns 0 if false (the BST has at least one element)
int bst_empty(bst_t* t){
    return t->size==0;
}

// Adds a new node containg item to the BST
// The item is added in the correct position in the BST.
//  - If the data is less than or equal to the current node we traverse left
//  - otherwise we traverse right.
// The bst_function returns '1' upon success
//  - bst_add should increment the 'size' of our BST.
// Returns a -1 if the operation fails.
//      (i.e. the memory allocation for a new node failed).
// Your implementation should should run in O(log(n)) time.
//  - A recursive imlementation is suggested.
int bst_add(bst_t* t, int item){
    int result=insertNode(&(t->root), item);
    if (result==1){
        t->size++;
    }
    return result;
}

// Prints the tree in ascending order if order = 0, otherwise prints in descending order.
// A BST that is NULL should print "(NULL)"
// It should run in O(n) time.
void bst_print(bst_t *t, int order){
    if(t==NULL || t->root==NULL){
        printf("(NULL)");
    }else{
        if (order!=0){
            printDescending(t->root);
        } else{
            printAscending(t->root);
        }

    }
}

int calculateSum(bstnode_t* node) {
    if (node == NULL) {
        return 0; 
    }
    int leftSum = calculateSum(node->leftChild);
    int rightSum = calculateSum(node->rightChild);
    return leftSum + rightSum + node->data;
}

// Returns the sum of all the nodes in the bst. 
// A BST that is NULL exits the program.
// It should run in O(n) time.
int bst_sum(bst_t *t){
  if (t==NULL || t->root==NULL){
    return 0;
  }
  return calculateSum(t->root);
}
int searching(bstnode_t* node, int value) {
    if (node == NULL) {
        return 0;
    }

    if (value < node->data) {
        return searching(node->leftChild, value);
    } else if (value > node->data) {
        return searching(node->rightChild, value);
    } else {
        return 1;
    }
}
// Returns 1 if value is found in the tree, 0 otherwise. 
// For NULL tree -- exit the program. 
// It should run in O(log(n)) time.
int bst_find(bst_t * t, int value){
    if (t==NULL || t->root==NULL){
        return 0;
    }
    return searching(t->root,value);    
}

// Returns the size of the BST
// A BST that is NULL exits the program.
// (i.e. A NULL BST cannot return the size)
unsigned int bst_size(bst_t* t){
    if (t){
        return t->size;
    } else {
        // Handle the NULL case by returning 0, or you could also handle it by exiting the program
        // if that's the intended behavior when t is NULL.
        return 0;
    }
}
void freeingTree(bstnode_t* node) {
    if (node) {
        freeingTree(node->leftChild);
        freeingTree(node->rightChild);
        free(node);
    }
}
// Free BST
// Removes a BST and ALL of its elements from memory.
// This should be called before the proram terminates.
void bst_free(bst_t* t){
    freeingTree(t->root);
    free(t);
}

