/**
 * Name: Kevin Chen
 * Assignment: Lab 3: Automated House Factory
 * Date: 2/13/2023
 * Notes: Class to generate a complex structure (house)
 */

public class HouseBlockFactory implements Factory {

    private double binStoneAmount, binWoodAmount;

    // We want our method to be able to take either Resources or Blocks
    // So we have to do quite a bit of checking
    @Override public void takeResource(Object material) {
        if (material == null) throw new IllegalArgumentException("Material cannot be null");

        ResourceType type;
        double amount;
        if (material instanceof Resource) {
            Resource resource = (Resource) material;
            type = resource.getType();
            if (type != ResourceType.WOOD && type != ResourceType.STONE) throw new IllegalArgumentException("Resource must be either Stone or Wood");
            amount = resource.getWeight();
            resource.addWeight(amount);
        } else if (material instanceof Block) {
            Block resource = (Block) material;
            type = resource.getType();
            amount = resource.getWeight();
        } else throw new IllegalArgumentException();

        switch (type) {
            case STONE: {
                binStoneAmount += amount;
                return;
            }
            case WOOD: {
                binWoodAmount  += amount;
                return;
            }
        }
        throw new IllegalArgumentException("Resource must be either Stone or Wood");
    }

    // If not enough resources are present, just don't return anything
    @Override public Block produce() {
        if (binStoneAmount < Const.HOUSE_STONE_BLOCKS || binWoodAmount < Const.HOUSE_WOOD_BLOCKS) return null;
        binStoneAmount -= Const.HOUSE_STONE_BLOCKS;
        binWoodAmount  -= Const.HOUSE_WOOD_BLOCKS;
        return new HouseBlock(
                new Resource(ResourceType.WOOD.unitWeight * Const.HOUSE_WOOD_BLOCKS, ResourceType.WOOD),
                new Resource(ResourceType.STONE.unitWeight * Const.HOUSE_STONE_BLOCKS, ResourceType.STONE)
        );
    }

    @Override public void displayInventory() {
        System.out.printf("Stone bin: %.1f\n", binStoneAmount);
        System.out.printf("Wood bin: %.1f\n", binWoodAmount);
    }
}