package org.example;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

// Clase para representar un pedido en el restaurante
class Pedido {
    private int numeroPedido;
    private String descripcion;
    private long tiempoPreparacionMs;

    public Pedido(int numeroPedido, String descripcion, long tiempoPreparacionMs) {
        this.numeroPedido = numeroPedido;
        this.descripcion = descripcion;
        this.tiempoPreparacionMs = tiempoPreparacionMs;
    }

    public int getNumeroPedido() {
        return numeroPedido;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public long getTiempoPreparacionMs() {
        return tiempoPreparacionMs;
    }

    public void preparar() {
        System.out.println("Cocinando pedido #" + numeroPedido + ": " + descripcion + "...");
        try {
            Thread.sleep(tiempoPreparacionMs);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Preparación del pedido #" + numeroPedido + " interrumpida.");
        }
    }

    @Override
    public String toString() {
        return "Pedido #" + numeroPedido + " ('" + descripcion + "')";
    }
}

public class pedido {

    public static void main(String[] args) {
        // Creamos una cola para los pedidos pendientes de preparación
        Queue<Pedido> colaPedidosPendientes = new LinkedList<>();
        Random random = new Random();

        System.out.println("--- INICIO: Sistema de Gestión de Pedidos del Restaurante ---");

        // Simular la llegada de pedidos
        System.out.println("\n*** Nuevos pedidos llegan a la cocina ***");
        colaPedidosPendientes.offer(new Pedido(101, "Pizza Pepperoni Grande", random.nextInt(3000) + 2000)); // entre 2 y 5 segundos
        System.out.println("Nuevo pedido recibido: " + colaPedidosPendientes.peek());
        colaPedidosPendientes.offer(new Pedido(102, "Hamburguesa con Papas", random.nextInt(2000) + 1500)); // entre 1.5 y 3.5 segundos
        System.out.println("Nuevo pedido recibido: " + colaPedidosPendientes.peek()); // Muestra el último añadido, pero `peek` muestra el primero
        colaPedidosPendientes.offer(new Pedido(103, "Ensalada César", random.nextInt(1000) + 1000)); // entre 1 y 2 segundos
        System.out.println("Nuevo pedido recibido: " + colaPedidosPendientes.peek());
        colaPedidosPendientes.offer(new Pedido(104, "Pasta Alfredo", random.nextInt(2500) + 1800)); // entre 1.8 y 4.3 segundos
        System.out.println("Nuevo pedido recibido: " + colaPedidosPendientes.peek());

        System.out.println("\n--- Estado actual de la cola de pedidos ---");
        System.out.println("Pedidos pendientes: " + colaPedidosPendientes.size());
        System.out.println("Cola de pedidos (del más antiguo al más reciente): " + colaPedidosPendientes);

        // Ver el siguiente pedido a preparar sin removerlo
        Pedido proximoAPreparar = colaPedidosPendientes.peek();
        if (proximoAPreparar != null) {
            System.out.println("\nEl próximo pedido a preparar es: " + proximoAPreparar);
        } else {
            System.out.println("\nNo hay pedidos pendientes en la cola.");
        }

        // Simular la preparación de pedidos
        System.out.println("\n*** La cocina empieza a preparar los pedidos ***");
        while (!colaPedidosPendientes.isEmpty()) {
            Pedido pedidoEnPreparacion = colaPedidosPendientes.poll(); // Obtiene y remueve el pedido del frente
            if (pedidoEnPreparacion != null) {
                pedidoEnPreparacion.preparar(); // Simula el tiempo de preparación
                System.out.println("¡Pedido #" + pedidoEnPreparacion.getNumeroPedido() + " listo para entregar!");
                System.out.println("Pedidos restantes en cola: " + colaPedidosPendientes.size());
            }
            try {
                Thread.sleep(random.nextInt(500) + 200); // Pequeña pausa entre preparaciones
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("\n--- FIN: Todos los pedidos han sido preparados y entregados. ---");
        System.out.println("La cola de pedidos está vacía: " + colaPedidosPendientes.isEmpty());
    }
}