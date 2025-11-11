package facade;

import services.*;
import adapter.*;
import java.util.List;
import java.util.ArrayList;
import strategy.*; // ✅ importante

public class PedidoFacade {
    private StockService stockService;
    private TaxService taxService;
    private PedidoRepository pedidoRepo;
    private ComprobanteService comprobanteService;
    private FacturaService facturaService;
    private ImpuestoStrategy estrategiaImpuesto;

    public PedidoFacade() {
        this.stockService = new StockService();
        this.taxService = new TaxService();
        this.pedidoRepo = new PedidoRepository();
        this.comprobanteService = new ComprobanteService();
        this.facturaService = new FacturaAdapter(new LegacyBillingSystem());
        this.estrategiaImpuesto = new IGV18Strategy(); // por defecto
    }

    // ✅ Selecciona la estrategia de impuesto
    public void seleccionarEstrategia(int opcion) {
        if (opcion == 1) {
            estrategiaImpuesto = new IGV18Strategy();
        } else {
            estrategiaImpuesto = new ExoneradoStrategy();
        }
    }

    // ✅ Muestra los productos disponibles
    public void mostrarProductos() {
        stockService.mostrarProductos();
    }

    // ✅ Procesa varios productos (un pedido completo por cliente)
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

        // ✅ Generar factura
        facturaService.generarFactura(cliente, total);

        // ✅ Mostrar comprobante con todos los productos
        comprobanteService.mostrarComprobanteMultiple(cliente, detalleProductos.toString(), subtotalTotal, impuesto, total);

        // ✅ Guardar todo el pedido junto en el archivo .txt
        pedidoRepo.guardarPedidoCompleto(cliente, productos, cantidades, total);
    }

    // ✅ Devuelve nombre del producto según opción
    public String obtenerNombreProducto(int opcionProducto) {
        return stockService.getProductoPorIndice(opcionProducto);
    }
}
