#include<iostream>
using namespace std;

int countCriticalEvents(int array[], int size, double t) {
    int criticalEvents = 0;
    for (int i = 0; i < size; ++i) {
        for (int j = i + 1; j < size; ++j) {
            if (array[i] > t * array[j]) {
                criticalEvents++;
            }
        }
    }
    return criticalEvents;
}

int main() {
    // Example array and threshold value
    int inputArray[] = { 3, 5, 2, 8, 6 };
    int size = sizeof(inputArray) / sizeof(inputArray[0]);
    double thresholdValue = 0.5;

    cout << "\nArray: ";
    for (int i = 0; i < size; i++)
        cout << inputArray[i] << " ";
    
    cout << "\nThreshold Value: " << thresholdValue;

    // Count and output the number of critical events
    int result = countCriticalEvents(inputArray, size, thresholdValue);
    std::cout << "\nNumber of critical events: " << result << std::endl;
    
    return 0;
}
