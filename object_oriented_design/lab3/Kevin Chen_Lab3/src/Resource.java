/**
 * Name: Kevin Chen
 * Assignment: Lab 3: Automated House Factory
 * Date: 2/13/2023
 * Notes: Class that represents raw material of some type
 */
public class Resource {
    private final ResourceType type;
    private double weight;

    public Resource(double amount, ResourceType type) {
        if (type == null) throw new IllegalArgumentException("Type cannot be null");
        this.type = type;
        addAmount(amount);
    }

    public double getAmount() {
        return weight / type.unitWeight;
    }
    public double getWeight() { return weight; }

    public ResourceType getType() {
        return type;
    }

    public void addWeight(double amount) {
        if (amount + weight < 0) throw new IllegalArgumentException("Cannot remove more mass than there is already");
        weight += amount;
    }
    public void addAmount(double count) {
        addWeight(count * type.unitWeight);
    }
}