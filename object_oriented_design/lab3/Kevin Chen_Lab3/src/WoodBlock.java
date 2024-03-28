/**
 * Name: Kevin Chen
 * Assignment: Lab 3: Automated House Factory
 * Date: 2/13/2023
 * Notes: Class to define a resource used (wood)
 */

public class WoodBlock extends Block {

    public WoodBlock() {
        super(ResourceType.WOOD);
    }

    // Only half is returned by breaking a wooden block
    @Override public Resource breakBlock() {
        return new Resource(0.5, getType());
    }
}