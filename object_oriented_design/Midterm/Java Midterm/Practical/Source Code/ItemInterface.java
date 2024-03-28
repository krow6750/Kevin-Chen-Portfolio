/**
 * Name: Kevin Chen
 * Assignment: Midterm Synthesis
 * Date: 3/3/23
 * Notes: ItemInterface class that defines a various set of methods
 */

public interface ItemInterface {
    int getId();
    void setId(int id);
    String getName();
    void setName(String name);
    int getQuantity();
    void setQuantity(int quantity);
    double getPrice();
    void setPrice(double price);
    String toString();
    double getTotal();
}
