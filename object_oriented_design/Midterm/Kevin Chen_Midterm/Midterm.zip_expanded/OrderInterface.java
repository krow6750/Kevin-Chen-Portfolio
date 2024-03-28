/**
 * Name: Kevin Chen
 * Assignment: Midterm synthesis
 * Date: 3/3/23
 * Notes: OrderInterface class gets and sets order number, customer name, list of items, and total price of the order. Also calculates the total price of the order based on a list of Item objects
 */

import java.util.ArrayList;

public interface OrderInterface {
    public int getOrderNo();
    public void setOrderNo(int orderNo);
    public String getCustomerName();
    public void setCustomerName(String customerName);
    public ArrayList<Item> getItems();
    public Double getTotalPrice();
    public void setTotalPrice(Double totalPrice);
    public Double calcTotalPrice(ArrayList<Item> list);
}
