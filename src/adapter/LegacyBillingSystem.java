package adapter;

public class LegacyBillingSystem {
    public void emitirFacturaAntigua(String nombreCliente, double total) {
        System.out.println("📄 [Legacy] Factura generada para " + nombreCliente + " por S/" + total);
    }
}