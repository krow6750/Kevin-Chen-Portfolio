// =================== Support Code =================
// Graph.
//
// - Implement each of the functions to create a working graph.
// - Do not change any of the function declarations
//   - (i.e. graph_t* create_graph() should not have additional arguments)
// - You should not have any 'printf' statements in your graph functions.
//   - (You may consider using these printf statements to debug, but they 
//      should be removed from your final version)
// ==================================================

#define MYGRAPH_H

#include "my_dll.h"
#include <stdlib.h>
#include <assert.h>
// Create a graph data structure
typedef struct graph {
    int numNodes;
    int numEdges;
    dll_t* nodes;     //an array of nodes storing all of our nodes.
} graph_t;

typedef struct graph_node {
    int data;
    dll_t* inNeighbors;
    dll_t* outNeighbors;
} graph_node_t;

// Creates a graph
// Returns a pointer to a newly created graph.
// The graph should be initialized with data on the heap.
// The graph fields should also be initialized to default values.
// Returns NULL if we cannot allocate memory.
graph_t* create_graph() {
    // Modify the body of this function as needed.
    graph_t* myGraph = malloc(sizeof(graph_t));
    if (myGraph == NULL)
        return NULL;

    myGraph->nodes = create_dll();
    myGraph->numEdges = 0;
    myGraph->numNodes = 0;
    return myGraph;
}

// Returns the node pointer if the node exists.
// Returns NULL if the node doesn't exist or the graph is NULL
graph_node_t* find_node(graph_t* g, int value) {

    node_t* temp = g->nodes->head;

    while (temp != NULL) {
        graph_node_t* graph_node = (graph_node_t*)temp->data;

        if (graph_node->data == value) {
            // Node with the specified value found, return it
            return graph_node;
        }

        temp = temp->next;
    }

    // Node with the specified value not found
    return NULL;
}

// Creates a graph node
// Note: This relies on your dll implementation.
graph_node_t* create_graph_node(int value) {
    graph_node_t* graph_node = malloc(sizeof(graph_node_t));

    if (graph_node == NULL) return NULL;

    graph_node->data = value;
    graph_node->inNeighbors = create_dll();
    graph_node->outNeighbors = create_dll();

    return graph_node;
}

// Returns 1 on success
// Returns 0 on failure ( or if the node already exists )
// Returns -1 if the graph is NULL.
int graph_add_node(graph_t* g, int value) {
    if (g == NULL) return -1;

    if (find_node(g, value) != NULL) return -1;

    graph_node_t* newNode = create_graph_node(value);
    if (newNode == NULL) return -1;

    assert(g->nodes);
    dll_push_back(g->nodes, newNode);
    g->numNodes++;

    return 1;
}

// Returns 1 on success
// Returns 0 on failure ( or if the node doesn't exist )
// Returns -1 if the graph is NULL.
int graph_remove_node(graph_t* g, int value) {
    // Check if the graph is NULL
    if (g == NULL) {
        return -1;  // Return -1 to indicate an error.
    }

    // Find the node with the specified value
    node_t* current = g->nodes->head;
    graph_node_t* node_to_remove = NULL;

    while (current != NULL) {
        graph_node_t* graph_node = (graph_node_t*)current->data;

        if (graph_node->data == value) {
            node_to_remove = graph_node;
            break;
        }

        current = current->next;
    }

    // If the node is not found, return 0 to indicate failure.
    if (node_to_remove == NULL) {
        return 0;
    }

    // Remove the node from inNeighbors and outNeighbors of other nodes
    current = g->nodes->head;

    while (current != NULL) {
        graph_node_t* graph_node = (graph_node_t*)current->data;

        // Remove edges from inNeighbors
        node_t* in_current = graph_node->inNeighbors->head;

        while (in_current != NULL) {
            graph_node_t* in_neighbor = (graph_node_t*)in_current->data;

            if (in_neighbor == node_to_remove) {
                // Remove the edge by updating the inNeighbors list
                // You need to implement your custom list removal logic here
                // (e.g., using your doubly linked list functions)
            }

            in_current = in_current->next;
        }

        // Remove edges from outNeighbors
        node_t* out_current = graph_node->outNeighbors->head;

        while (out_current != NULL) {
            graph_node_t* out_neighbor = (graph_node_t*)out_current->data;

            if (out_neighbor == node_to_remove) {
                // Remove the edge by updating the outNeighbors list
                // You need to implement your custom list removal logic here
                // (e.g., using your doubly linked list functions)
            }

            out_current = out_current->next;
        }

        current = current->next;
    }

    // Remove the node from the graph's list of nodes
    // You need to implement your custom list removal logic here
    // (e.g., using your doubly linked list functions)

    // Decrement the number of nodes and edges
    g->numNodes--;
    g->numEdges--;

    // Free the memory for the removed node
    free(node_to_remove);

    return 1;  // Return 1 to indicate success.
}

int contains_edge(graph_node_t* source, graph_node_t* destination) {
    // Assuming you have custom logic to iterate through the outNeighbors list
    node_t* current = source->outNeighbors->head;

    while (current != NULL) {
        graph_node_t* out_neighbor = (graph_node_t*)current->data;

        if (out_neighbor == destination) {
            return 1;  // Edge exists
        }

        current = current->next;
    }

    return 0;  // Edge does not exist
}

