package first;

public class Port extends Thread {

    private final Dock[] docks;
    private final Ship[] ships;

    public Port(int shipsCapacity, int docksNumber) {
        this.ships = new Ship[shipsCapacity];
        this.docks = new Dock[docksNumber];
        for (int i = 0; i < docksNumber; i++) {
            docks[i] = new Dock();
        }
    }

    public Port() {
        this(3, 2);
    }

    public void arrival(Ship ship) {
        synchronized (this) {
            while (full()) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
            for (int i = 0; i < ships.length; i++) {
                if (ships[i] == null) {
                    System.out.println("!!!!! Arrival " + ship + " to port " + " thread= " + this.getId());
                    ships[i] = ship;
                    notify();
                    break;
                }
            }
        }
    }

    public boolean empty() {
        boolean empty = true;
        for (Ship ship : ships) {
            if (ship != null) {
                empty = false;
                break;
            }
        }
        return empty;
    }

    public boolean full() {
        int count = 0;
        for (Ship ship : ships) {
            if (ship != null) {
                count++;
            }
        }
        return ships.length == count;
    }

    private void dispatch() {
        synchronized (this) {
            while (empty()) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        for (int i = 0; i < ships.length; i++) {
            if (ships[i] != null && toDock(ships[i])) {
                ships[i] = null;
                break;
            }
        }
    }

    private boolean toDock(Ship ship) {
        boolean inDock = false;
        for (Dock dock : docks) {
            if (dock.arrival(ship)) {
                inDock = true;
                break;
            }
        }
        return inDock;
    }

    @Override
    public void run() {
        System.out.println("first.Port starting " + " thread= " + this.getId());
        for (Dock dock : docks) {
            dock.start();
        }
        while (true) {
            dispatch();
        }
    }
}
