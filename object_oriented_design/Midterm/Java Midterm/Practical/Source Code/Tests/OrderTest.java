/**
 * Name: Kevin Chen
 * Assignment: Midterm synthesis
 * Date: 3/3/23
 * Notes: JUnit testing for Order class
 */

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

public class OrderTest {
    private Order order;
    private ArrayList<Item> items;

    @Before
    public void setUp() throws Exception {
        // Create a new order and add some items to it
        items = new ArrayList<Item>();
        items.add(new Item(1, "Item 1", 2, 2.0));
        items.add(new Item(2, "Item 2", 1, 11.0));
        order = new Order(123, "John Doe", items);
    }

    @Test
    public void testConstructorWithValidArguments() {
        assertEquals(123, order.getOrderNo());
        assertEquals("John Doe", order.getCustomerName());
        assertEquals(items, order.getItems());
        assertEquals(40.0, order.getTotalPrice(), 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithNegativeOrderNo() {
        new Order(-1, "John Doe", items);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithNullCustomerName() {
        new Order(123, null, items);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithEmptyCustomerName() {
        new Order(123, "", items);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithNoItems() {
        new Order(123, "John Doe", new ArrayList<Item>());
    }

    @Test
    public void testSetOrderNo() {
        order.setOrderNo(456);
        assertEquals(456, order.getOrderNo());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetOrderNoWithNegativeNumber() {
        order.setOrderNo(-1);
    }

    @Test
    public void testSetCustomerName() {
        order.setCustomerName("Jane Doe");
        assertEquals("Jane Doe", order.getCustomerName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetCustomerNameWithNull() {
        order.setCustomerName(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetCustomerNameWithEmptyString() {
        order.setCustomerName("");
    }

    @Test
    public void testSetTotalPrice() {
        order.setTotalPrice(50.0);
        assertEquals(50.0, order.getTotalPrice(), 0.001);
    }

    @Test
    public void testCalcTotalPrice() {
        assertEquals(40.0, order.calcTotalPrice(items), 0.001);
    }
}
