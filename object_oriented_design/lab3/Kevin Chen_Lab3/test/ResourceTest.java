/**
 * Name: Kevin Chen
 * Assignment: Lab 3: Automated House Factory
 * Date: 2/13/2023
 * Notes: Resource test class
 */

import org.junit.Test;

import static org.junit.Assert.*;

public class ResourceTest {
    @Test public void factoryExceptionTest() {
        StoneBlockFactory stoneMiner = new StoneBlockFactory();
        assertThrows(IllegalArgumentException.class, () -> stoneMiner.takeResource("Hello"));
        assertThrows(IllegalArgumentException.class, () -> stoneMiner.takeResource(new WoodBlock()));
        Resource stones = new Resource(1, ResourceType.STONE);
        stoneMiner.takeResource(stones);
        assertEquals(stones.getWeight(), 0, 0);
        assertTrue(stoneMiner.produce() instanceof StoneBlock);
        assertThrows(IllegalStateException.class, () -> stoneMiner.produce());
    }

    @Test public void resourceExceptionTest() {
        assertThrows(IllegalArgumentException.class, () -> new Resource(-1, ResourceType.WOOD));
        assertThrows(IllegalArgumentException.class, () -> new Resource(0, null));
        Resource wood = new Resource(0, ResourceType.WOOD);
        assertThrows(IllegalArgumentException.class, () -> wood.addWeight(-5));
        assertEquals(wood.getType(), ResourceType.WOOD);
        wood.addAmount(1);
        assertEquals(wood.getAmount(), 1, 0);
    }
}
