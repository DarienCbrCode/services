package services;

import java.util.HashMap;
import java.util.Map;

public class StockService {
    private Map<String, Integer> stock = new HashMap<>();
    private Map<String, Double> precios = new HashMap<>();

    public StockService() {
        stock.put("laptop", 5);
        stock.put("mouse", 10);
        stock.put("teclado", 8);

        precios.put("laptop", 2500.0);
        precios.put("mouse", 50.0);
        precios.put("teclado", 120.0);
    }

    public boolean validarStock(String producto, int cantidad) throws Exception {
        producto = producto.toLowerCase();

        if (cantidad <= 0) {
            throw new Exception("La cantidad debe ser positiva.");
        }
        if (!stock.containsKey(producto)) {
            throw new Exception("El producto no existe.");
        }
        if (stock.get(producto) < cantidad) {
            throw new Exception("Stock insuficiente.");
        }

        stock.put(producto, stock.get(producto) - cantidad);
        return true;
    }

    public double obtenerPrecio(String producto) throws Exception {
        producto = producto.toLowerCase();
        if (!precios.containsKey(producto)) {
            throw new Exception("No hay precio registrado para este producto.");
        }
        return precios.get(producto);
    }
}
