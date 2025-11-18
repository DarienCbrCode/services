package observer;

public class LogObserver implements IObserver {
    @Override
    public void update(String mensaje) {
        System.out.println("[LOG OBSERVER] " + mensaje);
    }
}
