/**
 * Name: Kevin Chen
 * Assignment: Lab 3: Automated House Factory
 * Date: 2/13/2023
 * Notes: Class to define HouseBlock
 */

public class HouseBlock extends Block {

    public HouseBlock(Resource wood, Resource stone) {
        super(ResourceType.HOUSE); // Must be the first statement, no way around it
        if ( wood.getType() != ResourceType.WOOD ) throw new IllegalArgumentException("The first argument must be Wood");
        if (stone.getType() != ResourceType.STONE) throw new IllegalArgumentException("The second argument must be Stone");
        if ( wood.getAmount() < Const.HOUSE_WOOD_BLOCKS  ) throw new IllegalArgumentException("Not enough Wood");
        if (stone.getAmount() < Const.HOUSE_STONE_BLOCKS ) throw new IllegalArgumentException("Not enough Stone");

         wood.addAmount(-Const.HOUSE_WOOD_BLOCKS );
        stone.addAmount(-Const.HOUSE_STONE_BLOCKS);
    }

    @Override public Resource breakBlock() {
        return new Resource(Const.HOUSE_STONE_BLOCKS, ResourceType.STONE);
    }
}