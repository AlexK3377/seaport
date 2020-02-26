package first;

public class ShipFactory {

    public Ship ship() {
        return new Ship();
    }

    public Ship ship(int deckCapacity) {
        return new Ship(deckCapacity);
    }

    public Ship loadedShip() {
        return ship().loadDeck();
    }

    public Ship loadedShip(int deckCapacity) {
        return ship(deckCapacity).loadDeck();
    }
}