// Returns 1 on success
// Returns 0 on failure ( or if the source or destination nodes don't exist, or the edge already exists )
// Returns -1 if the graph is NULL.
int graph_add_edge(graph_t* g, int source, int destination) {
    // Check if the graph is NULL
    if (g == NULL) {
        return -1;  // Return -1 to indicate an error.
    }

    // Find the source and destination nodes
    node_t* current = g->nodes->head;
    graph_node_t* source_node = NULL;
    graph_node_t* dest_node = NULL;

    while (current != NULL) {
        graph_node_t* graph_node = (graph_node_t*)current->data;

        if (graph_node->data == source) {
            source_node = graph_node;
        }
        if (graph_node->data == destination) {
            dest_node = graph_node;
        }

        if (source_node && dest_node) {
            // Both source and destination nodes are found, exit the loop
            break;
        }

        current = current->next;
    }

    // If either the source or destination node is not found, return -1 to indicate an error.
    if (source_node == NULL || dest_node == NULL) {
        return -1;
    }

    // Check if the edge already exists from source to destination
    // You need to implement your custom logic to check for existing edges

    // Assuming you have a function to check if an edge already exists
    if (contains_edge(source_node, dest_node)) {
        return 0;  // Return 0 to indicate that the edge already exists.
    }

    dll_push_back(source_node->outNeighbors, dest_node);
    dll_push_back(dest_node->inNeighbors, source_node);

    return 1;  // Return 1 to indicate success.
}


// Returns 1 on success
// Returns 0 on failure ( or if the source or destination nodes don't exist, or the edge doesn't exists )
// Returns -1 if the graph is NULL.
int graph_remove_edge(graph_t* g, int source, int destination) {
    if (g == NULL) {
        return -1;  // Return -1 to indicate an error.
    }

    // Find the source and destination nodes
    node_t* current = g->nodes->head;
    graph_node_t* source_node = NULL;
    graph_node_t* dest_node = NULL;

    while (current != NULL) {
        graph_node_t* graph_node = (graph_node_t*)current->data;

        if (graph_node->data == source) {
            source_node = graph_node;
        }
        if (graph_node->data == destination) {
            dest_node = graph_node;
        }

        if (source_node && dest_node) {
            // Both source and destination nodes are found, exit the loop
            break;
        }

        current = current->next;
    }

    // If either the source or destination node is not found, return -1 to indicate an error.
    if (source_node == NULL || dest_node == NULL) {
        return -1;
    }

    // Check if the edge already exists from source to destination
    // You need to implement your custom logic to check for existing edges

    // Assuming you have a function to check if an edge already exists
    if (contains_edge(source_node, dest_node)) {
        return 0;  // Return 0 to indicate that the edge already exists.
    }

    dll_delete(source_node->outNeighbors, dest_node);
    dll_delete(dest_node->inNeighbors, source_node);

    return 1;  // Return 1 to indicate success.
}

// Returns dll_t* of all the in neighbors of this node.
// Returns NULL if thte node doesn't exist or if the graph is NULL.
dll_t* getInNeighbors(graph_t* g, int value) {
        if (g == NULL) {
            return NULL;
        }

        // Find the node with the specified value
        graph_node_t* node = find_node(g, value);

        if (node == NULL) {
            return NULL;  // Node not found, return NULL
        }

        return node->inNeighbors;
    }



// Returns the number of in neighbors of this node.
// Returns -1 if the graph is NULL or the node doesn't exist.
int getNumInNeighbors(graph_t* g, int value) {
        if (g == NULL) {
            return NULL;
        }

        // Find the node with the specified value
        graph_node_t* node = find_node(g, value);

        if (node == NULL) {
            return NULL;  // Node not found, return NULL
        }

        return dll_size(node->inNeighbors);
    }


// Returns dll_t* of all the out neighbors of this node.
// Returns NULL if thte node doesn't exist or if the graph is NULL.
dll_t* getOutNeighbors(graph_t* g, int value) {
    if (g == NULL) {
        return NULL;
    }

    // Find the node with the specified value
    graph_node_t* node = find_node(g, value);

    if (node == NULL) {
        return NULL;  // Node not found, return NULL
    }

    return node->outNeighbors;
}

// Returns the number of out neighbors of this node.
// Returns -1 if the graph is NULL or the node doesn't exist.
int getNumOutNeighbors(graph_t* g, int value) {
if (g == NULL) {
            return NULL;
        }

        // Find the node with the specified value
        graph_node_t* node = find_node(g, value);

        if (node == NULL) {
            return NULL;  // Node not found, return NULL
        }

        return dll_size(node->outNeighbors);
    }

// Returns the number of nodes in the graph
// Returns -1 if the graph is NULL.
int graph_num_nodes(graph_t* g) {
    if (g == NULL)
        return -1;
    
    return g->numNodes;

}

// Returns the number of edges in the graph,
// Returns -1 on if the graph is NULL
int graph_num_edges(graph_t* g) {
    if (g == NULL)
        return -1;

    return g->numEdges;
}

// Free graph
// Removes a graph and ALL of its elements from memory.
// This should be called before the program terminates.
// Make sure you free all the dll's too.
void free_graph(graph_t* g) {
    if (g == NULL) {
        return;
    }

    if (g->nodes != NULL) {
        node_t* current = g->nodes->head;
        while (current != NULL) {
            graph_node_t* graph_node = (graph_node_t*)current->data;

            // Free inNeighbors and outNeighbors doubly linked lists for each node
            if (graph_node->inNeighbors != NULL) {
                free_dll(graph_node->inNeighbors);
            }
            if (graph_node->outNeighbors != NULL) {
                free_dll(graph_node->outNeighbors);
            }

            node_t* temp = current;
            current = current->next;
            free(temp);
        }

        free(g->nodes);
    }

    free(g);
}

