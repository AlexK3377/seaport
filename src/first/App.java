package first;

public class App {

    public static void main(String[] args) throws InterruptedException {
        Port port = new Port(3, 2);
        port.start();
        ShipFactory shipFactory = new ShipFactory();
        Thread.sleep(100);
        new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                port.arrival(shipFactory.loadedShip());
            }
        }).start();
    }
}
