package threads;

import observer.PedidoSubject;

public class PedidoWorker extends Thread {

    private String cliente;
    private PedidoSubject subject;

    public PedidoWorker(String cliente, PedidoSubject subject) {
        this.cliente = cliente;
        this.subject = subject;
    }

    @Override
    public void run() {
        System.out.println("→ Hilo PedidoWorker procesando pedido del cliente: " + cliente);
        try { Thread.sleep(1200); } catch (Exception e) {}

        subject.notificar("Pedido procesado para el cliente: " + cliente);
    }
}
