package threads;

import observer.PedidoSubject;

public class FacturaWorker extends Thread {

    private String cliente;
    private double total;
    private PedidoSubject subject;

    public FacturaWorker(String cliente, double total, PedidoSubject subject) {
        this.cliente = cliente;
        this.total = total;
        this.subject = subject;
    }

    @Override
    public void run() {
        System.out.println("→ Hilo FacturaWorker generando factura...");
        try { Thread.sleep(1000); } catch (Exception e) {}

        subject.notificar("Factura generada para: " + cliente + " por S/" + total);
    }
}
