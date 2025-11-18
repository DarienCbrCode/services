package services;

import java.util.HashMap;
import java.util.Map;

public class StockService {
    private Map<String, Double> precios;
    private Map<String, Integer> stock;

    public StockService() {
        precios = new HashMap<>();
        stock = new HashMap<>();

        // Productos disponibles
        precios.put("Laptop", 2500.00);
        precios.put("Mouse", 80.00);
        precios.put("Teclado", 150.00);

        stock.put("Laptop", 10);
        stock.put("Mouse", 30);
        stock.put("Teclado", 20);
    }

    public void mostrarProductos() {
        System.out.println("Productos disponibles:");
        int i = 1;
        for (String producto : precios.keySet()) {
            System.out.println(i + ". " + producto + " - S/" + precios.get(producto));
            i++;
        }
    }

    public String getProductoPorIndice(int index) {//producto según su posición en la lista
        return precios.keySet().toArray(new String[0])[index - 1];
    }

    public boolean verificarStock(String producto, int cantidad) {
        return stock.containsKey(producto) && stock.get(producto) >= cantidad;
    }

    public double getPrecio(String producto) {
        return precios.get(producto);
    }

    public void reducirStock(String producto, int cantidad) {
        if (stock.containsKey(producto)) {
            stock.put(producto, stock.get(producto) - cantidad);
        }
    }
}
