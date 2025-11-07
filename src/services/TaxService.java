package services;

public class TaxService {
    public double calcularIGV(double subtotal) {
        return subtotal * 0.18;
    }
}
