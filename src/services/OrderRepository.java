package services;

public class OrderRepository {
    public void registrar(String cliente, String producto, int cantidad, double total) {
        System.out.println("✅ Pedido registrado: " + cliente + " - " 
            + producto + " x" + cantidad + " = S/" + String.format("%.2f", total));
    }
}
