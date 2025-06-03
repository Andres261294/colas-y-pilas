package org.example;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

// Clase para representar un elemento en la cola
class Servicio {
    private String nombre;
    private int id;

    public Servicio(String nombre, int id) {
        this.nombre = nombre;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Servicio [ID: " + id + ", Nombre: " + nombre + "]";
    }
}

public class EjemploCola {

    public static void main(String[] args) {
        // Creamos una cola de Servicios usando LinkedList
        Queue<Servicio> colaDeServicios = new LinkedList<>();
        Random random = new Random();

        System.out.println("--- INICIO: Simulación de Fila de Servicios ---");

        // 1. Añadir elementos a la cola (enqueuing)
        System.out.println("\n*** Llegada de nuevos servicios a la cola ***");
        for (int i = 1; i <= 5; i++) {
            Servicio nuevoServicio = new Servicio("Servicio " + i, 100 + i);
            colaDeServicios.offer(nuevoServicio); // 'offer' es el método preferido para añadir
            System.out.println("Se añadió: " + nuevoServicio);
            try {
                Thread.sleep(random.nextInt(300) + 100); // Pausa para simular tiempo de llegada
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("\n--- Estado actual de la cola ---");
        System.out.println("Tamaño de la cola: " + colaDeServicios.size());
        System.out.println("Elementos en cola: " + colaDeServicios); // Imprime el contenido de la cola

        // 2. Ver el primer elemento sin removerlo (peek)
        Servicio proximoServicio = colaDeServicios.peek();
        if (proximoServicio != null) {
            System.out.println("\nEl próximo servicio a atender es: " + proximoServicio);
        } else {
            System.out.println("\nLa cola está vacía, no hay próximo servicio.");
        }

        // 3. Remover elementos de la cola (dequeuing)
        System.out.println("\n*** Procesando servicios de la cola ***");
        while (!colaDeServicios.isEmpty()) {
            Servicio servicioAtendido = colaDeServicios.poll(); // 'poll' es el método preferido para remover
            System.out.println("Procesando: " + servicioAtendido);
            System.out.println("Quedan " + colaDeServicios.size() + " servicios en cola.");
            try {
                Thread.sleep(random.nextInt(500) + 200); // Pausa para simular tiempo de procesamiento
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("\n--- FIN: Todos los servicios han sido procesados. ---");
        System.out.println("La cola está vacía: " + colaDeServicios.isEmpty());
    }
}