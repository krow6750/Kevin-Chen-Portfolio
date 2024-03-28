// Task: Implement the divide-and-conquer algorithm 
// for calculating mixed-up-ness score

// =================== Libraries ==================
#include <stdio.h> // Include file for standard input/output
#include <stdlib.h>

// =============== Helper Functions ===============
// TODO: Implement your helper functions here


int merge(int arr[], int temp[], int l, int m, int r, int* comparisonCount) {
    int i, j, k;
    int n1 = m - l + 1;
    int n2 = r - m;

    int* leftTemp = (int*)malloc(n1 * sizeof(int));
    int* rightTemp = (int*)malloc(n2 * sizeof(int));

    for (i = 0; i < n1; i++)
        leftTemp[i] = arr[l + i];
    for (j = 0; j < n2; j++)
        rightTemp[j] = arr[m + 1 + j];

    i = 0;
    j = 0;
    k = l;
    int inversions = 0;

    while (i < n1 && j < n2) {
        (*comparisonCount)++;
        if (leftTemp[i] <= rightTemp[j]) {
            arr[k] = leftTemp[i];
            i++;
        }
        else {
            arr[k] = rightTemp[j];
            j++;
            // Calculate inversions when an element in the right subarray is smaller than an element in the left subarray
            inversions += n1 - i;
        }
        k++;
    }

    while (i < n1) {
        arr[k] = leftTemp[i];
        i++;
        k++;
    }
    while (j < n2) {
        arr[k] = rightTemp[j];
        j++;
        k++;
    }

    return inversions;
}

int mergeSort(int arr[], int temp[], int l, int r, int* comparisonCount) {
    if (l < r) {
        int m = l + (r - l) / 2;
        int inversions = 0;
        inversions += mergeSort(arr, temp, l, m, comparisonCount);
        inversions += mergeSort(arr, temp, m + 1, r, comparisonCount);
        inversions += merge(arr, temp, l, m, r, comparisonCount);
        return inversions;
    }
    return 0;
}

void mergeSort_algo(int arr[], int temp[], int l, int r, int* comparisons)
{
    if (l < r)
    {
        int m = l + (r - l) / 2;

        mergeSort_algo(arr, temp, l, m, comparisons);
        mergeSort_algo(arr, temp, m + 1, r, comparisons);

        merge(arr, temp, l, m, r, comparisons);
    }
}

int sortIntegers(int* array, unsigned int size) {
    int* temp = (int*)malloc(size * sizeof(int));

    if (temp == NULL) {
        fprintf(stderr, "Memory allocation failed.\n");
        return -1;
    }

    int comparisonCount = 0;

    int inversions = mergeSort(array, temp, 0, size - 1, &comparisonCount);

    free(temp);

    printf("Total comparisons made: %d\n", comparisonCount);
    return inversions;
}

// Provided below is a mixed-up-ness score.
// Name: mixedupness
// Input(s):
//    (1) 'array' is a pointer to an integer address. 
//         This is the start of some 'contiguous block of memory' that we will sort.
//    (2) 'size' tells us how big the array of data is we are sorting.
// Output: The mixedupness score of the original array
int mixedupness(int* array, unsigned int size) {

    int* copy = (int*)malloc(size * sizeof(int));
    for (int i = 0; i < size; i++) {
        copy[i] = array[i];
    }

    int inversions = sortIntegers(copy, size);

    free(copy);

    return inversions;
}


// Input: A pointer to an array (i.e. the array itself points to the first index)
//        The size of the array (Because we do not know how big the array is automatically)
void printIntArray(int* array, unsigned int size) {
    unsigned int i; // Note: 'unsigned int' is a datatype for storing positive integers.
    for (i = 0; i < size; i = i + 1) {
        printf("%d ", array[i]);
    }
    printf("\n");
}

int main() {
    // Some test data sets.
    int dataset1[] = { 0,1,2,3,4,5,6,7,8,9,10 };
    int dataset2[] = { 100,87,65,10,54,42,27,37 };
    int dataset3[] = { 0,3,2,1,4,7,6,5,8,9,10 };
    int dataset4[] = { 10,9,8,7,6,5,4,3,2,1,0 };
    int dataset5[] = { 100,201,52,3223,24,55,623,75,8523,-9,150 };
    int dataset6[] = { -1,1,2,-3,4,5,-6,7,8,-9,10 };

    // Print out an array
    printf("dataset 1: ");
    printIntArray(dataset1, 11);
    printf("dataset 2: ");
    printIntArray(dataset2, 8);
    printf("dataset 3: ");
    printIntArray(dataset3, 11);
    printf("dataset 4: ");
    printIntArray(dataset4, 11);
    printf("dataset 5: ");
    printIntArray(dataset5, 11);
    printf("dataset 6: ");
    printIntArray(dataset6, 11);
    printf("\n");

    // TODO: Change these so that they print 
    // both the expected score and the calculated score
    printf("dataset 1 expected = %d, actual = %d\n", 0, mixedupness(dataset1, 11));
    printf("dataset 2 expected = %d, actual = %d\n",23, mixedupness(dataset2, 8));
    printf("dataset 3 expected = %d, actual = %d\n",6, mixedupness(dataset3, 11));
    printf("dataset 4 expected = %d, actual = %d\n",55, mixedupness(dataset4, 11));
    printf("dataset 5 expected = %d, actual = %d\n", 27 , mixedupness(dataset5, 11));
    printf("dataset 6 expected = %d, actual = %d\n", 18, mixedupness(dataset6, 11));

    return 0;
}
