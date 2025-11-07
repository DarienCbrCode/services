package facade;

import services.*;
import adapter.*;

public class PedidoFacade {
    private StockService stockService;
    private TaxService taxService;
    private OrderRepository orderRepo;
    private ComprobanteService comprobanteService;
    private FacturaService facturaService;

    public PedidoFacade() {
        this.stockService = new StockService();
        this.taxService = new TaxService();
        this.orderRepo = new OrderRepository();
        this.comprobanteService = new ComprobanteService();
        this.facturaService = new FacturaAdapter(new LegacyBillingSystem());
    }

    public void registrarPedido(String cliente, String producto, int cantidad) {
        try {
            System.out.println("\n=== Procesando pedido ===");

            // 1️⃣ Validación de stock
            if (stockService.validarStock(producto, cantidad)) {
                System.out.println("✅ Stock disponible para el producto: " + producto);
            }

            // 2️⃣ Obtener precio del producto
            double precioUnitario = stockService.obtenerPrecio(producto);
            System.out.println("💲 Precio unitario: S/" + String.format("%.2f", precioUnitario));

            // 3️⃣ Calcular subtotal, IGV y total
            double subtotal = cantidad * precioUnitario;
            double igv = taxService.calcularIGV(subtotal);
            double total = subtotal + igv;

            System.out.println("🧮 Subtotal: S/" + String.format("%.2f", subtotal));
            System.out.println("🧾 IGV (18%): S/" + String.format("%.2f", igv));
            System.out.println("💰 Total a pagar: S/" + String.format("%.2f", total));

            // 4️⃣ Registrar pedido
            orderRepo.registrar(cliente, producto, cantidad, total);

            // 5️⃣ Generar factura (usando el adaptador)
            facturaService.generarFactura(cliente, total);

            // 6️⃣ Generar comprobante final
            comprobanteService.generarComprobante(cliente, producto, subtotal, igv, total);

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
}
