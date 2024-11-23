class Salida implements Runnable {
    private final Parking parking;

    public Salida(Parking parking) {
        this.parking = parking;
    }

    @Override
    public void run() {
        parking.salidaCoche();
    }
}