/**
 * Name: Kevin Chen
 * Assignment: Lab 3: Automated House Factory
 * Date: 2/13/2023
 * Notes: Apartment class for extensions
 */

public class ApartmentBlock extends Block {

    public ApartmentBlock(Resource houses) {
        super(ResourceType.APARTMENT);
        if (houses.getType() != ResourceType.HOUSE  ) throw new IllegalArgumentException("The first argument must be House");
        if (houses.getAmount() < Const.APARTMENT_HOUSE_COUNT) throw new IllegalArgumentException("Not enough Houses");

        houses.addAmount(-Const.APARTMENT_HOUSE_COUNT);
    }

    @Override public Resource breakBlock() {
        return new Resource(Const.HOUSE_STONE_BLOCKS * Const.APARTMENT_HOUSE_COUNT, ResourceType.STONE);
    }
}