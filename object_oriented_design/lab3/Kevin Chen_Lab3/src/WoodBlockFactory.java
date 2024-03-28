/**
 * Name: Kevin Chen
 * Assignment: Lab 3: Automated House Factory
 * Date: 2/13/2023
 * Notes: Class to take in raw resource and produce blocks (wood blocks)
 */

public class WoodBlockFactory implements Factory {

    private double binAmount;
    @Override public void takeResource(Object material) {
        if (material == null) throw new IllegalArgumentException("Resource cannot be null");
        if (!(material instanceof Resource)) throw new IllegalArgumentException("Object must be Resource");

        Resource resource = (Resource) material;
        if (resource.getType() != ResourceType.WOOD) throw new IllegalArgumentException("Resource must be Wood");

        binAmount += resource.getAmount();
        resource.addWeight(-resource.getWeight());
    }

    @Override public Block produce() {
        if (binAmount < 1) throw new IllegalStateException("Not enough Resources in the bin to make a Block");
        binAmount--;
        return new WoodBlock();
    }

    @Override public void displayInventory() {
        System.out.printf("Wood bin: %.1f\n", binAmount);
    }
}