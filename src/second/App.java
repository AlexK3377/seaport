package second;

import first.Ship;

public class App {

    public static void main(String[] args) {
        Ship[] ships = new Ship[10];
        Port port = new Port(ships);
        Dock dock1 = new Dock(ships);
        Dock dock2 = new Dock(ships);

        dock1.start();
        dock2.start();
        port.start();
    }
}
