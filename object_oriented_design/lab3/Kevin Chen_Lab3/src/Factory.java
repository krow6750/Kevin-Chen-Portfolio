/**
 * Name: Kevin Chen
 * Assignment: Lab 3: Automated House Factory
 * Date: 2/13/2023
 * Notes: Factory interface to produce blocks by taking in materials
 */

public interface Factory {
    void takeResource(Object material);
    Block produce();
    void displayInventory();
}