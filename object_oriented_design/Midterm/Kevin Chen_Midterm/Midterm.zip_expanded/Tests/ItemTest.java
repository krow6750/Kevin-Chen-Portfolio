/**
 * Name: Kevin Chen
 * Assignment: Midterm synthesis
 * Date: 3/3/23
 * Notes: JUnit testing for Item class
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Before;
import org.junit.Test;

public class ItemTest {

    private Item item;

    @Before
    public void setup() {
        item = new Item(1, "Test Item", 10, 1.99);
    }

    @Test
    public void testConstructorWithInvalidId() {
        assertThrows(IllegalArgumentException.class, () -> new Item(-1, "Test Item", 10, 1.99));
    }

    @Test
    public void testConstructorWithInvalidName() {
        assertThrows(IllegalArgumentException.class, () -> new Item(1, null, 10, 1.99));
        assertThrows(IllegalArgumentException.class, () -> new Item(1, "", 10, 1.99));
    }

    @Test
    public void testConstructorWithInvalidQuantity() {
        assertThrows(IllegalArgumentException.class, () -> new Item(1, "Test Item", -10, 1.99));
    }

    @Test
    public void testConstructorWithInvalidPrice() {
        assertThrows(IllegalArgumentException.class, () -> new Item(1, "Test Item", 10, -1.99));
    }

    @Test
    public void testGettersAndSetters() {
        assertEquals(1, item.getId());
        assertEquals("Test Item", item.getName());
        assertEquals(10, item.getQuantity());
        assertEquals(1.99, item.getPrice(), 0.001);

        item.setId(2);
        assertEquals(2, item.getId());

        item.setName("New Item Name");
        assertEquals("New Item Name", item.getName());

        item.setQuantity(20);
        assertEquals(20, item.getQuantity());

        item.setPrice(2.99);
        assertEquals(2.99, item.getPrice(), 0.001);
    }

    @Test
    public void testToString() {
        String expectedString = "Test Item (10) - $1.99";
        assertEquals(expectedString, item.toString());
    }

    @Test
    public void testGetTotal() {
        assertEquals(19.9, item.getTotal(), 0.001);
    }
}
