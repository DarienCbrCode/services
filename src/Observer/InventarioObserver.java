package observer;

public class InventarioObserver implements IObserver {
    @Override
    public void update(String mensaje) {
        System.out.println("[INVENTARIO] Actualización recibida: " + mensaje);
    }
}
