package observer;

public class ClienteObserver implements IObserver {
    private String nombre;

    public ClienteObserver(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void update(String mensaje) {
        System.out.println("[CLIENTE " + nombre + "] Notificación: " + mensaje);
    }
}
