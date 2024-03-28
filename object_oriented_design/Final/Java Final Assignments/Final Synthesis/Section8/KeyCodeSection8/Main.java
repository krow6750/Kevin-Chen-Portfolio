// Zombie interface defining the common behavior of all zombie types
interface Zombie {
    void move(); // moves the zombie
    void attack(); // attacks a target
}

// ZombieFactory interface defining the method for creating zombies
interface ZombieFactory {
    Zombie createZombie(String type); // creates a specific type of zombie
}

// Concrete ZombieFactory that creates different types of zombies
class SimpleZombieFactory implements ZombieFactory {
    public Zombie createZombie(String type) {
        Zombie zombie = null;
        if (type.equals("Clicker")) { // if the type is "Clicker" create a ClickerZombie object
            zombie = new ClickerZombie();
        } else if (type.equals("Runner")) { // if the type is "Runner" create a RunnerZombie object
            zombie = new RunnerZombie();
        } else if (type.equals("Bloater")) { // if the type is "Bloater" create a BloaterZombie object
            zombie = new BloaterZombie();
        }
        return zombie; // return the created zombie
    }
}

// Concrete Zombie implementations implementing the Zombie interface
class RunnerZombie implements Zombie {
    public void move() {
        System.out.println("Runner zombie is moving..."); // implementation for the move method for RunnerZombie
    }

    public void attack() {
        System.out.println("Runner zombie is attacking..."); // implementation for the attack method for RunnerZombie
    }
}

class ClickerZombie implements Zombie {
    public void move() {
        System.out.println("Clicker zombie is moving..."); // implementation for the move method for ClickerZombie
    }

    public void attack() {
        System.out.println("Clicker zombie is attacking..."); // implementation for the attack method for ClickerZombie
    }
}

class BloaterZombie implements Zombie {
    public void move() {
        System.out.println("Bloater zombie is moving..."); // implementation for the move method for BloaterZombie
    }

    public void attack() {
        System.out.println("Bloater zombie is attacking..."); // implementation for the attack method for BloaterZombie
    }
}

// Client code
class ZombieStore {
    private ZombieFactory factory;

    public ZombieStore(ZombieFactory factory) {
        this.factory = factory;
    }

    public Zombie orderZombie(String type) {
        Zombie zombie = factory.createZombie(type); // use the factory to create a specific type of zombie
        zombie.move(); // move the zombie
        zombie.attack(); // make the zombie attack
        return zombie; // return the zombie
    }
}

public class Main {
    public static void main(String[] args) {
        ZombieFactory factory = new SimpleZombieFactory(); // create a factory for creating zombies
        ZombieStore store = new ZombieStore(factory); // create a store that uses the factory to create zombies
        Zombie zombie = store.orderZombie("Clicker"); // create a ClickerZombie using the store
    }
}