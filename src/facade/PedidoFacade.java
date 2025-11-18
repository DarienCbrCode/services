package facade;

import services.*;
import adapter.*;
import strategy.*;
import threads.*;
import observer.*;

import java.util.List;

public class PedidoFacade {

    private StockService stockService;
    private PedidoRepository pedidoRepo;
    private ComprobanteService comprobanteService;
    private FacturaService facturaService;
    private ImpuestoStrategy estrategiaImpuesto;

    // NUEVO → Subject central
    private PedidoSubject subject;

    public PedidoFacade() {
        this.stockService = new StockService();
        this.pedidoRepo = new PedidoRepository();
        this.comprobanteService = new ComprobanteService();
        this.facturaService = new FacturaAdapter(new LegacyBillingSystem());
        this.estrategiaImpuesto = new IGV18Strategy();

        // Inicializar Observer
        subject = new PedidoSubject();
        subject.agregarObserver(new LogObserver());
        subject.agregarObserver(new InventarioObserver());
    }

    public void seleccionarEstrategia(int opcion) {
        if (opcion == 1) estrategiaImpuesto = new IGV18Strategy();
        else estrategiaImpuesto = new ExoneradoStrategy();
    }

    public void mostrarProductos() {
        stockService.mostrarProductos();
    }

    public String obtenerNombreProducto(int opcionProducto) {
        return stockService.getProductoPorIndice(opcionProducto);
    }

    // ============================
    // PROCESAR PEDIDO COMPLETO
    // ============================
    public void procesarPedidoMultiple(String cliente, List<String> productos, List<Integer> cantidades) {

        double subtotalTotal = 0;
        StringBuilder detalleProductos = new StringBuilder();

        for (int i = 0; i < productos.size(); i++) {
            String producto = productos.get(i);
            int cantidad = cantidades.get(i);

            if (!stockService.verificarStock(producto, cantidad)) {
                System.out.println("❌ No hay stock suficiente para: " + producto);
                continue;
            }

            double precioUnitario = stockService.getPrecio(producto);
            double subtotal = precioUnitario * cantidad;
            subtotalTotal += subtotal;

            stockService.reducirStock(producto, cantidad);

            detalleProductos.append("Producto: ").append(producto)
                    .append(" - Cantidad: ").append(cantidad)
                    .append(" - Subtotal: S/").append(subtotal)
                    .append("\n");
        }

        double impuesto = estrategiaImpuesto.calcular(subtotalTotal);
        double total = subtotalTotal + impuesto;

        // GUARDAR PEDIDO
        pedidoRepo.guardarPedidoCompleto(cliente, productos, cantidades, total);

        // HILOS ACTIVOS
        Thread t1 = new PedidoWorker(cliente, subject);
        Thread t2 = new FacturaWorker(cliente, total, subject);
        Thread t3 = new NotificacionWorker(subject);

        t1.start();
        t2.start();
        t3.start();

        // IMPRIMIR COMPROBANTE
        comprobanteService.mostrarComprobanteMultiple(
                cliente, detalleProductos.toString(), subtotalTotal, impuesto, total
        );
    }
}
