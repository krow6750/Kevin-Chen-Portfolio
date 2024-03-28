/**
 * Name: Kevin Chen
 * Assignment: Lab 3: Automated House Factory
 * Date: 2/13/2023
 * Notes: ResourceType class to define allowable types for application
 */

// Main enum for all the possible Resources
// In Java, we can bind some values to said enums like demonstrated
// It allows us to edit constants in `Const.java` freely without having to refactor everything everywhere
// As the enum variants fetch and compute their weight automatically, accessing the weight from them is optimal
public enum ResourceType {
    STONE(Const.STONE_BLOCK_WEIGHT), WOOD(Const.WOOD_BLOCK_WEIGHT),
    HOUSE(WOOD.unitWeight * Const.HOUSE_WOOD_BLOCKS + STONE.unitWeight * Const.HOUSE_STONE_BLOCKS),
    APARTMENT(HOUSE.unitWeight * Const.APARTMENT_HOUSE_COUNT);

    public final double unitWeight;

    ResourceType(double unitWeight) {
        this.unitWeight = unitWeight;
    }

    @Override
    public String toString() {
        switch (this) {
            case STONE: return "Stone";
            case WOOD : return "Wood";
            case HOUSE: return "House";
            case APARTMENT: return "Apartment";
        }
        throw new UnsupportedOperationException();
    }
}