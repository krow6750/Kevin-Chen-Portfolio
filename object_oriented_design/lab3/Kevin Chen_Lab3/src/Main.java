/**
 * Name: Kevin Chen
 * Assignment: Lab 3: Automated House Factory
 * Date: 2/13/2023
 * Notes: Main class
 */

import java.lang.Math;

public class Main {
    // Main driver class
    // Modified to not loop constantly
    // It aims to generate three houses
    // Then make an apartment out of them
    // By using the factories construct
    public static void main(String[] args) {
        Factory stoneBlockProducer = new StoneBlockFactory();
        Factory woodBlockProducer = new WoodBlockFactory();
        Factory houseBlockProducer = new HouseBlockFactory();

        Resource houses = new Resource(0, ResourceType.HOUSE);

        int housesBuilt = 0;
        while (true) {
            Resource resource = mineResource();

            switch (resource.getType()) {
                case STONE: stoneBlockProducer.takeResource(resource); break;
                case WOOD: woodBlockProducer.takeResource(resource); break;
            }

            System.out.println("Stone Producer");
            stoneBlockProducer.displayInventory();
            System.out.println("Wood Producer");
            woodBlockProducer.displayInventory();
            System.out.println("House Producer");
            houseBlockProducer.displayInventory();
            System.out.println();

            Block h1 = houseBlockProducer.produce();
            if (h1 != null) {
                System.out.println("House Built");
                housesBuilt++;
                houses.addAmount(1);
                if (Const.APARTMENT_HOUSE_COUNT <= housesBuilt) break;
            }

            int producedStone = 0;
            try {
                while (true) {
                    Block stone = stoneBlockProducer.produce();
                    producedStone++;
                    houseBlockProducer.takeResource(stone);
                }
            } catch (Exception ignored) {

            }
            if (0 < producedStone) System.out.println("Couldn't produce stone yet");
            else System.out.println("Produced " + producedStone + " blocks of Stone");;

            int producedWood = 0;
            try {
                while (true) {
                    Block wood = woodBlockProducer.produce();
                    producedWood++;
                    houseBlockProducer.takeResource(wood);
                }
            } catch (Exception ignored) { }
            if (0 < producedWood) System.out.println("Produced " + producedWood + " blocks of Wood");
            else System.out.println("Couldn't produce wood yet");;


            System.out.println();
        }

        System.out.println(housesBuilt + " houses built, Building an apartment...");
        ApartmentFactory factory = new ApartmentFactory();
        factory.takeResource(houses);
        Block apartment = factory.produce();
        System.out.println("We got an apartment: " + apartment);
    }

    public static Resource mineResource() {
        switch ((int) Math.sqrt(Math.random() * 10)) {
            case 0: return new Resource(10, ResourceType.WOOD);
            case 1: return new WoodBlock ().breakBlock();
            case 2: return new StoneBlock().breakBlock();
            case 3: return new Resource(20, ResourceType.STONE);
        }
        throw new UnsupportedOperationException();
    }
}


