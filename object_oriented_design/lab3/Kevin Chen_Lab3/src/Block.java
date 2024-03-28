/**
 * Name: Kevin Chen
 * Assignment: Lab 3: Automated House Factory
 * Date: 2/13/2023
 * Notes: Abstract block class that cannot be instantiated
 */

//bound to a Resource type
public abstract class Block {
    protected ResourceType type;

    public Block(ResourceType type) {
        this.type = type;
    }

    public ResourceType getType() {
        return type;
    }

    public double getWeight() {
        return type.unitWeight;
    }

    public abstract Resource breakBlock();

    @Override public String toString() {
        return "Block of " + type;
    }
}