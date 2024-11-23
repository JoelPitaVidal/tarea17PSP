import java.util.ArrayList;
import java.util.Scanner;

public class Parking {

    private boolean abierto = true;
    private final Scanner sc = new Scanner(System.in);
    private final ArrayList<Integer> coches = new ArrayList<>();
    private final int capacidadMaxima = 5;

    // Metodo para agregar un coche al parking
    public synchronized void entradaCoche() {
        while (abierto) {
            try {
    // Esperar si no hay espacio
                while (!controlEntrada()) {
                    System.out.println("Parking lleno, esperando espacio...");
                    wait();
                }
    //Insertamos el número de coche
                System.out.println("Inserte el número de coche");
                int numeroCoche = sc.nextInt();
    //Añadimos el coche
                coches.add(numeroCoche);
                System.out.println("Coche ingresado con éxito. Coches en el parking: " + coches);
    // Notificar a otros hilos
                notifyAll();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    // Metodo para retirar un coche del parking
    public synchronized void salidaCoche() {
        while (abierto) {
            try {
    // Esperar si no hay coches
                while (coches.isEmpty()) {
                    System.out.println("Parking vacío, esperando coches...");
                    wait();
                }
    //Insertamos el número del coche a retirar
                System.out.println("Ingrese el número del coche que desea retirar");
                int ncoche = sc.nextInt();
    //Comprobamos si el coche insertado se encuentra dentro de la lista
                if (coches.contains(ncoche)) {
    //Si el coche se encuentra en la lista se elimina de esta
                    coches.remove(Integer.valueOf(ncoche));
                    System.out.println("Coche retirado con éxito. Coches en el parking: " + coches);
                } else {
    //Si no está dentro lanzamos un mensaje de error
                    System.out.println("Error: El número de coche no se encuentra en el parking");
                }
    // Notificar a otros hilos
                notifyAll();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    // Metodo para verificar si hay espacio en el parking
    public synchronized boolean controlEntrada() {
        return coches.size() < capacidadMaxima;
    }

    // Metodo para cerrar el parking
    public synchronized void cerrarParking() {
        abierto = false;
        notifyAll();
    }
}