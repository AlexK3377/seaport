package first;

import java.util.Arrays;
import java.util.UUID;

public class Ship {

    private final String id;
    private final Container[] deck;

    public Ship(String id, int deckCapacity) {
        this.id = id;
        this.deck = new Container[deckCapacity];
    }

    public Ship(int deckCapacity) {
        this(UUID.randomUUID().toString(), deckCapacity);
    }

    public Ship() {
        this(10);
    }

    public Ship loadDeck() {
        for (int i = 0; i < deck.length; i++) {
            if (deck[i] == null) {
                deck[i] = new Container();
            }
        }
        return this;
    }

    public Container unloadDeck() {
        Container container = null;
        for (int i = 0; i < deck.length; i++) {
            if (deck[i] != null) {
                container = deck[i];
                deck[i] = null;
                break;
            }
        }
        return container;
    }

    public boolean empty() {
        int count = 0;
        for (Container container : deck) {
            if (container == null) {
                count++;
            }
        }
        return this.deck.length == count;
    }

    public String fullInfo() {
        return "first.Ship{" +
                "id='" + id + '\'' +
                ", deckCapacity=" + deck.length +
                ", deck=" + Arrays.toString(deck) +
                '}';
    }

    @Override
    public String toString() {
        return "first.Ship{" +
                "id='" + id + '\'' +
                '}';
    }
}
