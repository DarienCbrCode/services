package threads;

import observer.PedidoSubject;

public class NotificacionWorker extends Thread {

    private PedidoSubject subject;

    public NotificacionWorker(PedidoSubject subject) {
        this.subject = subject;
    }

    @Override
    public void run() {
        System.out.println("→ Hilo NotificacionWorker enviando notificaciones...");
        try { Thread.sleep(800); } catch (Exception e) {}

        subject.notificar("Se enviaron las notificaciones del pedido.");
    }
}
