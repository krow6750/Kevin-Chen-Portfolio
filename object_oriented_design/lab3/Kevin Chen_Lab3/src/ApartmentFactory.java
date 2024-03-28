/**
 * Name: Kevin Chen
 * Assignment: Lab 3: Automated House Factory
 * Date: 2/13/2023
 * Notes: Apartment factory for extensions
 */

public class ApartmentFactory implements Factory {

    private double binStoneAmount, binWoodAmount;
    @Override public void takeResource(Object material) {
        if (material == null) throw new IllegalArgumentException("Material cannot be null");

        ResourceType type;
        double amount;
        if (material instanceof Resource) {
            Resource resource = (Resource) material;
            type = resource.getType();
            if (type != ResourceType.WOOD && type != ResourceType.STONE && type != ResourceType.HOUSE) throw new IllegalArgumentException("Resource must be either Stone or Wood or House");
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
            case HOUSE: {
                int totalBlocks = Const.HOUSE_STONE_BLOCKS + Const.HOUSE_WOOD_BLOCKS;
                binStoneAmount += amount * (totalBlocks / (double) Const.HOUSE_STONE_BLOCKS);
                binWoodAmount  += amount * (totalBlocks / (double) Const.HOUSE_WOOD_BLOCKS );
                return;
            }
        }
        throw new IllegalArgumentException("Resource must be either Stone or Wood or House");
    }

    @Override public Block produce() {
        if (    binStoneAmount < Const.HOUSE_STONE_BLOCKS * Const.APARTMENT_HOUSE_COUNT ||
                binWoodAmount  < Const.HOUSE_WOOD_BLOCKS  * Const.APARTMENT_HOUSE_COUNT) return null;
        binStoneAmount -= Const.HOUSE_STONE_BLOCKS * Const.APARTMENT_HOUSE_COUNT;
        binWoodAmount  -= Const.HOUSE_WOOD_BLOCKS * Const.APARTMENT_HOUSE_COUNT;
        return new ApartmentBlock(
                new Resource(ResourceType.HOUSE.unitWeight * Const.APARTMENT_HOUSE_COUNT, ResourceType.HOUSE)
        );
    }

    @Override public void displayInventory() {
        System.out.printf("Stone bin: %.1f\n", binStoneAmount);
        System.out.printf("Wood bin: %.1f\n", binWoodAmount);
    }

}
