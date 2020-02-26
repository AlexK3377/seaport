package first;

import java.util.UUID;

public class Container {

    private final String id;

    public Container() {
        this.id = UUID.randomUUID().toString();
    }

    @Override
    public String toString() {
        return "first.Container{" +
                "id='" + id + '\'' +
                '}';
    }
}
