package services;

public class ComprobanteService {

    // ✅ Comprobante para pedidos individuales
    public void mostrarComprobante(String cliente, String producto, double subtotal, double impuesto, double total) {
        System.out.println("\n--- COMPROBANTE ---");
        System.out.println("Cliente: " + cliente);
        System.out.println("Producto: " + producto);
        System.out.println("Subtotal: S/" + subtotal);
        System.out.println("Impuesto: S/" + impuesto);
        System.out.println("Total: S/" + total);
        System.out.println("--------------------");
    }

    // ✅ Nuevo comprobante para pedidos múltiples
    public void mostrarComprobanteMultiple(String cliente, String detalle, double subtotal, double impuesto, double total) {
        System.out.println("\n--- COMPROBANTE ---");
        System.out.println("Cliente: " + cliente);
        System.out.println(detalle); // muestra lista de productos con cantidades y subtotales
        System.out.println("Subtotal total: S/" + subtotal);
        System.out.println("Impuesto: S/" + impuesto);
        System.out.println("Total: S/" + total);
        System.out.println("--------------------");
    }
}
