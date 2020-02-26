package first;

import java.util.UUID;

public class Dock extends Thread {

    private final String id;
    private final int unloadDelay;
    private Ship ship;

    public Dock(String id, int unloadDelay) {
        this.id = id;
        this.unloadDelay = unloadDelay;
    }

    public Dock(int unloadDelay) {
        this(UUID.randomUUID().toString(), unloadDelay);
    }

    public Dock() {
        this(500);
    }

    public boolean arrival(Ship ship) {
        boolean arrival = false;
        synchronized (this) {
            while (this.ship == null) {
                System.out.println("Arrival " + ship + " to dock " + id + " thread= " + this.getId());
                this.ship = ship;
                arrival = true;
                notify();
            }
        }
        return arrival;
    }

    private void unload() {
        synchronized (this) {
            while (this.ship == null) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        while (!this.ship.empty()) {
            Container container = ship.unloadDeck();
            System.out.println("Unload " + container + " from " + ship + " in dock " + id + " thread= " + this.getId());
            try {
                Thread.sleep(unloadDelay);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        departure();

    }

    private Ship departure() {
        Ship departureShip = this.ship;
        this.ship = null;
        System.out.println("Departure " + departureShip + " thread= " + this.getId());
        synchronized (this) {
            this.notify();
        }
        return departureShip;
    }

    @Override
    public void run() {
        System.out.println("first.Dock starting "+ " thread= " + this.getId());
        while (true) {
            unload();
        }
    }
}
