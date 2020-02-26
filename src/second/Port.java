package second;

import first.Ship;

public class Port extends Thread {
private Ship[] ships;

    public Port(Ship[] ships) {
        this.ships = ships;
    }

    @Override
    public void run() {
        for (int i = 0; i < ships.length; i++) {
            try {
                Thread.sleep(1000);
                synchronized (ships) {
                    ships[i] = new Ship().loadDeck();
                    ships.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        while (true) {
            synchronized (ships) {
                ships.notifyAll();
            }
        }
    }
}
