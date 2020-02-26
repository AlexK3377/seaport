package second;

import first.Ship;

public class Dock extends Thread {

    private Ship[] ships;

    public Dock(Ship[] ships) {
        this.ships = ships;
    }

    @Override
    public void run() {
        while (true) {
            Ship ship = null;
            synchronized (ships) {
                while (ship == null) {
                    try {
                        ships.wait();
                        for (int i = 0; i < ships.length; i++) {
                            if (ships[i] != null) {
                                ship = ships[i];
                                ships[i] = null;
                                break;
                            }
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println(ship + " in dock " + Thread.currentThread().getId());
            while (!ship.empty()) {
                ship.unloadDeck();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(ship.fullInfo() + " unloaded " + Thread.currentThread().getId());
        }
    }
}
