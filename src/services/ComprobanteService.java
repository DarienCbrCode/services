package services;

public class ComprobanteService {
    public void generarComprobante(String cliente, String producto, double subtotal, double igv, double total) {
        System.out.println("\n--- COMPROBANTE ---");
        System.out.println("Cliente: " + cliente);
        System.out.println("Producto: " + producto);
        System.out.println("Subtotal: S/" + String.format("%.2f", subtotal));
        System.out.println("IGV (18%): S/" + String.format("%.2f", igv));
        System.out.println("Total: S/" + String.format("%.2f", total));
        System.out.println("--------------------");
    }
}
