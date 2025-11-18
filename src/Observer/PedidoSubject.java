package observer;

import java.util.ArrayList;
import java.util.List;

public class PedidoSubject implements ISubject {

    private List<IObserver> observers = new ArrayList<>();

    @Override
    public void agregarObserver(IObserver o) {
        observers.add(o);
    }

    @Override
    public void eliminarObserver(IObserver o) {
        observers.remove(o);
    }

    @Override
    public void notificar(String mensaje) {
        for (IObserver o : observers) {
            o.update(mensaje);
        }
    }
}
