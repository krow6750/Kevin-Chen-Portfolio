/**
 * Name: Kevin Chen
 * Assignment: Lab 3: Automated House Factory
 * Date: 2/13/2023
 * Notes: Class to define a resource used (stone)
 */

public class StoneBlock extends Block {

    public StoneBlock() {
        super(ResourceType.STONE);
    }

    @Override public Resource breakBlock() {
        return new Resource(1, getType());
    }
}