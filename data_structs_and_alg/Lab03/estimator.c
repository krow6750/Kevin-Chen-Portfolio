// Kevin Chen
// 10/15/2023
// CS5008 Lab03
//
// Implement your cycle count tool here.

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>


char* read_file(const char* filename);
void strToLower(char* str);
char* getNextSubstring(char* ptr, size_t length);
void parseFile(char* str, int* arr);


int main(int argc, char** argv) {
    //const char* filename = "barebones.s";
    const char* filename = argv[1];
    char* file_content = read_file(filename);

    int arr[9] = { 0,0,0,0,0,0,0,0,0 };

    parseFile(file_content, arr);

    int totalInstruc = 0;
    int totalCycles = 0;

    /*for (int i = 0; i < 9; i++)
        printf("%d ", arr[i]);*/

    printf("\n");


    for (int i = 0; i < 9; i++)
        totalInstruc += arr[i];

    for (int i = 0; i < 9; i++)
    {
        if (i == 0 && arr[i] != 0)
        {
            printf("Add: %d\n", arr[i]);
            totalCycles += arr[i];
        }
        else if (i == 1 && arr[i] != 0)
        {
            printf("Sub: %d\n", arr[i]);
            totalCycles += arr[i];
        }
        else if (i == 2 && arr[i] != 0)
        {
            printf("Mul: %d\n", arr[i]);
            totalCycles += (arr[i] * 3);
        }
        else if (i == 3 && arr[i] != 0)
        {
            printf("Div: %d\n", arr[i]);
            totalCycles += (arr[i] * 24);
        }
        else if (i == 4 && arr[i] != 0)
        {
            printf("Mov: %d\n", arr[i]);
            totalCycles += arr[i];
        }
        else if (i == 5 && arr[i] != 0)
        {
            printf("Lea: %d\n", arr[i]);
            totalCycles += (arr[i] * 3);
        }
        else if (i == 6 && arr[i] != 0)
        {
            printf("Push: %d\n", arr[i]);
            totalCycles += arr[i];
        }
        else if (i == 7 && arr[i] != 0)
        {
            printf("Pop: %d\n", arr[i]);
            totalCycles += arr[i];
        }
        else if (i == 8 && arr[i] != 0)
        {
            printf("Ret: %d\n", arr[i]);
            totalCycles += arr[i];
        }

    }

    printf("Total Instructions: %d\n", totalInstruc);
    printf("Total Cycles: %d\n", totalCycles);

    if (file_content != NULL) {
        //printf("File content:\n%s\n", file_content);
        free(file_content); // Don't forget to free the allocated memory
    }
    else {
        printf("Failed to read the file.\n");
    }

    return 0;
}


char* read_file(const char* filename) {
    FILE* file;
    file = fopen(filename, "r");

    if (file == NULL) {
        return NULL; // Failed to open the file
    }

    // Determine the file size
    fseek(file, 0, SEEK_END);
    long file_size = ftell(file);
    rewind(file);

    // Allocate memory for the file content plus one for the null-terminator
    char* content = (char*)malloc(file_size + 1);
    if (content == NULL) {
        fclose(file);
        return NULL; // Memory allocation failed
    }

    // Read the file into the allocated memory
    size_t bytes_read = fread(content, 1, file_size, file);
    if (bytes_read != file_size) {
        free(content);
        fclose(file);
        return NULL; // Failed to read the file
    }

    // Null-terminate the string
    content[file_size] = '\0';

    fclose(file);
    return content;
}


void strToLower(char* str) {
    for (int i = 0; str[i]; i++) {
        str[i] = tolower((unsigned char)str[i]);
    }
}

char* getNextSubstring(char* ptr, size_t length) {
    char* substring = (char*)malloc(length + 1);
    if (substring == NULL) {
        return NULL;  // Memory allocation error
    }

    strncpy(substring, ptr, length);
    substring[length] = '\0';

    return substring;
}

void parseFile(char* str, int* arr) {
    const char* keywords[] = { "add", "sub", "mul", "div", "mov", "lea", "push", "pop", "ret" };

    char* ptr = str;

    while (*ptr) {
        int keywordMatched = 0;
        for (int i = 0; i < 9; i++) {
            char* keywordCopy = (char*)malloc(strlen(keywords[i]) + 1);
            strcpy(keywordCopy, keywords[i]);
            strToLower(keywordCopy);

            char* nextSubstring = getNextSubstring(ptr, strlen(keywordCopy));
            strToLower(nextSubstring);

            if (nextSubstring != NULL && strcmp(nextSubstring, keywordCopy) == 0) {
                switch (i) {
                case 0: // "add"
                    arr[0]++;
                    break;
                case 1: // "sub"
                    arr[1]++;
                    break;
                case 2: // "mul"
                    arr[2]++;
                    break;
                case 3: // "div"
                    arr[3]++;
                    break;
                case 4: // "mov"
                    arr[4]++;
                    break;
                case 5: // "lea"
                    arr[5]++;
                    break;
                case 6: // "push"
                    arr[6]++;
                    break;
                case 7: // "pop"
                    arr[7]++;
                    break;
                case 8: // "ret"
                    arr[8]++;
                    break;
                }

                ptr += strlen(keywordCopy);
                free(keywordCopy);
                keywordMatched = 1;
            }

            //free(keywordCopy);
        }

        if (!keywordMatched) {
            ptr++;
        }
    }
}