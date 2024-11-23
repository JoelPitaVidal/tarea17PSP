public class Main {
    public static void main(String[] args) {
        Parking parking = new Parking();

        // Crear hilos
        Thread entrar = new Thread(new Entrada(parking));
        Thread salir = new Thread(new Salida(parking));

        // Iniciar hilos
        entrar.start();
        salir.start();

        // Simular cierre del parking tras un tiempo
        try {
        // Mantener abierto por 30 segundos
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Cerrar el parking
        parking.cerrarParking();
        System.out.println("Parking cerrado");
    }
}