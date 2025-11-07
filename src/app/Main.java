package app;

import facade.PedidoFacade;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PedidoFacade facade = new PedidoFacade();

        System.out.println("=== REGISTRO DE PEDIDO ===");

        System.out.print("Ingrese nombre del cliente: ");
        String cliente = sc.nextLine();

        System.out.print("Ingrese nombre del producto: ");
        String producto = sc.nextLine();

        System.out.print("Ingrese cantidad: ");
        int cantidad = sc.nextInt();

        facade.registrarPedido(cliente, producto, cantidad);

        sc.close();
    }
}
