package services;

import java.io.*;
import java.util.*;

public class PedidoRepository {
    private static final String FILE_NAME = "pedidos.txt";

    // ✅ Guarda todo el pedido junto
    public void guardarPedidoCompleto(String cliente, List<String> productos, List<Integer> cantidades, double total) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write("=== Pedido de: " + cliente + " ===");
            writer.newLine();
            for (int i = 0; i < productos.size(); i++) {
                writer.write("Producto: " + productos.get(i) + " | Cantidad: " + cantidades.get(i));
                writer.newLine();
            }
            writer.write("Total del pedido: S/" + total);
            writer.newLine();
            writer.write("----------------------------------------");
            writer.newLine();
        } catch (IOException e) {
            System.out.println("❌ Error al guardar el pedido: " + e.getMessage());
        }
    }

    // ✅ Nuevo método: muestra todos los pedidos guardados en el txt
    public void mostrarPedidosGuardados() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("⚠ No hay pedidos guardados aún.");
            return;
        }

        System.out.println("\n=== PEDIDOS GUARDADOS ===");
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                System.out.println(linea);
            }
            System.out.println("==========================\n");
        } catch (IOException e) {
            System.out.println("❌ Error al leer los pedidos: " + e.getMessage());
        }
    }
}
