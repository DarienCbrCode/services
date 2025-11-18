package app;

import facade.PedidoFacade;
import java.util.*;
import services.PedidoRepository; // ✅ Importar tu repositorio

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PedidoFacade facade = new PedidoFacade();//registrar pedidos...lógica,
        PedidoRepository repo = new PedidoRepository();//leer los pedidos del archivo

        int opcion;

        do {
            System.out.println("=== MENU DE PEDIDOS ===");
            System.out.println("1. Registrar nuevo pedido");
            System.out.println("2. Ver pedidos guardados");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opcion: ");
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1 -> {
                    System.out.println("\n=== REGISTRO DE PEDIDO ===");
                    System.out.print("Ingrese nombre del cliente: ");
                    String cliente = sc.nextLine();

                    List<String> productos = new ArrayList<>();
                    List<Integer> cantidades = new ArrayList<>();
                    boolean agregarMas = true;

                    while (agregarMas) {
                        facade.mostrarProductos();
                        System.out.print("Seleccione numero de producto (0 para finalizar): ");
                        int opcionProducto = sc.nextInt();
                        sc.nextLine();

                        if (opcionProducto == 0) break;

                        System.out.print("Ingrese cantidad: ");
                        int cantidad = sc.nextInt();
                        sc.nextLine();

                        productos.add(facade.obtenerNombreProducto(opcionProducto));
                        cantidades.add(cantidad);

                        System.out.print("¿Desea agregar otro producto? (s/n): ");
                        String respuesta = sc.nextLine().toLowerCase();
                        agregarMas = respuesta.equals("s");
                    }

                    System.out.println("\nSeleccione tipo de impuesto:");
                    System.out.println("1. IGV 18%");
                    System.out.println("2. Exonerado (0%)");
                    System.out.print("Opción: ");
                    int opcionImpuesto = sc.nextInt();
                    facade.seleccionarEstrategia(opcionImpuesto);

                    System.out.println("\n=== Procesando pedido ===");
                    facade.procesarPedidoMultiple(cliente, productos, cantidades);
                }

                case 2 -> {
                    repo.mostrarPedidosGuardados(); // ✅ muestra pedidos del txt
                }

                case 0 -> {
                    System.out.println("Saliendo del sistema...");
                }

                default -> {
                    System.out.println("Opcion no válida. Intente nuevamente.");
                }
            }

        } while (opcion != 0);
    }
}
