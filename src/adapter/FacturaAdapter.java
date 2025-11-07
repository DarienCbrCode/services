package adapter;

public class FacturaAdapter implements FacturaService {
    private LegacyBillingSystem legacySystem;

    public FacturaAdapter(LegacyBillingSystem legacyBillingSystem) {
        this.legacySystem = new LegacyBillingSystem();
    }

    @Override
    public void generarFactura(String cliente, double monto) {
        legacySystem.emitirFacturaAntigua(cliente, monto);
    }
}