package observer;

public interface ISubject {
    void agregarObserver(IObserver o);
    void eliminarObserver(IObserver o);
    void notificar(String mensaje);
}
